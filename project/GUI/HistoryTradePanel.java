package project.GUI;

import java.awt.BorderLayout
import javax.swing.*;

public class HistoryTradePanel extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Trade History");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(new HistoryTradePanel());
        frame.setVisible(true);
    }

    private JLabel historyTradeLabel = null;
    private JScrollPane historyTradeScrollPane = null;
    private String[] tradeRecord = {
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 200 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 300 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 400 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 500 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 600 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 700 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 800 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 900 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023", "aaa 100 2023", "aaa 100 2023", 
            "aaa 100 2023"
    };

    public HistoryTradePanel() {
        StringBuilder html = new StringBuilder("<html>");
        for (String str : tradeRecord) {
            html.append(str).append("<br>");
        }
        html.append("</html>");

        historyTradeLabel = new JLabel(html.toString());
        historyTradeScrollPane = new JScrollPane(historyTradeLabel);
        historyTradeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        historyTradeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(historyTradeScrollPane, BorderLayout.CENTER);
    }

    
}