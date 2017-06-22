package testcases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;

import pagecontenttester.fetcher.Page;

public class TestContentType {

    public void check(Page fetchedPage, String contentType) {
        String currentContentType = StringUtils.deleteWhitespace(fetchedPage.getContentType());
        String expectedContentType = StringUtils.deleteWhitespace(contentType);
        assertThat("Page returned wrong content type " + fetchedPage.getContentType(),
                currentContentType, is(expectedContentType));
    }
}
