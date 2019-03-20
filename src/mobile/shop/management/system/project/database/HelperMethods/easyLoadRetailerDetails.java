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
public class easyLoadRetailerDetails extends RecursiveTreeObject<easyLoadRetailerDetails> {
    public StringProperty retailerName;
    public StringProperty amountRecharged;
    public StringProperty dateOfRecharged;
    public StringProperty timeOfRecharged;
    public StringProperty telecomCompanyRecharged;
    
    public easyLoadRetailerDetails(String retailerName, String amountRecharged, String dateOfRecharged, String timeOfRecharged, String telecomCompanyRecharged) {
        this.retailerName = new SimpleStringProperty(retailerName);
        this.amountRecharged = new SimpleStringProperty(amountRecharged);
        this.dateOfRecharged = new SimpleStringProperty(dateOfRecharged);
        this.timeOfRecharged = new SimpleStringProperty(timeOfRecharged);
        this.telecomCompanyRecharged = new SimpleStringProperty(telecomCompanyRecharged);
    }
}
