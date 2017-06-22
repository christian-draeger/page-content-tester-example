package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;


public class TestAjaxFilters {

    private static final String SELECTED_FILTER_NAME = "13 Zoll";
    private static final String DESELECTED_FILTER_NAME = "12 Zoll";

    public void check(Page fetchedPage) throws Exception {
        Document document = fetchedPage.getDocument();

        Elements filterElements = document.select(".filter-tagItemLink");

        assertThat(filterElements.size(), is(greaterThan(0)));

        Optional<FilterEntry> selectedFilterEntry = filterElements.stream()
                .map(FilterEntry::new)
                .filter(entry -> entry.name.startsWith(SELECTED_FILTER_NAME))
                .findAny();

        assertThat("Could not find selected filter (\"" + SELECTED_FILTER_NAME + "\")",
                selectedFilterEntry.isPresent(), is(true));

        // TODO: check why true does not work
        assertThat("Expected filter to be selected (\"" + SELECTED_FILTER_NAME + "\")",
                selectedFilterEntry.get().selected, is(true));

        Optional<FilterEntry> notSelectedFilterEntry = filterElements.stream()
                .map(FilterEntry::new)
                .filter(entry -> entry.name.startsWith(DESELECTED_FILTER_NAME))
                .findAny();

        assertThat("Could not find selected filter (\"" + DESELECTED_FILTER_NAME + "\")",
                notSelectedFilterEntry.isPresent(), is(true));
        assertThat("Expected filter to be deselected (\"" + DESELECTED_FILTER_NAME + "\")",
                notSelectedFilterEntry.get().selected, is(false));
    }

    private static class FilterEntry {

        String name;
        boolean selected;

        FilterEntry(Element element) {
            name = element.text();
            selected = element.child(1).hasClass("filter-tagCheckbox--selected");
        }
    }
}
