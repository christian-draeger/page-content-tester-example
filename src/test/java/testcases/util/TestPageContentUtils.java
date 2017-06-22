package testcases.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;

public class TestPageContentUtils {

    private static final List<String> TEST_AND_STAGING_SERVERS = Arrays.asList(
            "sx379", "resin4-qa-01", "resin4-qa-02", "resin4-qa-lb",
            "local", "bx123", "bx86", "bx131", "bx132", "bx133", "bx134", "bx136", "bx137",
            "bx138", "bx201", "resin4-deploy-01", "resin4-test-01", "resin4-test-02");

    public static Map<String, String> getJsonAsMap(final String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
        Map<String, String> map = new TreeMap<>();
        Iterator jsonIterator = json.keys();
        while (jsonIterator.hasNext()) {
            String key = (String) jsonIterator.next();
            map.put(key, json.getString(key));
        }
        return map;
    }
}
