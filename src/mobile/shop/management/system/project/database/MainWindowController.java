/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.shop.management.system.project.database;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MainWindowController implements Initializable {

    @FXML
    private JFXButton receiptWindowOpner;
    @FXML
    private AnchorPane changingPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void receiptWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("receiptWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void easyLoadStockWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("easyLoadStockAddWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void accessoriesWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("accessoriesWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void easyLoadWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("easyLoadWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mobileRepairWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("mobileRepairWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void thingsToBuyWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("thingsToBuyWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void customerRecordWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("customerRecordWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mobilePhonesWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("mobilePhonesWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mobilePhonesStockWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("mobilePhonesStockWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reportsWindowLoader(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("reportsWindow.fxml"));
            changingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
