package testcases;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonPartEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestTagManagerScript {

    private String siteTld;
    private String pageType;
    private String currency;
    private boolean withProductIds;
    private boolean withPageLevels;

    private Integer productCode;
    private Integer selfProductCategoryId;
    private Integer selfSubProductCategoryId;

    public TestTagManagerScript(String siteTld, String pageType, String currency, boolean withProductIds, boolean withPageLevels) {
        this.siteTld = siteTld;
        this.pageType = pageType;
        this.currency = currency;
        this.withProductIds = withProductIds;
        this.withPageLevels = withPageLevels;
    }

    public void check(final Page fetchedPage) throws Exception {
        Elements dataLayer = fetchedPage.getDocument().select("script#tagManagerDataLayer");
        assertThat("DataLayer not present", dataLayer.size(), is(1));
        assertDataLayerTagManager(dataLayer.get(0).toString(), fetchedPage);

        Elements tagManager = fetchedPage.getDocument().select("script#googleTagManager");
        assertThat("TagManager not present", tagManager.size(), is(1));
    }

    private void assertDataLayerTagManager(final String dataLayer, final Page fetchedPage) throws JSONException {
        String dataLayerJson = dataLayer.split("var utag_data = \\[")[1].split("];")[0];
        JSONObject uTagAsJson = new JSONObject(dataLayerJson);

        // check site tld
        assertJsonPartEquals(siteTld, dataLayerJson, "site_tld");

        // check currency
        assertJsonPartEquals(currency, dataLayerJson, "site_currency");

        // check page type
        assertJsonPartEquals(pageType, dataLayerJson, "page_type");

        if (withProductIds) {
            assertThat(uTagAsJson.getJSONArray("product_ids").length(), both(greaterThan(0)).and(lessThanOrEqualTo(3)));
            assertThat(uTagAsJson.getJSONArray("product_names").length(), both(greaterThan(0)).and(lessThanOrEqualTo(3)));
            assertThat(uTagAsJson.getJSONArray("product_category_ids").length(), both(greaterThan(0)).and(lessThanOrEqualTo(3)));
        }

        if (withPageLevels) {
            assertThat(uTagAsJson.getJSONArray("page_levels").length(), greaterThan(0));
        }

        if (productCode != null) {
            assertJsonPartEquals(productCode, dataLayerJson, "product_code");
        }

        if (selfProductCategoryId != null) {
            assertJsonPartEquals(selfProductCategoryId, dataLayerJson, "self_product_category_id");
        }

        if (selfSubProductCategoryId != null) {
            assertJsonPartEquals(selfSubProductCategoryId, dataLayerJson, "self_sub_product_category_id");
        }
    }

    public TestTagManagerScript setProductCode(Integer productCode) {
        this.productCode = productCode;
        return this;
    }

    public TestTagManagerScript setSelfProductCategoryId(Integer selfProductCategoryId) {
        this.selfProductCategoryId = selfProductCategoryId;
        return this;
    }

    public TestTagManagerScript setSelfSubProductCategoryId(Integer selfSubProductCategoryId) {
        this.selfSubProductCategoryId = selfSubProductCategoryId;
        return this;
    }
}
