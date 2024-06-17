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
import project.GUI.GUITools.LoadingPage;

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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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


    /** new Login 和 Loading 頁面 */
    private void newAndAddLoginLoadingPanel() {
        // new
        this.loginPanel = new LoginPanel();
        this.loadingPage = new LoadingPage(getBackground());

        // add
        this.changePagePanel.add(this.loginPanel, LOGIN_PANEL_NAME);
        this.changePagePanel.add(this.loadingPage, LOADING_PAGE_NAME);
        this.add(this.changePagePanel);
    }


    /** new Main 頁面 */
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


    /**
     * 更新 MainWindow 的資料
     */
    public void updateMainWindow() {
        if (this.mainPanel != null) {
            this.mainPanel.updateMainPanel();
        }
    }

    /**
     * 背景執行，不干擾 GUI 顯示
     * <p>
     * 先檢查使用者輸入的 Key ID 是否正確
     * <p>
     * <ul>
     *      <li>
     *          如果是對的
     *          <ol>
     *              <li> 把 KeyAndID 存到 stockDataSystem </li>
     *              <li> 爬一開始的資料、存到 stockDataSystem </li>
     *              <li> 啟動 backgroundTimer，並設定 backgroundExexute </li>
     *              <li> new Main 頁面 </li>
     *              <li> 切換至 Main 頁面 </li>
     *          </ol>
     *      </li>
     *      <li>
     *          如果是錯的
     *          <ol>
     *              <li> 跳出 WrongInfo </li>
     *              <li> 切換至 Login 頁面 </li>
     *          </ol>
     *      </li>
     * </ul>
     */
    private class LoginAndCrawl extends SwingWorker<Void, Void> {
        /** 主視窗 */
        private MainWindow window = null;


        /** 紀錄 執行完後要換去的頁面 */
        private String changePageName = null;
    

        /**
         * 存主視窗是誰
         * @param window 主視窗
        */
        public LoginAndCrawl(MainWindow window) {
            this.window = window;
        }
    

        @Override
        protected Void doInBackground() throws Exception {
            // 要執行的內容
            String userKey = loginPanel.getUserKey();
            String userId = loginPanel.getUserId();
            
            if (WebCrawler.check_Key_ID(userId, userKey)) {  // 檢查使用者輸入的 Key ID 是否正確
                // store
                this.window.stockDataSystem.setKeyAndID(userKey, userId);   // 把 KeyAndID 存到 stockDataSystem
                App.crawlAndStoreData();                                    // 爬一開始的資料、存到 stockDataSystem
                App.initBackgroundExecute();                                // 啟動 backgroundTimer，並設定 backgroundExexute

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
            // 結束後，把頁面切換成要的頁面
            this.window.changePagePanel.showPage(this.changePageName);
            repaint();
        }
    }
}
