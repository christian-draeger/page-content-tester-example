package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

import org.junit.BeforeClass;
import org.junit.Test;

import fetcher.FetchedPage;

public class ExampleUsageTest {

    private final static String VALID_URL = "https://github.com/christian-draeger";
    private static FetchedPage page;
    private static FetchedPage pageAsMobileDevice;

    @BeforeClass
    public static void beforeAll() {
        page = FetchedPage.fetchPage(VALID_URL);
        pageAsMobileDevice = FetchedPage.fetchPageAsMobileDevice(VALID_URL);
    }

    @Test
    public void is_correct_user_name() {
        assertThat(page.getElement("h1").text(), containsString("christian-draeger"));
    }

    @Test
    public void is_profile_picture_present() {
        assertThat(page.isElementPresent("img.avatar"), is(true));
    }

    @Test
    public void check_profile_picture_src() {
        assertThat(page.getElement("img.avatar").attr("src"), containsString("githubusercontent.com"));
    }

    @Test
    public void check_login_cookie_status() {
        assertThat(page.getCookieValue("logged_in"), equalTo("no"));
    }

    @Test
    public void fetcher_should_return_cookies() {
        assertThat(page.getCookies(), hasEntry("logged_in", "no"));
    }
}
