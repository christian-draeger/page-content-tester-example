package testcases;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;

import pagecontenttester.fetcher.Page;

public class TestContentIsNotEmpty {

    public void check(Page fetchedPage) {
        assertTrue(StringUtils.isNoneEmpty(fetchedPage.getPageBody()));
    }
}
