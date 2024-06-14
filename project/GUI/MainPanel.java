package project.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;


/**
 * 最上層的Panel
 * @author Hanklo831
 */
public class MainPanel extends JPanel {

    private JButton stockButton = null;
    private JButton historyButton = null;
    private JPanel buttonPanel = null;



    public MainPanel() {
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.X_AXIS));

        this.stockButton = new JButton("stock");
        this.historyButton = new JButton("history");
        this.stockButton.setFont(new Font("Serif", Font.PLAIN, 30));
        this.historyButton.setFont(new Font("Serif", Font.PLAIN, 30));

        

        this.buttonPanel.add(Box.createRigidArea(new Dimension(470, 100)));
        this.buttonPanel.add(stockButton);
        this.buttonPanel.add(Box.createRigidArea(new Dimension(100, 100)));
        this.buttonPanel.add(historyButton);

        this.buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        this.buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.buttonPanel.setPreferredSize(new Dimension(1280, 100));
        this.add (this.buttonPanel);

        this.setPreferredSize(new Dimension(1280, 800));


    }

    /**
     * 取得 stockButton
     * @return stockButton
     */
    public JButton getStockButton() {
        return this.stockButton;
    }


    /**
     * 取得 historyButton
     * @return historyButton
     */
    public JButton getHistoryButton() {
        return  this.historyButton;
    }
    

    
}

