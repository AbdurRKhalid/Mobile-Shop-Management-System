/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.shop.management.system.project.database;

import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;



/**
 *
 * @author hp
 */
public class MobileShopManagementSystemProjectDatabase extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.getIcons().add(new Image(MobileShopManagementSystemProjectDatabase.class.getResourceAsStream("logo_transparent.png")));
        stage.setTitle("Saeed Mobile and Computer Shop");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
