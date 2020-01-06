package timeconverterapp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    
    private final TimeConverterView view;
    private final Converter converter;
    private final Difference difference;
    private final WorldTime worldTime;
    private final Planner planner;
    
    public Controller() throws FileNotFoundException {
        this.converter = new Converter();
        this.difference = new Difference();
        this.worldTime = new WorldTime();
        this.planner = new Planner();
        this.view = new TimeConverterView(this);
        this.view.setVisible(true);
        actionInit();
    }
    
    private void actionInit() {
        //TabConverter - targetZoneCombo
        view.getTabConverter().setComboListener((ActionEvent e) -> {
            int tempIndex = view.getTabConverter().getTargetZoneCombo().getSelectedIndex();
            view.getTabConverter().setTargetOffset(tempIndex);
        });
        //TabConverter - convertButton
        view.getTabConverter().setButtonListener((ActionEvent e) -> {
            converter.calcTargetOffset(view.getTabConverter().getTargetOffset());
            view.getTabConverter().getTargetZoneCombo().setBackground(Color.white);
        });
        //TabDifference - timeOneField
        view.getTabDifference().setFieldListener(view.getTabDifference().getTimeOneField(), (ActionEvent e) -> {
            int[] tempArray = view.getTabDifference().grabFieldItems();
            difference.setTimeValues(tempArray);
            difference.calcZoneDiff();
            difference.calcTimeDiff();
            view.getTabDifference().printOutput(difference.getTimeDiff(), difference.getStatusDayJump());
        });
        //TabDifference - timeTwoField
        view.getTabDifference().setFieldListener(view.getTabDifference().getTimeTwoField(), (ActionEvent e) -> {
            int[] tempArray = view.getTabDifference().grabFieldItems();
            difference.setTimeValues(tempArray);
            difference.calcZoneDiff();
            difference.calcTimeDiff();
            view.getTabDifference().printOutput(difference.getTimeDiff(), difference.getStatusDayJump());
        });
        //TabDifference - enterButton
        view.getTabDifference().setButtonListener((ActionEvent e) -> {
            int[] tempArray = view.getTabDifference().grabFieldItems();
            difference.setTimeValues(tempArray);
            difference.calcZoneDiff();
            difference.calcTimeDiff();
            view.getTabDifference().printOutput(difference.getTimeDiff(), difference.getStatusDayJump());
        });
        //TabDifference - timeOneCombo
        view.getTabDifference().setComboListener(view.getTabDifference().getTimeOneCombo(), (ActionEvent e) -> {
            int tempIndexOne = view.getTabDifference().getTimeOneCombo().getSelectedIndex();
            int tempIndexTwo = view.getTabDifference().getTimeTwoCombo().getSelectedIndex();
            difference.setTimeZones(tempIndexOne, tempIndexTwo);
            view.getTabDifference().setStatusZone(1);
        });
        //TabDifference - timeTwoCombo
        view.getTabDifference().setComboListener(view.getTabDifference().getTimeTwoCombo(), (ActionEvent e) -> {
            int tempIndexOne = view.getTabDifference().getTimeOneCombo().getSelectedIndex();
            int tempIndexTwo = view.getTabDifference().getTimeTwoCombo().getSelectedIndex();
            difference.setTimeZones(tempIndexOne, tempIndexTwo);
            view.getTabDifference().setStatusZone(1);
        });
        //TabWorldTime - addRemoveButton
        view.getTabWorldTime().setButtonListener((ActionEvent e) -> {
            view.getTabWorldList().setVisible(true);
            view.getTabWorldList().clearSelection();
        });
        //TabWorldTimeList - doneButton
        view.getTabWorldList().setButtonListener((ActionEvent e) -> {
            view.getTabWorldList().origButtonColor();
            if (view.getTabWorldList().addSelections()) {
                view.getTabWorldList().setVisible(false);
                view.getTabWorldTime().getLiveTime().updateNodes(view.getTabWorldList().getSelections());
            } else {
                view.getTabWorldList().errorButtonColor();
            }
        });
        //TabPlanner - viewButton
        view.getTabPlanner().setViewButtonListener((ActionEvent e) -> {
            view.getTabPlannerList().refreshList();
            view.getTabPlannerList().setVisible(true);
        });
        //TabPlanner - addButton
        view.getTabPlanner().setAddButtonListener((ActionEvent e) -> {
            try {
                String title = planner.addDummyEvent();
                view.getTabPlannerDetail().showEvent(title);
                view.getTabPlannerDetail().setVisible(true);
            } catch (IOException ex) {
                System.out.println("TabPlannerDetail addEvent IOException");
            }
        });
        //TabPlanner - viewUpcomingButton
        view.getTabPlanner().setUpcomingButtonListener((ActionEvent e) -> {
            if (planner.findUpcomingEvent() != null) {
                try {
                    view.getTabPlannerDetail().showEvent(planner.findUpcomingEvent().getTitle());
                    view.getTabPlannerDetail().setVisible(true);
                } catch (IOException ex) {
                    System.out.println("TabPlannerDetail showEvent IOException");
                }
            }
        });
        //TabPlannerList - doneButton
        view.getTabPlannerList().setDoneListener((ActionEvent e) -> {
            view.getTabPlannerList().setVisible(false);
            view.getTabPlannerList().origButtonColor();
            view.getTabPlannerList().clearSelection();
        });
        //TabPlannerList - viewButton
        view.getTabPlannerList().setViewListener((ActionEvent e) -> {
            view.getTabPlannerList().origButtonColor();
            if (view.getTabPlannerList().addSelection()) {
                try {
                    view.getTabPlannerList().setVisible(false);
                    view.getTabPlannerDetail().showEvent(view.getTabPlannerList().getSelection());
                    view.getTabPlannerDetail().setVisible(true);
                } catch (IOException ex) {
                    System.out.println("TabPlannerDetail showEvent IOException");
                }
            } else {
                view.getTabPlannerList().errorButtonColor();
            }
        });
        //TabPlannerDetail - saveButton
        view.getTabPlannerDetail().setSaveButtonListener((ActionEvent e) -> {
            try {
                String orig = view.getTabPlannerDetail().getSelection();
                PlannerEvent tempEvent = view.getTabPlannerDetail().packEvent();
                planner.editEvent(orig, tempEvent);
                view.getTabPlanner().refreshUpcoming();
                view.getTabPlannerDetail().setVisible(false);
                view.getTabPlannerDetail().clearFields();
            } catch (FileNotFoundException ex) {
                System.out.println("Planner editEvent FileNotFoundException");
            }
        });
        //TabPlannerDetail - deleteButton
        view.getTabPlannerDetail().setDeleteButtonListener((ActionEvent e) -> {
            String target = view.getTabPlannerDetail().getSelection();
            try {
                planner.deleteEvent(planner.getEventMap().get(target));
                view.getTabPlanner().refreshUpcoming();
                view.getTabPlannerDetail().setVisible(false);
                view.getTabPlannerDetail().clearFields();
            } catch (FileNotFoundException ex) {
                System.out.println("Planner deleteEvent FileNotFoundException");
            }
        });
        //TabPlannerDetail - cancelButton
        view.getTabPlannerDetail().setCancelButtonListener((ActionEvent e) -> {
            view.getTabPlannerDetail().setVisible(false);
            view.getTabPlannerDetail().clearFields();
        });
    }
    
    public Converter getConverter() {
        return converter;
    }
    
    public Difference getDifference() {
        return difference;
    }
    
    public WorldTime getWorldTime() {
        return worldTime;
    }
    
    public Planner getPlanner() {
        return planner;
    }
    
}
