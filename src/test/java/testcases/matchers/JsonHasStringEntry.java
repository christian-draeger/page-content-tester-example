package testcases.matchers;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHasStringEntry extends TypeSafeMatcher<JSONObject> {

    private final String expectedKey;
    private final Matcher<String> valueMatcher;

    private boolean keyPresent;

    private JsonHasStringEntry(final String expectedKey, final Matcher<String> valueMatcher) {
        this.expectedKey = expectedKey;
        this.valueMatcher = valueMatcher;
    }

    @Override
    protected boolean matchesSafely(final JSONObject item) {
        try {
            keyPresent = item.has(expectedKey);
            return keyPresent && valueMatcher.matches(item.getString(expectedKey));
        } catch (JSONException e) {
            // prevented by with with item.has()
            return false;
        }
    }

    @Override
    public void describeTo(final Description description) {
        if (!keyPresent) {
            description.appendText("JSON object has entry for key: " + expectedKey);
        } else {
            description.appendText("JSON object has entry for key <" + expectedKey + "> " + "that ");
            valueMatcher.describeTo(description);
        }
    }

    @Override
    protected void describeMismatchSafely(final JSONObject item, final Description mismatchDescription) {
        if (!keyPresent) {
            mismatchDescription.appendText("was " + item);
        } else {
            mismatchDescription.appendText("entry value ");
            try {
                valueMatcher.describeMismatch(item.getString(expectedKey), mismatchDescription);
            } catch (JSONException e) {
                // prevented by with to keyPresent
            }
        }
    }

    public static Matcher<JSONObject> jsonHasStringEntry(final String expectedKey, final Matcher<String> expectedValue) {
        return new JsonHasStringEntry(expectedKey, expectedValue);
    }

    public static Matcher<JSONObject> jsonHasNonEmptyStringEntry(final String expectedKey) {
        return new JsonHasStringEntry(expectedKey, not(isEmptyOrNullString()));
    }
}
