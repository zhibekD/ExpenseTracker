package model.src.main.model;

import org.json.JSONArray;
import org.json.JSONObject;
import model.src.main.persistence.Writable;

import java.util.ArrayList;

// Represents Summary Tracker with ArrayList of Items. Has two filtering options:
// filtering item list by month and year, filtering item list by category and year.
// By using these filtering options, SummaryTracker can make calculation of grand
// total of a month in year, as well as total by category throughout a year.

public class SummaryTracker implements Writable {

    private ArrayList<Item> listOfItems;   // list of items


     // EFFECTS: creates an empty list of items
    public SummaryTracker(ArrayList<Item> listOfItems) {
        this.listOfItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new item to the list of items
    public void addItem(Item item) {
        this.listOfItems.add(item);

        EventLog.getInstance().logEvent(new Event("Item added: " + item.getItemName()));
    }

    // REQUIRES: non-empty listOfItems
    // MODIFIES: this
    // EFFECTS: adds a new item to the list of items
    public void removeItem(Item item) {
        this.listOfItems.remove(item);

        EventLog.getInstance().logEvent(new Event("Item removed: " + item.getItemName()));
    }

    // EFFECTS: returns the list of items
    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    // MODIFIES: this
    // EFFECTS: sets the list of items
    public void setListOfItems(ArrayList<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    // EFFECTS: returns number of items in this list
    public int numOfItems() {
        return listOfItems.size();
    }

    // REQUIRES: 1 <= month <= 12, 0 < year
    // MODIFIES: this
    // EFFECTS: filters a list of items by category with the same year
    public ArrayList<Item> filterItemsByMonth(int month, int year) {
        ArrayList<Item> listByMonth = new ArrayList<Item>();
        for (Item item : getListOfItems()) {
            if ((item.getMonth() == month) && (item.getYear() == year)) {
                listByMonth.add(item);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered items by month and year."));
        return listByMonth;
    }

    // REQUIRES: year > 0
    // MODIFIES: this
    // EFFECTS: filters a list of items by category with the same year
    public ArrayList<Item> filterItemsByCategory(String category, int year) {
        ArrayList<Item> listByCategory = new ArrayList<Item>();
        for (Item item : getListOfItems()) {
            if ((item.getItemCategory().equals(category)) && (item.getYear() == year)) {
                listByCategory.add(item);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered items by category and year."));
        return listByCategory;
    }

    // REQUIRES: 0 < year
    // MODIFIES: this
    // EFFECTS: calculates a grand total of a month in the same year
    public int calculateGrandTotal(int month, int year) {
        int grandTotal = 0;
        for (Item item : filterItemsByMonth(month, year)) {
            grandTotal += item.getItemPrice();
        }
        return grandTotal;
    }

    // REQUIRES: 0 < year
    // MODIFIES: this
    // EFFECTS: calculates a total amount spent by category throughout a year
    public int calculateTotalByCategory(String category, int year) {
        int totalByCategory = 0;
        for (Item item : filterItemsByCategory(category, year)) {
            totalByCategory += item.getItemPrice();
        }
        return totalByCategory;
    }

    // EFFECTS: creates new JSONObject and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfItems", itemsToJson());
        return json;
    }

    // EFFECTS: returns items in this summary tracker as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : listOfItems) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
