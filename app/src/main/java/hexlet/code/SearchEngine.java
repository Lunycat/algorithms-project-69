package hexlet.code;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class SearchEngine {

    private static final Map<String, List<String>> index = new HashMap<>();

    public static List<String> search(List<Map<String, String>> documents, String found) {
        Map<String, Integer> rankMap = new HashMap<>();
        int count = 0;

        for (var map : documents) {
            String[] docArr = normalize(map.get("text")).split(" ");
            String[] foundArr = normalize(found).split(" ");
            updateIndex(index, docArr, map.get("id"));

            for (String foundSub : foundArr) {
                for (String docSub : docArr) {
                    if (docSub.equals(foundSub)) {
                        count++;
                    }
                }
            }

            if (count > 0) {
                rankMap.put(map.get("id"), count);
                count = 0;
            }
        }

        return sortResult(rankMap);
    }

    private static void updateIndex(Map<String, List<String>> index, String[] words, String nameDoc) {
        for (String s : words) {
            List<String> list = index.getOrDefault(s, null) != null ? index.get(s) : new ArrayList<>();
            if (!list.contains(nameDoc)) {
                list.add(nameDoc);
            }
            index.put(s, list);
        }
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
        return str.toLowerCase().replaceAll("[^a-z ]", "");
    }
}
