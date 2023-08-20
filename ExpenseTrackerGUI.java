package ui;

import model.Date;
import model.Item;
import model.SummaryTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a GUI of Expense Tracker Application with
// add and remove buttons features
public class ExpenseTrackerGUI extends JFrame {

    private ArrayList<Item> itemList;               // item list
    private DefaultListModel<Item> itemListModel;   // model item list
    protected JList<Item> itemJList;                // item JList
    private JTextField itemJName;                   // item name
    private JTextField itemJPrice;                  // item price
    private JTextField itemJCategory;               // item category
    private JTextField itemJDate;                   // item date
    private JPanel itemDetailPanel;                 // item detail panel
    private SummaryTracker summaryTracker;          // summary tracker
    protected boolean loadButtonPressed = false;    // is load button pressed

    // Constructs ExpenseTrackerGUI
    public ExpenseTrackerGUI() {
        loadButtonPressed = false;
        itemDetailPanel = new JPanel(new FlowLayout());
        initializeGuiFields();
        createGui();
        createButtonPanel();
        setUpFrame();
    }

    // EFFECTS: returns item list
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    // EFFECTS: returns itemListModel
    public DefaultListModel<Item> getItemListModel() {
        return itemListModel;
    }

    // MODIFIES: this
    // EFFECTS: initializes the fields for GUI
    private void initializeGuiFields() {
        this.itemList = new ArrayList<>();
        this.summaryTracker = new SummaryTracker(itemList);
        this.itemListModel = new DefaultListModel<>();
        this.itemJList = new JList<>(itemListModel);
        this.itemJName = new JTextField();
        this.itemJPrice = new JTextField();
        this.itemJCategory = new JTextField();
        this.itemJDate = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: creates a GUI with input panel, scroll pane, button panel,
    //          fits and places them in certain regions
    protected void createGui() {
        JPanel inputPanel = createInputPanel();
        JScrollPane scrollPane = new JScrollPane(itemJList);
        JPanel buttonPanel = createButtonPanel();

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(itemDetailPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: creates an input panel for item fields with a button that adds an item
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemJName);
        inputPanel.add(new JLabel("Item Price:"));
        inputPanel.add(itemJPrice);
        inputPanel.add(new JLabel("Item Category:"));
        inputPanel.add(itemJCategory);
        inputPanel.add(new JLabel("Date of Purchase:"));
        inputPanel.add(itemJDate);
        return inputPanel;
    }

    // MODIFIES: JPanel
    // EFFECTS: creates a panel with addButton AND removeButton
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createAddButton());
        buttonPanel.add(createRemoveButton());
        return buttonPanel;
    }

    // EFFECTS: creates a button that adds expenses
    //          with button icon and green colored outer color
    private JButton createAddButton() {
        JButton addButton = new JButton("Add Expense");
        ImageIcon backgroundImage = new ImageIcon("data/favicon.png");
        addButton.setIcon(backgroundImage);
        addButton.setBackground(new Color(127, 141, 106));
        addButton.setOpaque(true);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        return addButton;
    }

    // EFFECTS: creates a button that removes selected expense
    //          with button icon and red colored outer color
    private JButton createRemoveButton() {
        JButton removeButton = new JButton("Remove Expense");
        ImageIcon backgroundImage = new ImageIcon("data/favicon.png");
        removeButton.setIcon(backgroundImage);
        removeButton.setBackground(new Color(178, 112, 107));
        removeButton.setOpaque(true);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeExpense();
            }
        });

        return removeButton;
    }

    // MODIFIES: this
    // EFFECTS: creates a frame with title, sets up its size, close operation,
    //          location, and visibility
    private void setUpFrame() {
        setTitle("Expense Tracker");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this, itemList, itemListModel, itemJName, itemJPrice,
    //           itemJCategory, itemJDate
    // EFFECTS: adds an item with its fields to the list of items,
    //          clears all text fields once item was added to the list
    private void addExpense() {
        String itemName = itemJName.getText();
        int itemPrice = Integer.parseInt(itemJPrice.getText());
        String itemCategory = itemJCategory.getText();
        String itemDateText = itemJDate.getText();

        Item item = new Item(itemName, itemPrice, itemCategory, parseDate(itemDateText));
        itemList.add(item);
        summaryTracker.setListOfItems(itemList);
        itemListModel.addElement(item);

        itemJName.setText("");
        itemJPrice.setText("");
        itemJCategory.setText("");
        itemJDate.setText("");
    }

    // REQUIRES: itemDateText should be in MM-DD-YYYY format
    // EFFECTS: parses the date from the text field into the Date object and returns it
    private Date parseDate(String itemDateText) {
        String[] dateParts = itemDateText.split("-");
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        Date itemDate = new Date(month, day, year);
        return itemDate;
    }


    // REQUIRES: itemList should contain valid Item,
    //           itemJList should display list of expenses
    //           in the same order as itemList
    // MODIFIES: itemList, itemJList
    // EFFECTS: removes selected item from the GUI's itemJList and
    //          its data structure itemList
    private void removeExpense() {
        int selectedIndex = itemJList.getSelectedIndex();
        if (selectedIndex != -1) {
            Item removedItem = itemList.remove(selectedIndex);
            summaryTracker.removeItem(removedItem);
            itemListModel.remove(selectedIndex);
        }
    }

}
