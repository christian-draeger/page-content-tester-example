package testcases.bunchofcalls;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.junit.Test;

import fetcher.FetchedPage;

public class Example9Test {

    Random random = new Random();
    
    @Test
    public void test99() {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test1() {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test2() {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test3() {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test4() {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }

    @Test
    public void test5() {
        FetchedPage page = FetchedPage.fetchPage(random.getRandomGoogleUrl());
        String originalSearchTerm = page.getDocument().select("a.spell_orig").text();
        assertThat(page.getUrl(), containsString(originalSearchTerm));
    }
}
