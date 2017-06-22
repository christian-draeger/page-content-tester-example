package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assume.assumeTrue;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;
import testcases.util.StageScanner;
import testcases.util.TestPageContentUtils;

public class TestWebtrekkContentId {

    private final String contentId;
    private final String contentIdJson;

    public TestWebtrekkContentId(final String contentId, final String contentIdJson) {
        this.contentId = contentId;
        this.contentIdJson = contentIdJson;
    }

    public void check(final Page fetchedPage) throws Exception {
        assumeTrue(new StageScanner().getStage().equals("PRODUCTION") || new StageScanner().getStage().equals("STAGING"));
        // wt = new webtrekkV3({"linkTrack":"standard","form":"","contentId":"de.%s.no_webtrekk_contentid"});
        Pattern constructorPattern = Pattern.compile(".*webtrekkV3\\((.*?)\\).*", Pattern.DOTALL);
        // wt.contentGroup = {"1":"de","2":"pv","10":"no_webtrekk_contentid"};
        Pattern contentIdPattern = Pattern.compile(".*wt\\.contentGroup\\s*=\\s*(.*?)\\s*;.*", Pattern.DOTALL);

        Elements elements = fetchedPage.getDocument().select("script");
        String constructorParam = null;
        String contentId = null;
        for (Element element : elements) {
            List<Node> childNodes = element.childNodes();
            if (childNodes.isEmpty()) {
                continue;
            }
            String content = childNodes.get(0).outerHtml();
            Matcher constructorMatcher = constructorPattern.matcher(content);
            if (constructorMatcher.matches()) {
                constructorParam = constructorMatcher.group(1);
            }
            Matcher contentIdMatcher = contentIdPattern.matcher(content);
            if (contentIdMatcher.matches()) {
                contentId = contentIdMatcher.group(1);
            }
        }

        assertContentIdInScript(constructorParam, contentId, fetchedPage.isMobile());
    }

    private void assertContentIdInScript(final String constructorParam, final String contentId, final boolean mobile) throws JSONException {
        assertThat(constructorParam, is(notNullValue()));
        assertThat(contentId, is(notNullValue()));

        JSONObject constructorParamJson = new JSONObject(constructorParam);
        assertThat(constructorParamJson.getString("contentId"), is(getContentId(mobile)));

        assertThat(TestPageContentUtils.getJsonAsMap(contentId), is(equalTo(getContentIdMap(mobile))));
    }

    private String getContentId(final boolean mobile) {
        if (mobile) {
            return String.format(contentId, "mpv");
        }
        return String.format(contentId, "pv");
    }

    private Map<String, String> getContentIdMap(final boolean mobile) throws JSONException {
        if (mobile) {
            return TestPageContentUtils.getJsonAsMap(String.format(contentIdJson, "mpv"));
        }
        return TestPageContentUtils.getJsonAsMap(String.format(contentIdJson, "pv"));
    }

}
