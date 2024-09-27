package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchEngineTest {

    String doc1;
    String doc2;
    String doc3;
    List<Map<String, String>> docs;

    @BeforeEach
    public void beforeEach() {
        doc1 = "I can't shoot straight unless I've had a pint!?";
        doc2 = "Don't shoot shoot shoot that thing at me.";
        doc3 = "I'm your shooter.";

        docs = new ArrayList<>(List.of(
                Map.of("id", "doc1", "text", doc1),
                Map.of("id", "doc2", "text", doc2),
                Map.of("id", "doc3", "text", doc3)
        ));
    }

    @Test
    public void testSearch() {
        List<String> actual = SearchEngine.search(docs, "shoot");
        List<String> expected = List.of("doc2", "doc1");

        assertEquals(expected, actual);
    }

    @Test
    public void testSearchEmpty() {
        List<String> actual = SearchEngine.search(new ArrayList<>(), "shoot");
        List<String> expected = List.of();

        assertEquals(expected, actual);
    }

    @Test
    public void testWithSigns() {
        List<String> actual= SearchEngine.search(docs, "...,<>,pint!?@#$%??");
        List<String> expected = List.of("doc1");

        assertEquals(expected, actual);
    }

    @Test
    public void testFuzzySearch() {
        List<String> actual = SearchEngine.search(docs, "shoot unless had I");
        List<String> expected = List.of("doc1", "doc2");

        assertEquals(expected, actual);
    }
}
