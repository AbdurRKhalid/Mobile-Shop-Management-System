/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.shop.management.system.project.database;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;
import mobile.shop.management.system.project.database.HelperMethods.receiptDetail;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ReceiptWindowController implements Initializable {
    
    @FXML
    private Label stockLabel;

    @FXML
    private Label pricePerUnitLabel;

    @FXML
    private JFXButton addToReceiptButton;

    @FXML
    private JFXTextField noOfUnitGetter;

    @FXML
    private JFXTextField customerNameGetter;

    @FXML
    private JFXTextField customerMobileNoGetter;

    @FXML
    private JFXTreeTableView<receiptDetail> receiptItemTable;

    @FXML
    private ChoiceBox categorySelector;

    @FXML
    private ChoiceBox modelSelector;
    
    
    public String Category;
    
    public String model;
    
    public ObservableList<String> categories;
    
    public ObservableList<String> models;
    
    public TreeItem<receiptDetail> root;
    
    ObservableList<receiptDetail> receiptItems = FXCollections.observableArrayList();
    int total;
    int actualTotal;
    
    
    
    public ArrayList<String> namesOfItems = new ArrayList<>();
    public ArrayList<Integer> unitsOfItems = new ArrayList<>();
    @FXML
    private Label totalLabel;
    @FXML
    private JFXTextField discountGetter;
    @FXML
    private Label afterDiscountLabel;
    @FXML
    private JFXButton printReceiptButton;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        categories = FXCollections.observableArrayList();
        models = FXCollections.observableArrayList();
        HelperMethods helperMethods = new HelperMethods();
        
        categories = helperMethods.getCategories();
        categorySelector.setItems(categories);
        
        categorySelector.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
                Category = newValue.toString();
                models = helperMethods.modelsGetter(Category);
                modelSelector.setItems(models);
        });
        
        modelSelector.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
               model = newValue.toString();
               if (helperMethods.getAccessoryStock(model) == 0) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning alert");
 
        // Header Text: null
                        alert.setHeaderText(null);
                        alert.setContentText("There are No Stock Left for This Current Accessory!");
 
                        alert.showAndWait();
            }
               stockLabel.setText(Integer.toString(helperMethods.getAccessoryStock(model)));
               pricePerUnitLabel.setText(Integer.toString(helperMethods.getAccessoryPricePerUnit(model)));
        });
        
        JFXTreeTableColumn<receiptDetail, String> itemName = new JFXTreeTableColumn<>("Item Name");
        itemName.setPrefWidth(215);
        itemName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<receiptDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<receiptDetail, String> param) {
                return param.getValue().getValue().nameOfAccessory;
            }
        });
        
        JFXTreeTableColumn<receiptDetail, String> pricePerUnit = new JFXTreeTableColumn<>("Price Per Unit");
        pricePerUnit.setPrefWidth(215);
        pricePerUnit.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<receiptDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<receiptDetail, String> param) {
                return param.getValue().getValue().pricePerUnit;
            }
        });
        
        JFXTreeTableColumn<receiptDetail, String> units = new JFXTreeTableColumn<>("No of Units");
        units.setPrefWidth(215);
        units.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<receiptDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<receiptDetail, String> param) {
                return param.getValue().getValue().quantityOfAccessory;
            }
        });
        
        JFXTreeTableColumn<receiptDetail, String> subPrice = new JFXTreeTableColumn<>("Price Of Quantity");
        subPrice.setPrefWidth(215);
        subPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<receiptDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<receiptDetail, String> param) {
                return param.getValue().getValue().priceOfQuantity;
            }
        });
//        
//        root =  new RecursiveTreeItem<receiptDetail>(receiptItems, RecursiveTreeObject::getChildren);
        receiptItemTable.getColumns().setAll(itemName, pricePerUnit, units,subPrice);

    }    
    
    @FXML
    public void addItemsToReceipt(ActionEvent actionEvent){
        String itemName = model;
        int pricePerUnit = Integer.parseInt(pricePerUnitLabel.getText());
        int quantity = Integer.parseInt(noOfUnitGetter.getText());
        int subToatl = pricePerUnit * quantity;
        total = total + subToatl;
        
        totalLabel.setText(Integer.toString(total));
        
        
        System.out.println(itemName);
        System.out.println(pricePerUnit);
        System.out.println(quantity);
        System.out.println(subToatl);
        
        namesOfItems.add(itemName);
        unitsOfItems.add(quantity);
        
        receiptItems.add(new receiptDetail(itemName, Integer.toString(quantity), Integer.toString(subToatl), Integer.toString(pricePerUnit)));
        root =  new RecursiveTreeItem<receiptDetail>(receiptItems, RecursiveTreeObject::getChildren);
        receiptItemTable.setRoot(root);
        receiptItemTable.setShowRoot(false);
    }
    
    @FXML
    public void printRceiptAndSaveData(ActionEvent actionEvent){
        String customerName = customerNameGetter.getText();
        BigDecimal bd;
        String mobileNo = customerMobileNoGetter.getText();
        if (mobileNo.isEmpty()) {
            bd = new BigDecimal(0);
        }else{
            bd = new BigDecimal(mobileNo);
        }
        
        if (customerName.isEmpty()) {
            customerName = "WALK IN CUSTOMER";
        }
        
        int totalOfOrder = actualTotal;
        
        HelperMethods helperMethods = new HelperMethods();
        
        helperMethods.updateAccessoryStockFromReceiptWindow(namesOfItems, unitsOfItems);
        
        helperMethods.insertCustomerFromReceipt(customerName, bd, totalOfOrder);
        
        
        totalLabel.setText("");
        afterDiscountLabel.setText("");
        customerNameGetter.setText("");
        customerMobileNoGetter.setText("");
        
        
    }
    
    public void actualTotalCalculation(ActionEvent actionEvent) {
        int discountAmountGetter;
        if (discountGetter.getText() == "") {
            discountAmountGetter = 0;
        actualTotal = total - discountAmountGetter;
        afterDiscountLabel.setText(Integer.toString(actualTotal));
        }else{
        discountAmountGetter = Integer.parseInt(discountGetter.getText());
        actualTotal = total - discountAmountGetter;
        afterDiscountLabel.setText(Integer.toString(actualTotal));
        

        
        
        }
    }
    
}
