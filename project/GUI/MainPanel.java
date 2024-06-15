package project.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import project.GUI.GUITools.ChangeablePanel;
import project.GUI.GUITools.RoundButton;
import project.GUI.GUITools.ShadowPanel;


/**
 * 最上層的Panel
 * @author Hanklo831
 */
public class MainPanel extends JPanel {

    /** 框 */
    protected ShadowPanel centralPanel = null;

    private JPanel buttonPanel = null;
    private RoundButton stockButton = null;
    private RoundButton historyButton = null;

    /** 裝 StockPanel, HistoryPanel，可切換 */
    private ChangeablePanel pageBody = null;
    private HistoryPanel historyPanel = null;
    private StockPanel stockPanel = null;


    /** historyPanel 的名字 */
    public static final String HISTORY_PANEL_NAME = "HistoryPanel";
    /** stockPanel 的名字 */
    public static final String STOCK_PANEL_NAME = "StockPanel";



    /** 製作 main page 頁面 */
    public MainPanel() {
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(new GridBagLayout());
        

        // inner
        this.centralPanel = new ShadowPanel(this.getBackground());
        this.centralPanel.setPreferredSize(new Dimension(1280, 800));
        this.centralPanel.setLayout(new BoxLayout(this.centralPanel.getRoot(), BoxLayout.Y_AXIS));

        this.centralPanel.setShadowShift(0);


        // set panels
        settingButtonPanel();
        settingPageBody();

        addListener();

        
        // add
        this.centralPanel.add(this.buttonPanel);
        this.centralPanel.add(this.pageBody);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(this.centralPanel);
    }


    


    /** 設定 buttonPanel */
    private void settingButtonPanel() {
        // new and setting
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.X_AXIS));

        this.stockButton = new RoundButton("stock", this.getBackground());
        this.historyButton = new RoundButton("history", this.getBackground());

        Font font = new Font("Serif", Font.PLAIN, 30);
        this.stockButton.setFont(font);
        this.historyButton.setFont(font);

        // this.buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        this.buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.buttonPanel.setPreferredSize(new Dimension(1280, 100));


        // add
        // this.buttonPanel.add(Box.createRigidArea(new Dimension(470, 100)));
        this.buttonPanel.add(stockButton);
        this.buttonPanel.add(Box.createRigidArea(new Dimension(100, 100)));
        this.buttonPanel.add(historyButton);
    }


    /** 設定 pageBody */
    private void settingPageBody() {
        // new and set
        this.pageBody = new ChangeablePanel();

        this.historyPanel = new HistoryPanel();
        this.stockPanel = new StockPanel();

        this.pageBody.setAlignmentX(Component.CENTER_ALIGNMENT);


        // add
        this.pageBody.add(this.historyPanel, HISTORY_PANEL_NAME);
        this.pageBody.add(this.stockPanel, STOCK_PANEL_NAME);

        this.pageBody.showPage(HISTORY_PANEL_NAME);
    }


    /** 加 button 的 listener 和 action */
    private void addListener() {
        this.historyButton.addActionListener(this.pageBody.createChangePagePerformed(HISTORY_PANEL_NAME));
        this.stockButton.addActionListener(this.pageBody.createChangePagePerformed(STOCK_PANEL_NAME));
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

