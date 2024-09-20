package hexlet.code;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class SearchEngine {

    public static List<String> search(List<Map<String, String>> documents, String text) {
        Map<String, Integer> rankMap = new HashMap<>();
        int count = 0;

        for (var map : documents) {
            String doc = normalize(map.get("text"));
            text = normalize(text);
            String[] arr = doc.split(" ");

            for (String s : arr) {
                if (s.equals(text)) {
                    count++;
                }
            }

            if (count > 0) {
                rankMap.put(map.get("id"), count);
                count = 0;
            }
        }

        return sortResult(rankMap);
    }

    private static List<String> sortResult(Map<String, Integer> rankMap) {
        List<String> resultList = new ArrayList<>();
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(rankMap.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (var entry : sortedList) {
            resultList.add(entry.getKey());
        }

        return resultList;
    }

    private static String normalize(String str) {
        return str.toLowerCase().replaceAll("[^a-zA-Z ]", "");
    }
}
