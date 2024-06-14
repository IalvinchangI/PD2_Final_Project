package project.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 歷史紀錄的顯示
 * @author Hanklo831
 */
public class HistoryPanel  extends JPanel {

    private ProfitPanel pp = new ProfitPanel();
    private  HistoryTradePanel htp = new HistoryTradePanel();

    public HistoryPanel() {

        this.setPreferredSize(new Dimension (800, 600));
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
    private double profit = 10.01;
    private JLabel profitLabel = null;

    ProfitPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.profitTextLabel = new JLabel("profit and loss:");
        this.profitLabel = new JLabel(Double.toString(profit));

        this.profitTextLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        this.profitLabel.setFont(new Font("Serif", Font.PLAIN, 60));
        this.add(this.profitTextLabel);
        this.add(this.profitLabel);
        this.setPreferredSize(new Dimension(700, 150));
    }
    
}


/**
 * Panel of history trade record
 */
class HistoryTradePanel extends JPanel {

    private JLabel historyTradeLabel = null;
    private JScrollPane historyTradeScrollPane = null;
    private String[] tradeRecord = {"aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
    "aaa 200 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
    "aaa 300 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 400 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 500 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 600 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 700 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 800 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 900 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023",
    "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
    "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", };

    HistoryTradePanel() {
        StringBuilder html = new StringBuilder("<html>");
        for (String str : this.tradeRecord) {
            html.append(str).append("<br>");
        }
        html.append("</html>");
        this.historyTradeLabel = new JLabel(html.toString());
        this.historyTradeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        this.historyTradeScrollPane = new JScrollPane(this.historyTradeLabel);
        this.historyTradeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.historyTradeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.historyTradeScrollPane.setPreferredSize(new Dimension(700, 400));
        this.add(this.historyTradeScrollPane);

    }   
}