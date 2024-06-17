package project.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import project.GUI.GUITools.ChangeablePanel;
import project.GUI.GUITools.StockDetail;
import project.System.MarketInfo;
import project.System.Stock;
import project.System.StockDataSystem;

/**
 * 股票列、股票細節
 * @author Hanklo831
 */
public class StockPanel extends JPanel {
    private String[] stockNameList = {"AAPL", "GOOGL", "AMZN", "META", "MSFT", "TSLA"};
    private JList<String> stockList = null;

    private ChangeablePanel changePage = null;

    StockDetail sd;

    private HashMap <String, StockDetail> panelHashMap = new HashMap<>();

    private StockDataSystem stockDataSystem;

    public StockPanel(StockDataSystem stockDataSystem) {
        
        this.stockDataSystem = stockDataSystem;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.stockList = new JList<>(this.stockNameList);
        this.stockList.setPreferredSize(new Dimension(100, 500));
        this.stockList.setFont(new Font("Serif", Font.PLAIN, 20));
        this.stockList.setAlignmentY(Component.TOP_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 10)));
        this.add(this.stockList);

        this.changePage = new ChangeablePanel();
        this.add(this.changePage);


        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new GridBagLayout());
        tmpPanel.setPreferredSize(new Dimension(1000, 800));

        JLabel tmpLabel = new JLabel("click the side bar");
        tmpLabel.setFont(new Font("Serif", Font.PLAIN, 50));

        GridBagConstraints constraints = new GridBagConstraints();
        tmpPanel.add(tmpLabel, constraints);
        this.changePage.add(tmpPanel, "tmp");
        this.changePage.showPage("tmp");
        StockPanel stockPanel = this;
        stockList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = stockList.locationToIndex(e.getPoint());
                if (index != -1) {
                    String selectedItem = stockList.getModel().getElementAt(index);

                    if (stockPanel.changePage.showPage(selectedItem) == false) {
                        // 未成功切換 => 代表沒有頁面
                        Stock selectedStock = stockDataSystem.getStock(selectedItem);
                        Double stockPrice = 0.0;
                        if (selectedStock != null) {
                            stockPrice = selectedStock.getStockPrice();
                        }
                        List<MarketInfo> record = stockDataSystem.getRecorder(selectedItem, "DAY");
                        sd = new StockDetail(selectedItem, stockPrice, record, stockDataSystem);
                        //System.out.println(sd);
                        stockPanel.changePage.add(sd, selectedItem);

                        // show
                        stockPanel.changePage.showPage(selectedItem);

                        stockPanel.panelHashMap.put(selectedItem, sd);
                    }
                }
                
            }
        });

        
        
    }

    public void updateStockPanel() {
        for (String str : this.panelHashMap.keySet()) {
            Stock selectedStock = this.stockDataSystem.getStock(str);
            Double stockPrice = 0.0;
            if (selectedStock != null) {
                stockPrice = selectedStock.getStockPrice();
            }
            this.panelHashMap.get(str).updateStockPrice(stockPrice);
        }
    }

    /**
     * 取得stockDetail
     * @return stockDetail
     */
    public StockDetail getStockDetail() {
        return sd;
    }
}

