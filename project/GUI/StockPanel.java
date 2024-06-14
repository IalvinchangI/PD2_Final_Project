package project.GUI;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import project.GUI.GUITools.StockDetail;

/**
 * 股票列、股票細節
 * @author Hanklo831
 */
public class StockPanel extends JPanel {
    private String[] stockNameList = {"AAPL", "GOOGL", "AMZN", "META", "MSFT", "TSLA"};
    private JList<String> stockList = null;

    StockDetail sd = new StockDetail(getName(), 10.0, null);

    public StockPanel () {
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.stockList = new JList<>(this.stockNameList);
        this.stockList.setPreferredSize(new Dimension(100, 800));
        
        this.add (this.stockList);
        

        stockList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = stockList.locationToIndex(e.getPoint());
                if (index != -1) {
                    String selectedItem = stockList.getModel().getElementAt(index);
                    sd = new StockDetail(selectedItem, index, null);
                }
                
            }
        });

        this.add (sd);
        
    }

    /**
     * 取得stockDetail
     * @return stockDetail
     */
    public StockDetail getStockDetail() {
        return sd;
    }
}

