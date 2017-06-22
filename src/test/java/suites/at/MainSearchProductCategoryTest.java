package suites.at;

import static pagecontenttester.annotations.Fetch.Protocol.HTTP;
import static pagecontenttester.fetcher.FetchedPage.DeviceType.MOBILE;

import org.json.JSONException;
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
@Fetch(protocol = HTTP, url= At.MAIN_SEARCH_PRODUCT_CATEGORY)
public class MainSearchProductCategoryTest extends PageContentTester {

    @Test
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "MAIN_SEARCH_PRODUCT_CATEGORY", "EUR", true, false).check(page.get());
    }

    @Test
    public void checkAdSense() throws JSONException {
        new TestAdSense().check(page.get(), "idealo_suche_at",
                "www.idealo.at/preisvergleich/Relocate/250182.html?categoryId=100&ref=idealo&shop=google-pagesearch&sid=250182&site=2&tp=1&type=ad");
    }

    @Test
    @Fetch(protocol = HTTP, url= At.MAIN_SEARCH_PRODUCT_CATEGORY)
    @Fetch(protocol = HTTP, url= At.MAIN_SEARCH_PRODUCT_CATEGORY, device = MOBILE)
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get(0));
        new TestUnwantedStrings().check(page.get(1));
    }

}
