package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static testcases.matchers.JsonHasStringEntry.jsonHasNonEmptyStringEntry;
import static testcases.matchers.JsonHasStringEntry.jsonHasStringEntry;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestIPCInitTag {

    public void with(Page fetchedPage, boolean offersOfProduct, String pageName) throws JSONException {
        Elements elements = fetchedPage.getDocument().select("meta[name=ipc-init]");
        assertThat(elements.size(), is(1));
        checkJson(fetchedPage, elements.get(0).attr("data-ipc-init"), offersOfProduct, pageName);
    }

    private void checkJson(Page fetchedPage, String jsonAsString, boolean offersOfProduct, String pageName) throws JSONException {

        final String[] jsonKeys = { "hasVariants", "embedLazyLoading" };
        final String[] toasterJsonKeys = { "message", "track", "status" };
        final String[] translationJsonKeys = {
                "wishlist.page.compare_link_text",
                "common.scroll-top",
                "appbanner.goto_app",
                "appbanner.app",
                "appbanner.install",
                "appbanner.open_in_app",
                "wishlist.page.compare_element"
        };
        final String comparePageUrl = "/preisvergleich/CompareProducts/100.html";

        // parse java script init tag string to String
        JSONObject json = new JSONObject(jsonAsString);
        JSONObject toasterJson = json.getJSONObject("toaster");
        JSONObject translationJson = json.getJSONObject("translations");

        // with if value of jsonKeys from parsed json are not null or empty
        for (String jsonKey : jsonKeys) {
            assertThat(json, jsonHasNonEmptyStringEntry(jsonKey));
        }

        // with toaster json
        for (String jsonKey : toasterJsonKeys) {
            assertThat(toasterJson, jsonHasStringEntry(jsonKey, isEmptyString()));
        }

        // with translation json
        for (String jsonKey : translationJsonKeys) {
            assertThat(translationJson, jsonHasNonEmptyStringEntry(jsonKey));
        }

        // with comparePageUrl
        assertThat(json, jsonHasStringEntry("comparePageUrl", is(comparePageUrl)));

        // with site ID
        assertThat(json, jsonHasStringEntry("siteId", containsString("1")));

        // with page name
        assertThat(json, jsonHasStringEntry("pageName", containsString(pageName)));

        // oop specific checks
        if (offersOfProduct) {

            // with product id
            assertThat(json, jsonHasNonEmptyStringEntry("productId"));
            assertThat(fetchedPage.getUrl(), containsString(json.getString("productId")));

            // with media box is true
            assertThat(json, jsonHasStringEntry("addMediaBoxScripts", is("true")));
        }
    }
}
