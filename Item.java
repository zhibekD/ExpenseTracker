package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Item with name, price, category, and date that can be added to the Item List
// implements Writable
public class Item implements Writable {

    private String itemName;        // name of the item
    private int itemPrice;          // amount of money spent on item in CAD
    private String itemCategory;    // category of an item
    private Date itemDate;          // date when the item was purchased

    private int month;              // month when the item was purchased
    private int day;                // day when the item was purchased
    private int year;               // year when the item was purchased

    /*
     * REQUIRES: itemName, itemCategory have a non-zero length, price > 0
     * EFFECTS: name on item is set to itemName; item price is a
     *          positive integer, item category is set to category,
     *          date is set to date format MM-DD-YYYY
     */
    public Item(String itemName, int itemPrice, String itemCategory, Date itemDate) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;

        this.month = itemDate.getMonth();
        this.day = itemDate.getDay();
        this.year = itemDate.getYear();
        this.itemDate = new Date(month, day, year);

        EventLog.getInstance().logEvent(new Event("Item created: " + itemName));
    }

    @Override
    // EFFECTS: return item name and price in CAD
    public String toString() {
        return getItemName() + " " + getItemPrice() + " CAD";
    }

    // EFFECTS: returns item name
    public String getItemName() {
        return itemName;
    }

    // EFFECTS: returns item price in CAD
    public int getItemPrice() {
        return itemPrice;
    }

    // EFFECTS: returns item category
    public String getItemCategory() {
        return itemCategory;
    }


    // EFFECTS: returns the Date as a String in MM-DD-YYYY format
    public String getDate() {
        return itemDate.getDate();
    }

    // EFFECTS: returns the month the item was purchased
    public int getMonth() {
        return month;
    }

    // EFFECTS: returns the day the item was purchased
    public int getDay() {
        return day;
    }

    // EFFECTS: returns the year the item was purchased
    public int getYear() {
        return year;
    }

    // REQUIRES: non-zero length itemName
    // MODIFIES: this
    // EFFECTS: sets item name
    public void setItemName(String itemName) {
        this.itemName = itemName;

        EventLog.getInstance().logEvent(new Event("Item name updated: " + itemName));
    }

    // MODIFIES: this
    // EFFECTS: sets item price
    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;

        EventLog.getInstance().logEvent(new Event("Item price updated: " + itemName));
    }

    // REQUIRES: non-zero length itemCategory
    // MODIFIES: this
    // EFFECTS: sets item category
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;

        EventLog.getInstance().logEvent(new Event("Item category updated: " + itemName));
    }

    // REQUIRES: month to be 1 <= int <= 12
    //           day to be 1 <= int <= 31
    //           year to be 1000 <= int
    // MODIFIES: this
    // EFFECTS: sets the date item was purchased
    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;

        EventLog.getInstance().logEvent(new Event("Item date updated: " + itemName));
    }

    // EFFECTS: creates new JSONObject and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemName", itemName);
        json.put("itemPrice", itemPrice);
        json.put("itemCategory", itemCategory);
        json.put("itemDate", itemDate.toJson());
        return json;
    }
}
