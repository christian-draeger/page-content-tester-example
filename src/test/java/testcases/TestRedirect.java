package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import com.google.common.collect.ImmutableSet;

import lombok.SneakyThrows;
import pagecontenttester.fetcher.Page;
import testcases.util.StageScanner;

/**
 * @author david.graesser
 * @since 28.03.17
 */
public class TestRedirect {
    private static final Set<String> EXCLUDE_QUERY_PARAMETER_NAME = ImmutableSet.of("customid", "ascsubtag");

    public void check(Page fetchedPage, Page fetchedPageLive) {
        assumeTrue(new StageScanner().getStage().equals("PRODUCTION") || new StageScanner().getStage().equals("STAGING"));

        if (fetchedPage.getStatusCode() == 301 || fetchedPage.getStatusCode() == 303) {
            String fetchedPageLocation = fetchedPage.getLocation();
            String fetchedPageLiveLocation = fetchedPageLive.getLocation();
            assertThat(toURI(fetchedPageLocation), is(equalTo(toURI(fetchedPageLiveLocation))));
        } else {
            assertTrue("Fetched page did not return status 301 but " + fetchedPage.getStatusCode(), false);
        }
    }

    @SneakyThrows
    private URI toURI(final String urlString) {
        URIBuilder builder = new URIBuilder(urlString);
        List<NameValuePair> queryParams = builder.getQueryParams();
        builder.clearParameters();
        queryParams
                .stream()
                .filter(queryParam -> !EXCLUDE_QUERY_PARAMETER_NAME.contains(queryParam.getName()))
                .forEach(queryParam -> {
                    builder.addParameter(queryParam.getName(), queryParam.getValue());
                });
        return builder.build();
    }
}
