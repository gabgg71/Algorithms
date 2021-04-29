package vista;

import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import modelo.Persona;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class GraficoContagiados extends JFrame{
    JPanel panel;
    static ArrayList<Persona> per;

     public GraficoContagiados(ArrayList<Persona> per, String title) {
        this.per = per;
        setTitle("Grafico 1");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        inicializa();
    }
     
    public void inicializa(){
        panel = new JPanel();
        getContentPane().add(panel);
        DefaultPieDataset data = new DefaultPieDataset();
        int enfermos = 0, sanos = 0;
        for (Persona p : per) {
            if (p.getEstado() == 1) {
                enfermos += 1;
            } else {
                sanos += 1;
            }
        }
        data.setValue("Enfermos", enfermos);
        data.setValue("Sanos", sanos);
        
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
                "Ejemplo Rapido de Grafico en un ChartFrame",
                data,
                true,
                true,
                false);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }

}
