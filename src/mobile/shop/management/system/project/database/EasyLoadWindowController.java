/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.shop.management.system.project.database;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;
import mobile.shop.management.system.project.database.HelperMethods.customerRecord;
import mobile.shop.management.system.project.database.HelperMethods.easyLoadDetail;
import mobile.shop.management.system.project.database.HelperMethods.easyLoadDetail;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EasyLoadWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ToggleGroup easyLoad_Group;

    @FXML
    private JFXTreeTableView<easyLoadDetail> easyLoadTable;
    
    TreeItem<easyLoadDetail> root;
    @FXML
    private JFXRadioButton searchByNumberRadioButton;
    @FXML
    private JFXRadioButton searchByCompanyByRadioButton;
    @FXML
    private JFXTextField easyLoadSearchText;
    @FXML
    private JFXButton searchRecordButton;
    @FXML
    public ChoiceBox companySelector;
    @FXML
    private Label stockLabel;
    
    String selectedCompany;
    
    
    @FXML
    private JFXTextField easyLoadNumber;
    @FXML
    private JFXTextField easyLoadAmount;
    @FXML
    private JFXDatePicker easyLoadDate;
    @FXML
    private JFXTimePicker easyLoadTime;
    @FXML
    private JFXButton addEasyLoadButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Showing the Telecom Companies List and Showing the Respected Stock Left.
        ObservableList<String> telecomCompanies = FXCollections.observableArrayList();
        HelperMethods helperMethods = new HelperMethods();
        telecomCompanies = helperMethods.getTelecomCompanies();
        companySelector.setItems(telecomCompanies);
        
        companySelector.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
                selectedCompany = newValue.toString();
                String stock = Integer.toString(helperMethods.getStock(newValue.toString()));
                stockLabel.setText(stock + " Rs");
        });
        //Ending is here.
        
        
        
        // TODO
        JFXTreeTableColumn<easyLoadDetail, String> company = new JFXTreeTableColumn<>("Company");
        company.setPrefWidth(150);
        company.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadDetail, String> param) {
                return param.getValue().getValue().company;
            }
        });
        
        JFXTreeTableColumn<easyLoadDetail, String> amount = new JFXTreeTableColumn<>("Amount");
        amount.setPrefWidth(150);
        amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadDetail, String> param) {
                return param.getValue().getValue().amount;
            }
        });
        
        
        JFXTreeTableColumn<easyLoadDetail, String> date = new JFXTreeTableColumn<>("Date");
        date.setPrefWidth(150);
        date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadDetail, String> param) {
                return param.getValue().getValue().date;
            }
        });
        
        
        JFXTreeTableColumn<easyLoadDetail, String> time = new JFXTreeTableColumn<>("Time");
        time.setPrefWidth(150);
        time.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadDetail, String> param) {
                return param.getValue().getValue().time;
            }
        });
        
        JFXTreeTableColumn<easyLoadDetail, String> mobile = new JFXTreeTableColumn<>("Mobile No");
        mobile.setPrefWidth(150);
        mobile.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadDetail, String> param) {
                return param.getValue().getValue().mobileNo;
            }
        });
        
        HelperMethods methods = new HelperMethods();
        root = new RecursiveTreeItem<easyLoadDetail>(methods.getEasyLoadRecord(), RecursiveTreeObject::getChildren);
        
        easyLoadTable.getColumns().setAll(company, amount, date,time,mobile);
        easyLoadTable.setRoot(root);
        easyLoadTable.setShowRoot(false);
        

    }    
    
    @FXML
    public void searchEasyLoadRecord(ActionEvent actionEvent) {
        if(searchByNumberRadioButton.isSelected()) {
        root = null;
        BigDecimal mobileNumber = new BigDecimal(easyLoadSearchText.getText());
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<easyLoadDetail>(helperMethods.getEasyLoadRecordByMobileNumber(mobileNumber), RecursiveTreeObject::getChildren);
        easyLoadTable.setRoot(root);
        easyLoadTable.setShowRoot(false);
        easyLoadSearchText.setText("");
        }
        
        if(searchByCompanyByRadioButton.isSelected()) {
        root = null;
        String Company = easyLoadSearchText.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<easyLoadDetail>(helperMethods.getEasyLoadRecordByCompany(Company), RecursiveTreeObject::getChildren);
        easyLoadTable.setRoot(root);
        easyLoadTable.setShowRoot(false);
        easyLoadSearchText.setText("");
        }
    }
    
    public void addNewEasyLoadRecrod(ActionEvent actionEvent) {
        String Company = selectedCompany;
        BigDecimal number = new BigDecimal(easyLoadNumber.getText());
        int amount = Integer.parseInt(easyLoadAmount.getText());
        LocalDate date = easyLoadDate.getValue();
        LocalTime time = easyLoadTime.getValue();
       
        HelperMethods addNewEasyLoadRecord = new HelperMethods();
        addNewEasyLoadRecord.insertEasyLoadRecord(selectedCompany, amount, date.toString(), time.toString(), number);
        
        int updatedStock = addNewEasyLoadRecord.getStock(Company);
        stockLabel.setText(Integer.toString(updatedStock) + " Rs");
        
        root = null;
        root = new RecursiveTreeItem<easyLoadDetail>(addNewEasyLoadRecord.getEasyLoadRecord(), RecursiveTreeObject::getChildren);
        easyLoadTable.setRoot(root);
        easyLoadTable.setShowRoot(false);
    } 
    
}
