package timeconverterapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TabPlanner extends JPanel {
    
    private JPanel northBackPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southBackPanel;
    private JPanel southPanel;
    
    private final JButton viewButton;
    private final JButton addButton;
    private final JButton viewUpcomingButton;
    private final JTextArea eventTextArea;
    
    private final TimeConverterView view;
    
    public TabPlanner(TimeConverterView view) throws FileNotFoundException {
        super();
        
        this.view = view;
        initWindow();
        
        this.viewButton = new JButton("View List");
        this.addButton = new JButton("Add Event");
        this.viewUpcomingButton = new JButton("View Upcoming");
        this.eventTextArea = new JTextArea();
        eventTextArea.setPreferredSize(new Dimension(300, 60));
        eventTextArea.setEditable(false);
        eventTextArea.setLineWrap(true);
        eventTextArea.setWrapStyleWord(true);
        northPanel.add(viewButton);
        northPanel.add(addButton);
        centerPanel.add(viewUpcomingButton);
        southPanel.add(eventTextArea);
        
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
        gbc2.weighty = 0.5;
        gbc2.fill = GridBagConstraints.BOTH;
        this.northBackPanel = new JPanel();
        this.southBackPanel = new JPanel();
        this.northPanel = new JPanel();
        this.centerPanel = new JPanel();
        this.southPanel = new JPanel();
        southBackPanel.setLayout(new GridLayout(2, 1, 0, 5));
        northPanel.setLayout(new GridLayout(2, 1, 0, 15));
        add(northBackPanel, gbc1);
        add(southBackPanel, gbc2);
        northBackPanel.add(northPanel);
        southBackPanel.add(centerPanel);
        southBackPanel.add(southPanel);
    }
    
    private void initItems() throws FileNotFoundException {
        refreshUpcoming();
    }
    
    public void refreshUpcoming() throws FileNotFoundException {
        view.getCntl().getPlanner().refreshEventList();
        if (view.getCntl().getPlanner().getEventList().size() > 0) {
            if (view.getCntl().getPlanner().findUpcomingEvent() != null) {
                String output = view.getCntl().getPlanner().formatEventRead(
                        view.getCntl().getPlanner().findUpcomingEvent());
                eventTextArea.setText("Next Upcoming Event\n" + output);
            } else {
                eventTextArea.setText("No upcoming events");
            }
        } else {
            eventTextArea.setText("No upcoming events");
        }
    }
    
    public void setViewButtonListener(ActionListener ae) {
        this.viewButton.addActionListener(ae);
    }
    public void setAddButtonListener(ActionListener ae) {
        this.addButton.addActionListener(ae);
    }
    public void setUpcomingButtonListener(ActionListener ae) {
        this.viewUpcomingButton.addActionListener(ae);
    }
    
}
