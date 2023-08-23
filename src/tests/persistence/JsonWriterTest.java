package model.src.tests.persistence;

import model.src.main.model.SummaryTracker;
import model.src.main.persistence.JsonReader;
import model.src.main.persistence.JsonWriter;
import model.src.main.model.Date;
import model.src.main.model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            SummaryTracker st = new SummaryTracker(null);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySummaryTracker() {
        try {
            SummaryTracker st = new SummaryTracker(null);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySummaryTracker.json");
            writer.open();
            writer.write(st);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySummaryTracker.json");
            st = reader.read();
            assertEquals(0, st.numOfItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            SummaryTracker st = new SummaryTracker(new ArrayList<>());
            st.addItem(new Item("book", 10, "education",
                    new Date(12, 2, 2020)));
            st.addItem(new Item("pen", 2, "education",
                    new Date(12, 1, 2020)));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSummaryTracker.json");
            writer.open();
            writer.write(st);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSummaryTracker.json");
            st = reader.read();
            List<Item> listOfItems = st.getListOfItems();
            assertEquals(2, listOfItems.size());
            checkItem(listOfItems.get(0), "book", 10, "education", "12-02-2020");
            checkItem(listOfItems.get(1), "pen", 2, "education", "12-01-2020");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}