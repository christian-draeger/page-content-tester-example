package testcases;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonPartEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeTrue;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;
import testcases.util.StageScanner;

public class TestAdSense {

    public void check(final Page fetchedPage, String channel, String clickTrackUrl) throws JSONException {

        assumeTrue(new StageScanner().getStage().equals("PRODUCTION"));
        parseAdsenseScript(fetchedPage, channel, clickTrackUrl);

    }

    public void check(final Page fetchedPage) throws JSONException {
        parseAdsenseScript(fetchedPage, "sem", "");
    }

    private void parseAdsenseScript(final Page fetchedPage, String channel, String clickTrackUrl) throws JSONException {
        final Elements adsenseContainer = fetchedPage.getDocument().select("#adwords-bottom");
        assertThat(adsenseContainer.size(), is(1));
        String adsenseConfig = adsenseContainer.get(0).attr("data-adSense");
        adsenseConfig = adsenseConfig.substring(adsenseConfig.indexOf("\"config\":{"), adsenseConfig.indexOf("}") + 1);
        adsenseConfig = adsenseConfig.substring(adsenseConfig.indexOf("{"), adsenseConfig.indexOf("}") + 1);
        assertAdSense(adsenseConfig, channel, clickTrackUrl);
    }

    private void assertAdSense(final String adSense, String channel, String clickTrackUrl) throws JSONException {
        String adSenseJsonString = StringEscapeUtils.unescapeEcmaScript(adSense);
        // Remove singe quoatation mark in JSON value for e.g. 'query' : 'Women's Shirts & Blouses',
        adSenseJsonString = adSenseJsonString.replaceAll("'s ", "s");
        JSONObject adSenseJson = new JSONObject(adSenseJsonString);

        assertThat(adSenseJson.toString(), either(containsString("\"pubId\":\"partner-idealo"))
                .or(containsString("\"pubId\":\"partner-idealo-browse"))
                .or(containsString("\"pubId\":\"partner-idealo-referral"))
                .or(containsString("\"pubId\":\"partner-idealo-int"))
                .or(containsString("\"pubId\":\"partner-idealo-int-browse"))
                .or(containsString("\"pubId\":\"partner-idealo-int-referral")));

        if (!channel.contains("sem")) {
            assertJsonPartEquals(channel, adSenseJson.toString(), "channel");
            assertThat(adSenseJson.getString("clicktrackUrl"), containsString(clickTrackUrl));
        } else {
            // assertThat(adSenseJson.getString("channel"), containsString("sem"));
        }
    }
}
