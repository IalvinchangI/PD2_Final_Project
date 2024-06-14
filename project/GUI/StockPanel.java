package project.GUI;

import java.awt.Dimension;
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

    StockDetail sd = new StockDetail(getName(), 10.0, null);

    public StockPanel () {
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.stockList = new JList<>(this.stockNameList);
        this.stockList.setPreferredSize(new Dimension(100, 800));
        
        this.add (this.stockList);
        this.add (sd);
    }

}

