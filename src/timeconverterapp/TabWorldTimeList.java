package timeconverterapp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class TabWorldTimeList extends JFrame {
    
    private JScrollPane scrollPanel;
    private JPanel backPanel;
    
    private JList<String> clockList;
    private final JButton doneButton;
    private final JLabel instLabel;
    
    private ArrayList<String> selection;
    
    private final Controller cntl;
    
    public TabWorldTimeList(Controller cntl) {
        this.cntl = cntl;
        addListItems(cntl.getWorldTime().getCityList());
        initWindow();
        
        this.doneButton = new JButton();
        doneButton.setText("Done");
        this.instLabel = new JLabel();
        instLabel.setText("Hold Ctrl to multi select");
        backPanel.add(instLabel);
        backPanel.add(doneButton);
    }
    
    private void initWindow() {
        setTitle("World Clock List");
        setSize(250, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridheight = 5;
        gbc1.weightx = 0.1;
        gbc1.weighty = 0.5;
        gbc1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridy = 6;
        gbc2.weightx = 0.1;
        gbc2.weighty = 0.1;
        gbc2.fill = GridBagConstraints.BOTH;
        this.backPanel = new JPanel();
        this.scrollPanel = new JScrollPane(clockList);
        add(scrollPanel, gbc1);
        add(backPanel, gbc2);
    }
    
    private void addListItems(ArrayList<String> list) {
        String[] tempList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempList[i] = list.get(i);
        }
        this.clockList = new JList<>(tempList);
        clockList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.selection = new ArrayList<>();
    }
    
    public boolean addSelections() {
        List<String> tempList = clockList.getSelectedValuesList();
        if (tempList.size() > 0) {
            tempList.forEach((item) -> {
            selection.add(item);
            });
            return true;
        } else {
            return false;
        }
    }
    
    public void clearSelection() {
        this.selection = new ArrayList<>();
    }
    
    public void origButtonColor() {
        this.doneButton.setBackground(new JButton().getBackground());
    }
    
    public void errorButtonColor() {
        this.doneButton.setBackground(Color.pink);
    }
    
    public void setButtonListener(ActionListener ae) {
        this.doneButton.addActionListener(ae);
    }
    
    public ArrayList<TabWorldTimeCity> getSelections() {
        HashMap sourceMap = cntl.getWorldTime().getCityMap();
        ArrayList<TabWorldTimeCity> targetList = new ArrayList<>();
        selection.forEach((String item) -> {
            targetList.add(new TabWorldTimeCity(item, (int) sourceMap.get(item)));
        });
        return targetList;
    }
    
}
