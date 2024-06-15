package project.GUI.GUITools;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import project.GUI.CandleStick;

import java.util.*;
import javax.print.attribute.standard.JobImpressions;
import javax.swing.JButton;
import project.System.StockDataSystem;
import project.System.MarketInfo;
import project.System.Stock;
/**
 * 股票的顯示細節 (股票名、價格、K線圖、買賣設定)
 * @author Hanklo831
 */
public class StockDetail extends JPanel {

    private JPanel stock = null;
    private JLabel stockNameLabel = null, stockPriceLabel = null;
    private InputField buyPanel = null, sellPanel = null, intervalPanel = null, stockCountPanel = null;
    private JButton finishButton = null;
    private JPanel graph;



    /**
     * 建造：左上股票名、右上股票價格、中間K線圖、下面買賣處理
     * @param stockName ：股票名稱
     * @param stockPrice ：股票價格
     * @param stockHistoryPrice ：股票近三十天的最高價、最低價、開盤價、收盤價
     */
    public StockDetail(String stockName, double stockPrice, List<MarketInfo> stockHistoryPrice) {

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
        this.graph = CandleStick(stockName, stockHistoryPrice);


        //inputField    
        this.buyPanel = new InputField("B U Y : ");
        this.sellPanel = new InputField("S E L L : ");
        this.intervalPanel = new InputField("I N T E R V A L : ");
        this.stockCountPanel = new InputField("S T O C K   C O N U T : ");

        this.finishButton = new JButton("finish setting");

        this.add(this.stock);
        this.add(this.graph);
        this.add(this.buyPanel);
        this.add(this.sellPanel);
        this.add(this.intervalPanel);
        this.add(this.stockCountPanel);
        this.add(this.finishButton);
        this.setPreferredSize(new Dimension(1000, 800));
        
    }

    /**
     * 取得finishButton
     * @return finishButton
     */
    public JButton getFinishButton() {
        return this.finishButton;
    }

    /**
     * 取得 buy 的設定
     * @return 輸入的 buy 設定 
     */
    public String getBuy() {
        return this.buyPanel.getText();
    }

    /**
     * 取得 sell 的設定
     * @return 輸入的 sell 設定 
     */
    public String getSell() {
        return this.sellPanel.getText();
    }

    /**
     * 取得 interval 的設定
     * @return 輸入的 interval 設定 
     */
    public String getInterval() {
        return this.intervalPanel.getText();
    }

    /**
     * 取得 stockCount 的設定
     * @return 輸入的 stockCount 設定
     */
    public String getStockCount() {
        return this.stockCountPanel.getText();
    }

}
