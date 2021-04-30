package vista;

import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoMuertes extends JFrame {

    JPanel panel;
    ArrayList<Integer> muertes;

    public GraficoMuertes(ArrayList<Integer> muertes) {
        this.muertes = muertes;
        setTitle("Grafico 1");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        init();
    }

    private void init() {
        panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        for (int i = 0; i < muertes.size(); i++) {
            line_chart_dataset.addValue(muertes.get(i), "Frecuencia", ""+(i+1));
        }

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart("Tendencias de muerte",
                "Dia", "Frecuencia", line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }


}
