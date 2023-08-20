# Expense Tracker Application

## Track and manage your own expenses
<p> A helpful tool for anyone who would like to track and manage their expenses.
Users are able to input the amount of money they have spent on each item. 
This application allows users to categorize their expenses and see monthly report
with a total amount spent by category during a year and the grand total amount spent during a month.
It a truly handy and interesting application project for me to stay on track of my personal 
expenses and learn how to manage them better. </p>

A *user stories* list:
- As a user, I want to be able to add an item to the list. The panel must display all items that have been added.
- As a user, I want to be able to remove an item from the list. Once removed, updated list
  should be displayed on the panel.
- As a user, I want to be able to set the name, price, category and date for an item
- As a user, I want to be able to view a list of items so far with name, price.
  The panel must display them.
- As a user, I want to be able to filter my list items by category in certain year. The panel must have a dropdown menu
  with two filtering options, and once an option was selected further have a box for inputs.
- As a user, I want to be able to filter my list of items by month the item was purchased in, during a certain year.
  The panel must have a dropdown menu with two filtering options, and once an option was selected further have 
  a box for inputs.
- As a user, I want to be able to see the grand total amount spend by category during a year.
- As a user, I want to be able to see the total amount of money spent in a specific month during a certain year
- As a user, I want to be able to save my list of items to file. 
  The panel should have a save button that saves data.
- As a user, I want to be able to load my list of items from file.
  The panel should have a load button that loads data.

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by filling out the fields for an item you wish
  to add, and then clicking on "Add Expense" labelled button. If you wish to remove a certain item from the list, 
  simply select an item by clicking on it and clicking on "Remove Expense" labelled button.
- You can generate the second required action related to adding Xs to a Y by first resizing the window to the right, 
  then you will be able to see the further features of this application such as filtering, saving and loading.
  Now you are be able to see the Dropdown menu too. By clicking on the Dropdown menu labelled "Select Filter", 
  you will see two filtering options available to you. You can choose the one you wish to use, and a panel for 
  needed inputs will appear. You now can select what year, or category, or month you want to filter for and fill out
  these boxes. And finally, click "Filter" labelled button, and the application will filter out your list of 
  items and display results on the panel.
- You can locate my visual component by running the application and see the button icons added to "Save Expense" and
  "Remove Expense" labelled buttons, as well as the colors added to differentiate them.
- You can save the state of my application by first resizing the window to the right, then you will be able to see
  the further features of this application such as filtering, saving and loading. First you need to click on the
  "Load items" labelled button to let the application know where to store your items.
  Then, by pressing on the "Save Items" labelled button you can save your items you have added to your list.
- You can reload the state of my application by first resizing the window to the right, then you will be able to see
  the further features of this application such as filtering, saving and loading.
  By pressing on the "Load Items" labelled button you can load your items you have added to your list earlier.

# Phase 4: Task 2

## Sample 1 with removing
- Logged Events:
- Fri Aug 11 02:49:10 PDT 2023
- Item created: book
- Fri Aug 11 02:49:10 PDT 2023
- Item created: cake
- Fri Aug 11 02:49:10 PDT 2023
- Item created: book
- Fri Aug 11 02:49:10 PDT 2023
- Item added: book
- Fri Aug 11 02:49:10 PDT 2023
- Item created: cake
- Fri Aug 11 02:49:10 PDT 2023
- Item added: cake
- Fri Aug 11 02:49:11 PDT 2023
- Item removed: cake

## Sample 2 with filtering 
- Logged Events:
- Fri Aug 11 02:54:53 PDT 2023
- Item created: book
- Fri Aug 11 02:54:53 PDT 2023
- Item created: book
- Fri Aug 11 02:54:53 PDT 2023
- Item added: book
- Fri Aug 11 02:55:10 PDT 2023
- Item created: cake
- Fri Aug 11 02:55:22 PDT 2023
- Filtered items by month and year.

# Phase 4: Task 3

If I had more time to work on the Expense Tracker Application, one potential refactoring I would consider is to 
separate classes by their responsibilities, for instance, I could separate SummaryTracker class into two classes, 
the one that will be responsible for filtering and the other one be responsible for calculations. I would also consider
refactoring in ui package classes. At the moment, those classes have various responsibilities that handle adding, 
removing items, as well as filtering, saving and loading. This application has two filtering and calculations options 
and both have quite a lot of repeating code that could have also been abstracted out. Also, I found that having only 
ExpenseTrackerGUI and FurtherFeaturesGUI classes made things code a bit complicated and hard to read, hence I think 
refactoring would improve the readability and performance of the application overall.