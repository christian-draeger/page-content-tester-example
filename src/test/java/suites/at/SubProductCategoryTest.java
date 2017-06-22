package suites.at;

import static pagecontenttester.annotations.Fetch.Protocol.HTTP;

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
public class SubProductCategoryTest extends PageContentTester {

    @Test
    @Fetch(protocol = HTTP, url= At.SUB_PRODUCT_CATEGORY)
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "SUB_PRODUCT_CATEGORY", "EUR", true, false).check(page.get());
    }

    @Test
    @Fetch(protocol = HTTP, url= At.SUB_PRODUCT_CATEGORY)
    public void checkAdSense() throws JSONException {
        new TestAdSense().check(page.get(), "idealo_pcat_at",
                "www.idealo.at/preisvergleich/Relocate/250177.html?categoryId=1560&ref=idealo&shop=google-pcat&sid=250177&site=2&tp=1&type=ad");
    }

    @Test
    @Fetch(protocol = HTTP, url= At.SUB_PRODUCT_CATEGORY)
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get());
    }

}
