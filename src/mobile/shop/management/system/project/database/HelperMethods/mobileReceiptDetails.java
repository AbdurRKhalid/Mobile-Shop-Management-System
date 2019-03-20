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
public class mobileReceiptDetails extends RecursiveTreeObject<mobileReceiptDetails> {
    public StringProperty companyName;
    public StringProperty mobileModel;
    public StringProperty mobilePrice;
    
    public mobileReceiptDetails(String companyName, String mobileModel, String mobilePrice){
        this.companyName = new SimpleStringProperty(companyName);
        this.mobileModel = new SimpleStringProperty(mobileModel);
        this.mobilePrice = new SimpleStringProperty(mobilePrice);
    }
    
}
