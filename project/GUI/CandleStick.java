package project.GUI;

import java.lang.management.PlatformLoggingMXBean;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;


/**
 * 繪製K線圖
 * @author Hanklo831
 */
public class CandleStick extends JPanel {

    public CandleStick(String stockName, ArrayList <HashMap <String, Double>> stockHistoryPrice) {
        String xAxisLable = "time";
        String yAxisLable = "money";
        OHLCDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createCandlestickChart(stockName, xAxisLable, yAxisLable, dataset, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainAxes(new DateAxis("date"));
        plot.setRangeAxes(new NumberAxis("price"));

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MM-dd-yyyy"));

        CandlestickRenderer renderer = new CandlestickRenderer();
        plot.setRenderer(renderer);
        return new ChartPanel(chart);

    }

    private OHLCDataset createDataset() {
        Date[] dates = new Date[] {

        };
        double[] highs = new double[]{};
        double[] lows = new double[]{};
        double[] opens = new double[]{};
        double[] closes = new double[]{};
        double[] volumes = new double[]{};

        return  new DefaultHighLowDataset("股票數據", dates, highs, lows, opens, closes, volumes);

    }
    
}
