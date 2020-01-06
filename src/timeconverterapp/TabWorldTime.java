package timeconverterapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TabWorldTime extends JPanel {
    
    private JPanel northPanel;
    private JScrollPane scrollPanel;
    private JPanel innerPanel;
    
    private final JLabel instLabel;
    private final JButton addRemoveButton;
    
    private final TimeConverterView view;
    private final TabWorldTimeLiveTime liveTime;
    
    public TabWorldTime(TimeConverterView view) {
        super();
        
        this.view = view;
        initWindow();
        liveTime = new TabWorldTimeLiveTime(this);
        
        this.instLabel = new JLabel();
        instLabel.setText("Click to add or remove clocks. Scroll to refresh list  ");
        this.addRemoveButton = new JButton();
        addRemoveButton.setText("Add/Remove");
        
        innerPanel.add(liveTime);
        northPanel.add(instLabel);
        northPanel.add(addRemoveButton);
    }
    
    private void initWindow() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridheight = 1;
        gbc1.weightx = 0.1;
        gbc1.weighty = 0.1;
        gbc1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridy = 1;
        gbc2.weightx = 0.1;
        gbc2.weighty = 0.5;
        gbc2.fill = GridBagConstraints.BOTH;
        this.northPanel = new JPanel();
        this.innerPanel = new JPanel();
        this.scrollPanel = new JScrollPane(innerPanel);
        scrollPanel.setPreferredSize(new Dimension(300, 200));
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(northPanel, gbc1);
        add(scrollPanel, gbc2);
    }
    
    public void setButtonListener(ActionListener ae) {
        this.addRemoveButton.addActionListener(ae);
    }
    
    public TabWorldTimeLiveTime getLiveTime() {
        return this.liveTime;
    }
    
    public TimeConverterView getView() {
        return this.view;
    }
    
}
