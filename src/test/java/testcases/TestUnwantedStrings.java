package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestUnwantedStrings {

    private static final List<String> UNWANTED_STRINGS_IN_SOURCE_CODE = Arrays.asList(
            "???", " key=\"", "{0}", "<i18n:", "<os:", "<ipc:", "<idealo:", "${", "<str:", "<c:", "<fmt:", "/1m",
            "<form:", "<log:", "<mvc:", "<rx:", "<spring:", "<string:", "<x:", /*"img.idealo.com/ipc",*/ ".idealo.com/energie",
            ".idealo.com/apps", ".idealo.com/fonts", ".idealo.com/logos", /*"cdn.idealo.com/js",*/ ".idealo.com/pwm", ".idealo.com/static", "errorpage",
            "<<", ">&gt;", "<&lt;");
    private static final List<String> UNWANTED_STRINGS_IN_BODY_TEXT = Arrays.asList(
            "null", "@amp;", "Exception", "exception");
    private static final List<String> UNWANTED_STRINGS_IN_TITLE_ATTRIBUTES = Arrays.asList("null", "@amp;");
    private static final List<String> UNWANTED_STRINGS_IN_CONTENT_ATTRIBUTES = Arrays.asList("null");


    public void check(final Page fetchedPage) {
        checkPageSource(fetchedPage);
        checkPageText(fetchedPage);
        checkTitleAttributes(fetchedPage);
        checkContentAttributes(fetchedPage);
    }

    private void checkPageSource(final Page fetchedPage) {
        String pageBody = fetchedPage.getPageBody();
        for (String unwantedString : UNWANTED_STRINGS_IN_SOURCE_CODE) {
            assertThat("unwanted string in whole page source: " + unwantedString,
                    pageBody.contains(unwantedString), is(false));
        }
    }

    private void checkPageText(final Page fetchedPage) {
        String contentText = fetchedPage.getDocument().text();
        for (String unwantedString : UNWANTED_STRINGS_IN_BODY_TEXT) {
            assertThat("unwanted string in body-tag: " + unwantedString,
                    contentText.contains(unwantedString), is(false));
        }
    }

    private void checkTitleAttributes(final Page fetchedPage) {
        Elements elementsWithTitleAttribute = fetchedPage.getDocument().select("[title]");
        for (Element element : elementsWithTitleAttribute) {
            for (String unwantedString : UNWANTED_STRINGS_IN_TITLE_ATTRIBUTES) {
                assertThat("Unwanted string in title attribute of \"" + element.tagName() + "\" tag.",
                        element.attr("title"), not(containsString(unwantedString)));
            }
        }
    }

    private void checkContentAttributes(final Page fetchedPage) {
        Elements elementsWithTitleAttribute = fetchedPage.getDocument().select("[content]");
        for (Element element : elementsWithTitleAttribute) {
            for (String unwantedString : UNWANTED_STRINGS_IN_CONTENT_ATTRIBUTES) {
                assertThat("Unwanted string in title attribute of \"" + element.tagName() + "\" tag.",
                        element.attr("content"), not(containsString(unwantedString)));
            }
        }
    }
}
