package testcases;

import org.jsoup.select.Elements;
import org.junit.Assert;

import pagecontenttester.fetcher.Page;

public class TestWebtrekkTrackId {

    private final String trackId = "trackId";

    public void check(Page fetchedPage) {
        Elements webtrekk = fetchedPage.getDocument().select("script");
        String trackId = getWebtrekkVarValue(webtrekk.outerHtml());
//        if (fetchedPage.getServerName().isEmpty()) {
//            assertThat("server name is empty therefore production trackId is checked", trackId, is("558922804910526"));
//        } else {
//            assertThat("server name is not empty therefore testing trackId is checked", trackId, is("734089071898567"));
//        }

    }

    private String getWebtrekkVarValue(final String source) {
        final int index = source.indexOf(trackId);
        if (index == -1) {
            Assert.fail("No " + trackId + " found!");
        }
        String varValue = source.substring(index + trackId.length());
        varValue = varValue.substring(varValue.indexOf("\":\"") + 3);
        varValue = varValue.substring(0, varValue.indexOf("\""));
        return varValue;
    }
}
