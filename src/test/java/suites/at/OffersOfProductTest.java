package suites.at;

import static pagecontenttester.annotations.Fetch.Protocol.HTTP;
import static pagecontenttester.fetcher.FetchedPage.DeviceType.MOBILE;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.Shopping;
import pagecontenttester.annotations.Fetch;
import pagecontenttester.runner.PageContentTester;
import testcases.TestTagManagerScript;
import testcases.TestUnwantedStrings;
import urls.At;

@Category(Shopping.class)
public class OffersOfProductTest extends PageContentTester {

    @Test
    @Fetch(protocol = HTTP, url= At.OFFERS_OF_PRODUCT)
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "OFFERS_OF_PRODUCT", "EUR", true, false).check(page.get());
    }

    @Test
    @Fetch(protocol = HTTP, url= At.OFFERS_OF_PRODUCT)
    @Fetch(protocol = HTTP, url= At.OFFERS_OF_PRODUCT, device = MOBILE)
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get(0));
        new TestUnwantedStrings().check(page.get(1));
    }
}
