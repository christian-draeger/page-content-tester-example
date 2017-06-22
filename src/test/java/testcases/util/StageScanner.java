package testcases.util;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.with;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StageScanner {

    private final Properties properties = new Properties();
    public String getStage() {

        String urlPrefix = getUrlPrefix();
        System.out.println("urlPrefix: " + urlPrefix);

        String statusHealthUrl = "https://" + urlPrefix + "/o_n_l_y__i_n_t_e_r_n_a_l/status/health";

        RequestSpecification requestSpec = new RequestSpecBuilder().setConfig(
                RestAssured.config()
                        .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation()))
                        .setProxy("ipc-dnsproxy-04.srv00.pro03.eu.idealo.com", 3128)
                .build();
        String body = given(requestSpec)
                .get(statusHealthUrl)
                .asString();

        String stage = with(body).getString("additionalInfo.stage");
        String deployer = with(body).getString("additionalInfo.deploymentInformation.deployer");
        String deployDate = with(body).getString("additionalInfo.deploymentInformation.deployedAt");
        String serverStatus = with(body).getString("status");
        log.info("server {} identifies itself in stage {} and is in status {}", urlPrefix, stage, serverStatus);
        log.info("last deployment of server {} has been on {} by {}", urlPrefix, deployDate, deployer);

        return stage;
    }

    private String getUrlPrefix() {

        String urlPrefix = "";

        final InputStream inputStream = getClass().getResourceAsStream("/pagecontent.properties");

        try {

            properties.load(inputStream);
            urlPrefix =  System.getProperty("urlPrefix", properties.getProperty("urlPrefix"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return  urlPrefix.isEmpty() ? "www.idealo.de" : urlPrefix + ".www.idealo.de";
    }
}
