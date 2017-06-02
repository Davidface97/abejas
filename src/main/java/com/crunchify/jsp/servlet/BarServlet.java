package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.DepartamentoDAO;
import java.awt.Color;
import java.awt.Paint;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

public class BarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        JFreeChart chart = getChart();
        int width = 500;
        int height = 350;
        ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);

    }

    public JFreeChart getChart() {

        DepartamentoDAO dao = new DepartamentoDAO();
        ArrayList datos = new ArrayList();
        datos = dao.Pie();
        int Sumatoria=0;
        
        for(int i=0; i<datos.size(); i++){
        int paneles = (Integer) datos.get(i);
        Sumatoria=Sumatoria+paneles;
        }
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        //Crear la capa de servicios que se enlace con el DAO
        int alimentos = (Integer)datos.get(1);
        
        int tortaalimentos = (alimentos*100)/Sumatoria;
        int demas = ((Sumatoria-alimentos)*100)/Sumatoria;
        
        dataset.setValue("Otros Panales", demas);
        dataset.setValue("Paneles Con Alimento", tortaalimentos);

        boolean textoinferior = true;
        boolean tool = true;
        boolean urls = false;

        JFreeChart chart = ChartFactory.createPieChart("Kilos", dataset, textoinferior, tool, urls);

        return chart;
    }

}
