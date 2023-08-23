package model.src.main.persistence;

import model.src.main.model.SummaryTracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.src.main.model.Date;
import model.src.main.model.Item;
import org.json.*;

// Represents a reader that reads summary tracker from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads summary tracker from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public SummaryTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSummaryTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private SummaryTracker parseSummaryTracker(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfItems");
        ArrayList<Item> listOfItems = parseItems(jsonArray);
        SummaryTracker st = new SummaryTracker(listOfItems);
        addItems(st, jsonObject);
        return st;
    }

    // EFFECTS: parses list of items from JSON array and returns it
    private ArrayList<Item> parseItems(JSONArray jsonArray) {
        ArrayList<Item> listOfItems = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            listOfItems.add(parseItem(nextItem));
        }
        return listOfItems;
    }


    // EFFECTS: parses Item from JSON object and returns it
    private Item parseItem(JSONObject jsonObject) {
        String itemName = jsonObject.getString("itemName");
        String itemCategory = jsonObject.getString("itemCategory");
        int itemPrice = jsonObject.getInt("itemPrice");
        JSONObject dateJson = jsonObject.getJSONObject("itemDate");
        Date itemDate = parseDate(dateJson);
        return new Item(itemName, itemPrice, itemCategory, itemDate);
    }

    private Date parseDate(JSONObject jsonObject) {
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        int year = jsonObject.getInt("year");
        return new Date(month, day, year);
    }

    // MODIFIES: st
    // EFFECTS: parses items from JSON object and adds them to summary tracker
    private void addItems(SummaryTracker st, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfItems");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(st, nextItem);
        }
    }

    // MODIFIES: st
    // EFFECTS: parses item from JSON object and adds it to summary tracker
    private void addItem(SummaryTracker st, JSONObject jsonObject) {
        String itemName = jsonObject.getString("itemName");
        int itemPrice = jsonObject.getInt("itemPrice");
        String itemCategory = jsonObject.getString("itemCategory");
        JSONObject dateJson = jsonObject.getJSONObject("itemDate");
        Date itemDate = parseDate(dateJson);
        Item item = new Item(itemName, itemPrice, itemCategory, itemDate);

        st.addItem(item);
    }

}