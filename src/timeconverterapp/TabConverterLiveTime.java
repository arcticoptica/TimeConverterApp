package timeconverterapp;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TabConverterLiveTime extends JPanel implements ActionListener {
    
    private JPanel timePanel;
    private JLabel localTimeLabel;
    private JLabel targetTimeLabel;
    private JTextField localTimeField;
    private JTextField targetTimeField;
    private final TabConverter tab;
            
    public TabConverterLiveTime(TabConverter tab) {
        super();
        this.tab = tab;
        initComponents();
        localTimeField.setText(tab.getView().getCntl().getConverter().getCurrentTime());
        new Timer(1000, this).start();
    }
    
    private void initComponents() {
        this.timePanel = new JPanel();
        timePanel.setLayout(new GridLayout(4, 1, 0, 5));
        add(timePanel);
        this.localTimeLabel = new JLabel();
        localTimeLabel.setText("Local Time");
        this.localTimeField = new JTextField();
        localTimeField.setPreferredSize(new Dimension (150, 30));
        this.targetTimeLabel = new JLabel();
        targetTimeLabel.setText("Target Time");
        this.targetTimeField = new JTextField();
        targetTimeField.setPreferredSize(new Dimension (150, 30));
        timePanel.add(localTimeLabel);
        timePanel.add(localTimeField);
        timePanel.add(targetTimeLabel);
        timePanel.add(targetTimeField);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        localTimeField.setText(tab.getView().getCntl().getConverter().getCurrentTime());
        targetTimeField.setText(tab.getView().getCntl().getConverter().getTargetTime());
    }
    
    //https://www.dreamincode.net/forums/topic/317871-date-and-time-display-live/
}
