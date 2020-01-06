package timeconverterapp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TabWorldTimeLiveTime extends JPanel implements ActionListener{
    
    private JPanel mainPanel;
    private final TabWorldTime tab;
    private ArrayList<TabWorldTimeCity> cityList;
            
    public TabWorldTimeLiveTime(TabWorldTime tab) {
        super();
        this.tab = tab;
        initComponents();
        new Timer(1000, this).start();
    }
    
    private void initComponents() {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 5, 5));
        add(mainPanel);
        cityList = new ArrayList<>();
        
        cityList.add(new TabWorldTimeCity("State College", -5));
        
        cityList.forEach((city) -> {
            mainPanel.add(city);
        });
    }
    
    public void updateNodes(ArrayList<TabWorldTimeCity> array) {
        mainPanel.removeAll();
        cityList = array;
        cityList.forEach((city) -> {
            mainPanel.add(city);
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        cityList.forEach((city) -> {
            city.setGMTTime(tab.getView().getCntl().getWorldTime().getCurrentTime());
        });
    }
    
}
