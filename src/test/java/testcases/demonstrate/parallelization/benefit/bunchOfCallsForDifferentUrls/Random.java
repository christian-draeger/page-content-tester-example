package testcases.demonstrate.parallelization.benefit.bunchOfCallsForDifferentUrls;

import java.util.UUID;

public class Random {

    String getRandomGoogleUrl() {
        return "https://www.google.de/#q=" + randomString() + "&*";
    }

    private String randomString() {
        final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
