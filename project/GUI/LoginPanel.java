package project.GUI;


import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import project.GUI.GUITools.InputField;
import project.GUI.GUITools.PasswordField;
import project.GUI.GUITools.ShadowPanel;



/**
 * login 頁面
 * @author hanklo831
 * @author IalvinchangI
 */
public class LoginPanel extends JPanel {

    /** 框 */
    protected ShadowPanel centralPanel = null;


    /** input user secret key */
    private InputField userKey = null;


    /** input user key id */
    private PasswordField userId = null;


    /** 觸發 login 的按鈕 */
    private JButton loginButton = null;


    /** 如果未註冊過 Alpaca，可用此按鈕跳轉至 Alpaca 的網頁 */
    private JButton registerButton = null;


    /** 製作 login 頁面 */
    public LoginPanel() {
        // outer
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints constraint = new GridBagConstraints();


        // inner
        this.centralPanel = new ShadowPanel(this.getBackground(), new Color(122, 149, 211), new Color(185, 185, 185));
        this.centralPanel.setArc(50, 50);
        this.centralPanel.setPreferredSize(new Dimension(500, 375));
        this.centralPanel.setLayout(new BoxLayout(this.centralPanel.getRoot(), BoxLayout.Y_AXIS));
        


        // set Field
        Font font = new Font("Arial", Font.PLAIN, 15);

        this.userKey = new InputField("S E C R E T - K E Y : ");
        this.userId = new PasswordField("K E Y - I D : ", '*');
        
        this.userKey.setBackground(this.centralPanel.getBackground());
        this.userId.setBackground(this.centralPanel.getBackground());

        this.userKey.setAllFont(font);
        this.userId.setAllFont(font);

        this.userKey.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.userId.setAlignmentX(Component.CENTER_ALIGNMENT);


        // set Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(this.centralPanel.getBackground());

        this.loginButton = new JButton("login");
        this.registerButton = new JButton("register");
        
        Dimension buttonSize = new Dimension(200, 30);
        this.loginButton.setSize(buttonSize);
        this.registerButton.setSize(buttonSize);

        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(registerButton);

        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.userKey.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.userId.setAlignmentX(Component.CENTER_ALIGNMENT);


        // add
        this.centralPanel.add(this.userKey);
        this.centralPanel.add(Box.createHorizontalStrut(10));
        this.centralPanel.add(this.userId);
        this.centralPanel.add(Box.createHorizontalStrut(10));
        this.centralPanel.add(buttonPanel);

        constraint.gridx = 0;  // Column 0
        constraint.gridy = 0;  // Row 0
        this.add(this.centralPanel, constraint);

    
        // add listener
        this.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://alpaca.markets/");
                    Desktop.getDesktop().browse(uri);
                } catch (IOException | URISyntaxException u) {
                    u.printStackTrace();
                }
            }
        });
    }


    /**
     * 取得在 userKey 輸入的 key
     * @return 輸入的 key
     */
    public String getUserKey() {
        return this.userKey.getText();
    }


    /**
     * 取得在 userId 輸入的 ID
     * @return 輸入的 ID
     */
    public String getUserId() {
        return this.userId.getText();
    }


    /**
     * 取得 loginButton
     * @return loginButton
     */
    public JButton getLoginButton() {
        return this.loginButton;
    }
}
