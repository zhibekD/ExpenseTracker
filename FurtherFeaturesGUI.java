package ui;

import model.Item;
import model.SummaryTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Represents a GUI of Expense Tracker Application with further features
// such as filtering by category and year, filtering by month and year,
// as well as saving items and loading saved items.
public class FurtherFeaturesGUI extends ExpenseTrackerGUI {

    private static final String JSON_STORE = "./data/summaryTracker.json";  // Json store location
    private JsonWriter jsonWriter;                                          // Json Writer
    private JsonReader jsonReader;                                          // Json Reader

    private JPanel filterPanel;                                             // Filter Panel
    private JComboBox<String> filterDropdown;                               // Dropdown menu for filter
    private JPanel filterInputPanel;                                        // Filter Input Panel
    private JButton filterButton;                                           // Filter Button
    private ArrayList<Item> filteredItems;                                  // Filtered list of items
    private SummaryTracker summaryTracker;                                  // Summary Tracker
    private JButton saveButton;                                             // Save Button
    private JButton loadButton;                                             // Load Button

    // Constructs the FurtherFeaturesGUI
    public FurtherFeaturesGUI() throws FileNotFoundException {
        super();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadItemsFromJson(JSON_STORE);
        initializeFilterPanel();
        initializeSaveLoadButtons();
    }

    // EFFECTS: initializes the filter panel
    private void initializeFilterPanel() {
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));

        initializeFilterDropdown();

        filterInputPanel = new JPanel();  // Initialize to an empty panel for inputs
        filterPanel.add(new JLabel("Filter by:"), BorderLayout.NORTH);
        filterPanel.add(filterDropdown);
        filterPanel.add(filterInputPanel);
        add(filterPanel, BorderLayout.EAST);

        filterPanel.setPreferredSize(new Dimension(200, getHeight()));
    }

    // MODIFIES: this
    // EFFECTS: initializes the filter dropdown
    private void initializeFilterDropdown() {
        String[] filterOptions = {"Select Filter", "Category and Year", "Month and Year"};
        filterDropdown = new JComboBox<>(filterOptions);
        filterDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onFilterSelection();
            }
        });
        filterPanel.add(filterDropdown);
    }

    // MODIFIES: this
    // EFFECTS: called when a filter option is selected from the dropdown
    //          provides filtering options that can be selected
    private void onFilterSelection() {
        String selectedFilter = (String) filterDropdown.getSelectedItem();
        filterPanel.remove(filterInputPanel);
        filterPanel.revalidate();
        filterPanel.repaint();

        if ("Category and Year".equals(selectedFilter)) {
            initializeCategoryAndYearFilterPanel();
        } else if ("Month and Year".equals(selectedFilter)) {
            initializeMonthAndYearFilterPanel();
        }

        filterPanel.add(filterInputPanel, BorderLayout.CENTER);
        filterPanel.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: initializes the category and year filter panel
    private void initializeCategoryAndYearFilterPanel() {
        filterInputPanel = new JPanel(new GridLayout(3, 2));
        JTextField categoryField = new JTextField();
        JTextField yearField = new JTextField();
        filterInputPanel.add(new JLabel("Category: "));
        filterInputPanel.add(categoryField);
        filterInputPanel.add(new JLabel("Year: "));
        filterInputPanel.add(yearField);

        filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String category = categoryField.getText();
                int year = Integer.parseInt(yearField.getText());
                filterByCategoryAndYear(category, year);
            }
        });

        filterInputPanel.add(new JLabel());
        filterInputPanel.add(filterButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes the month and year filter panel
    private void initializeMonthAndYearFilterPanel() {
        filterInputPanel = new JPanel(new GridLayout(3, 2));
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();
        filterInputPanel.add(new JLabel("Month (1-12): "));
        filterInputPanel.add(monthField);
        filterInputPanel.add(new JLabel("Year: "));
        filterInputPanel.add(yearField);

        filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int month = Integer.parseInt(monthField.getText());
                int year = Integer.parseInt(yearField.getText());
                filterByMonthAndYear(month, year);
            }
        });

        filterInputPanel.add(new JLabel());
        filterInputPanel.add(filterButton);
    }

    // MODIFIES: this
    // EFFECTS: filters the item list by category and year and displays the filtered items
    private void filterByCategoryAndYear(String category, int year) {
        filteredItems = summaryTracker.filterItemsByCategory(category, year);
        updateItemList();
    }

    // MODIFIES: this
    // EFFECTS: filters the item list by month and year and displays the filtered items
    private void filterByMonthAndYear(int month, int year) {
        filteredItems = summaryTracker.filterItemsByMonth(month, year);
        updateItemList();
    }

    // EFFECTS: updates the item list display with the filtered items
    private void updateItemList() {
        getItemListModel().clear();
        for (Item item : filteredItems) {
            getItemListModel().addElement(item);
        }
        itemJList.setSelectedIndex(-1);  // Clear the selection after filtering
    }

    // MODIFIES: this
    // EFFECTS: initializes a button that will load items
    private void initializeSaveLoadButtons() {
        initializeSaveButton();
        initializeLoadButton();
    }

    // MODIFIES: this
    // EFFECTS: initializes a button that will save items
    private void initializeSaveButton() {
        saveButton = new JButton("Save Items");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveItemsToJson(JSON_STORE);
            }
        });

        JPanel buttonPanel = createButtonPanel(saveButton);
        filterPanel.add(buttonPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: initializes a button that will load items
    private void initializeLoadButton() {
        loadButton = new JButton("Load Items");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadButtonPressed = true;
                loadItemsFromJson(JSON_STORE);
            }
        });

        JPanel buttonPanel = createButtonPanel(loadButton);
        filterPanel.add(buttonPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for buttons
    private JPanel createButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: saves the list of items to a JSON file
    private void saveItemsToJson(String fileName) {
        try {
            JsonWriter jsonWriter = new JsonWriter(fileName);
            jsonWriter.open();

            if (!loadButtonPressed) {
                summaryTracker.setListOfItems(getItemList());
            } else {
                summaryTracker = new SummaryTracker(getItemList());
            }

            jsonWriter.write(summaryTracker);
            jsonWriter.close();

            System.out.println(summaryTracker.getListOfItems());
            System.out.println("Saved " + "summary tracker" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads the list of items from a JSON file
    private void loadItemsFromJson(String filePath) {
        if (loadButtonPressed) {
            try {
                JsonReader jsonReader = new JsonReader(filePath);
                summaryTracker = jsonReader.read();

                getItemList().clear();
                getItemList().addAll(summaryTracker.getListOfItems());

                for (Item item : getItemList()) {
                    getItemListModel().addElement(item);
                }

            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
                e.printStackTrace();
            } finally {
                // Reset the loadButtonPressed
                loadButtonPressed = false;
            }
        }
    }


}

