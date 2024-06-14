package project.GUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import project.GUI.GUITools.StockDetail;

public class MainWindow {

    public void showGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1050);

        LoginPanel loginPanel = new LoginPanel();
        MainPanel mainStockPanel = new MainPanel();
        MainPanel mainHistoryPanel = new MainPanel();

        StockPanel stockPanel = new StockPanel();
        HistoryPanel historyPanel = new HistoryPanel();

        JPanel mainPanel = new JPanel(new CardLayout());

        mainStockPanel.add(stockPanel);
        mainHistoryPanel.add(historyPanel);

        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(mainStockPanel, "StockPanel");
        mainPanel.add(mainHistoryPanel, "HistoryPanel");

        JButton loginButton = loginPanel.getLoginButton();
        JButton stockButton = mainHistoryPanel.getStockButton();
        JButton historyButton = mainStockPanel.getHistoryButton();
        StockDetail sd = stockPanel.getStockDetail();
        JButton finishButton = sd.getFinishButton();
        

        
        
        CardLayout c = (CardLayout) mainPanel.getLayout();
        c.show(mainPanel, "LoginPanel");



        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userKey = loginPanel.getUserKey();
                String userId = loginPanel.getUserId();
                if (true) {
                    c.show(mainPanel, "HistoryPanel");

                }
                else {
                    JDialog wrong = new WrongInfo(frame);
                    wrong.setVisible(true);
                }
            }
        });

        stockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.show(mainPanel, "HistoryPanel");
            }
        });

        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.show(mainPanel, "StockPanel");
            }
        });

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buy = sd.getBuy();
                String sell = sd.getSell();
                String interval = sd.getInterval();
            }
        });

        frame.setVisible(true);

    }
}
