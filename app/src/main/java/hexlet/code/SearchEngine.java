package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchEngine {

    public static List<String> search(List<Map<String, String>> documents, String text) {
        List<String> found = new ArrayList<>();

        for (var map : documents) {
            if (contains(map.get("text"), text)) {
                found.add(map.get("id"));
            }
        }

        return found;
    }

    private static boolean contains(String doc, String text) {
        String[] arr = doc.split(" ");

        for (String s : arr) {
            if (s.equals(text)) {
                return true;
            }
        }
        return false;
    }
}
