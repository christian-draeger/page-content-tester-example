package testcases.bunchofcalls;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.junit.Test;

import fetcher.FetchedPage;

public class Example7Test {

    Random random = new Random();
    
    @Test
    public void test99() throws InterruptedException {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test1() throws InterruptedException {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test2() throws InterruptedException {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test3() throws InterruptedException {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test4() throws InterruptedException {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test5() throws InterruptedException {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }
}
