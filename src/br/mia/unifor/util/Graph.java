/*
 * Graph.java
 *
 * Created on September 29, 2007, 9:27 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.mia.unifor.util;

import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph {
    
    public static ChartPanel getGrafico(List<Map> list) {
        
        JFreeChart chart = ChartFactory.createBarChart("Cross Validation",
                "Range Fold", "Value error (%)", createDataset(list),
                PlotOrientation.VERTICAL, Boolean.TRUE, Boolean.TRUE,
                Boolean.FALSE);
        
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        
        return chartPanel;
    }
    
    private static CategoryDataset createDataset(List<Map> list) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(Map map: list){
            for(Object key: map.keySet()){
                dataset.addValue((Double)map.get(key), (String)key, "");
            }
        }
        
        return dataset;
    }
}