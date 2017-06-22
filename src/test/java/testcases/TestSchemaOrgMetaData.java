/**
 * Created on 06.03.17
 * (c) idealo internet GmbH 2017
 */
package testcases;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pagecontenttester.fetcher.Page;

/**
 *
 * TestSchemaOrgMetaData
 *
 * @author kaikesselring
 */
public class TestSchemaOrgMetaData {

    /**
     * Checks the schema.org data on a main product page.
     *
     * @param oop {@link Page} for a main product page.
     *
     * @throws JSONException when an error occurs while extracting the schema.org json from the page
     */
    public void checkMainProduct(Page oop) throws JSONException {
        JSONObject json = getSchemaDotOrgJson(oop);
        assertStaticContent(json, Product.TYPE_MAIN_PRODUCT);
        assertCommonSchemaOrgData(json);
    }

    /**
     * Checks the schema.org data on an (variant) oop.
     *
     * @param oop {@link Page} for an (variant) oop.
     *
     * @throws JSONException when an error occurs while extracting the schema.org json from the page
     */
    public void checkOop(Page oop) throws JSONException {
        JSONObject schemaOrgJson = getSchemaDotOrgJson(oop);
        assertStaticContent(schemaOrgJson, Product.TYPE_OOP);
        assertCommonSchemaOrgData(schemaOrgJson);
        assertIsRelatedTo(getSchemaDotOrgJson(oop));
    }

    private JSONObject getSchemaDotOrgJson(Page oop) throws JSONException {
        return new JSONObject(oop.getDocument().select("script[type='application/ld+json']").html());
    }

    /**
     * Checks the schema.org data which common on all oops.
     *
     * @param json the schema.org data from the current page.
     * @throws JSONException when an error occurs while working on the schema.org json
     */
    private void assertCommonSchemaOrgData(JSONObject json) throws JSONException {
        assertCommonProductData(json);
        assertOffersData(json);
        assertManufacturerData(json);
        assertReviews(json);
    }

    /**
     * Checks the schema.org product data which common on all oops.
     *
     * @param json the schema.org data from the current page.
     * @throws JSONException when an error occurs while working on the schema.org json
     */
    private void assertCommonProductData(JSONObject json) throws JSONException {
        // static data
        assertStaticContent(json, Product.CONTEXT);

        // dynamic data, just check for key
        assertJsonKey(json, Product.NAME);
        assertJsonKey(json, Product.IMAGE);
    }

    private void assertOffersData(JSONObject json) throws JSONException {
        assertJsonKey(json, Product.OFFERS);

        JSONObject offersDataJson = json.getJSONObject(Product.OFFERS.getKey());
        assertThat("No offers data found in schema.org data", offersDataJson, notNullValue());

        assertJsonKey(offersDataJson, Offers.TYPE);
        assertJsonKey(offersDataJson, Offers.LOW_PRICE);
        assertJsonKey(offersDataJson, Offers.CURRENCY);
        assertJsonKey(offersDataJson, Offers.OFFER_COUNT);
    }

    private void assertManufacturerData(JSONObject json) throws JSONException {
        assertJsonKey(json, Product.MANUFACTURER);

        JSONObject manufactureDataJson = json.getJSONObject(Product.MANUFACTURER.getKey());
        assertThat("No manufacture data found in schema.org data", manufactureDataJson, notNullValue());

        assertJsonKey(manufactureDataJson, Manufacturer.TYPE);
        assertJsonKey(manufactureDataJson, Manufacturer.NAME);
    }

    private void assertReviews(JSONObject json) throws JSONException {
        assertJsonKey(json, Product.REVIEW);

        JSONArray reviewDataJson = json.getJSONArray(Product.REVIEW.getKey());
        assertThat("No review data found in schema.org data", reviewDataJson, notNullValue());
        assertThat("Empty reviews in schema.org data", reviewDataJson.length(), is(Matchers.greaterThan(0)));

        for (int reviewIndex = 0; reviewIndex < reviewDataJson.length(); reviewIndex++) {
            JSONObject review = reviewDataJson.getJSONObject(reviewIndex);
            assertStaticContent(review, Review.TYPE);
            assertJsonKey(review, Review.AUTHOR);
            assertJsonKey(review, Review.HEADLINE);
            assertJsonKey(review, Review.REVIEW_BODY);
            assertJsonKey(review, Review.REVIEW_RATING);

            JSONObject rating = review.getJSONObject(Review.REVIEW_RATING.getKey());

            assertStaticContent(rating, Rating.TYPE);
            assertJsonKey(rating, Rating.BEST_RATING);
            assertJsonKey(rating, Rating.WORSE_RATING);
            assertJsonKey(rating, Rating.RATING_VALUE);
        }
    }

    private void assertIsRelatedTo(JSONObject json) throws JSONException {
        assertJsonKey(json, Product.IS_RELATED_TO);

        JSONArray isRelatedToData = json.getJSONArray(Product.IS_RELATED_TO.getKey());

        for (int relationIndex = 0; relationIndex < isRelatedToData.length(); relationIndex++) {
            JSONObject relationData = isRelatedToData.getJSONObject(relationIndex);
            assertStaticContent(relationData, RelatedTo.TYPE);
            assertJsonKey(relationData, RelatedTo.URL);
        }
    }

    private void assertStaticContent(JSONObject actualJson, SchemaOrgJson expectedJson) throws JSONException {
        assertJsonKey(actualJson, expectedJson);
        assertThat("Wrong value in schema.org json for " + expectedJson.getClass().toString() + " data " + expectedJson.getKey(), actualJson.get(expectedJson.getKey()), is(equalTo(expectedJson.getValue())));
    }

    private void assertStaticContent(JSONObject actualJson, SchemaOrgJson expectedJson, String expectNonDefaultValue) throws JSONException {
        assertJsonKey(actualJson, expectedJson);
        assertThat("Wrong value in schema.org json for " + expectedJson.getClass().toString() + " data " + expectedJson.getKey(), actualJson.get(expectedJson.getKey()), is(equalTo(expectNonDefaultValue)));
    }

    private void assertJsonKey(JSONObject actualJson, SchemaOrgJson expectedJson) {
        assertThat("schema.org json does not contain " + expectedJson.getClass().toString() + " data for " + expectedJson.getKey(), actualJson.has(expectedJson.getKey()), is(true));
    }

    /**
     * Interface for all enums which provide information about the schema.org json.
     *
     */
    interface SchemaOrgJson {

        default String getValue() {
            return "";
        }

        String getKey();
    }

    private enum Product implements SchemaOrgJson {

        CONTEXT("@context", "http://schema.org"),
        TYPE_MAIN_PRODUCT("@type", "Product"),
        TYPE_OOP("@type", "ProductModel"),
        NAME("name"),
        OFFERS("offers"),
        MANUFACTURER("manufacturer"),
        IMAGE("image"),
        REVIEW("review"),
        IS_RELATED_TO("isRelatedTo");

        private String key;

        private String value;

        Product(String key, String value) {
            this.key = key;
            this.value = value;
        }

        Product(String key) {
            this(key, "");
        }


        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getKey() {
            return key;
        }
    }

    private enum Offers implements SchemaOrgJson {

        TYPE("@type"),
        LOW_PRICE("lowPrice"),
        CURRENCY("priceCurrency"),
        OFFER_COUNT("offerCount"),;

        private String key;

        Offers(final String key) {
            this.key = key;
        }

        @Override
        public String getKey() {
            return key;
        }
    }

    private enum Manufacturer implements SchemaOrgJson {

        TYPE("@type"),
        NAME("name");

        String key;

        Manufacturer(final String key) {
            this.key = key;
        }

        @Override
        public String getKey() {
            return key;
        }
    }

    private enum Review implements SchemaOrgJson {

        TYPE("@type", "Review"),
        AUTHOR("author"),
        HEADLINE("headline"),
        REVIEW_BODY("reviewBody"),
        REVIEW_RATING("reviewRating");

        private String key;

        private String value;

        Review(final String key, String value) {
            this.key = key;
            this.value = value;
        }

        Review(final String key) {
            this(key, "");
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getKey() {
            return key;
        }
    }

    private enum Rating implements SchemaOrgJson {

        TYPE("@type", "Rating"),
        BEST_RATING("bestRating"),
        WORSE_RATING("bestRating"),
        RATING_VALUE("ratingValue");

        private String key;

        private String value;

        Rating(final String key, String value) {
            this.key = key;
            this.value = value;
        }

        Rating(final String key) {
            this(key, "");
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getKey() {
            return key;
        }
    }

    private enum RelatedTo implements SchemaOrgJson {

        TYPE("@type", "Product"),
        URL("url");

        private String key;

        private String value;

        RelatedTo(final String key, String value) {
            this.key = key;
            this.value = value;
        }

        RelatedTo(final String key) {
            this(key, "");
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getKey() {
            return key;
        }
    }
}
