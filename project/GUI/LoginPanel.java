package project.GUI;


import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import project.GUI.GUITools.InputField;
import project.GUI.GUITools.PasswordField;



/**
 * login 頁面
 * @author hanklo831
 * @author IalvinchangI
 */
public class LoginPanel extends JPanel {

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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(340, 256));


        // set
        this.userKey = new InputField("K E Y : ");
        this.userId = new PasswordField("I D : ", '*');
        this.loginButton = new JButton("login");
        this.registerButton = new JButton("register");


        // alignment
        this.userKey.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.userId.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // add
        this.add(this.userKey);
        this.add(this.userId);
        this.add(this.loginButton);
        this.add(this.registerButton);

    
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
