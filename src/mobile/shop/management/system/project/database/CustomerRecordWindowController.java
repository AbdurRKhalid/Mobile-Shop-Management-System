/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.shop.management.system.project.database;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;
import mobile.shop.management.system.project.database.HelperMethods.customerRecord;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CustomerRecordWindowController implements Initializable {
    
    
    @FXML
    private JFXTextField textGetter;
    
    @FXML
    private ToggleGroup customerRecord_group;

    @FXML
    private JFXTreeTableView<customerRecord> customerTable;
    
    TreeItem<customerRecord> root;
    @FXML
    private JFXRadioButton searchByNameRadioButton;
    @FXML
    private JFXRadioButton searchByReceiptNoRadioButton;
    @FXML
    private JFXRadioButton searchByMobileNoRadioButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JFXTreeTableColumn<customerRecord, String> receiptNo = new JFXTreeTableColumn<>("Receipt No");
        receiptNo.setPrefWidth(150);
        receiptNo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerRecord, String> param) {
                return param.getValue().getValue().receiptNo;
            }
        });
        
        
        JFXTreeTableColumn<customerRecord, String> Name = new JFXTreeTableColumn<>("Customer Name");
        Name.setPrefWidth(150);
        Name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerRecord, String> param) {
                return param.getValue().getValue().customerName;
            }
        });
        
        
        JFXTreeTableColumn<customerRecord, String> mobileNo = new JFXTreeTableColumn<>("Mobile No");
        mobileNo.setPrefWidth(150);
        mobileNo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerRecord, String> param) {
                return param.getValue().getValue().mobileNo;
            }
        });
        
        
        JFXTreeTableColumn<customerRecord, String> date = new JFXTreeTableColumn<>("Date");
        date.setPrefWidth(150);
        date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerRecord, String> param) {
                return param.getValue().getValue().date;
            }
        });
        
        
        JFXTreeTableColumn<customerRecord, String> time = new JFXTreeTableColumn<>("Time");
        time.setPrefWidth(150);
        time.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerRecord, String> param) {
                return param.getValue().getValue().time;
            }
        });
        
        
        JFXTreeTableColumn<customerRecord, String> total = new JFXTreeTableColumn<>("Receipt Total");
        total.setPrefWidth(150);
        total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerRecord, String> param) {
                return param.getValue().getValue().total;
            }
        });
        
        JFXTreeTableColumn<customerRecord, String> updatePcs = new JFXTreeTableColumn<>("Update Pcs");
        total.setPrefWidth(150);
        total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerRecord, String> param) {
                return param.getValue().getValue().total;
            }
        });
        
        
        
        
        HelperMethods methods = new HelperMethods();
        root = new RecursiveTreeItem<customerRecord>(methods.getCustomerRecords(), RecursiveTreeObject::getChildren);
        
        customerTable.getColumns().setAll(receiptNo, Name, mobileNo,date,time,total);
        customerTable.setRoot(root);
        customerTable.setShowRoot(false);
    }
    
    public void showCustomerRecordByReceiptNo(ActionEvent actionEvent) {
        if(searchByReceiptNoRadioButton.isSelected()){
        root = null;
        int receiptNo = Integer.parseInt(textGetter.getText());
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<customerRecord>(helperMethods.getCustomerRecordByReceiptNo(receiptNo), RecursiveTreeObject::getChildren);
        customerTable.setRoot(root);
        customerTable.setShowRoot(false);
        textGetter.setText("");
        }
        
        if(searchByNameRadioButton.isSelected()) {
        root = null;
        String Name = textGetter.getText();
        HelperMethods helperMethods = new HelperMethods();
        root =new RecursiveTreeItem<customerRecord>(helperMethods.getCustomerRecordsByName(Name), RecursiveTreeObject::getChildren);
        customerTable.setRoot(root);
        customerTable.setShowRoot(false);
        textGetter.setText("");
        }
        
        if(searchByMobileNoRadioButton.isSelected()) {
        root = null;
        BigDecimal MobileNo = new BigDecimal(textGetter.getText());
        HelperMethods helperMethods = new HelperMethods();
        root =new RecursiveTreeItem<customerRecord>(helperMethods.getCustomerRecordsByMobileNo(MobileNo), RecursiveTreeObject::getChildren);
        customerTable.setRoot(root);
        customerTable.setShowRoot(false);
        textGetter.setText("");
        }
        
        
        
    }
    
}


