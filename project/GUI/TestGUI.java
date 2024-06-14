package project.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;


import project.GUI.*;
import project.GUI.GUITools.*;


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
        frame.setSize(600, 600);

        LoginPanel loginPanel = new LoginPanel();
        frame.add(loginPanel);

        frame.setVisible(true);
    }
}
