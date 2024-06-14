package project.GUI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;


/**
 * 測試元件用的
 * <p>
 * 不會被加到釋出軟體中
 */
public class TestGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);
        frame.setSize(1280, 800);

        //LoginPanel loginPanel = new LoginPanel();
        //frame.add(loginPanel);

        // StockPanel stockPanel = new StockPanel();
        // frame.add(stockPanel);

        MainPanel mainPanel = new MainPanel();
        

        HistoryPanel historyPanel = new HistoryPanel();
        historyPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        historyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(historyPanel);
        frame.add(mainPanel);

        frame.setVisible(true);
    }
}
