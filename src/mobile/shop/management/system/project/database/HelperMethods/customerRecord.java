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
public class customerRecord extends RecursiveTreeObject<customerRecord> {
    
    public StringProperty receiptNo;
    public StringProperty customerName;
    public StringProperty mobileNo;
    public StringProperty date;
    public StringProperty time;
    public StringProperty total;

    public customerRecord(String receiptNo, String customerName, String mobileNo, String date, String time, String total){
        this.receiptNo = new SimpleStringProperty(receiptNo);
        this.customerName = new SimpleStringProperty(customerName);
        this.mobileNo = new SimpleStringProperty(mobileNo);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.total = new SimpleStringProperty(total);
    }
}
