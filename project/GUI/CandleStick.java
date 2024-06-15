package project.GUI;

import java.awt.Color;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.reflect.Array;
import java.text.ParseException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import project.System.StockMarketInfoRecorder;



/**
 * 繪製K線圖
 * @author Hanklo831
 */
public class CandleStick extends JPanel {

    public ChartPanel CandleStick(String stockName, List<MarketInfo> stockHistoryPrice) {
        String xAxisLable = "time";
        String yAxisLable = "money";
        OHLCDataset dataset = createDataset(stockName, stockHistoryPrice);
        JFreeChart chart = ChartFactory.createCandlestickChart(stockName, xAxisLable, yAxisLable, dataset, false);
        TextTitle title = chart.getTitle();
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
  
        plot.setDomainAxis(new DateAxis("date"));
        plot.setRangeAxis(new NumberAxis("price"));

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MM-dd-yyyy"));




        CandlestickRenderer renderer = new CandlestickRenderer();
        plot.setRenderer(renderer);
        return new ChartPanel(chart);

    }

    private OHLCDataset createDataset(String stockName, List<MarketInfo> stockHistoryPrice) {

        ArrayList <Date> dates = new ArrayList<>();
        ArrayList <Double> highs = new ArrayList<>();
        ArrayList <Double> lows = new ArrayList<>();
        ArrayList <Double> opens = new ArrayList<>();
        ArrayList <Double> closes = new ArrayList<>();
        ArrayList <Double> volumes = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (MarketInfo tmp : stockHistoryPrice) {
            String dateString = tmp.getDate();
            Date date;
            try {
                date = dateFormat.parse(dateString);
            } catch(ParseException e) {
                e.printStackTrace();
            }
            dates.add(date);
            highs.add(tmp.getHighPrice());
            lows.add(tmp.getLowPrice());
            opens.add(tmp.getOpeningPrice());
            closes.add(tmp.getClosingPrice());
            highs.add(tmp.getHighPrice());
            volumes.add(0.0);

        }

        Date[] datesArray = new Date[dates.size()];
        double[] highsArray = new double[highs.size()];
        double[] lowsArray = new double[lows.size()];
        double[] opensArray = new double[opens.size()];
        double[] closesArray = new double[closes.size()];
        double[] volumesArray = new double[volumes.size()];

        for (int i = 0; i < dates.size(); i++) {
            datesArray[i] = dates.get(i);
            highsArray[i] = highs.get(i);
            lowsArray[i] = lows.get(i);
            opensArray[i] = opens.get(i);
            closesArray[i] = closes.get(i);
            volumesArray[i] = volumes.get(i);
        }

        return  new DefaultHighLowDataset(stockName, datesArray, highsArray, lowsArray, opensArray, closesArray, volumesArray);

    }
    
}
