package model.src.tests.persistence;

import model.src.main.model.SummaryTracker;
import model.src.main.persistence.JsonReader;
import model.src.main.model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SummaryTracker st = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySummaryTracker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySummaryTracker.json");
        try {
            SummaryTracker st = reader.read();
            assertEquals(0, st.numOfItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSummaryTracker() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSummaryTracker.json");
        try {
            SummaryTracker st = reader.read();
            List<Item> listOfItems = st.getListOfItems();
            assertEquals(2, listOfItems.size());
            checkItem(listOfItems.get(0), "book", 10, "education", "12-02-2020");
            checkItem(listOfItems.get(1), "pen", 2, "education", "12-01-2020");

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}