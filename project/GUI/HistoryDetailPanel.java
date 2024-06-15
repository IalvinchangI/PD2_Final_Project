package project.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import project.System.HistoryRecord;
import project.System.Deal;

/**
 * 歷史紀錄的顯示
 * @author Hanklo831
 */
public class HistoryDetailPanel  extends JPanel {

    private ProfitPanel pp = new ProfitPanel();
    private  HistoryTradePanel htp = null;

    public HistoryDetailPanel(String stockName) {

        this.htp = new HistoryTradePanel(stockName);
        this.setPreferredSize(new Dimension (600, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.pp.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(this.pp);
        this.add(Box.createHorizontalStrut(10));
        this.add(this.htp);
        

    }

}

/**
 *  Panel of profit and loss
 */
class ProfitPanel extends JPanel {

    private JLabel profitTextLabel = null;
    private double profit = getProfitAndLoss();
    private JLabel profitLabel = null;

    ProfitPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.profitTextLabel = new JLabel("profit and loss:");
        this.profitLabel = new JLabel(Double.toString(profit));

        this.profitTextLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        this.profitLabel.setFont(new Font("Serif", Font.PLAIN, 60));
        this.add(this.profitTextLabel);
        this.add(this.profitLabel);
        this.setPreferredSize(new Dimension(550, 150));
    }
    
}


/**
 * Panel of history trade record
 */
class HistoryTradePanel extends JPanel {


    private JLabel historyTradeLabel = null;
    private JScrollPane historyTradeScrollPane = null;
    
    

    HistoryTradePanel(String stockName) {
        List <Deal> tradeRecord = getRecords(stockName);
        StringBuilder html = new StringBuilder("<html>");
        for (Deal tmp : tradeRecord) {
            String date = tmp.getDateAndTime();
            double profit = tmp.getProfitAndLoss();
            int stockCount = tmp.getStockCount();
            boolean buy = true;
            if (profit > 0) {
                buy = false;
            }
            else {
                profit *= (-1);
            }
            double stockPrice = (double) profit / stockCount;
            String detail = "";
            if (buy) {
                detail = "On " + date + ", buy " +  Integer.toString(stockCount) + "shares of " + stockName + " at " + String.format("%.2f", stockPrice) + " per share.";
            }
            else {
                detail = "On " + date + ", sell " +  Integer.toString(stockCount) + "shares of " + stockName + " at " + String.format("%.2f", stockPrice) + " per share.";
            }
            html.append(detail).append("<br>");
        }
        html.append("</html>");
        this.historyTradeLabel = new JLabel(html.toString());
        this.historyTradeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        this.historyTradeScrollPane = new JScrollPane(this.historyTradeLabel);
        this.historyTradeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.historyTradeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.historyTradeScrollPane.setPreferredSize(new Dimension(550, 300));
        this.add(this.historyTradeScrollPane);

    }   
}