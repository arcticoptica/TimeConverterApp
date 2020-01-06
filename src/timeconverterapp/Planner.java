package timeconverterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Planner {
    
    private HashMap<String, PlannerEvent> eventMap;
    private List<String> eventList;
    private PlannerEvent upcomingEvent;
    
    private Calendar currentDate;
    private int currentMonth;
    private int currentDay;
    private int currentYear;
    
    private final File file;
    
    public Planner() throws FileNotFoundException {
        this.file = new File("eventList.txt");
        this.eventMap = new HashMap<>();
        this.eventList = new ArrayList<>();
        this.upcomingEvent = new PlannerEvent();
        fillEventMap();
    }
    
    private void fillEventMap() throws FileNotFoundException {
        String[] tempEvents = readFile();
        for (String block : tempEvents) {
            String[] subBlock = block.split("<<>>");
            String title = subBlock[0];
            int month = Integer.valueOf(subBlock[1]);
            int day = Integer.valueOf(subBlock[2]);
            int year = Integer.valueOf(subBlock[3]);
            String desc = subBlock[4];
            eventMap.put(title, new PlannerEvent(title, month, day, year, desc));
            eventList.add(title);
        }
    }
    
    private String[] readFile() throws FileNotFoundException {
        String[] tempEvents = {};
        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                tempEvents = input.split("/////");
            }
        }
        return tempEvents;
    }
    
    public void refreshEventList() throws FileNotFoundException {
        eventMap = new HashMap<>();
        eventList = new ArrayList<>();
        fillEventMap();
    }
    
    private String formatEventPrint(PlannerEvent event) {
        String month = Integer.toString(event.getDate()[0]);
        String day = Integer.toString(event.getDate()[1]);
        String year = Integer.toString(event.getDate()[2]);
        String output = event.getTitle() + "<<>>" + month + "<<>>"
                + day + "<<>>" + year + "<<>>" + event.getDesc() + "/////";
        return output;
    }
    
    public String formatEventRead(PlannerEvent event) {
        String title = event.getTitle();
        String desc = event.getDesc();
        String date = event.getDate()[0] + "/" + event.getDate()[1] + "/"
                + event.getDate()[2];
        String output = "Name: " + title + " Date: " + date + " Description: " + desc;
        return output;
    }
    
    public void addEvent(PlannerEvent event) throws IOException {
        String output = formatEventPrint(event);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.print(output);
        }
        refreshEventList();
    }
    
    public void deleteEvent(PlannerEvent event) throws FileNotFoundException {
        String[] tempEvents = readFile();
        ArrayList<String> targetEvents = new ArrayList<>();
        for (String block : tempEvents) {
            String[] subBlock = block.split("<<>>");
            String title = subBlock[0];
            if (!title.equals(event.getTitle())) {
                targetEvents.add(block + "/////");
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
            targetEvents.forEach((target) -> {
                writer.print(target);
            });
        }
        refreshEventList();
    }
    
    public void editEvent(String oldTitle, PlannerEvent newEvent) throws FileNotFoundException {
        String[] tempEvents = readFile();
        ArrayList<String> targetEvents = new ArrayList<>();
        for (String block : tempEvents) {
            String[] subBlock = block.split("<<>>");
            String title = subBlock[0];
            if (title.equals(oldTitle)) {
                targetEvents.add(formatEventPrint(newEvent));
            } else {
                targetEvents.add(block + "/////");
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
            targetEvents.forEach((target) -> {
                writer.print(target);
            });
        }
        refreshEventList(); //just in case
    }
    
    public PlannerEvent findUpcomingEvent() {
        this.currentDate = Calendar.getInstance();
        this.currentMonth = currentDate.get(Calendar.MONTH) + 1;
        this.currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        this.currentYear = currentDate.get(Calendar.YEAR);
        List<String> targets = new ArrayList<>();
        eventList.forEach((event) -> {
            PlannerEvent temp = eventMap.get(event);
            if (temp.getDate()[2] > currentYear) {
                targets.add(event);
            } else if (temp.getDate()[2] == currentYear) {
                if (temp.getDate()[0] > currentMonth) {
                    targets.add(event);
                } else if (temp.getDate()[0] == currentMonth) {
                    if (temp.getDate()[1] >= currentDay) {
                        targets.add(event);
                    }
                }
            }
        });
        while (targets.size() > 1) {
            for (int i = 0; i < targets.size()-1; i++ ) {
                if (eventMap.get(targets.get(i)).getDate()[2] < eventMap.get(targets.get(i+1)).getDate()[2]) {
                    if (eventMap.get(targets.get(i)).getDate()[0] < eventMap.get(targets.get(i+1)).getDate()[0]) {
                        if (eventMap.get(targets.get(i)).getDate()[1] < eventMap.get(targets.get(i+1)).getDate()[1]) {
                            targets.remove(eventList.get(i+1));
                        } else {
                            targets.remove(eventList.get(i));
                        }
                    } else {
                        targets.remove(eventList.get(i));
                    }
                } else targets.remove(eventList.get(i));
            }
        }
        if (targets.size() > 0) {
            this.upcomingEvent = eventMap.get(targets.get(0));
        } else {
            this.upcomingEvent = null;
        }
        return upcomingEvent;
    }
    
    public String addDummyEvent() throws IOException {
        String newTitle = "Event" + eventList.size();
        PlannerEvent temp = new PlannerEvent(newTitle, 0, 0, 0, "BLANK");
        addEvent(temp);
        return newTitle;
    }
    
    public HashMap<String, PlannerEvent> getEventMap() {
        return this.eventMap;
    }
    
    public List<String> getEventList() {
        return this.eventList;
    }
    
}
