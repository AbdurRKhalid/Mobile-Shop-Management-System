/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.shop.management.system.project.database.HelperMethods;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hp
 */
public class repairRequestDetails extends RecursiveTreeObject<repairRequestDetails> {
    public StringProperty receivedDate;
    public StringProperty returnedDate;
    public StringProperty deviceName;
    public StringProperty charges;
    
    public repairRequestDetails(String receivedDate, String returnedDate, String deviceName, String charges){
        this.receivedDate = new SimpleStringProperty(receivedDate);
        this.returnedDate = new SimpleStringProperty(returnedDate);
        this.deviceName = new SimpleStringProperty(deviceName);
        this.charges = new SimpleStringProperty(charges);
    }
}
