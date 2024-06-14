package project.GUI;

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
import java.util.Date;
import java.util.TimeZone;

public class CandleStick extends JFrame {

    public CandleSticl(String title) {
        super(title);

        // 创建数据集
        OHLCDataset dataset = createDataset();

        // 创建图表
        JFreeChart chart = createChart(dataset);

        // 将图表放入面板
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));
        setContentPane(chartPanel);
    }

    private OHLCDataset createDataset() {
        // 示例数据
        Date[] date = new Date[5];
        double[] high = new double[5];
        double[] low = new double[5];
        double[] open = new double[5];
        double[] close = new double[5];
        double[] volume = new double[5];

        // 初始化数据
        for (int i = 0; i < 5; i++) {
            date[i] = new Date(2023, 1, i + 1);
            high[i] = 100 + i;
            low[i] = 90 - i;
            open[i] = 95 + i;
            close[i] = 98 + i;
            volume[i] = 1000 * (i + 1);
        }

        return new DefaultHighLowDataset("Stock Data", date, high, low, open, close, volume);
    }

    private JFreeChart createChart(OHLCDataset dataset) {
        // 创建图表
        JFreeChart chart = ChartFactory.createCandlestickChart(
                "K Line Chart Example", "Date", "Price", dataset, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);

        // 使用CandlestickRenderer来绘制K线图
        CandlestickRenderer renderer = new CandlestickRenderer();
        plot.setRenderer(renderer);

        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KLineChartExample example = new KLineChartExample("K Line Chart Example");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
