import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

public class Grapher extends ApplicationFrame {
    public Grapher(String title) {
        super(title);

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createScatterPlot("Oberth Effect", "Elevation (km)", "Power (dek/dt)", dataset, PlotOrientation.VERTICAL, false, false, false);

        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {
        OberthMath oberthMath = new OberthMath();

        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series = new XYSeries("Oberth");
        for (double[] i : oberthMath.getPowers()) {
            series.add(i[0], i[1]);
        }

        dataset.addSeries(series);
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Grapher example = new Grapher("Oberth Effect");
            example.setSize(1200, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}