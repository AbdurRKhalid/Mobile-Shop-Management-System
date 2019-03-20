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
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;
import mobile.shop.management.system.project.database.HelperMethods.mobileReceiptDetails;
import mobile.shop.management.system.project.database.HelperMethods.mobileReceiptDetails;
import mobile.shop.management.system.project.database.HelperMethods.receiptDetail;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MobilePhonesWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private JFXButton addReceiptButton;

    @FXML
    private JFXTextField customerNameGetter;

    @FXML
    private JFXTextField customerMobileNoGetter;

    @FXML
    private ChoiceBox companySelector;

    @FXML
    private ChoiceBox modelSelector;

    @FXML
    private Label boughtPriceLabel;

    @FXML
    private JFXTextField salePriceGetter;
    
    @FXML
    private JFXTreeTableView<mobileReceiptDetails> mobileReceiptTable;
    
    public TreeItem<mobileReceiptDetails> root;
    
    String companySelected;
    
    String modelSelected;
    
    public ArrayList<Integer> boughtPrice = new ArrayList<>();
    public ArrayList<Integer> sellPrices = new ArrayList<>();
    
    int total = 0;
    
    
    ObservableList<mobileReceiptDetails> receiptItems = FXCollections.observableArrayList();
    @FXML
    private Label totalPriceLabel;
    @FXML
    private JFXButton printReceiptButton;
    @FXML
    private JFXTextField customerCNICGetter;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        HelperMethods helperMethods = new HelperMethods();
        companySelector.setItems(helperMethods.getCompanies());
        
        
        companySelector.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
                companySelected = newValue.toString();
                modelSelector.setItems(helperMethods.getMobileModels(companySelected));
        });
        
        
        modelSelector.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
                modelSelected = newValue.toString();
                boughtPriceLabel.setText(helperMethods.getMobileBoughtPrice(modelSelected).toString());
        });
        
        
        JFXTreeTableColumn<mobileReceiptDetails, String> companyName = new JFXTreeTableColumn<>("Company");
        companyName.setPrefWidth(250);
        companyName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileReceiptDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileReceiptDetails, String> param) {
                return param.getValue().getValue().companyName;
            }
        });
        
        JFXTreeTableColumn<mobileReceiptDetails, String> modelName = new JFXTreeTableColumn<>("Model");
        modelName.setPrefWidth(250);
        modelName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileReceiptDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileReceiptDetails, String> param) {
                return param.getValue().getValue().mobileModel;
            }
        });
        
        JFXTreeTableColumn<mobileReceiptDetails, String> salePrice = new JFXTreeTableColumn<>("Sale Price");
        salePrice.setPrefWidth(250);
        salePrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileReceiptDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileReceiptDetails, String> param) {
                return param.getValue().getValue().mobilePrice;
            }
        });
        
        mobileReceiptTable.getColumns().setAll(companyName, modelName, salePrice);
        
        
    }

    @FXML
    public void addToReceipt(ActionEvent actionEvent){
        
        int sellPrice = Integer.parseInt(salePriceGetter.getText());
        int boughtPrice = Integer.parseInt(boughtPriceLabel.getText());
        total = total + sellPrice;
        totalPriceLabel.setText(Integer.toString(total));
        receiptItems.add(new mobileReceiptDetails(companySelected, modelSelected, Integer.toString(sellPrice)));
        
        
        this.boughtPrice.add(boughtPrice);
        this.sellPrices.add(sellPrice);  
        
         salePriceGetter.setText("");
         
         root = new RecursiveTreeItem<mobileReceiptDetails>(receiptItems, RecursiveTreeObject::getChildren);
         
         mobileReceiptTable.setRoot(root);
         mobileReceiptTable.setShowRoot(false);
         
    }
    
    @FXML
    public void printReceiptUpdateRecords(ActionEvent actionEvent){
        
        
        String name = customerNameGetter.getText();
        BigDecimal customerCNIC = new BigDecimal(customerCNICGetter.getText());
        
        if (name.isEmpty()) {
            name = "WALK IN CUSTOMER";
        }
        
        
        BigDecimal bd;
        String mobileNo = customerMobileNoGetter.getText();
        if (mobileNo.isEmpty()) {
            bd = new BigDecimal(0);
        }else{
            bd = new BigDecimal(mobileNo);
        }
        
         HelperMethods helperMethods = new HelperMethods();
        
         helperMethods.updateMobileRecord(boughtPrice, sellPrices, customerCNIC);
        
        helperMethods.insertCustomerFromReceipt(name, bd, total);
    }
    
    
}
