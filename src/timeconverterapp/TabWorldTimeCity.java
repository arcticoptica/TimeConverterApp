package timeconverterapp;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TabWorldTimeCity extends JPanel {
    
    private final String name;
    private final int offset;
    private int gmtHour;
    private int gmtMinute;
    private int targetHour;
    
    private JPanel nodePanel;
    private JLabel nodeName;
    private JTextField nodeField;
    
    public TabWorldTimeCity(String name, int offset) {
        super();
        this.name = name;
        this.offset = offset;
        
        initWindow();
    }
    
    private void initWindow() {
        this.nodePanel = new JPanel();
        nodePanel.setLayout(new GridLayout(2, 1, 0, 5));
        this.nodeName = new JLabel();
        nodeName.setText(this.name);
        this.nodeField = new JTextField();
        nodeField.setPreferredSize(new Dimension (150, 30));
        add(nodePanel);
        nodePanel.add(nodeName);
        nodePanel.add(nodeField);
    }
    
    public void setGMTTime(int[] array) {
        this.gmtHour = array[0];
        this.gmtMinute = array[1];
        this.targetHour = gmtHour + offset;
        if (targetHour > 23) {
            targetHour -= 24;
        } else if (targetHour < 0) {
            targetHour += 24;
        }
        this.nodeField.setText(String.format("%02d:%02d", targetHour, gmtMinute));
    }
    
}
