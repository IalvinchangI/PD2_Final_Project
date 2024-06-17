package project.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;

import project.GUI.GUITools.ChangeablePanel;
import project.System.HistoryRecord;
import project.System.StockDataSystem;

public class HistoryPanel extends JPanel{
    private String[] stockNameList = {"AAPL", "GOOGL", "AMZN", "META", "MSFT", "TSLA"};
    private JList<String> stockList = null;
    HistoryDetailPanel hdp;
    private ChangeablePanel changePage = null;
    private HashMap <String, HistoryDetailPanel> panelHashMap = new HashMap<>();


    public HistoryPanel(StockDataSystem stockDataSystem) {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.stockList = new JList<>(this.stockNameList);
        this.stockList.setPreferredSize(new Dimension(100, 500));
        this.stockList.setFont(new Font("Serif", Font.PLAIN, 20));
        this.stockList.setAlignmentY(Component.TOP_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 10)));
        this.add (this.stockList);
        this.changePage = new ChangeablePanel();
        this.add(this.changePage);
        hdp = new HistoryDetailPanel("AAPL", stockDataSystem);
        this.changePage.add(hdp, "AAPL");
        this.changePage.showPage("AAPL");
        this.panelHashMap.put("AADL", hdp);
        HistoryPanel historyPanel = this;

        stockList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = stockList.locationToIndex(e.getPoint());
                if (index != -1) {
                    String selectedItem = stockList.getModel().getElementAt(index);
                    hdp = new HistoryDetailPanel(selectedItem, stockDataSystem);
                    historyPanel.changePage.add(hdp, selectedItem);
                    historyPanel.changePage.showPage(selectedItem);
                    historyPanel.panelHashMap.put(selectedItem, hdp);
                }
                
            }
        });
    }

    public void updateHistory() {
        for (String str : this.panelHashMap.keySet()) {
            this.panelHashMap.get(str).updateHistoryDetail();
        }
    } 
    
}
