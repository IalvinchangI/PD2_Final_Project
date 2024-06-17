package project.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import java.util.*;
import javax.print.attribute.standard.JobImpressions;
import javax.swing.JButton;
import project.System.StockDataSystem;
import project.GUI.GUITools.InputField;
import project.GUI.GUITools.RoundButton;
import project.System.MarketInfo;
import project.System.Stock;
/**
 * 股票的顯示細節 (股票名、價格、K線圖、買賣設定)
 * @author Hanklo831
 */
public class StockDetail extends JPanel {

    private JLabel stockNameLabel = null, stockPriceLabel = null;
    private InputField buyPanel = null, sellPanel = null, intervalPanel = null, stockCountPanel = null;
    private RoundButton finishButton = null;
    private JPanel graph;


    /**
     * 建造：左上股票名、右上股票價格、中間K線圖、下面買賣處理
     * @param stockName ：股票名稱
     * @param stockPrice ：股票價格
     * @param stockHistoryPrice ：股票近三十天的最高價、最低價、開盤價、收盤價
     */
    public StockDetail(String stockName, double stockPrice, List<MarketInfo> stockHistoryPrice, StockDataSystem stockDataSystem) {

        // layout
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // constraints.fill = GridBagConstraints.BOTH;

        // label
        this.stockNameLabel = new JLabel(stockName);
        if (stockPrice == 0.0) {
            this.stockPriceLabel = new JLabel("close");
        }
        else {        
            this.stockPriceLabel = new JLabel(Double.toString(stockPrice));
        }
        this.stockNameLabel.setFont(new Font("Serif", Font.BOLD, 40));
        this.stockPriceLabel.setFont(new Font("Serif", Font.BOLD, 30));

        
        
        // graph
        this.graph = new CandleStick(stockName, stockHistoryPrice);


        //inputField    
        this.buyPanel = new InputField("BIDPRICE : ");
        this.sellPanel = new InputField("OFFERSTEP : ");
        this.intervalPanel = new InputField("BIDSTEP : ");
        this.stockCountPanel = new InputField("STOCK CONUT : ");

        this.buyPanel.setComponentSize(150, 5, 20);
        this.sellPanel.setComponentSize(150, 5, 20);
        this.intervalPanel.setComponentSize(150, 5, 20);
        this.stockCountPanel.setComponentSize(150, 5, 20);
        
        this.finishButton = new RoundButton(
            "finish", new Color(240, 240, 240),
            new Color(248, 222, 179), new Color(130, 130, 130), 
            new Color(250, 205, 131), new Color(100, 100, 100)
        );
        this.finishButton.setPreferredSize(new Dimension(150, 50));
        this.finishButton.setFont(new Font("Serif", Font.BOLD, 25));
        this.finishButton.setArc(20, 20);


        // this.add(this.stock);

        // y = 0
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.add(this.stockNameLabel, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel(), constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(this.stockPriceLabel, constraints);


        // y = 1
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 0;
        constraints.gridheight = 1;
        JLabel box = new JLabel();
        box.setPreferredSize(new Dimension(1, 50));
        this.add(box, constraints);


        // y = 2 ~ 6
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 5;
        this.add(this.graph, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(this.buyPanel, constraints);

        constraints.gridy = 3;
        constraints.insets = new Insets(0,0,0,0);
        this.add(this.sellPanel, constraints);

        constraints.gridy = 4;
        constraints.insets = new Insets(0,0,0,0);

        this.add(this.intervalPanel, constraints);

        constraints.gridy = 5;
        this.add(this.stockCountPanel, constraints);

        constraints.gridy = 6;
        this.add(this.finishButton, constraints);



        
        this.setPreferredSize(new Dimension(1000, 800));

        this.finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buy = getBuy();
                String sell = getSell();
                String interval = getInterval();
                String stockCount = getStockCount();
                stockDataSystem.saveBuyingSetting(stockName, Double.parseDouble(buy), Double.parseDouble(sell), Double.parseDouble(interval), Integer.parseInt(stockCount));
            }
        });
    }


    public void updateStockPrice(double stockPrice) {
        if (stockPrice == 0.0) {
            this.stockPriceLabel.setText("close");
        }
        else {        
            this.stockPriceLabel.setText(Double.toString(stockPrice));
        }
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
