package suites.at;

import static org.junit.Assume.assumeTrue;
import static pagecontenttester.annotations.Fetch.Protocol.HTTP;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.ProductDiscovery;
import pagecontenttester.annotations.Fetch;
import pagecontenttester.runner.PageContentTester;
import testcases.TestTagManagerScript;
import testcases.TestUnwantedStrings;
import testcases.util.StageScanner;
import urls.At;

@Category(ProductDiscovery.class)
@Fetch(protocol = HTTP, url= At.ALLE_TEST_PRODUKTE)
public class AlleTestprodukteTest extends PageContentTester {

    @BeforeClass
    public static void beforeAll() throws IOException, URISyntaxException {
        assumeTrue("run against production only", new StageScanner().getStage().equals("production"));
    }

    @Test
    public void checkTagManagerScript() throws Exception {
        new TestTagManagerScript("at", "ALL_TEST_PRODUCTS", "EUR", false, false).check(page.get());
    }

    @Test
    public void checkUnwantedStrings() {
        new TestUnwantedStrings().check(page.get());
    }


}
