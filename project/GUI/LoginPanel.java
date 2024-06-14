package project.GUI;


import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.GUI.GUITools.InputField;
import project.GUI.GUITools.PasswordField;
import project.GUI.GUITools.ShadowPanel;
import project.GUI.GUITools.RoundButton;



/**
 * login 頁面
 * @author hanklo831
 * @author IalvinchangI
 */
public class LoginPanel extends JPanel {

    /** 框 */
    protected ShadowPanel centralPanel = null;


    /** title */
    private JLabel title = null;


    /** input user secret key */
    private PasswordField userKey = null;


    /** input user key id */
    private InputField userId = null;



    /** 觸發 login 的按鈕 */
    private RoundButton loginButton = null;


    /** 如果未註冊過 Alpaca，可用此按鈕跳轉至 Alpaca 的網頁 */
    private RoundButton registerButton = null;


    /** 製作 login 頁面 */
    public LoginPanel() {
        // outer
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0, 0, 0, 0));


        // inner
        this.centralPanel = new ShadowPanel(this.getBackground());
        // this.centralPanel = new ShadowPanel(this.getBackground(), new Color(122, 149, 211), new Color(180, 180, 180));
        // this.centralPanel = new ShadowPanel(this.getBackground(), new Color(240, 240, 240), new Color(180, 180, 180));
        this.centralPanel.setArc(40, 40);
        this.centralPanel.setPreferredSize(new Dimension(600, 450));
        this.centralPanel.setLayout(new BoxLayout(this.centralPanel.getRoot(), BoxLayout.Y_AXIS));
        
        
        // set title
        this.title = new JLabel("LOGIN");
        this.title.setFont(new Font("Calibri", Font.BOLD, 60));
        this.title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // set Field
        fieldSetting();


        // set Button
        JPanel buttonPanel = buttonSetting();


        // add
        this.centralPanel.add(this.title);
        this.centralPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.centralPanel.add(this.userId);
        this.centralPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.centralPanel.add(this.userKey);
        this.centralPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.centralPanel.add(buttonPanel);
        this.centralPanel.add(Box.createVerticalGlue());

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
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


    /** 排 userId, userKey Field */
    private void fieldSetting() {
        Font font = new Font("Calibri", Font.BOLD, 18);

        this.userId = new InputField("K E Y - I D : ");
        this.userKey = new PasswordField("S E C R E T - K E Y : ", '*');

        this.userId.setSize(180, 25);
        this.userKey.setSize(180, 25);
        
        this.userId.setBackground(this.centralPanel.getBackground());
        this.userKey.setBackground(this.centralPanel.getBackground());

        this.userId.getTextField().setBorder(BorderFactory.createLineBorder(new Color(130, 130, 130), 3));
        this.userKey.getTextField().setBorder(BorderFactory.createLineBorder(new Color(130, 130, 130), 3));
        
        this.userId.getTextField().setBackground(new Color(248, 222, 179));
        this.userKey.getTextField().setBackground(new Color(248, 222, 179));

        this.userId.setFont(font);
        this.userKey.setFont(font);
    }

    /**
     * 排 login, register Button
     * @return buttonPanel
     */
    private JPanel buttonSetting() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(this.centralPanel.getBackground());

        this.loginButton = new RoundButton("login", this.centralPanel.getBackground());
        this.registerButton = new RoundButton("register", this.centralPanel.getBackground());
        
        Dimension buttonSize = new Dimension(150, 50);
        this.loginButton.setMaximumSize(buttonSize);
        this.registerButton.setMaximumSize(buttonSize);
        this.loginButton.setPreferredSize(buttonSize);
        this.registerButton.setPreferredSize(buttonSize);
        
        this.loginButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.registerButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        buttonPanel.add(this.loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        buttonPanel.add(this.registerButton);

        return buttonPanel;
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
