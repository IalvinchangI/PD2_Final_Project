package project.GUI;

import javax.swing.*;



public class MainGUI extends JFrame {

    /**
     * MainGUI 裡，最上層的 JPanel
     */
    private JPanel root;


    private JPanel upperPanel;


    private JPanel lowerPanel;


    private JButton stockButton, histroyButton;

    JPanel stockRoot;
    

    MainGUI() {
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

        

        /* 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 300, 600, 250);
        */
    }


    private void settingMainGUI() {

    }
}

