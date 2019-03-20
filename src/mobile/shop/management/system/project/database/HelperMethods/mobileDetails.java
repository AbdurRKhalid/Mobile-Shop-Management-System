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
public class mobileDetails extends RecursiveTreeObject<mobileDetails> {
    public StringProperty vendorName;
    public StringProperty mobileModel;
    public StringProperty mobileImeiNo;
    public StringProperty mobileCondition;
    public StringProperty boughtPrice;
    public StringProperty sellerCnicNo;
    public StringProperty sellerName;
    public StringProperty sellerAddress;
    
    
    public mobileDetails(String vendorName, String mobileModel, String mobileImeiNo, String mobileCondition, String boughtPrice, String sellerCnicNo, String sellerName, String sellerAddress){
        this.vendorName = new SimpleStringProperty(vendorName);
        this.mobileModel = new SimpleStringProperty(mobileModel);
        this.mobileImeiNo = new SimpleStringProperty(mobileImeiNo);
        this.mobileCondition = new SimpleStringProperty(mobileCondition);
        this.boughtPrice = new SimpleStringProperty(boughtPrice);
        this.sellerCnicNo = new SimpleStringProperty(sellerCnicNo);
        this.sellerName = new SimpleStringProperty(sellerName);
        this.sellerAddress = new SimpleStringProperty(sellerAddress);
    }
    
}
