package project.GUI.GUITools;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;

/**
 * 股票的顯示細節
 * @author Hanklo
 */
public class StockDetail extends JPanel {

    protected JPanel stock;
    protected JLabel stockNameLabel, stockPriceLabel;

    protected FlowLayout stockLayout;

    protected JPanel buyPanel, sellPanel, intervalPanel;



    /**
     * 建造：左上股票名、右上股票價格、中間K線圖、下面買賣處理
     * @param stockName ：股票名稱
     * @param stockPrice ：股票價格
     * @param stockHistoryPrice ：股票近三十天的最高價、最低價、開盤價、收盤價
     */
    public StockDetail(String stockName, double stockPrice,ArrayList <Double> stockHistoryPrice) {

        // layout
        this.stockLayout = new FlowLayout();

        // panel
        this.stock = new JPanel();

        // label
        this.stockNameLabel = new JLabel(stockName);
        this.stockPriceLabel = new JLabel(Double.toString(stockPrice));

        this.stock.add(this.stockNameLabel);
        this.stock.add(this.stockPriceLabel);
        
        // graph



        //inputField    
        this.buyPanel = new InputField("buy");
        this.sellPanel = new InputField("sell");
        this.intervalPanel = new InputField("interval");

        this.setLayout(stockLayout);
        this.add(this.stock);
        this.add(this.buyPanel);
        this.add(this.sellPanel);
        this.add(this.intervalPanel);


    }


}
