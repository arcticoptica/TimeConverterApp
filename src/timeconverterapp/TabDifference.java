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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TabDifference extends JPanel {
    
    private JPanel northBackPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    
    private final JLabel blankLabel;
    private final JLabel headerLabel;
    private final JLabel timeOneLabel;
    private final JLabel timeTwoLabel;
    private final JTextField timeOneField;
    private final JTextField timeTwoField;
    private final JComboBox<String> timeOneCombo;
    private final JComboBox<String> timeTwoCombo;
    private final JButton enterButton;
    private final JTextArea outputField;
    private int statusTime;
    private int statusZone;
    
    private final TimeConverterView view;
    
    public TabDifference(TimeConverterView view) {
        super();
        
        this.view = view;
        initWindow();
        
        this.headerLabel = new JLabel();
        headerLabel.setText("Please input as HH:MM");
        this.blankLabel = new JLabel();
        blankLabel.setText("");
        this.timeOneLabel = new JLabel();
        timeOneLabel.setText("Time one");
        this.timeTwoLabel = new JLabel();
        timeTwoLabel.setText("Time two");
        this.timeOneField = new JTextField();
        timeOneField.setPreferredSize(new Dimension(150,30));
        this.timeOneCombo = new JComboBox<>();
        timeOneCombo.setPreferredSize(new Dimension(150,30));
        this.timeTwoField = new JTextField();
        timeTwoField.setPreferredSize(new Dimension(150,30));
        this.timeTwoCombo = new JComboBox<>();
        timeTwoCombo.setPreferredSize(new Dimension(150,30));
        this.enterButton = new JButton("Enter");
        this.outputField = new JTextArea();
        outputField.setPreferredSize(new Dimension(300,60));
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        outputField.setWrapStyleWord(true);
        this.statusTime = 0;
        this.statusZone = 0;
        
        northPanel.add(headerLabel);
        northPanel.add(blankLabel);
        northPanel.add(timeOneLabel);
        northPanel.add(blankLabel);
        northPanel.add(timeOneField);
        northPanel.add(timeOneCombo);
        northPanel.add(timeTwoLabel);
        northPanel.add(blankLabel);
        northPanel.add(timeTwoField);
        northPanel.add(timeTwoCombo);
        centerPanel.add(enterButton);
        southPanel.add(outputField);
        
        initItems();
    }
    
    private void initWindow() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridheight = 5;
        gbc1.weightx = 0.1;
        gbc1.weighty = 0.5;
        gbc1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridy = 5;
        gbc2.weightx = 0.1;
        gbc2.weighty = 0.1;
        gbc2.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridy = 6;
        gbc3.weightx = 0.1;
        gbc3.weighty = 0.5;
        gbc3.fill = GridBagConstraints.BOTH;
        this.northBackPanel = new JPanel();
        this.northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(5, 2, 15, 5));
        this.centerPanel = new JPanel();
        this.southPanel = new JPanel();
        add(northBackPanel, gbc1);
        add(centerPanel, gbc2);
        add(southPanel, gbc3);
        northBackPanel.add(northPanel);
    }
    
    private void initItems() {
        addComboItems(view.getCntl().getDifference().getTimeZoneList());
        outputField.setText("Please enter times and their time zones."
                + "\nHit the Enter/Return key in a time field or click the Enter button to calculate the difference in times.");
    }
    
    public void printOutput(int[] timeDiff, int dayJump) {
        if (statusTime == 1) {
            if (statusZone == 1) {
                setCombosWhite();
                if (timeDiff[0] >= 0) {
                    if (dayJump == 0) {
                        outputField.setText("Time two is " + timeDiff[0] + " hour(s) and " + timeDiff[1] + " minute(s) ahead of time one.");
                    } else if (dayJump == 1) {
                        outputField.setText("Time two is one day, " + timeDiff[0] + " hour(s), and " + timeDiff[1] + " minute(s) ahead of time one.");
                    }
                } else {
                    if (dayJump == 0) {
                        outputField.setText("Time two is " + -(timeDiff[0]) + " hour(s) and " + timeDiff[1] + " minute(s) behind time one.");
                    } else if (dayJump == 1) {
                        outputField.setText("Time two is " + -(timeDiff[0]) + " hour(s) and " + timeDiff[1] + " minute(s) behind time one and on the previous day.");
                    }
                }
            } else {
                outputField.setText("Please select time zones.");
            }
        } else {
            outputField.setText("Please enter times to calculate.");
        }
    }
    
    public int[] grabFieldItems() {
        int[] intArray = new int[4];
        if (timeOneField.getText().split(":").length == 2) {
            String[] tempList = timeOneField.getText().trim().split(":");
            try {
                if (Integer.valueOf(tempList[0]) < 24) {
                    intArray[0] = Integer.valueOf(tempList[0]);
                    timeOneField.setBackground(Color.white);
                    statusTime = 1;
                } else {
                    timeOneField.setBackground(Color.pink);
                    statusTime = 0;
                }
                if (Integer.valueOf(tempList[1]) < 60) {
                    intArray[1] = Integer.valueOf(tempList[1]);
                    timeOneField.setBackground(Color.white);
                    statusTime = 1;
                } else {
                    timeOneField.setBackground(Color.pink);
                    statusTime = 0;
                }
            }
            catch (NumberFormatException e) {
                timeOneField.setBackground(Color.pink);
            }
        } else {
            timeOneField.setBackground(Color.pink);
        }
        if (timeTwoField.getText().split(":").length == 2) {
            String [] tempList = timeTwoField.getText().trim().split(":");
            try {
                if (Integer.valueOf(tempList[0]) < 24) {
                    intArray[2] = Integer.valueOf(tempList[0]);
                    timeTwoField.setBackground(Color.white);
                    statusTime = 1;
                } else {
                    timeTwoField.setBackground(Color.pink);
                    statusTime = 0;
                }
                if (Integer.valueOf(tempList[1]) < 60) {
                    intArray[3] = Integer.valueOf(tempList[1]);
                    timeTwoField.setBackground(Color.white);
                    statusTime = 1;
                } else {
                    timeTwoField.setBackground(Color.pink);
                    statusTime = 0;
                }
            }
            catch (NumberFormatException e) {
                timeTwoField.setBackground(Color.pink);
            }
        } else {
            timeTwoField.setBackground(Color.pink);
        }
        return intArray;
    }
    
    public void addComboItems(String[] list) {
        for (String item:list) {
            this.timeOneCombo.addItem(item);
            this.timeTwoCombo.addItem(item);
        }
    }
    
    public void setButtonListener(ActionListener ae) {
        this.enterButton.addActionListener(ae);
    }
    
    public void setComboListener(JComboBox combo, ActionListener ae) {
        combo.addActionListener(ae);
    }
    
    public void setFieldListener(JTextField field, ActionListener ae) {
        field.addActionListener(ae);
    }
    
    public void setStatusZone(int status) {
        this.statusZone = status;
    }
    
    public void setCombosWhite() {
        this.timeOneCombo.setBackground(Color.white);
        this.timeTwoCombo.setBackground(Color.white);
    }
    
    public JTextField getTimeOneField() {
        return this.timeOneField;
    }
    
    public JTextField getTimeTwoField() {
        return this.timeTwoField;
    }
    
    public JComboBox getTimeOneCombo() {
        return this.timeOneCombo;
    }
    
    public JComboBox getTimeTwoCombo() {
        return this.timeTwoCombo;
    }
    
}
