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
import project.System.HistoryRecord;

public class HistoryPanel extends JPanel{
    private String[] stockNameList = {"AAPL", "GOOGL", "AMZN", "META", "MSFT", "TSLA"};
    private JList<String> stockList = null;
    HistoryDetailPanel hdp;

    public  HistoryPanel() {


        HistoryRecord historyRecord = new HistoryRecord();
        hdp = new HistoryDetailPanel("AAPL", historyRecord);
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
                    hdp = new HistoryDetailPanel(selectedItem, historyRecord);
                }
                
            }
        });
        this.add(hdp);
    }
    
}
