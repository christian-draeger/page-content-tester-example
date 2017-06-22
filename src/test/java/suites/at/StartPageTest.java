package suites.at;

import static pagecontenttester.annotations.Fetch.Protocol.HTTP;
import static pagecontenttester.fetcher.FetchedPage.DeviceType.MOBILE;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.ProductDiscovery;
import pagecontenttester.annotations.Fetch;
import pagecontenttester.runner.PageContentTester;
import testcases.TestIpcUid;
import testcases.TestTagManagerScript;
import testcases.TestTeaser;
import testcases.TestUnwantedStrings;
import urls.At;

@Category(ProductDiscovery.class)
public class StartPageTest extends PageContentTester {

    @Test
    @Fetch(protocol = HTTP, url= At.START_PAGE)
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "MAIN_PRODUCT_CATEGORY", "EUR", false, false).check(page.get());
    }

    @Test
    @Fetch(protocol = HTTP, url= At.START_PAGE)
    @Fetch(protocol = HTTP, url= At.START_PAGE, device = MOBILE)
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get(0));
        new TestUnwantedStrings().check(page.get(1));
    }

    @Test
    @Fetch(protocol = HTTP, url= At.START_PAGE)
    @Fetch(protocol = HTTP, url= At.START_PAGE, device = MOBILE)
    public void checkIpcUid() {
        new TestIpcUid().check(page.get(0));
        new TestIpcUid().check(page.get(1));
    }

    @Ignore
    @Test
    @Fetch(protocol = HTTP, url= At.START_PAGE + "?teaserNow=1420070400")
    public void checkFallbackTeaser() {
        new TestTeaser().checkUniversalTeaser(page.get());
    }

    @Test
    @Fetch(protocol = HTTP, url= At.START_PAGE)
    public void checkTeaser() {
        new TestTeaser().check(page.get());
    }

}
