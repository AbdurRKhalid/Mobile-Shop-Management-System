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
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import mobile.shop.management.system.project.database.HelperMethods.customerRecord;
import mobile.shop.management.system.project.database.HelperMethods.mobileDetails;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MobilePhonesStockWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        @FXML
    private ToggleGroup condition_group;

    @FXML
    private JFXRadioButton searchByCompanyRadioButton;

    @FXML
    private ToggleGroup mobilePhones_group;

    @FXML
    private JFXRadioButton searchByImeiNoRadioButton;

    @FXML
    private JFXRadioButton searchByModelRadioButton;

    @FXML
    private JFXRadioButton searchByCnicRadioButton;

    @FXML
    private JFXRadioButton searchBySellerNameRadioButton;

    @FXML
    private JFXTreeTableView<mobileDetails> mobileRecordTable;
    
    TreeItem<mobileDetails> root;
    @FXML
    private JFXTextField searchTextMobileRecord;
    @FXML
    private JFXTextField mobileModelGetter;
    @FXML
    private JFXTextField sellerNameGetter;
    @FXML
    private JFXTextField imeiNoGetter;
    @FXML
    private JFXTextField priceGetter;
    @FXML
    private JFXTextField cnicNoGetter;
    @FXML
    private JFXTextField addressGetter;
    @FXML
    private JFXDatePicker dateGetter;
    @FXML
    private JFXTimePicker timeGetter;
    @FXML
    private JFXRadioButton newConditionRadioButton;
    @FXML
    private JFXRadioButton badConditionRadioButton;
    @FXML
    private JFXRadioButton normalConditionRadioButton;
    @FXML
    private JFXRadioButton goodConditionRadioButton;
    @FXML
    private JFXRadioButton excellentConditionRadioButton;
    @FXML
    private JFXButton addMobileStockButton;
    @FXML
    private JFXButton searchButtonMobileRecord;
    @FXML
    private ChoiceBox companySelector;
    
    String selectedCompany;
    
    String mobileCondition = "New";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> companies = FXCollections.observableArrayList();
        HelperMethods helperMethods = new HelperMethods();
        companies = helperMethods.getVendors();
        companySelector.setItems(companies);
        
        companySelector.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
                selectedCompany = newValue.toString();
                System.out.println(selectedCompany);
        });
        
        JFXTreeTableColumn<mobileDetails, String> company = new JFXTreeTableColumn<>("Company");
        company.setPrefWidth(150);
        company.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileDetails, String> param) {
                return param.getValue().getValue().vendorName;
            }
        });
        
        
        
        JFXTreeTableColumn<mobileDetails, String> model = new JFXTreeTableColumn<>("Model");
        model.setPrefWidth(150);
        model.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileDetails, String> param) {
                return param.getValue().getValue().mobileModel;
            }
        });
        
        
        JFXTreeTableColumn<mobileDetails, String> imeiNo = new JFXTreeTableColumn<>("IMEI No");
        imeiNo.setPrefWidth(150);
        imeiNo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileDetails, String> param) {
                return param.getValue().getValue().mobileImeiNo;
            }
        });
        
        
        JFXTreeTableColumn<mobileDetails, String> condition = new JFXTreeTableColumn<>("Condition");
        condition.setPrefWidth(150);
        condition.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileDetails, String> param) {
                return param.getValue().getValue().mobileCondition;
            }
        });
        
        
        
        JFXTreeTableColumn<mobileDetails, String> boughtPrice = new JFXTreeTableColumn<>("Bought Price");
        boughtPrice.setPrefWidth(150);
        boughtPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileDetails, String> param) {
                return param.getValue().getValue().boughtPrice;
            }
        });
        
        
        JFXTreeTableColumn<mobileDetails, String> cnicNo = new JFXTreeTableColumn<>("Seller CNIN No");
        cnicNo.setPrefWidth(150);
        cnicNo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileDetails, String> param) {
                return param.getValue().getValue().sellerCnicNo;
            }
        });
        
        
        
        JFXTreeTableColumn<mobileDetails, String> sellerName = new JFXTreeTableColumn<>("Seller Name");
        sellerName.setPrefWidth(150);
        sellerName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileDetails, String> param) {
                return param.getValue().getValue().sellerName;
            }
        });
        
        
        
        JFXTreeTableColumn<mobileDetails, String> sellerAddress = new JFXTreeTableColumn<>("Seller Address");
        sellerAddress.setPrefWidth(150);
        sellerAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<mobileDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<mobileDetails, String> param) {
                return param.getValue().getValue().sellerAddress;
            }
        });
        
        HelperMethods methods = new HelperMethods();
        root = new RecursiveTreeItem<mobileDetails>(methods.getMobileRecord(), RecursiveTreeObject::getChildren);
        
        mobileRecordTable.getColumns().setAll(company,model,imeiNo,condition,boughtPrice,cnicNo,sellerName,sellerAddress);
        mobileRecordTable.setRoot(root);
        mobileRecordTable.setShowRoot(false);
        
    }

    @FXML
        public void searchMobileRecord(ActionEvent actionEvent) {
        if (searchByCompanyRadioButton.isSelected()) {
        root = null;
        String companyName = searchTextMobileRecord.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<mobileDetails>(helperMethods.getMobileRecordByCompany(companyName), RecursiveTreeObject::getChildren);
        mobileRecordTable.setRoot(root);
        mobileRecordTable.setShowRoot(false);
        searchTextMobileRecord.setText("");
        }
        
        
        if (searchByImeiNoRadioButton.isSelected()) {
        root = null;
        BigDecimal imei = new BigDecimal(searchTextMobileRecord.getText());
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<mobileDetails>(helperMethods.getMobileRecordByIMEINo(imei), RecursiveTreeObject::getChildren);
        mobileRecordTable.setRoot(root);
        mobileRecordTable.setShowRoot(false);
        searchTextMobileRecord.setText("");
        }
        
        if (searchByModelRadioButton.isSelected()) {
        root = null;
        String Model = searchTextMobileRecord.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<mobileDetails>(helperMethods.getMobileRecordByModel(Model), RecursiveTreeObject::getChildren);
        mobileRecordTable.setRoot(root);
        mobileRecordTable.setShowRoot(false);
        searchTextMobileRecord.setText("");
        }
        
        
        if (searchByCnicRadioButton.isSelected()) {
        root = null;
        BigDecimal cnicNo = new BigDecimal(searchTextMobileRecord.getText());
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<mobileDetails>(helperMethods.getMobileRecordByCnicNo(cnicNo), RecursiveTreeObject::getChildren);
        mobileRecordTable.setRoot(root);
        mobileRecordTable.setShowRoot(false);
        searchTextMobileRecord.setText("");
        }
        
        if (searchBySellerNameRadioButton.isSelected()) {
        root = null;
        String sellerName = searchTextMobileRecord.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<mobileDetails>(helperMethods.getMobileRecordBySellerName(sellerName), RecursiveTreeObject::getChildren);
        mobileRecordTable.setRoot(root);
        mobileRecordTable.setShowRoot(false);
        searchTextMobileRecord.setText("");
            }
        
        }
        
        public void addNewMobile(ActionEvent actionEvent) {
            //Seller Information.
            String sellerName = sellerNameGetter.getText();
            String cnicNo = cnicNoGetter.getText();
            String address = addressGetter.getText();
            LocalDate date = dateGetter.getValue();
            LocalTime time = timeGetter.getValue();
            
            
            //Mobile Phone Information.
            String model = mobileModelGetter.getText();
            BigDecimal imei = new BigDecimal(imeiNoGetter.getText());
            BigDecimal price = new BigDecimal(priceGetter.getText());
            if(newConditionRadioButton.isSelected()){
                mobileCondition = "New";
            }else if(badConditionRadioButton.isSelected()){
                mobileCondition = "Bad";
            }else if(normalConditionRadioButton.isSelected()){
                mobileCondition = "Normal";
            }else if(goodConditionRadioButton.isSelected()){
                mobileCondition = "Good";
            }else if(excellentConditionRadioButton.isSelected()) {
                mobileCondition = "Excellent";
            }
            
            HelperMethods helperMethods = new HelperMethods();
            helperMethods.addNewMobile(sellerName, new BigDecimal(cnicNo), address, date.toString(), time.toString(), model, imei, price, mobileCondition, selectedCompany);
            root = null;
            root = new RecursiveTreeItem<mobileDetails>(helperMethods.getMobileRecord(), RecursiveTreeObject::getChildren);
            mobileRecordTable.setRoot(root);
            mobileRecordTable.setShowRoot(false);
            
            sellerNameGetter.setText("");
            cnicNoGetter.setText("");
            addressGetter.setText("");
            dateGetter.setValue(LocalDate.now());
            timeGetter.setValue(LocalTime.now());
            mobileModelGetter.setText("");
            imeiNoGetter.setText("");
            priceGetter.setText("");
            selectedCompany = "";
            
            
            
            
        }
        
    }
        
