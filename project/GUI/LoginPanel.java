package project.GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.GUI.GUITools.InputField;
import project.GUI.GUITools.PasswordField;




public class LoginPanel extends JPanel {


    JPanel root = null;


    InputField userKey = null;


    PasswordField userId = null;


    JButton login = null;


    JButton register = null;


    public LoginPanel() {
        root = new JPanel();
        this.root.setLayout(new );

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

        
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    URI uri = new URI("https://alpaca.markets/");
                    Desktop.getDesktop().browse(uri);
                } catch (IOException | URISyntaxException u) {
                    u.printStackTrace();
                }
            }
        });
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
