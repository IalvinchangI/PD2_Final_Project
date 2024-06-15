package project.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import project.GUI.GUITools.RoundPanel;


/**
 * 測試元件用的
 * <p>
 * 不會被加到釋出軟體中
 */
public class TestGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(new Color(253, 232, 181));
        // frame.setBackground(Color.WHITE);
        frame.setSize(1500, 800);

        // LoginPanel loginPanel = new LoginPanel();
        // frame.add(loginPanel);

        // StockPanel stockPanel = new StockPanel();
        // frame.add(stockPanel);

        MainPanel mainPanel = new MainPanel();
        
        frame.add(mainPanel);


        frame.setVisible(true);
    }
}
