package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    private Item testItemOne;

    @BeforeEach
    void runBefore() {
        testItemOne = new Item("Book", 30, "Education",
                new Date(07, 16, 2023));
    }

    @Test
    void testConstructor() {
        assertEquals("Book", testItemOne.getItemName());
        assertEquals(30, testItemOne.getItemPrice());
        assertEquals("Education",testItemOne.getItemCategory());
        assertEquals(07, testItemOne.getMonth());
        assertEquals(16, testItemOne.getDay());
        assertEquals(2023, testItemOne.getYear());
        assertEquals("07-16-2023", testItemOne.getDate());
    }

    @Test
    void testSetItemName() {
        testItemOne.setItemName("Laptop");
        assertEquals("Laptop", testItemOne.getItemName());
    }

    @Test
    void testSetItemPrice() {
        testItemOne.setItemPrice(1000);
        assertEquals(1000, testItemOne.getItemPrice());
    }

    @Test
    void testSetItemCategory() {
        testItemOne.setItemCategory("Electronics");
        assertEquals("Electronics", testItemOne.getItemCategory());
    }

    @Test
    void testSetItemDate() {
        testItemOne.setItemDate(new Date(12, 31, 2022));
        assertEquals("12-31-2022", testItemOne.getDate());
    }

    @Test
    void testToString() {
        assertEquals("Book 30 CAD", testItemOne.toString());
    }
}