package urls.seo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum In {

    URLS(
            "/compare/3835753/apple-iphone-5s.html",
            //TODO find use case for this "/compare/4899287E2060J2/xiaomi-redmi-note-3.html",
            "/compare/4058533/apple-iphone-5s-32gb-space-grey.html",
            //"/compare/4968510E2060J2/xiaomi-redmi-note-3-32gb.html",
            "/compare/4905677/canon-pixma-mg3670.html",
            "/compare/2316320/tefal-532718.html",
            "/cat/3189.html",
            "/cat/19116.html?q=sony",
            "/cat/3189F4686438.html",
            "/cat/3189F4686424-4686438.html",
            "/cat/5292F4708528.html",
            "/cat/3189F4686547.html",
            "/cat/3189F4686424.html",
            "/cat/3189F4686424-4686547.html"
    );

    private final String mainProduct;
    //TODO private final String mainProductWithUsedFilter;
    //OBSOLETE private final String mainProductSecondPage;
    private final String variant;
    //TODO private final String variantWithUsedFilter;
    //TODO private final String variantSecondPage;
    private final String product;
    private final String productWithoutOffer;
    //TODO private final String productWithUsedFilter;
    //TODO private final String productSecondPage;

    private final String category;
    private final String categoryWithQuery;
    private final String categoryWithNotWhitelistedFilter;
    private final String categoryWithManufacturerAndNotWhitelistedFilter;
    private final String categoryWithWhitelistedFilter;
    private final String categoryWithProductTypeFilter;
    private final String categoryWithManufacturerTypeFilter;
    private final String categoryWithProductTypeAndManufacturerTypeFilter;
}
