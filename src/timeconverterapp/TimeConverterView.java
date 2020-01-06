package timeconverterapp;

import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TimeConverterView extends JFrame {
    
    private JTabbedPane mainTabPanel;
    private TabConverter tabConverter;
    private TabDifference tabDifference;
    private TabWorldTime tabWorldTime;
    private TabWorldTimeList tabWorldList;
    private TabPlanner tabPlanner;
    private TabPlannerList tabPlannerList;
    private TabPlannerDetail tabPlannerDetail;
    
    private final Controller cntl;
    
    public TimeConverterView(Controller cntl) throws FileNotFoundException {
        this.cntl = cntl;
        initWindow();
    }
    
    private void initWindow() throws FileNotFoundException {
        setTitle("TimeConverterApp");
        setSize(500, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainTabPanel = new JTabbedPane();
        add(mainTabPanel);
        tabConverter = new TabConverter(this);
        tabDifference = new TabDifference(this);
        tabWorldTime = new TabWorldTime(this);
        tabPlanner = new TabPlanner(this);
        mainTabPanel.add("Converter", tabConverter);
        mainTabPanel.add("Difference", tabDifference);
        mainTabPanel.add("World Clocks", tabWorldTime);
        mainTabPanel.add("Planner", tabPlanner);
        
        tabWorldList = new TabWorldTimeList(cntl);
        tabPlannerList = new TabPlannerList(cntl);
        tabPlannerDetail = new TabPlannerDetail(cntl);
    }
    
    public TabConverter getTabConverter() {
        return this.tabConverter;
    }
    public TabDifference getTabDifference() {
        return this.tabDifference;
    }
    
    public TabWorldTime getTabWorldTime() {
        return this.tabWorldTime;
    }
    
    public TabWorldTimeList getTabWorldList() {
        return this.tabWorldList;
    }
    
    public TabPlanner getTabPlanner() {
        return this.tabPlanner;
    }
    
    public TabPlannerList getTabPlannerList() {
        return this.tabPlannerList;
    }
    
    public TabPlannerDetail getTabPlannerDetail() {
        return this.tabPlannerDetail;
    }
    
    public Controller getCntl() {
        return this.cntl;
    }
}
