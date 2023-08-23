package model.src.main.ui;

import model.src.main.model.EventLog;
import model.src.main.model.Event;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            FurtherFeaturesGUI gui = new FurtherFeaturesGUI();

            // to print logged events on quit
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                printLoggedEvents();
            }));

        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    // EFFECTS: prints all logged events
    private static void printLoggedEvents() {
        EventLog eventLog = EventLog.getInstance();
        System.out.println("Logged Events:");
        for (Event event : eventLog) {
            System.out.println(event);
        }
    }
}