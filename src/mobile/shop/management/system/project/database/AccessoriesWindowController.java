/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.shop.management.system.project.database;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;
import mobile.shop.management.system.project.database.HelperMethods.accessoryDetail;
import mobile.shop.management.system.project.database.HelperMethods.accessoryDetail;
import mobile.shop.management.system.project.database.HelperMethods.customerRecord;

/**
 * FXML Controller class
 *
 * @author hp
 */

public class AccessoriesWindowController implements Initializable {
    @FXML
    private JFXTreeTableView<accessoryDetail> accessoryTable;
    TreeItem<accessoryDetail> root;
    @FXML
    private JFXRadioButton searchByTypeRadioButton;
    @FXML
    private ToggleGroup accessories_Group;
    @FXML
    private JFXTextField searchTextAccessory;
    @FXML
    private JFXRadioButton searchByPricePerUnitRadioButton;
    @FXML
    private JFXTextField noOfUnitsTextGetter;
    @FXML
    private JFXTextField modelTextGetter;
    @FXML
    private JFXTextField pricePerUnitTextGetter;
    @FXML
    private JFXDatePicker dateSelectore;
    @FXML
    private JFXButton addAccessoriesStockButton;
    @FXML
    private JFXButton searchButton;
    @FXML
    private ChoiceBox categorySelector;
    
    String selectedCategory;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> accessoriesCategories = FXCollections.observableArrayList();
        HelperMethods helperMethods = new HelperMethods();
        accessoriesCategories = helperMethods.getCategories();
        categorySelector.setItems(accessoriesCategories);
        
        
        categorySelector.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
                selectedCategory = newValue.toString();
        });
        
        // TODO
        JFXTreeTableColumn<accessoryDetail, String> category = new JFXTreeTableColumn<>("Category");
        category.setPrefWidth(200);
        category.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<accessoryDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<accessoryDetail, String> param) {
                return param.getValue().getValue().category;
            }
        });
        
        
        JFXTreeTableColumn<accessoryDetail, String> name = new JFXTreeTableColumn<>("Accessory Model");
        name.setPrefWidth(200);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<accessoryDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<accessoryDetail, String> param) {
                return param.getValue().getValue().Name;
            }
        });
        
        
        JFXTreeTableColumn<accessoryDetail, String> pricePerUnit = new JFXTreeTableColumn<>("Price Per Unit");
        pricePerUnit.setPrefWidth(200);
        pricePerUnit.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<accessoryDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<accessoryDetail, String> param) {
                return param.getValue().getValue().pricePerUnit;
            }
        });
        
        JFXTreeTableColumn<accessoryDetail, String> stock = new JFXTreeTableColumn<>("Stock Left");
        stock.setPrefWidth(225);
        stock.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<accessoryDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<accessoryDetail, String> param) {
                return param.getValue().getValue().stock;
            }
        });
        
        HelperMethods methods = new HelperMethods();
        root = new RecursiveTreeItem<accessoryDetail>(methods.getAccessoryRecords(), RecursiveTreeObject::getChildren);
        accessoryTable.getColumns().setAll(category, name, pricePerUnit,stock);
        accessoryTable.setRoot(root);
        accessoryTable.setShowRoot(false);   
    }   
    
    @FXML
    public void accessorySearch(ActionEvent actionEvent){
        if (searchByTypeRadioButton.isSelected()) {
        root = null;
        String textSearchByType = searchTextAccessory.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<accessoryDetail>(helperMethods.getAccessoryRecordsByTypes(textSearchByType), RecursiveTreeObject::getChildren);
        accessoryTable.setRoot(root);
        accessoryTable.setShowRoot(false);
        searchTextAccessory.setText("");
        }
        
        if(searchByPricePerUnitRadioButton.isSelected()){
        root = null;
        int textSearchByType = Integer.parseInt(searchTextAccessory.getText());
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<accessoryDetail>(helperMethods.getAccessoryRecordsByPricePerUnit(textSearchByType), RecursiveTreeObject::getChildren);
        accessoryTable.setRoot(root);
        accessoryTable.setShowRoot(false);
        searchTextAccessory.setText("");
        }
    }
    
    public void updateOrAddNewAccessoryStock(ActionEvent actionEvent){
        
        String accessoryName = modelTextGetter.getText();
        int pricePerUnit = Integer.parseInt(pricePerUnitTextGetter.getText());
        int newStock = Integer.parseInt(noOfUnitsTextGetter.getText());
        LocalDate date = dateSelectore.getValue();
        
        HelperMethods helperMethods = new HelperMethods();
        helperMethods.updateAccessoryStockOrAddNewAccessory(accessoryName, newStock, pricePerUnit, date.toString(), selectedCategory);
        
        
        root = null;
        root = new RecursiveTreeItem<accessoryDetail>(helperMethods.getAccessoryRecords(), RecursiveTreeObject::getChildren);
        accessoryTable.setRoot(root);
        accessoryTable.setShowRoot(false);
        
        modelTextGetter.setText("");
        pricePerUnitTextGetter.setText("");
        noOfUnitsTextGetter.setText("");
        selectedCategory = "";
    }
    
}
