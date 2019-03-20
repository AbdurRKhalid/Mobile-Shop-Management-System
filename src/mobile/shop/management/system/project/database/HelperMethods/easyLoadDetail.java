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
public class easyLoadDetail extends RecursiveTreeObject<easyLoadDetail> {
    public StringProperty company;
    public StringProperty amount;
    public StringProperty date;
    public StringProperty time;
    public StringProperty mobileNo;

    public easyLoadDetail(String company, String amount, String date, String time, String mobileNo) {
        this.company = new SimpleStringProperty(company);
        this.amount = new SimpleStringProperty(amount);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.mobileNo = new SimpleStringProperty(mobileNo);
    }
    
}
