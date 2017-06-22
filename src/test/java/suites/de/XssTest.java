package suites.de;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.ParallelRunner;

import categories.ProductDiscovery;
import pagecontenttester.runner.PageContentTester;

@RunWith(ParallelRunner.class)
@Category(ProductDiscovery.class)
public class XssTest extends PageContentTester {

    public enum Results {
        WITH_RESULTS,
        NO_RESULTS
    }

    public enum PageType {
        MANUFACTURER,
        SHOP,
        PRODUCTSEARCH,
        MAINSEARCH,
        PCAT,
        CATALOG
    }

    @Test
    public void name() throws Exception {

    }

//    private static volatile FetchedPage fetchedMainSearchXssAttackAttributeWithoutResults;
//    private static volatile FetchedPage fetchedMainSearchXssAttackAttributeWithResults;
//    private static volatile FetchedPage fetchedMainSearchXssAttackElementWithoutResults;
//    private static volatile FetchedPage fetchedMainSearchXssAttackElementWithResults;
//    private static volatile FetchedPage fetchedMainSearchXssAttackTextWithoutResults;
//    private static volatile FetchedPage fetchedMainSearchXssAttackTextWithResults;
//    private static volatile FetchedPage fetchedProductCategoryXssAttackAttributeWithResults;
//    private static volatile FetchedPage fetchedProductCategoryXssAttackElementWithResults;
//    private static volatile FetchedPage fetchedProductCategoryXssAttackTextWithResults;
//    private static volatile FetchedPage fetchedCatalogXssAttackAttributeWithoutResults;
//    private static volatile FetchedPage fetchedCatalogXssAttackAttributeWithResults;
//    private static volatile FetchedPage fetchedCatalogXssAttackElementWithoutResults;
//    private static volatile FetchedPage fetchedCatalogXssAttackElementWithResults;
//    private static volatile FetchedPage fetchedCatalogXssAttackTextWithoutResults;
//    private static volatile FetchedPage fetchedCatalogXssAttackTextWithResults;
//    private static volatile FetchedPage fetchedManufacturerXssAttackAttributeWithResults;
//    private static volatile FetchedPage fetchedManufacturerXssAttackElementWithResults;
//    private static volatile FetchedPage fetchedManufacturerXssAttackTextWithResults;
//    private static volatile FetchedPage fetchedShopXssAttackAttributeWithResults;
//    private static volatile FetchedPage fetchedShopXssAttackElementWithResults;
//    private static volatile FetchedPage fetchedShopXssAttackTextWithResults;
//    private static volatile FetchedPage fetchedProductSearchXssAttackAttributeWithoutResults;
//    private static volatile FetchedPage fetchedProductSearchXssAttackAttributeWithResults;
//    private static volatile FetchedPage fetchedProductSearchXssAttackElementWithoutResults;
//    private static volatile FetchedPage fetchedProductSearchXssAttackElementWithResults;
//    private static volatile FetchedPage fetchedProductSearchXssAttackTextWithoutResults;
//    private static volatile FetchedPage fetchedProductSearchXssAttackTextWithResults;
//
//
//    @BeforeClass
//    public static void beforeAll() throws IOException, URISyntaxException {
//        fetchedMainSearchXssAttackAttributeWithoutResults = FetchedPage.fetchedDesktopPage(Xss.MAIN_SEARCH_XSS_ATTACK_ATTRIBUTE_WITHOUT_RESULTS, "de");
//        fetchedMainSearchXssAttackAttributeWithResults = FetchedPage.fetchedDesktopPage(Xss.MAIN_SEARCH_XSS_ATTACK_ATTRIBUTE_WITH_RESULTS, "de");
//        fetchedMainSearchXssAttackElementWithoutResults = FetchedPage.fetchedDesktopPage(Xss.MAIN_SEARCH_XSS_ATTACK_ELEMENT_WITHOUT_RESULTS, "de");
//        fetchedMainSearchXssAttackElementWithResults = FetchedPage.fetchedDesktopPage(Xss.MAIN_SEARCH_XSS_ATTACK_ELEMENT_WITH_RESULTS, "de");
//        fetchedMainSearchXssAttackTextWithoutResults = FetchedPage.fetchedDesktopPage(Xss.MAIN_SEARCH_XSS_ATTACK_TEXT_WITHOUT_RESULTS, "de");
//        fetchedMainSearchXssAttackTextWithResults = FetchedPage.fetchedDesktopPage(Xss.MAIN_SEARCH_XSS_ATTACK_TEXT_WITH_RESULTS, "de");
//        fetchedProductCategoryXssAttackAttributeWithResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_CATEGORY_XSS_ATTACK_ATTRIBUTE_WITH_RESULTS, "de");
//        fetchedProductCategoryXssAttackElementWithResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_CATEGORY_XSS_ATTACK_ELEMENT_WITH_RESULTS, "de");
//        fetchedProductCategoryXssAttackTextWithResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_CATEGORY_XSS_ATTACK_TEXT_WITH_RESULTS, "de");
//        fetchedCatalogXssAttackAttributeWithoutResults = FetchedPage.fetchedDesktopPage(Xss.CATALOG_XSS_ATTACK_ATTRIBUTE_WITHOUT_RESULTS, "de");
//        fetchedCatalogXssAttackAttributeWithResults = FetchedPage.fetchedDesktopPage(Xss.CATALOG_XSS_ATTACK_ATTRIBUTE_WITH_RESULTS, "de");
//        fetchedCatalogXssAttackElementWithoutResults = FetchedPage.fetchedDesktopPage(Xss.CATALOG_XSS_ATTACK_ELEMENT_WITHOUT_RESULTS, "de");
//        fetchedCatalogXssAttackElementWithResults = FetchedPage.fetchedDesktopPage(Xss.CATALOG_XSS_ATTACK_ELEMENT_WITH_RESULTS, "de");
//        fetchedCatalogXssAttackTextWithoutResults = FetchedPage.fetchedDesktopPage(Xss.CATALOG_XSS_ATTACK_TEXT_WITHOUT_RESULTS, "de");
//        fetchedCatalogXssAttackTextWithResults = FetchedPage.fetchedDesktopPage(Xss.CATALOG_XSS_ATTACK_TEXT_WITH_RESULTS, "de");
//        fetchedManufacturerXssAttackAttributeWithResults = FetchedPage.fetchedDesktopPage(Xss.MANUFACTURER_XSS_ATTACK_ATTRIBUTE_WITH_RESULTS, "de");
//        fetchedManufacturerXssAttackElementWithResults = FetchedPage.fetchedDesktopPage(Xss.MANUFACTURER_XSS_ATTACK_ELEMENT_WITH_RESULTS, "de");
//        fetchedManufacturerXssAttackTextWithResults = FetchedPage.fetchedDesktopPage(Xss.MANUFACTURER_XSS_ATTACK_TEXT_WITH_RESULTS, "de");
//        fetchedShopXssAttackAttributeWithResults = FetchedPage.fetchedDesktopPage(Xss.SHOP_XSS_ATTACK_ATTRIBUTE_WITH_RESULTS, "de");
//        fetchedShopXssAttackElementWithResults = FetchedPage.fetchedDesktopPage(Xss.SHOP_XSS_ATTACK_ELEMENT_WITH_RESULTS, "de");
//        fetchedShopXssAttackTextWithResults = FetchedPage.fetchedDesktopPage(Xss.SHOP_XSS_ATTACK_TEXT_WITH_RESULTS, "de");
//        fetchedProductSearchXssAttackAttributeWithoutResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_RATING_SEARCH_XSS_ATTACK_ATTRIBUTE_WITHOUT_RESULTS, "de");
//        fetchedProductSearchXssAttackAttributeWithResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_RATING_SEARCH_XSS_ATTACK_ATTRIBUTE_WITH_RESULTS, "de");
//        fetchedProductSearchXssAttackElementWithoutResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_RATING_SEARCH_XSS_ATTACK_ELEMENT_WITHOUT_RESULTS, "de");
//        fetchedProductSearchXssAttackElementWithResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_RATING_SEARCH_XSS_ATTACK_ELEMENT_WITH_RESULTS, "de");
//        fetchedProductSearchXssAttackTextWithoutResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_RATING_SEARCH_XSS_ATTACK_TEXT_WITHOUT_RESULTS, "de");
//        fetchedProductSearchXssAttackTextWithResults = FetchedPage.fetchedDesktopPage(Xss.PRODUCT_RATING_SEARCH_XSS_ATTACK_TEXT_WITH_RESULTS, "de");
//    }
//
//    @Test
//    public void checkRwdXss() {
//        new TestRwdXss().check(fetchedMainSearchXssAttackAttributeWithoutResults, PageType.MAINSEARCH, Results.NO_RESULTS);
//        new TestRwdXss().check(fetchedMainSearchXssAttackAttributeWithResults, PageType.MAINSEARCH, Results.WITH_RESULTS);
//        new TestRwdXss().check(fetchedMainSearchXssAttackElementWithoutResults, PageType.MAINSEARCH, Results.NO_RESULTS);
//        new TestRwdXss().check(fetchedMainSearchXssAttackElementWithResults, PageType.MAINSEARCH, Results.WITH_RESULTS);
//        new TestRwdXss().check(fetchedMainSearchXssAttackTextWithoutResults, PageType.MAINSEARCH, Results.NO_RESULTS);
//        new TestRwdXss().check(fetchedMainSearchXssAttackTextWithResults, PageType.MAINSEARCH, Results.WITH_RESULTS);
//        new TestRwdXss().check(fetchedProductCategoryXssAttackAttributeWithResults, PageType.PCAT, Results.WITH_RESULTS);
//        new TestRwdXss().check(fetchedProductCategoryXssAttackElementWithResults, PageType.PCAT, Results.WITH_RESULTS);
//        new TestRwdXss().check(fetchedProductCategoryXssAttackTextWithResults, PageType.PCAT, Results.WITH_RESULTS);
//        new TestRwdXss().check(fetchedCatalogXssAttackAttributeWithoutResults, PageType.CATALOG, Results.NO_RESULTS);
//        new TestRwdXss().check(fetchedCatalogXssAttackAttributeWithResults, PageType.CATALOG, Results.WITH_RESULTS);
//        new TestRwdXss().check(fetchedCatalogXssAttackElementWithoutResults, PageType.CATALOG, Results.NO_RESULTS);
//        new TestRwdXss().check(fetchedCatalogXssAttackElementWithResults, PageType.CATALOG, Results.WITH_RESULTS);
//        new TestRwdXss().check(fetchedCatalogXssAttackTextWithoutResults, PageType.CATALOG, Results.NO_RESULTS);
//        new TestRwdXss().check(fetchedCatalogXssAttackTextWithResults, PageType.CATALOG, Results.WITH_RESULTS);
//    }
//
//    @Test
//    public void checkXss() {
//        new TestXss().check(fetchedManufacturerXssAttackAttributeWithResults, PageType.MANUFACTURER, Results.WITH_RESULTS);
//        new TestXss().check(fetchedManufacturerXssAttackElementWithResults, PageType.MANUFACTURER, Results.WITH_RESULTS);
//        new TestXss().check(fetchedManufacturerXssAttackTextWithResults, PageType.MANUFACTURER, Results.WITH_RESULTS);
//        new TestXss().check(fetchedShopXssAttackAttributeWithResults, PageType.SHOP, Results.WITH_RESULTS);
//        new TestXss().check(fetchedShopXssAttackElementWithResults, PageType.SHOP, Results.WITH_RESULTS);
//        new TestXss().check(fetchedShopXssAttackTextWithResults, PageType.SHOP, Results.WITH_RESULTS);
//        new TestXss().check(fetchedProductSearchXssAttackAttributeWithoutResults, PageType.PRODUCTSEARCH, Results.NO_RESULTS);
//        new TestXss().check(fetchedProductSearchXssAttackAttributeWithResults, PageType.PRODUCTSEARCH, Results.WITH_RESULTS);
//        new TestXss().check(fetchedProductSearchXssAttackElementWithoutResults, PageType.PRODUCTSEARCH, Results.NO_RESULTS);
//        new TestXss().check(fetchedProductSearchXssAttackElementWithResults, PageType.PRODUCTSEARCH, Results.WITH_RESULTS);
//        new TestXss().check(fetchedProductSearchXssAttackTextWithoutResults, PageType.PRODUCTSEARCH, Results.NO_RESULTS);
//        new TestXss().check(fetchedProductSearchXssAttackTextWithResults, PageType.PRODUCTSEARCH, Results.WITH_RESULTS);
//    }

}
