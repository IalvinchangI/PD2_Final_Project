package project.GUI;

import java.awt.Dimension;
import javax.swing.*;


/**
 * 最上層的Panel
 * @author Hanklo831
 */
public class MainGUI extends JPanel {

    private JButton stockButton = null;
    private JButton historyButton = null;
    private JPanel buttonPanel = null;



    public MainGUI() {
        this.buttonPanel.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.stockButton = new JButton("stock");
        this.historyButton = new JButton("history");

        this.buttonPanel.add(stockButton);
        this.buttonPanel.add(historyButton);

        this.buttonPanel.setPreferredSize(new Dimension(1440, 100));

        this.setPreferredSize(new Dimension(1600, 1050));


    }
    

    
}

