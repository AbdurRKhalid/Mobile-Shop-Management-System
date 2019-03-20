/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.shop.management.system.project.database;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ReportsWindowController implements Initializable {

    @FXML
    private BarChart<?, ?> salesBarGraph;
    @FXML
    private NumberAxis saleAxis;
    @FXML
    private CategoryAxis monthAxis;
    @FXML
    private Label dailySalesLabel;
    @FXML
    private Label weeklySalesLabel;
    @FXML
    private Label monthlySalesLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        HelperMethods helperMethods = new HelperMethods();
        String currentMonthSales = Integer.toString(helperMethods.getCurrentMothSales());
        monthlySalesLabel.setText(currentMonthSales + " Rs");
        String  currentDaySales = Integer.toString(helperMethods.getCurrentDaySales());
        dailySalesLabel.setText(currentDaySales + " Rs");
        
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        helperMethods.getMonthSlaes();
        
        ArrayList<Integer> sales = helperMethods.getMonthSlaes();
                
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("January - " + currentYear, sales.get(0)));
        set1.getData().add(new XYChart.Data("Feburary - " + currentYear, sales.get(1)));
        set1.getData().add(new XYChart.Data("March - " + currentYear, sales.get(2)));
        set1.getData().add(new XYChart.Data("April - " + currentYear, sales.get(3)));
        set1.getData().add(new XYChart.Data("May - " + currentYear, sales.get(4)));
        set1.getData().add(new XYChart.Data("June - " + currentYear, sales.get(5)));
        set1.getData().add(new XYChart.Data("July - " + currentYear, sales.get(6)));
        set1.getData().add(new XYChart.Data("August - " + currentYear, sales.get(7)));
        set1.getData().add(new XYChart.Data("September - " + currentYear, sales.get(8)));
        set1.getData().add(new XYChart.Data("October - " + currentYear, sales.get(9)));
        set1.getData().add(new XYChart.Data("November - " + currentYear, sales.get(10)));
        set1.getData().add(new XYChart.Data("December - " + currentYear, sales.get(11)));
        
        salesBarGraph.getData().addAll(set1);
    }    
    
}
