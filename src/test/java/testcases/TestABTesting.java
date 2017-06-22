package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.xerces.impl.dv.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestABTesting {

    public void check(Page fetchedPage) throws IOException, JSONException {

        // with a/b test cookie
        String cookieValue = fetchedPage.getCookieValue("ab_tests");
        String decodedCookieValue = URLDecoder.decode(cookieValue, "UTF-8");
        GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(Base64.decode(decodedCookieValue)));
        String decodedValue = IOUtils.toString(gzipInputStream);
        JSONObject json = new JSONObject(decodedValue);
        assertThat(json.has("variants"), is(true));

        Elements isRwdPage = fetchedPage.getDocument().select(".page-wrapper");

        if (fetchedPage.getUrl().contains("?param.testing=1")) {
            assertThat(isRwdPage.size(), is(0));
        }
        if (fetchedPage.getUrl().contains("?param.testing=0")) {
            assertThat(isRwdPage.size(), is(1));
        }
    }
}
