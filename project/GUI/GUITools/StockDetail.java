package project.GUI.GUITools;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.GUI.CandleStick;

import java.util.*;

/**
 * 股票的顯示細節
 * @author Hanklo
 */
public class StockDetail extends JPanel {

    private  JPanel stock = null;
    private JLabel stockNameLabel = null, stockPriceLabel = null;
    private JPanel buyPanel = null, sellPanel = null, intervalPanel = null;



    /**
     * 建造：左上股票名、右上股票價格、中間K線圖、下面買賣處理
     * @param stockName ：股票名稱
     * @param stockPrice ：股票價格
     * @param stockHistoryPrice ：股票近三十天的最高價、最低價、開盤價、收盤價
     */
    public StockDetail(String stockName, double stockPrice,ArrayList <HashMap <String, Double>> stockHistoryPrice) {

        // layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // panel
        this.stock = new JPanel();

        // label
        this.stockNameLabel = new JLabel(stockName);
        this.stockPriceLabel = new JLabel(Double.toString(stockPrice));

        this.stock.add(this.stockNameLabel);
        this.stock.add(this.stockPriceLabel);
        
        // graph
        this.graph = new CandleStick(stockHistoryPrice);


        //inputField    
        this.buyPanel = new InputField("buy");
        this.sellPanel = new InputField("sell");
        this.intervalPanel = new InputField("interval");

        this.add(this.stock);
        this.add(this.graph);
        this.add(this.buyPanel);
        this.add(this.sellPanel);
        this.add(this.intervalPanel);


    }


}
