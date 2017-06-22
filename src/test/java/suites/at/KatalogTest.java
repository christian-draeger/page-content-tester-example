package suites.at;

import static pagecontenttester.annotations.Fetch.Protocol.HTTP;
import static pagecontenttester.fetcher.FetchedPage.DeviceType.MOBILE;

import org.json.JSONException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.ProductDiscovery;
import pagecontenttester.annotations.Fetch;
import pagecontenttester.runner.PageContentTester;
import testcases.TestAdSense;
import testcases.TestTagManagerScript;
import testcases.TestUnwantedStrings;
import urls.At;

@Category(ProductDiscovery.class)
public class KatalogTest extends PageContentTester {

    @Test
    @Fetch(protocol = HTTP, url= At.KATALOG)
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "OPEN_CATEGORY", "EUR", false, false).check(page.get());
    }

    @Test
    @Fetch(protocol = HTTP, url= At.KATALOG)
    @Fetch(protocol = HTTP, url= At.KATALOG, device = MOBILE)
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get(0));
        new TestUnwantedStrings().check(page.get(1));
    }

    @Test
    @Fetch(protocol = HTTP, url= At.KATALOG)
    public void checkAdSense() throws JSONException {
        new TestAdSense().check(page.get(), "idealo_pcat_at",
                "www.idealo.at/preisvergleich/Relocate/250177.html?categoryId=22562&ref=idealo&shop=google-pcat&sid=250177&site=2&tp=1&type=ad");
    }

    @Ignore
    @Test
    @Fetch(protocol = HTTP, url= At.KATALOG + "?camp=google1000c1")
    public void checkAdSenseGoogle() throws JSONException {
        new TestAdSense().check(page.get());
    }

    @Ignore
    @Test
    @Fetch(protocol = HTTP, url= At.KATALOG + "?camp=ysm1000c1")
    public void checkAdSenseYahoo() throws JSONException {
        new TestAdSense().check(page.get());
    }

}
