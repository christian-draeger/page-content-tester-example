package testcases;

import static testcases.util.ElementFinder.assertAndGetFirstElement;

import pagecontenttester.fetcher.Page;

public class TestEnergyEfficiencyHint {

    public void check(Page fetchedPage) {
        assertAndGetFirstElement(fetchedPage, ".energy-tire-icon.icon-tire-fuel");
        assertAndGetFirstElement(fetchedPage, ".energy-tire-icon.icon-tire-wet");
        assertAndGetFirstElement(fetchedPage, ".energy-tire-icon.icon-tire-noise");
    }

}
