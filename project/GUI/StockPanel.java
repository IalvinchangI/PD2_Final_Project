package project.GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StockPanel extends JPanel {


    private JList stockList;
}



class StockDetailPanel extends JPanel {


    private JLabel stockNameLabel, stockPriceLabel, buyChooseLabel, sellChooseLabel, intervalChooseLable;


    private JTextField buyChooseField, sellChooseField, intervalChooseField;

    
    private JButton finish;


    StockDetailPanel() {
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
    }

    
}
