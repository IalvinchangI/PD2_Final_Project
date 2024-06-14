package project.GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 歷史紀錄的顯示
 * @author Hanklo
 */
public class HistoryPanel  extends JPanel {
    
}

/**
 *  Panel of profit and loss
 */
class ProfitPanel extends JPanel {

    private JPanel profitPanel;
    private double profit = 10.01;
    private JLabel profitLabel;

    ProfitPanel () {
        profitPanel = new JPanel();
        profitLabel = new JLabel(Double.toString(profit));
        profitPanel.add(profitLabel);
    }
    
}


/**
 * Panel of history trade record
 */
class HistoryTradePanel extends JPanel {

    private JPanel historyTradePanel;
    private JLabel historyTradeLabel;
    private JScrollPane historyTradeScrollPane;
    private String[] tradeRecord = {"aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
    "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
    "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", "aaa 100 2023"};

    HistoryTradePanel () {
        StringBuilder html = new StringBuilder("<html>");
        for (String str : tradeRecord) {
            html.append(str).append("<br>");
        }
        html.append("</html>");
        historyTradeLabel = new JLabel(html.toString());
        historyTradeScrollPane = new JScrollPane(historyTradeLabel);
        historyTradePanel.add(historyTradeScrollPane);

    }   
}