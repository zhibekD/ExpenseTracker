package model.src.tests.model;

import model.src.main.model.Date;
import model.src.main.model.Item;
import model.src.main.model.SummaryTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummaryTrackerTest {

    private SummaryTracker testTracker;
    private SummaryTracker testTrackerZero;
    private ArrayList<Item> testListOfItemsOne;
    private ArrayList<Item> testListOfItemsTwo;
    private ArrayList<Item> testListOfItemsThree;
    private Item itemBook;
    private Item itemHoodie;
    private Item itemCoffee;
    private Item itemSalad;


    @BeforeEach
    void runBefore() {
        this.itemBook = new Item("Book", 30,
                "Education", new Date(6, 22, 2020));
        this.itemHoodie = new Item("Hoodie", 40,
                "Clothes", new Date(10, 30, 2022));
        this.itemCoffee = new Item("Coffee", 3,
                "Food", new Date(1, 15, 2023));
        this.itemSalad = new Item("Salad", 10,
                "Food", new Date(1, 12, 2023));

        this.testListOfItemsOne = new ArrayList<>();
        testListOfItemsOne.add(itemBook);
        testListOfItemsOne.add(itemCoffee);
        testListOfItemsOne.add(itemHoodie);
        testListOfItemsOne.add(itemSalad);

        this.testTracker = new SummaryTracker(new ArrayList<>());
        testTracker.addItem(itemBook);
        testTracker.addItem(itemCoffee);
        testTracker.addItem(itemHoodie);
        testTracker.addItem(itemSalad);

        this.testTrackerZero = new SummaryTracker(new ArrayList<>());
        this.testListOfItemsTwo = new ArrayList<>();
        this.testListOfItemsThree = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(testListOfItemsOne, testTracker.getListOfItems());
    }

    @Test
    void testAddOneItem() {
        testTracker.addItem(itemHoodie);
        testListOfItemsOne.add(itemHoodie);
        assertEquals(testListOfItemsOne, testTracker.getListOfItems());
    }

    @Test
    void testAddMoreItems() {
        testTracker.addItem(itemSalad);
        testTracker.addItem(itemHoodie);
        testTracker.addItem(itemCoffee);
        testListOfItemsOne.add(itemSalad);
        testListOfItemsOne.add(itemHoodie);
        testListOfItemsOne.add(itemCoffee);
        assertEquals(testListOfItemsOne, testTracker.getListOfItems());
    }

    @Test
    void testRemoveOneItem() {
        testTracker.removeItem(itemSalad);
        testListOfItemsOne.remove(itemSalad);
        assertEquals(testListOfItemsOne, testTracker.getListOfItems());
    }

    @Test
    void testRemoveMoreItems() {
        testTracker.removeItem(itemSalad);
        testTracker.removeItem(itemCoffee);
        testTracker.removeItem(itemHoodie);
        testListOfItemsOne.remove(itemSalad);
        testListOfItemsOne.remove(itemCoffee);
        testListOfItemsOne.remove(itemHoodie);
        assertEquals(testListOfItemsOne, testTracker.getListOfItems());
    }

    @Test
    void testRemoveAllItems() {
        testTracker.removeItem(itemSalad);
        testTracker.removeItem(itemCoffee);
        testTracker.removeItem(itemHoodie);
        testTracker.removeItem(itemBook);
        testListOfItemsOne.remove(itemSalad);
        testListOfItemsOne.remove(itemCoffee);
        testListOfItemsOne.remove(itemHoodie);
        testListOfItemsOne.remove(itemBook);
        assertEquals(testListOfItemsTwo, testTracker.getListOfItems());
    }

    @Test
    void testSetListOfItems() {
        testTracker.setListOfItems(testListOfItemsTwo);
        assertEquals(testListOfItemsTwo, testTracker.getListOfItems());
    }

    @Test
    void testNumOfItemsZero() {
        assertEquals(0, testTrackerZero.numOfItems());
    }

    @Test
    void testNumOfItemsOne() {
        testTrackerZero.addItem(itemSalad);
        assertEquals(1, testTrackerZero.numOfItems());
    }

    @Test
    void testNumOfItemsMore() {
        assertEquals(4, testTracker.numOfItems());
    }

    @Test
    void testFilterItemsByMonthZeroMatches() {
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByMonth(2, 2019));
    }

    @Test
    void testFilterItemsByMonthMonthMismatch() {
        testListOfItemsTwo.add(itemBook);
        assertEquals(testListOfItemsThree, testTracker.filterItemsByMonth(2, 2020));
    }

    @Test
    void testFilterItemsByMonthYearMismatch() {
        testListOfItemsTwo.add(itemBook);
        assertEquals(testListOfItemsThree, testTracker.filterItemsByMonth(6, 2021));
    }

    @Test
    void testFilterItemsByMonthFullMismatch() {
        testListOfItemsTwo.add(itemBook);
        assertEquals(testListOfItemsThree, testTracker.filterItemsByMonth(7, 2021));
    }

    @Test
    void testFilterItemsByMonthOneMatch() {
        testListOfItemsTwo.add(itemBook);
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByMonth(6, 2020));
    }

    @Test
    void testFilterItemsByMonthMoreMatches() {
        testListOfItemsTwo.add(itemCoffee);
        testListOfItemsTwo.add(itemSalad);
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByMonth(1, 2023));
    }

    @Test
    void testFilterItemsByCategoryCompleteZeroMatch() {
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByCategory("Sports", 2019));
    }

    @Test
    void testFilterItemsByCategoryCategoryMismatch() {
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByCategory("Sports", 2022));
    }

    @Test
    void testFilterItemsByCategoryYearMismatch() {
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByCategory("Education", 2021));
    }

    @Test
    void testFilterItemsByCategoryFullMismatch() {
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByCategory("Education", 2000));
    }

    @Test
    void testFilterItemsByCategoryOneMatch() {
        testListOfItemsTwo.add(itemBook);
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByCategory("Education", 2020));
    }

    @Test
    void testFilterItemsByCategoryMoreMatches() {
        testListOfItemsTwo.add(itemCoffee);
        testListOfItemsTwo.add(itemSalad);
        assertEquals(testListOfItemsTwo, testTracker.filterItemsByCategory("Food", 2023));
    }

    @Test
    void testCalculateGrandTotalZeroMatchEmptyList() {
        testTracker.setListOfItems(testListOfItemsTwo);
        assertEquals(0, testTracker.calculateGrandTotal(6, 2020));
    }

    @Test
    void testCalculateGrandTotalZeroMatch() {
        assertEquals(0, testTracker.calculateGrandTotal(4, 2020));
    }

    @Test
    void testCalculateGrandTotalOneMatch() {
        assertEquals(itemBook.getItemPrice(), testTracker.calculateGrandTotal(6, 2020));
    }

    @Test
    void testCalculateGrandTotalMoreMatch() {
        int total = itemCoffee.getItemPrice() + itemSalad.getItemPrice();
        assertEquals(total, testTracker.calculateGrandTotal(1, 2023));
    }

    @Test
    void testCalculateGrandTotalManyMatch() {
        testTracker.addItem(itemCoffee);
        testTracker.addItem(itemSalad);
        testTracker.addItem(itemSalad);
        int bigTotal = (itemCoffee.getItemPrice() * 2) + (itemSalad.getItemPrice() * 3);
        assertEquals(bigTotal, testTracker.calculateGrandTotal(1, 2023));
    }

    @Test
    void testCalculateGrandTotalAllMatch() {
        testListOfItemsTwo.add(itemCoffee);
        testListOfItemsTwo.add(itemSalad);
        testListOfItemsTwo.add(itemSalad);
        testTracker.setListOfItems(testListOfItemsTwo);
        int newTotal = itemCoffee.getItemPrice() + itemSalad.getItemPrice() * 2;
        assertEquals(newTotal, testTracker.calculateGrandTotal(1, 2023));
    }

    @Test
    void testCalculateTotalByCategoryZeroMatchEmptyList() {
        testTracker.setListOfItems(testListOfItemsTwo);
        assertEquals(0, testTracker.calculateTotalByCategory("Sports", 2019));
    }

    @Test
    void testCalculateTotalByCategoryYearZeroMatch() {
        assertEquals(0, testTracker.calculateTotalByCategory("Food", 2020));
    }

    @Test
    void testCalculateTotalByCategoryCategoryZeroMatch() {
        assertEquals(0, testTracker.calculateTotalByCategory("Sports", 2023));
    }

    @Test
    void testCalculateTotalByCategoryOneMatch() {
        assertEquals(itemHoodie.getItemPrice(), testTracker.calculateTotalByCategory("Clothes", 2022));
    }

    @Test
    void testCalculateTotalByCategoryMoreMatches() {
        int categoryTotal = itemCoffee.getItemPrice() + itemSalad.getItemPrice();
        assertEquals(categoryTotal, testTracker.calculateTotalByCategory("Food", 2023));
    }

    @Test
    void testCalculateTotalByCategoryManyMatch() {
        testTracker.addItem(itemCoffee);
        testTracker.addItem(itemSalad);
        testTracker.addItem(itemSalad);
        int totalCategory = (itemCoffee.getItemPrice() * 2) + (itemSalad.getItemPrice() * 3);
        assertEquals(totalCategory, testTracker.calculateTotalByCategory("Food", 2023));
    }

    @Test
    void testCalculateTotalByCategoryAllMatch() {
        testListOfItemsTwo.add(itemCoffee);
        testListOfItemsTwo.add(itemSalad);
        testListOfItemsTwo.add(itemSalad);
        testTracker.setListOfItems(testListOfItemsTwo);
        int newTotal = itemCoffee.getItemPrice() + itemSalad.getItemPrice() * 2;
        assertEquals(newTotal, testTracker.calculateTotalByCategory("Food", 2023));
    }
}
