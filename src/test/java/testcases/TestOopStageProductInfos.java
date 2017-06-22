package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;

import pagecontenttester.fetcher.Page;

public class TestOopStageProductInfos {

    private final Page fetchedPage;

    public TestOopStageProductInfos(final Page fetchedPage) {
        this.fetchedPage = fetchedPage;
    }

    public void checkLinkedProductInfos() {
        assertThat("There are linked product infos on the OOP stage",
                fetchedPage.getDocument().select(".oopStage-productInfoTopItem a"), not(empty()));
    }

    public void checkNotLinkedProductInfos() {
        int countLinkedProductInfos = fetchedPage.getDocument().select(".oopStage-productInfoTopItem a").size();
        int countProductInfos = fetchedPage.getDocument().select(".oopStage-productInfoTopItem").size() - countLinkedProductInfos;
        assertThat("There are product infos without links on OOP stage", countProductInfos - countLinkedProductInfos, is(greaterThan(0)));

    }

}
