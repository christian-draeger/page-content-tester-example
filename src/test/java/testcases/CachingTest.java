package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import fetcher.FetchedPage;

public class CachingTest {

    @Test
    public void alreadyFetchedUrlShouldBeCached() {
        FetchedPage page1 = FetchedPage.fetchPage("http://github.com");
        FetchedPage page2 = FetchedPage.fetchPage("http://github.com");
        assertThat(page1, equalTo(page2));
    }
}
