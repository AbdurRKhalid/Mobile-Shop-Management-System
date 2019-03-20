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
import mobile.shop.management.system.project.database.HelperMethods.easyLoadRetailerDetails;
import mobile.shop.management.system.project.database.HelperMethods.easyLoadRetailerDetails;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EasyLoadStockAddWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ToggleGroup easyLoadStock_group;

    @FXML
    private JFXTreeTableView<easyLoadRetailerDetails> easyLoadRetailerTable;
    
    TreeItem<easyLoadRetailerDetails> root;
    @FXML
    private JFXRadioButton searcgByRetailerNameRadioButton;
    @FXML
    private JFXRadioButton searchByCompanyRadioButton;
    @FXML
    private JFXTextField retailerSearchText;
    @FXML
    private JFXButton searchEasyLoadRetailerRecord;
    @FXML
    private JFXTextField retailerNameGetter;
    @FXML
    private JFXTextField loadedAmountGetter;
    @FXML
    private JFXDatePicker loadedDateGetter;
    @FXML
    private JFXTimePicker loadedTimeGetter;
    @FXML
    private JFXButton addEasyLoadStockButton;
    @FXML
    private ChoiceBox companySelector;
    
    String selectedCompany;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> telecomCompanies = FXCollections.observableArrayList();
        HelperMethods helperMethods = new HelperMethods();
        telecomCompanies = helperMethods.getTelecomCompanies();
        companySelector.setItems(telecomCompanies);
        
        
        companySelector.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
                selectedCompany = newValue.toString();
        });
        
        
        // TODO
        JFXTreeTableColumn<easyLoadRetailerDetails, String> retailerName = new JFXTreeTableColumn<>("Name");
        retailerName.setPrefWidth(150);
        retailerName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String> param) {
                return param.getValue().getValue().retailerName;
            }
        });
        
        JFXTreeTableColumn<easyLoadRetailerDetails, String> amountRecharged = new JFXTreeTableColumn<>("Amount Recharged");
        amountRecharged.setPrefWidth(150);
        amountRecharged.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String> param) {
                return param.getValue().getValue().amountRecharged;
            }
        });
        
        
        JFXTreeTableColumn<easyLoadRetailerDetails, String> dateRecharged = new JFXTreeTableColumn<>("Date");
        dateRecharged.setPrefWidth(150);
        dateRecharged.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String> param) {
                return param.getValue().getValue().dateOfRecharged;
            }
        });
        
        JFXTreeTableColumn<easyLoadRetailerDetails, String> timeRecharged = new JFXTreeTableColumn<>("Time");
        timeRecharged.setPrefWidth(150);
        timeRecharged.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String> param) {
                return param.getValue().getValue().timeOfRecharged;
            }
        });
        
        JFXTreeTableColumn<easyLoadRetailerDetails, String> telecomCompany = new JFXTreeTableColumn<>("Telecom Company");
        telecomCompany.setPrefWidth(150);
        telecomCompany.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<easyLoadRetailerDetails, String> param) {
                return param.getValue().getValue().telecomCompanyRecharged;
            }
        });
        
        
        HelperMethods methods = new HelperMethods();
        root = new RecursiveTreeItem<easyLoadRetailerDetails>(methods.getEasyLoadRetailerRecord(), RecursiveTreeObject::getChildren);
        
        easyLoadRetailerTable.getColumns().setAll(retailerName, amountRecharged, dateRecharged,timeRecharged,telecomCompany);
        easyLoadRetailerTable.setRoot(root);
        easyLoadRetailerTable.setShowRoot(false);
        
    }

    @FXML
    public void searchEasyLoadRetailerRecord(ActionEvent actionEvent){
        if(searcgByRetailerNameRadioButton.isSelected()) {
        root = null;
        String name = retailerSearchText.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<easyLoadRetailerDetails>(helperMethods.getEasyLoadRetailerRecordByRetailerName(name), RecursiveTreeObject::getChildren);
        easyLoadRetailerTable.setRoot(root);
        easyLoadRetailerTable.setShowRoot(false);
        retailerSearchText.setText("");
        }
        
        if(searchByCompanyRadioButton.isSelected()){
        root = null;
        String company = retailerSearchText.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<easyLoadRetailerDetails>(helperMethods.getEasyLoadRetailerRecordByCompany(company), RecursiveTreeObject::getChildren);
        easyLoadRetailerTable.setRoot(root);
        easyLoadRetailerTable.setShowRoot(false);
        retailerSearchText.setText("");
        }
    }
    
    public void addEasyLoadRetailerRecord(ActionEvent actionEvent) {
        
        String loadDanayWalayKaName = retailerNameGetter.getText();
        int kitnayKaLoadDiya = Integer.parseInt(loadedAmountGetter.getText()) ;
        LocalDate kisDateKoLoadDiya = loadedDateGetter.getValue();
        LocalTime kisTimeKoLoadDiya = loadedTimeGetter.getValue();
        String kisCompanyKaLoadDiya = selectedCompany;
        
        HelperMethods helperMethods = new HelperMethods();
        helperMethods.addEasyLoadRetailerRecord(loadDanayWalayKaName, kitnayKaLoadDiya, kisDateKoLoadDiya.toString(), kisTimeKoLoadDiya.toString(), selectedCompany);
        
        root = new RecursiveTreeItem<easyLoadRetailerDetails>(helperMethods.getEasyLoadRetailerRecord(), RecursiveTreeObject::getChildren);
        easyLoadRetailerTable.setRoot(root);
        easyLoadRetailerTable.setShowRoot(false);
        retailerSearchText.setText("");
    }
    
}
