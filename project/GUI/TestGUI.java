package project.GUI;

import java.awt.Color;
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
        frame.setSize(800, 800);

        LoginPanel loginPanel = new LoginPanel();
        frame.add(loginPanel);

        // HistoryPanel historyPanel = new HistoryPanel();
        // frame.add(historyPanel);
        

        frame.setVisible(true);
    }
}
