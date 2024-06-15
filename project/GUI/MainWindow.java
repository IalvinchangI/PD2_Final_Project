package project.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import project.GUI.GUITools.ChangeablePanel;
import project.System.DataSystem;
import project.System.StockDataSystem;
import project.AlpacaAPICall.WebCrawler;


/**
 * 主視窗
 * @author Hanklo831
 * @author IalvinchangI
 */
public class MainWindow extends JFrame {

    private ChangeablePanel changePagePanel = null;
    
    private LoginPanel loginPanel = null;
    private MainPanel mainPanel = null;

    /** loginPanel 的名字 */
    public static final String LOGIN_PANEL_NAME = "LoginPanel";
    /** mainPanel 的名字 */
    public static final String MAIN_PANEL_NAME = "MainPanel";


    /** 存取資料的地方 */
    private StockDataSystem stockDataSystem = null;
    

    /**
     * 設定視窗
     * @param title     視窗右上角顯示的標題
     * @param width     視窗的寬
     * @param height    視窗的高
     */
    public MainWindow(String title, int width, int height, StockDataSystem stockDataSystem) {
        this.stockDataSystem = stockDataSystem;
        this.setTitle(title);
        // this.setSize(1600, 1050);
        this.setSize(width, height);
        this.setBackground(new Color(253, 232, 181));
        // this.setBackground(Color.WHITE);

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
        this.mainPanel = new MainPanel(stockDataSystem);


        // add
        this.changePagePanel.add(this.loginPanel, LOGIN_PANEL_NAME);
        this.changePagePanel.add(this.mainPanel, MAIN_PANEL_NAME);

        this.add(this.changePagePanel);
    }


    /** 加 button 的 listener 和 action */
    private void addListener() {
        MainWindow window = this;
        
        JButton loginButton = loginPanel.getLoginButton();
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userKey = loginPanel.getUserKey();
                String userId = loginPanel.getUserId();
                
                if (WebCrawler.check_Key_ID(userId, userKey)) {
                    window.stockDataSystem.setKeyAndID(userKey, userId);
                    window.changePagePanel.showPage(MAIN_PANEL_NAME);
                    repaint();
                }
                else {
                    JDialog wrong = new WrongInfo(window);
                    wrong.setSize(200, 150);
                    wrong.setVisible(true);
                }
            }
        });


        // StockDetail sd = stockPanel.getStockDetail();
        // JButton finishButton = sd.getFinishButton();
        // finishButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         String buy = sd.getBuy();
        //         String sell = sd.getSell();
        //         String interval = sd.getInterval();
        //     }
        // });
    }


    public static void main(String[] args) {
        StockDataSystem dataSystem = new DataSystem();
        WebCrawler.downloadStockDataSystem(dataSystem);  // load StockDataSystem
        
        MainWindow window = new MainWindow("股票機器人", 1400, 850, dataSystem);
        
        
        window.showGUI();
    }
}
