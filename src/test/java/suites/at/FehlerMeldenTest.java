package suites.at;

import static pagecontenttester.annotations.Fetch.Protocol.HTTP;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.ProductDiscovery;
import pagecontenttester.annotations.Fetch;
import pagecontenttester.runner.PageContentTester;
import testcases.TestTagManagerScript;
import testcases.TestUnwantedStrings;
import urls.At;

@Category(ProductDiscovery.class)
@Fetch(protocol = HTTP, url= At.FEHLER_MELDEN)
public class FehlerMeldenTest extends PageContentTester {

    @Test
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "REPORT_ERROR", "EUR", false, false).check(page.get());
    }

    @Test
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get());
    }

}
