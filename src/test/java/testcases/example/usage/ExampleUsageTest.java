package testcases.example.usage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.jsoup.Connection.Method.POST;
import static pagecontenttester.fetcher.FetchedPage.DeviceType.MOBILE;
import static pagecontenttester.fetcher.FetchedPage.call;
import static pagecontenttester.fetcher.FetchedPage.fetchPage;

import java.util.Collections;

import org.json.JSONObject;
import org.junit.Test;

import pagecontenttester.annotations.Fetch;
import pagecontenttester.fetcher.FetchedPage;
import pagecontenttester.runner.PageContentTester;

public class ExampleUsageTest extends PageContentTester {

    private static final String GITHUB_URL = "https://github.com/christian-draeger";
    private static final String GOOGLE_URL = "http://www.google.de";
    private static final String VALID_SELECTOR = "h1";

    @Test
    @Fetch(url=GITHUB_URL)
    public void fetcher_should_return_count_of_certain_element() {
        assertThat(page.get().getElementCount(VALID_SELECTOR), is(1));
    }

    @Test
    @Fetch(url=GITHUB_URL)
    @Fetch(url=GOOGLE_URL)
    public void fetch_multiple_pages_via_annotation_and_get_pages_by_url_snippet() {

        FetchedPage github = page.get("github");

        assertThat(github.isElementPresent("h1"), is(true));
        assertThat(github.getUrl(), equalTo(GITHUB_URL));
        assertThat(github.getElement("h1").text(), containsString("christian-draeger"));
        assertThat(github.isElementPresent("img.avatar"), is(true));
        assertThat(github.getElement("img.avatar").attr("src"), containsString("githubusercontent.com"));
        assertThat(github.getCookieValue("logged_in"), equalTo("no"));
        assertThat(github.getCookies(), hasEntry("logged_in", "no"));

        FetchedPage google = page.get("google");
        assertThat(google.isElementPresent("#footer"), is(true));
    }

    @Test
    @Fetch(url=GITHUB_URL)
    @Fetch(url=GOOGLE_URL)
    public void fetch_multiple_pages_via_annotation_and_get_pages_by_index() {
        FetchedPage github = page.get(0);
        FetchedPage google = page.get(1);

        assertThat(github.isElementPresent("h1"), is(true));

        assertThat(google.isElementPresent("#footer"), is(true));
        assertThat(google.getUrl(), equalTo(GOOGLE_URL));
    }

    @Test
    public void fetch_multiple_pages_in_test_method() {
        FetchedPage github = fetchPage(GITHUB_URL);
        FetchedPage google = fetchPage(GOOGLE_URL);

        assertThat(github.isElementPresent("h1"), is(true));
        assertThat(github.getUrl(), equalTo(GITHUB_URL));

        assertThat(google.isElementPresent("#footer"), is(true));
        assertThat(google.getUrl(), equalTo(GOOGLE_URL));
    }

    @Test
    @Fetch(url = "http://whatsmyuseragent.org/", device = MOBILE)
    public void fetch_page_and_emulate_mobile_device_by_annotation() {
        String ua = page.get().getElement("p.intro-text").text();
        assertThat(ua, containsString(page.get().getConfig().getUserAgent(MOBILE)));
    }

    @Test
    public void do_post_request_and_check_response() throws Exception {
        final FetchedPage fetchedPage = call("http://httpbin.org/post", POST, Collections.emptyMap());
        JSONObject responseBody = fetchedPage.getJsonResponse();
        assertThat(responseBody.get("url"), equalTo("http://httpbin.org/post"));
    }
}
