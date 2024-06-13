import javax.swing.*;

import java.awt.*;
import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


//input all caps
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
                    SwingUtilities.invokeLater(() -> {
                        mainGUI mainGui = new mainGUI();
                        mainGui.setVisible(true);
                    });
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

    static class mainGUI extends JFrame {
        JPanel root;
        JButton stock, histroy;

        JPanel stockRoot;
        JList stockList;
        JLabel stockNameLabel, stockPriceLabel, buyChooseLabel, sellChooseLabel, intervalChooseLable;
        JTextField buyChooseField, sellChooseField, intervalChooseField;
        JButton finish;

        mainGUI () {
            root = new JPanel(); 
            setContentPane(root);
            setLayout(null);

            stock = new JButton("stock");
            stock.setBounds(10 ,10, 80, 23);
            root.add (stock);

            histroy = new JButton("history");
            histroy.setBounds(300, 10, 80, 23);
            root.add(histroy);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBounds(0, 0, 800, 600);
            
            stock.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    
                }
            });
            /* 
            histroy.addActionListener(new ActionListener() {
                public void actionPerformed (ActionListener e) {
                    
                    //
                }
            });*/

            stockRoot = new JPanel();
            //setContentPane(stockRoot);
            setLayout(null);


            String[] stockName = {"AAPL", "GOOG", "AMZN", "FB", "MSFT", "FB", "TSLA", "BRK.A", "JPM"};
            double[] stockPrice = {1.11, 2.22, 3.33, 4.44, 5.55, 6.66, 7.77, 8.88, 9.99};
            stockList = new JList(stockName);
            stockList.setBounds(10, 40, 100, 200);
            stockRoot.add (stockList);

            stockNameLabel = new JLabel(stockName[0]);
            stockNameLabel.setBounds(130, 40, 50, 15);
            stockRoot.add (stockNameLabel);

            stockPriceLabel = new JLabel(Double.toString (stockPrice[0]));
            stockPriceLabel.setBounds(200, 40, 50, 15);
            stockRoot.add (stockPriceLabel);

            //graph

            buyChooseLabel = new JLabel("buy");
            buyChooseLabel.setBounds(130, 250, 50, 15);
            stockRoot.add (buyChooseLabel);

            buyChooseField = new JTextField(12);
            buyChooseField.setBounds(190, 250, 100, 15);
            stockRoot.add(buyChooseField);

            sellChooseLabel = new JLabel("sell");
            sellChooseLabel.setBounds(130, 270, 50, 15);
            stockRoot.add (sellChooseLabel);

            sellChooseField = new JTextField(12);
            sellChooseField.setBounds(190, 270, 100, 15);
            stockRoot.add(sellChooseField);

            intervalChooseLable = new JLabel("interval");
            intervalChooseLable.setBounds(130, 290, 50, 15);
            stockRoot.add (intervalChooseLable);

            intervalChooseField = new JTextField(12);
            intervalChooseField.setBounds(190, 290, 100, 15);
            stockRoot.add(intervalChooseField);

            finish = new JButton("finish");
            finish.setBounds(190, 310, 100, 15);
            stockRoot.add (finish);

            /* 
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBounds(100, 300, 600, 250);
            */
            

            
        }

       
    }

}
