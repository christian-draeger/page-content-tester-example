package suites.at;

import static pagecontenttester.annotations.Fetch.Protocol.HTTP;

import org.json.JSONException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.ProductDiscovery;
import pagecontenttester.annotations.Fetch;
import pagecontenttester.runner.PageContentTester;
import testcases.TestAdSense;
import testcases.TestInfoText;
import testcases.TestTagManagerScript;
import testcases.TestUnwantedStrings;
import urls.At;

@Category(ProductDiscovery.class)
@Fetch(protocol = HTTP, url= At.PRODUCT_CATEGORY)
public class ProductCategoryTest extends PageContentTester {

    @Test
    public void checkAdSense() throws JSONException {
        new TestAdSense().check(page.get(), "idealo_pcat_at",
                "www.idealo.at/preisvergleich/Relocate/250177.html?categoryId=3747&ref=idealo&shop=google-pcat&sid=250177&site=2&tp=1&type=ad");
    }

    @Ignore
    @Test
    @Fetch(protocol = HTTP, url= At.PRODUCT_CATEGORY + "?camp=google1000c1")
    public void checkAdSenseGoogle() throws JSONException {
        new TestAdSense().check(page.get());
    }

    @Ignore
    @Test
    @Fetch(protocol = HTTP, url= At.PRODUCT_CATEGORY + "?camp=ysm1000c1")
    public void checkAdSenseYahoo() throws JSONException {
        new TestAdSense().check(page.get());
    }

    @Test
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "PRODUCT_CATEGORY", "EUR", true, false).check(page.get());
    }

    @Test
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get());
    }

    @Test
    public void checkInfoText() {
        new TestInfoText().check(page.get(), true);
    }

}
