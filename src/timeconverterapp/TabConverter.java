package timeconverterapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TabConverter extends JPanel {
    
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    
    private final JTextField localZoneField;
    private final JComboBox<String> targetZoneCombo;
    private final JLabel localZoneLabel;
    private final JLabel targetZoneLabel;
    private final JButton convertButton;
    
    private int targetOffset;
    
    private final TimeConverterView view;
    private final TabConverterLiveTime liveTime;
    
    
    public TabConverter(TimeConverterView view) {
        super();
        
        this.view = view;
        initWindow();
        liveTime = new TabConverterLiveTime(this);
        
        this.localZoneLabel = new JLabel();
        localZoneLabel.setText("Local Time Zone");
        this.localZoneField = new JTextField();
        localZoneField.setPreferredSize(new Dimension(200,30));
        localZoneField.setEditable(false);
        this.targetZoneLabel = new JLabel();
        targetZoneLabel.setText("Target Time Zone");
        this.targetZoneCombo = new JComboBox<>();
        targetZoneCombo.setPreferredSize(new Dimension(200,30));
        this.convertButton = new JButton("Convert");
        
        westPanel.add(liveTime);
        eastPanel.add(localZoneLabel);
        eastPanel.add(localZoneField);
        eastPanel.add(targetZoneLabel);
        eastPanel.add(targetZoneCombo);
        southPanel.add(convertButton);
        
        initItems();
    }
    
    private void initWindow() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridheight = 2;
        gbc1.weightx = 0.1;
        gbc1.weighty = 0.5;
        gbc1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridy = 2;
        gbc2.weightx = 0.1;
        gbc2.weighty = 0.1;
        gbc2.fill = GridBagConstraints.BOTH;
        this.northPanel = new JPanel();
        this.westPanel = new JPanel();
        this.eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(4, 1, 0, 5));
        this.southPanel = new JPanel();
        add(northPanel, gbc1);
        northPanel.add(westPanel);
        northPanel.add(eastPanel);
        add(southPanel, gbc2);
    }
    
    private void initItems() {
        localZoneField.setText(view.getCntl().getConverter().getCurrentZone());
        addTargetZoneItems(view.getCntl().getConverter().getTimeZoneList());
    }
    
    public void addTargetZoneItems(String[] list) {
        for (String item:list) {
            this.targetZoneCombo.addItem(item);
        }
    }
    
    public void setTargetOffset(int index) {
        this.targetOffset = index;
    }
    
    public void setComboListener(ActionListener ae) {
        this.targetZoneCombo.addActionListener(ae);
    }
    
    public void setButtonListener(ActionListener ae) {
        this.convertButton.addActionListener(ae);
    }
    
    public JComboBox getTargetZoneCombo() {
        return this.targetZoneCombo;
    }
    
    public int getTargetOffset() {
        return this.targetOffset;
    }
    
    public TimeConverterView getView() {
        return this.view;
    }
}
