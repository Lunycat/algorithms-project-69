package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchEngineTest {

    String doc1 = "I can't shoot straight unless I've had a pint!?";
    String doc2 = "Don't shoot shoot shoot that thing at me.";
    String doc3 = "I'm your shooter.";

    List<Map<String, String>> docs = new ArrayList<>(List.of(
            Map.of("id", "doc1", "text", doc1),
            Map.of("id", "doc2", "text", doc2),
            Map.of("id", "doc3", "text", doc3)
    )) ;

    @Test
    public void testSearch() {
        List<String> expected = SearchEngine.search(docs, "shoot");
        List<String> actual = List.of("doc1", "doc2");

        assertEquals(expected, actual);
    }

    @Test
    public void testSearchEmpty() {
        List<String> expected = SearchEngine.search(new ArrayList<>(), "shoot");
        List<String> actual = List.of();

        assertEquals(expected, actual);
    }

    @Test
    public void testWithSigns() {
        List<String> expected = SearchEngine.search(docs, "...,<>,pint!?@#$%??");
        List<String> actual = List.of("doc1");

        assertEquals(expected, actual);
    }
}
