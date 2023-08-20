package model;

// Represents the date with month, day, and year as integers.
// Converts them into the string with MM-DD-YYYY format.

import org.json.JSONObject;
import persistence.Writable;

public class Date implements Writable {

    private int month;    // month, an integer in 1 < int < 12 range
    private int day;      // day, an integer in 1 < int < 31 range
    private int year;     // year, 4-digit positive integer

    /*
     * REQUIRES: month, day are positive integers with min value of 01,
     *           year is positive 4-digit
     * EFFECTS: month is set to month and day is set to day,
     *          year is set to year, all are positive integers
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /*
     * REQUIRES: only positive values
     * MODIFIES: this
     * EFFECTS: converts date into MM-DD-YYYY format
     */
    public String getDate() {
        String monthString = betterFormat(month);
        String dayString = betterFormat(day);
        return monthString + "-" + dayString + "-" + year;
    }

    /*
     * REQUIRES: i > 0
     * MODIFIES: this
     * EFFECTS: converts int to string, if int < 10
     *          returns converted int to string and adds "0" before it,
     *          else returns converted int to string
     */
    public String betterFormat(int i) {
        if (i < 10) {
            return "" + "0" + i;
        } else {
            return "" + i;
        }
    }

    // REQUIRES: 1 <= month <= 12
    // MODIFIES: this
    // EFFECTS: sets the month an item was purchased
    public void setMonth(int month) {
        this.month = month;
    }

    // REQUIRES: 1 <= day <= 31
    // MODIFIES: this
    // EFFECTS: sets the day an item was purchased
    public void setDay(int day) {
        this.day = day;
    }

    // REQUIRES: 1000 <= year
    // MODIFIES: this
    // EFFECTS: sets the year an item was purchased
    public void setYear(int year) {
        this.year = year;
    }

    // EFFECTS: returns the month an item was purchased
    public int getMonth() {
        return month;
    }

    // EFFECTS: returns the day an item was purchased
    public int getDay() {
        return day;
    }

    // EFFECTS: returns the year an item was purchased
    public int getYear() {
        return year;
    }


    // EFFECTS: creates new JSONObject and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("month", month);
        json.put("day", day);
        json.put("year", year);
        return json;
    }

}
