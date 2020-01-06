package timeconverterapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TabPlannerDetail extends JFrame {
    
    private JPanel northBackPanel;
    private JPanel centerBackPanel;
    private JPanel southBackPanel;
    private JPanel buttonBackPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel buttonPanel;
    
    private final JButton saveButton;
    private final JButton deleteButton;
    private final JButton cancelButton;
    private final JTextField titleField;
    private final JTextField monthField;
    private final JTextField dayField;
    private final JTextField yearField;
    private final JTextArea descArea;
    private final JLabel titleLabel;
    private final JLabel monthLabel;
    private final JLabel dayLabel;
    private final JLabel yearLabel;
    private final JLabel descLabel;
    
    private String selection;
    
    private final Controller cntl;
    
    public TabPlannerDetail(Controller cntl) {
        super();
        
        this.cntl = cntl;
        initWindow();
        
        this.saveButton = new JButton("Save event");
        this.deleteButton = new JButton("Delete event");
        this.cancelButton = new JButton("Cancel/Back");
        this.titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(150, 30));
        this.monthField = new JTextField();
        monthField.setPreferredSize(new Dimension(40, 30));
        this.dayField = new JTextField();
        dayField.setPreferredSize(new Dimension(40, 30));
        this.yearField = new JTextField();
        yearField.setPreferredSize(new Dimension(80, 30));
        this.descArea = new JTextArea();
        descArea.setPreferredSize(new Dimension(300, 80));
        this.titleLabel = new JLabel("Event Name: ");
        this.monthLabel = new JLabel("Month: ");
        this.dayLabel = new JLabel("Day: ");
        this.yearLabel = new JLabel("Year: ");
        this.descLabel = new JLabel("Event info");
        northPanel.add(titleLabel);
        northPanel.add(titleField);
        centerPanel.add(monthLabel);
        centerPanel.add(monthField);
        centerPanel.add(dayLabel);
        centerPanel.add(dayField);
        centerPanel.add(yearLabel);
        centerPanel.add(yearField);
        southPanel.add(descLabel);
        southPanel.add(descArea);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
    }
    
    private void initWindow() {
        setTitle("Event details");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridy = 3;
        gbc3.weightx = 0.1;
        gbc3.weighty = 0.5;
        gbc3.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridy = 5;
        gbc4.weightx = 0.1;
        gbc4.weighty = 0.1;
        gbc4.fill = GridBagConstraints.BOTH;
        this.northBackPanel = new JPanel();
        this.centerBackPanel = new JPanel();
        this.southBackPanel = new JPanel();
        this.buttonBackPanel = new JPanel();
        this.northPanel = new JPanel();
        this.centerPanel = new JPanel();
        this.southPanel = new JPanel();
        this.buttonPanel = new JPanel();
        add(northBackPanel, gbc1);
        add(centerBackPanel, gbc2);
        add(southBackPanel, gbc3);
        add(buttonBackPanel, gbc4);
        northBackPanel.add(northPanel);
        centerBackPanel.add(centerPanel);
        southBackPanel.add(southPanel);
        buttonBackPanel.add(buttonPanel);
    }
    
    public void showEvent(String title) throws IOException {
        this.selection = title;
        loadFields();
    }
    
    public PlannerEvent packEvent() {
        PlannerEvent tempEvent = new PlannerEvent();
        tempEvent.setTitle(titleField.getText());
        int[] tempDate = new int[3];
        try {
            if (Integer.valueOf(monthField.getText()) <= 12) {
                tempDate[0] = Integer.valueOf(monthField.getText());
            } else {
                monthField.setBackground(Color.pink);
            }
            if (Integer.valueOf(dayField.getText()) <= 31) {
                tempDate[1] = Integer.valueOf(dayField.getText());
            } else {
                dayField.setBackground(Color.pink);
            }
            if (Integer.valueOf(yearField.getText()) >= 0) {
                tempDate[2] = Integer.valueOf(yearField.getText());
            } else {
                yearField.setBackground(Color.pink);
            }
        }
        catch (NumberFormatException e) {
            monthField.setBackground(Color.pink);
            dayField.setBackground(Color.pink);
            yearField.setBackground(Color.pink);
        }
        tempEvent.setDate(tempDate);
        tempEvent.setDesc(descArea.getText());
        return tempEvent;
    }
    
    public void loadFields() {
        this.titleField.setText(selection);
        this.monthField.setText(Integer.toString(cntl.getPlanner().getEventMap().get(selection).getDate()[0]));
        this.dayField.setText(Integer.toString(cntl.getPlanner().getEventMap().get(selection).getDate()[1]));
        this.yearField.setText(Integer.toString(cntl.getPlanner().getEventMap().get(selection).getDate()[2]));
        this.descArea.setText(cntl.getPlanner().getEventMap().get(selection).getDesc());
    }
    
    public void clearFields() {
        this.titleField.setText("");
        this.monthField.setText("");
        this.dayField.setText("");
        this.yearField.setText("");
        this.descArea.setText("");
    }
    
    public void setSaveButtonListener(ActionListener ae) {
        this.saveButton.addActionListener(ae);
    }
    
    public void setDeleteButtonListener(ActionListener ae) {
        this.deleteButton.addActionListener(ae);
    }
    
    public void setCancelButtonListener(ActionListener ae) {
        this.cancelButton.addActionListener(ae);
    }
    
    public String getSelection() {
        return this.selection;
    }
    
}
