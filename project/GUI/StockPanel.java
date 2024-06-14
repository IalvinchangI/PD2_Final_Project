package project.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import project.GUI.GUITools.StockDetail;

/**
 * 股票列、股票細節
 * @author Hanklo831
 */
public class StockPanel extends JPanel {
    private String[] stockNameList = {"AAPL", "GOOGL", "AMZN", "META", "MSFT", "TSLA"};
    private JList<String> stockList = null;

    //StockDetail sd = new StockDetail(getName(), 10.0, null);

    public StockPanel () {
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.stockList = new JList<>(this.stockNameList);
        this.stockList.setPreferredSize(new Dimension(100, 500));
        this.stockList.setFont(new Font("Serif", Font.PLAIN, 20));
        this.stockList.setAlignmentY(Component.TOP_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 10)));
        this.add (this.stockList);
        

        stockList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = stockList.locationToIndex(e.getPoint());
                if (index != -1) {
                    String selectedItem = stockList.getModel().getElementAt(index);
                    System.out.println(selectedItem);
                    //sd = new StockDetail(selectedItem, index, null);
                }
                
            }
        });

        //this.add (sd);
        
    }

    /**
     * 取得stockDetail
     * @return stockDetail
     */
    public StockDetail getStockDetail() {
        return sd;
    }
}

