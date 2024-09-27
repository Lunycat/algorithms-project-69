package hexlet.code;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class SearchEngine {

    public static Map<String, List<String>> index;

    public static List<String> search(List<Map<String, String>> documents, String found) {
        Map<String, Double> rankMap = new HashMap<>();
        index = createIndex(documents);

        for (var map : documents) {
            String[] docArr = normalize(map.get("text")).split(" ");
            String[] foundArr = normalize(found).split(" ");

            double tf = getTermFrequency(docArr, foundArr);
            double idf = getInverseDocumentFrequency(map, index, foundArr);
            double tfIdf = tf * idf;

            if (tf > 0) {
                rankMap.put(map.get("id"), tfIdf);
            }
        }

        return sortResult(rankMap);
    }

    private static Map<String, List<String>> createIndex(List<Map<String, String>> documents) {
        Map<String, List<String>> result = new HashMap<>();

        for (var map : documents) {
            String nameDoc = map.get("id");
            String[] words = normalize(map.get("text")).split(" ");

            for (String s : words) {
                List<String> list = result.getOrDefault(s, null) != null ? result.get(s) : new ArrayList<>();
                if (!list.contains(nameDoc)) {
                    list.add(nameDoc);
                }
                result.put(s, list);
            }
        }
        return result;
    }

    private static double getInverseDocumentFrequency(Map<String, String> document,
                                                      Map<String, List<String>> lIndex, String[] foundArr) {
        double sum = 0;

        for (String foundWord : foundArr) {
            sum += (double) document.size() / lIndex.get(foundWord).size();
        }
        return sum;
    }

    private static double getTermFrequency(String[] docArr, String[] foundArr) {
        int total = 0;
        int countWord = 0;

        for (String foundWord : foundArr) {
            countWord++;
            for (String docWord : docArr) {
                if (foundWord.equals(docWord)) {
                    total++;
                }
            }
        }
        return total == 0 ? 0 : (double) countWord / total;
    }

    private static List<String> sortResult(Map<String, Double> rankMap) {
        List<String> resultList = new ArrayList<>();
        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(rankMap.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());

        for (var entry : sortedList) {
            resultList.add(entry.getKey());
        }

        return resultList;
    }

    private static String normalize(String str) {
        return str.toLowerCase().replaceAll("[^a-z ]", "");
    }
}
