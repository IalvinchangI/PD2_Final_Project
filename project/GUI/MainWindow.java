package project.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import project.GUI.GUITools.ChangeablePanel;
import project.GUI.GUITools.StockDetail;
import project.System.DataSystem;

public class MainWindow extends JFrame {

    private LoginPanel loginPanel = null;
    private MainPanel mainStockPanel = null;
    private MainPanel mainHistoryPanel = null;

    private ChangeablePanel changePagePanel = null;
    

    /**
     * 設定視窗
     * @param title     視窗右上角顯示的標題
     * @param width     視窗的寬
     * @param height    視窗的高
     */
    public MainWindow(String title, int width, int height) {
        this.setTitle(title);
        // this.setSize(1600, 1050);
        this.setSize(width, height);
        this.setBackground(Color.WHITE);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.newAndAddPanel();
        this.addListener();
    }

    
    /** 顯示 window */
    public void showGUI() {
        this.setVisible(true);
    }


    /** new 頁面 */
    private void newAndAddPanel() {
        // new
        this.changePagePanel = new ChangeablePanel();

        this.loginPanel = new LoginPanel();
        this.mainStockPanel = new MainPanel();
        this.mainHistoryPanel = new MainPanel();

        StockPanel stockPanel = new StockPanel();
        HistoryPanel historyPanel = new HistoryPanel();


        // add
        this.mainStockPanel.add(stockPanel);
        this.mainHistoryPanel.add(historyPanel);

        this.changePagePanel.add(loginPanel, "LoginPanel");
        this.changePagePanel.add(mainStockPanel, "StockPanel");
        this.changePagePanel.add(mainHistoryPanel, "HistoryPanel");

        this.add(this.changePagePanel);
    }


    /** 加 button 的 listener 和 action */
    private void addListener() {
        JButton loginButton = loginPanel.getLoginButton();
        JButton stockButton = mainHistoryPanel.getStockButton();
        JButton historyButton = mainStockPanel.getHistoryButton();
        // StockDetail sd = stockPanel.getStockDetail();
        // JButton finishButton = sd.getFinishButton();
        
        stockButton.addActionListener(this.changePagePanel.createChangePagePerformed("StockPanel"));
        historyButton.addActionListener(this.changePagePanel.createChangePagePerformed("HistoryPanel"));


        JFrame frame = this;
        ChangeablePanel changePage = this.changePagePanel;

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userKey = loginPanel.getUserKey();
                String userId = loginPanel.getUserId();
                setKeyAndID(userKey, userId);
                
                if (true) {
                    changePage.showPage("HistoryPanel");
                }
                else {
                    JDialog wrong = new WrongInfo(frame);
                    wrong.setVisible(true);
                }
            }
        });


        // finishButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         String buy = sd.getBuy();
        //         String sell = sd.getSell();
        //         String interval = sd.getInterval();
        //     }
        // });
    }


    public static void main(String[] args) {
        MainWindow window = new MainWindow("股票機器人", 1400, 800);
        window.showGUI();
    }
}
