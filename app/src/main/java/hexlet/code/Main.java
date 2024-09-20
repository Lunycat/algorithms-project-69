package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String doc1 = "I can't shoot straight unless I've had a pint!?";
        String doc2 = "Don't shoot shoot shoot that thing at me.";
        String doc3 = "I'm your shooter.";

        List<Map<String, String>> docs = new ArrayList<>(List.of(
                Map.of("id", "doc1", "text", doc1),
                Map.of("id", "doc2", "text", doc2),
                Map.of("id", "doc3", "text", doc3)
        ));

        SearchEngine.search(docs, "shoot");
    }
}
