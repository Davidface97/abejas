/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.DepartamentoDAO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ChartServlet extends HttpServlet {

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
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        datos=dao.Bars();
        boolean checker1 = false;
        boolean checker2 = false;
        int kilos = 0;
        String id="";
        
        //int kilos1 = (Integer)datos.get(0);
        //String id1 = (String)datos.get(1);
        //int kilos2 = (Integer)datos.get(2);
        //String id2 = (String)datos.get(3);
        //int kilos3 = (Integer)datos.get(4);
        //String id3 = (String)datos.get(5);

        for(int i=0; i<datos.size(); i++){
            
            if(i== 0 || i%2==0){
                kilos = (Integer)datos.get(i);
                checker1 = true;
            } else {
                id = (String)datos.get(i);
                checker2 = true;
            }
            
            if(checker1==true && checker2== true){
                dataset.addValue(kilos, id, "Colmena "+(i+1));
                checker1 = false;
                checker2 = false;
            }
            
        }

        //dataset.addValue(kilos1, id1, "Colmena 1");
        //dataset.addValue(kilos2, id2, "Colmena 2");
        //dataset.addValue(kilos3, id3, "Colmena 3");
        
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Total Kilos Miel Por Colmena", // chart title
                "Colmena", // domain axis label
                "Kilos", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
        );

        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setItemLabelsVisible(true);
        BarRenderer r = (BarRenderer) renderer;
        r.setMaximumBarWidth(0.05);
        return chart;

    }

}
