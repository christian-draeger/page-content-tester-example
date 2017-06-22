package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;

import pagecontenttester.fetcher.Page;

public class TestMainNaviLinksAndOrdering {

    private static final Pattern PATTERN_ID_FROM_HREF = Pattern.compile(".*/(\\d+)\\.html");
    private static final Pattern PATTERN_SKIP_TRAILING_NONSENSE_IN_URL = Pattern.compile("(.*\\.html).*");
    public List<String> expectedIds = Arrays.asList("1342", "1560", "3626", "1940", "1000", "1760", "1002", "3326", "3686", "3932", "2400", "9572", "9908");

    public void check(Page fetchedPage) {
        Elements mainNaviNodes = fetchedPage.getDocument().select(".navi-main:first-of-type .navi-item .navi-link[href]");
        Elements mainNaviMoreLinks = fetchedPage.getDocument().select(".navi-sub-item-more a[href]");
        assertThat(mainNaviNodes.size(), is(mainNaviMoreLinks.size()));
        for (int i = 0; i < mainNaviNodes.size(); i++) {
            assertThat(mainNaviNodes.get(i).attr("href"), is(mainNaviMoreLinks.get(i).attr("href")));
        }
        final List<String> actualIds = getNaviMainNodesIds(mainNaviNodes);
        assertThat(this.expectedIds.size(), is(actualIds.size()));
        assertThat(this.expectedIds.equals(actualIds), is(true));

    }

    public List<String> getNaviMainNodesIds(Elements elements) {
        final List<String> ids = Lists.newLinkedList();
        for (final String href : getNaviMainNodesLinks(elements)) {
            final Matcher matcher = PATTERN_ID_FROM_HREF.matcher(href);
            String id = "";
            while (matcher.find()) {
                id = matcher.group(1);
            }
            ids.add(id);
        }
        return ids;
    }

    public List<String> getNaviMainNodesLinks(Elements elements) {
        final List<String> list = Lists.newLinkedList();
        for (final Element element : elements) {
            list.add(cleanUpLink(element.attr("href")));
        }
        return list;
    }

    public String cleanUpLink(String href) {
        final Matcher matcher = PATTERN_SKIP_TRAILING_NONSENSE_IN_URL.matcher(href);
        String url = "";
        while (matcher.find()) {
            url = matcher.group(1);
        }
        return url;
    }
}
