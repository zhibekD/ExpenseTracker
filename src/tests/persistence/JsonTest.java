package model.src.tests.persistence;

import model.src.main.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    public void checkItem(Item item, String itemName, int itemPrice, String itemCategory, String itemDate) {
        assertEquals(item.getItemName(), itemName);
        assertEquals(item.getItemPrice(), itemPrice);
        assertEquals(item.getItemCategory(), itemCategory);
        assertEquals(item.getDate(), itemDate);
    }
}
