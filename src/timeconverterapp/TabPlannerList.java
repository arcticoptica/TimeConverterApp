package timeconverterapp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class TabPlannerList extends JFrame {
    
    private JScrollPane scrollPanel;
    private JPanel backPanel;
    
    private JList<String> eventList;
    private final JButton doneButton;
    private final JButton viewButton;
    private final JLabel instLabel;
    
    private DefaultListModel<String> listModel;
    private String selection;
    
    private final Controller cntl;
    
    public TabPlannerList(Controller cntl) {
        addListItems(cntl.getPlanner().getEventList());
        this.cntl = cntl;
        initWindow();
        
        this.doneButton = new JButton();
        doneButton.setText("Done");
        this.viewButton = new JButton();
        viewButton.setText("View");
        this.instLabel = new JLabel();
        instLabel.setText("Select an event");
        backPanel.add(instLabel);
        backPanel.add(doneButton);
        backPanel.add(viewButton);
    }
    
    private void initWindow() {
        setTitle("Event List");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridheight = 4;
        gbc1.weightx = 0.1;
        gbc1.weighty = 0.5;
        gbc1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridy = 5;
        gbc2.weightx = 0.1;
        gbc2.weighty = 0.1;
        gbc2.fill = GridBagConstraints.BOTH;
        this.backPanel = new JPanel();
        this.scrollPanel = new JScrollPane(eventList);
        add(scrollPanel, gbc1);
        add(backPanel, gbc2);
    }
    
    private void addListItems(List<String> list) {
        if (list.size() > 0) {
            listModel = new DefaultListModel<>();
            list.forEach((item) -> {
                listModel.addElement(item);
            });
            this.eventList = new JList<>(listModel);
            eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.selection = "";
        }
    }
    
    public boolean addSelection() {
        if (eventList.getSelectedValue() != null) {
            selection = eventList.getSelectedValue();
            return true;
        } else {
            return false;
        }
    }
    
    public void clearSelection() {
        this.selection = "";
    }
    
    public void origButtonColor() {
        this.viewButton.setBackground(new JButton().getBackground());
    }
    
    public void errorButtonColor() {
        this.viewButton.setBackground(Color.pink);
    }
    
    public void setDoneListener(ActionListener ae) {
        this.doneButton.addActionListener(ae);
    }
    
    public void setViewListener(ActionListener ae) {
        this.viewButton.addActionListener(ae);
    }
    
    public void refreshList() {
        listModel.removeAllElements();
        List<String> tempList = cntl.getPlanner().getEventList();
        tempList.forEach((item) -> {
            listModel.addElement(item);
        });
    }
    
    public String getSelection() {
        return this.selection;
    }
    
    public int getListSize() {
        return this.eventList.getModel().getSize();
    }
    
}
