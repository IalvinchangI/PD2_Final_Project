package project.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import project.GUI.GUITools.ChangeablePanel;

import project.System.DataSystem;
import project.System.StockDataSystem;
import project.App;
import project.AlpacaAPICall.WebCrawler;


/**
 * 主視窗
 * @author Hanklo831
 * @author IalvinchangI
 */
public class MainWindow extends JFrame {
    // 換頁面的包裝
    private ChangeablePanel changePagePanel = null;
    
    // 頁面
    private LoginPanel loginPanel = null;
    private LoadingPage loadingPage = null;
    private MainPanel mainPanel = null;

    /** loginPanel 的名字 */
    public static final String LOGIN_PANEL_NAME = "LoginPanel";
    /** loginPanel 的名字 */
    public static final String LOADING_PAGE_NAME = "LoadingPage";
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

        // set CloseOperation
        MainWindow window = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                App.stopBackgroundExecute();
                
                window.dispose();  // 釋放視窗相關的資源
                System.exit(0);  // 退出程式
            }
        });
        
        this.changePagePanel = new ChangeablePanel();

        this.newAndAddLoginLoadingPanel();
        this.addLoginListener();
    }

    
    /** 顯示 window */
    public void showGUI() {
        this.setVisible(true);
    }


    /** new 頁面 */
    private void newAndAddLoginLoadingPanel() {
        // new
        this.loginPanel = new LoginPanel();
        this.loadingPage = new LoadingPage(getBackground());


        // add
        this.changePagePanel.add(this.loginPanel, LOGIN_PANEL_NAME);
        this.changePagePanel.add(this.loadingPage, LOADING_PAGE_NAME);
        this.add(this.changePagePanel);
    }


    private void newAndAddMainPanel() {
        // new
        this.mainPanel = new MainPanel(stockDataSystem);


        // add
        this.changePagePanel.add(this.mainPanel, MAIN_PANEL_NAME);
        this.add(this.changePagePanel);
    }


    /** 加 button 的 listener 和 action */
    private void addLoginListener() {
        MainWindow window = this;
        
        JButton loginButton = loginPanel.getLoginButton();
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.changePagePanel.showPage(LOADING_PAGE_NAME);
                repaint();

                new MainWindow.LoginAndCrawl(window).execute();
            }
        });
    }


    public static void main(String[] args) {
        StockDataSystem dataSystem = new DataSystem();
        WebCrawler.downloadStockDataSystem(dataSystem);  // load StockDataSystem
        
        MainWindow window = new MainWindow("股票機器人", 1400, 850, dataSystem);
        
        
        window.showGUI();
    }


    /**
     * 背景執行
     */
    private class LoginAndCrawl extends SwingWorker<Void, Void> {
    
        private MainWindow window = null;
        private String changePageName = null;
    
        public LoginAndCrawl(MainWindow window) {
            this.window = window;
        }
    
        @Override
        protected Void doInBackground() throws Exception {
            String userKey = loginPanel.getUserKey();
            String userId = loginPanel.getUserId();
            
            if (WebCrawler.check_Key_ID(userId, userKey)) {

                // store
                this.window.stockDataSystem.setKeyAndID(userKey, userId);
                App.crawlAndStoreData();
                App.initBackgroundExecute();

                // GUI
                newAndAddMainPanel();
                this.changePageName = MainWindow.MAIN_PANEL_NAME;
            }
            else {
                JDialog wrong = new WrongInfo(window);
                wrong.setSize(200, 150);
                wrong.setVisible(true);
                
                this.changePageName = MainWindow.LOGIN_PANEL_NAME;
            }
            return null;
        }
        
        
        @Override
        protected void done() {
            this.window.changePagePanel.showPage(this.changePageName);
            repaint();
        }
    }
}




