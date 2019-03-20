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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import mobile.shop.management.system.project.database.HelperMethods.HelperMethods;
import mobile.shop.management.system.project.database.HelperMethods.customerRecord;
import mobile.shop.management.system.project.database.HelperMethods.repairRequestDetails;
import mobile.shop.management.system.project.database.HelperMethods.repairRequestDetails;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MobileRepairWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private JFXRadioButton searchByDeviceNameRadioButton;

    @FXML
    private ToggleGroup mobileRepair_Group;

    @FXML
    private JFXRadioButton searchByReceivedDateRadioButton;

    @FXML
    private JFXTextField repairRequestSearchText;

    @FXML
    private JFXTreeTableView<repairRequestDetails> repairRequestTable;
    
    TreeItem<repairRequestDetails> root;
    @FXML
    private JFXTextField repairDeviceGetter;
    @FXML
    private JFXTextField repairChargesGetter;
    @FXML
    private JFXDatePicker receivedDateGetter;
    @FXML
    private JFXDatePicker returnedDateGetter;
    @FXML
    private JFXButton addRepairRequestToRecordButton;
    @FXML
    private JFXButton searchButtonRepairRequest;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JFXTreeTableColumn<repairRequestDetails, String> receivedDate = new JFXTreeTableColumn<>("Received Date");
        receivedDate.setPrefWidth(250);
        receivedDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<repairRequestDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<repairRequestDetails, String> param) {
                return param.getValue().getValue().receivedDate;
            }
        });
        
        
        JFXTreeTableColumn<repairRequestDetails, String> returnDate = new JFXTreeTableColumn<>("Returned Date");
        returnDate.setPrefWidth(250);
        returnDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<repairRequestDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<repairRequestDetails, String> param) {
                return param.getValue().getValue().returnedDate;
            }
        });
        
        
        JFXTreeTableColumn<repairRequestDetails, String> deviceName = new JFXTreeTableColumn<>("Device Name");
        deviceName.setPrefWidth(250);
        deviceName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<repairRequestDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<repairRequestDetails, String> param) {
                return param.getValue().getValue().deviceName;
            }
        });
        
        JFXTreeTableColumn<repairRequestDetails, String> charges = new JFXTreeTableColumn<>("Charges");
        charges.setPrefWidth(225);
        charges.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<repairRequestDetails, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<repairRequestDetails, String> param) {
                return param.getValue().getValue().charges;
            }
        });
        
        HelperMethods methods = new HelperMethods();
        root = new RecursiveTreeItem<repairRequestDetails>(methods.getRepairRequestRecords(), RecursiveTreeObject::getChildren);
        
        repairRequestTable.getColumns().setAll(receivedDate, returnDate, deviceName,charges);
        repairRequestTable.setRoot(root);
        repairRequestTable.setShowRoot(false);
    }    
    
    @FXML
    public void searchRepairRequestRecord(ActionEvent actionEvent) {
        if (searchByDeviceNameRadioButton.isSelected()) {
        root = null;
        String DeviceName = repairRequestSearchText.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<repairRequestDetails>(helperMethods.getRepairRequestRecordsByDeviceName(DeviceName), RecursiveTreeObject::getChildren);
        repairRequestTable.setRoot(root);
        repairRequestTable.setShowRoot(false);
        repairRequestSearchText.setText("");
        }
        
        
        if (searchByReceivedDateRadioButton.isSelected()) {
        root = null;
        String enteredDate = repairRequestSearchText.getText();
        HelperMethods helperMethods = new HelperMethods();
        root = new RecursiveTreeItem<repairRequestDetails>(helperMethods.getRepairRequestRecordsByReceivedDate(enteredDate), RecursiveTreeObject::getChildren);
        repairRequestTable.setRoot(root);
        repairRequestTable.setShowRoot(false);
        repairRequestSearchText.setText("");            
        }
    }
    
    public void enterRequestToRecord(ActionEvent actionEvent) {
        String deviceName = repairDeviceGetter.getText();
        String charges = repairChargesGetter.getText();
        LocalDate dateReceived = receivedDateGetter.getValue();
        LocalDate dateReturned = returnedDateGetter.getValue();
        HelperMethods helperMethods = new HelperMethods();
        helperMethods.insertCustomerRepairRequest(dateReceived.toString(), dateReturned.toString(), deviceName, Integer.parseInt(charges)); 
        
        root = null;
        root = new RecursiveTreeItem<repairRequestDetails>(helperMethods.getRepairRequestRecords(), RecursiveTreeObject::getChildren);
        repairRequestTable.setRoot(root);
        repairRequestTable.setShowRoot(false);
    }
    
}
