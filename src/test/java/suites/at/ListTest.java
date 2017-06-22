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
public class ListTest extends PageContentTester {

    @Test
    @Fetch(protocol = HTTP, url= At.LIST)
    public void checkAdSense() throws JSONException {
        new TestAdSense().check(page.get(), "idealo_listen_at",
                "www.idealo.at/preisvergleich/Relocate/250183.html?categoryId=19116&ref=idealo&shop=google-search&sid=250183&site=2&tp=1&type=ad");
    }

    @Ignore
    @Test
    @Fetch(protocol = HTTP, url= At.LIST + "?camp=google1000c1")
    public void checkAdSenseGoogle() throws JSONException {
        new TestAdSense().check(page.get());
    }

    @Ignore
    @Test
    @Fetch(protocol = HTTP, url= At.LIST + "?camp=ysm1000c1")
    public void checkAdSenseYahoo() throws JSONException {
        new TestAdSense().check(page.get());
    }

    @Test
    @Fetch(protocol = HTTP, url= At.LIST)
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "LIST", "EUR", false, false).check(page.get());
    }

    @Test
    @Fetch(protocol = HTTP, url= At.LIST)
    @Fetch(protocol = HTTP, url= At.LIST, device = MOBILE)
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get(0));
        new TestUnwantedStrings().check(page.get(1));
    }

}
