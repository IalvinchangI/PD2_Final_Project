package project;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui {
    static String key = "", id = "";
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginGUI loginGui = new loginGUI();
            loginGui.setVisible(true);
            loginGui.getLoginButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    key = loginGui.getUserKey();
                    id = loginGui.getUserId();
                    loginGui.dispose();
                    System.out.println(key + " finish " + id);
                }
            });
        });
        
    }
    

    static class loginGUI extends JFrame {
        JPanel root;
        JLabel userKeyLable, userIdLable;
        JTextField userKeyField;
        JButton login, register;
        JPasswordField userIdField;

        loginGUI () {
            root = new JPanel(); 
            setContentPane(root);
            setLayout(null);

            //key lable
            userKeyLable = new JLabel("K E Y : ");
            userKeyLable.setBounds (52, 33, 54, 15);
            root.add (userKeyLable);

            //key field
            userKeyField = new JTextField(20);
            userKeyField.setBounds(100, 30, 139, 21);
            root.add(userKeyField);

            //id lable
            userIdLable = new JLabel("I D : ");
            userIdLable.setBounds (52, 73, 54, 15);
            root.add (userIdLable);

            //id field
            userIdField = new JPasswordField(20);
            userIdField.setBounds(100, 70, 139, 21);
            userIdField.setEchoChar('*');
            root.add(userIdField);

            //login 
            login = new JButton("login");
            login.setBounds(64, 116, 80, 23);
            root.add (login);

            //register
            register = new JButton("register");
            register.setBounds(174, 116, 80, 23);
            root.add (register);

            //set 
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBounds(400, 300, 340, 256);
        }

        public String getUserKey() {
            return userKeyField.getText();
        }

        public String getUserId() {
            return new String(userIdField.getPassword());
        }

        public JButton getLoginButton() {
            return login;
        }

    }

}
