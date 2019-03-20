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
public class receiptDetail extends RecursiveTreeObject<receiptDetail> {
    public StringProperty nameOfAccessory;
    public StringProperty quantityOfAccessory;
    public StringProperty priceOfQuantity;
    public StringProperty pricePerUnit;
   
    public receiptDetail(String name, String Quantity, String price, String pricePerUnit){
        this.nameOfAccessory = new SimpleStringProperty(name);
        this.quantityOfAccessory = new SimpleStringProperty(Quantity);
        this.priceOfQuantity = new SimpleStringProperty(price);
        this.pricePerUnit = new SimpleStringProperty(pricePerUnit);
    }
    
}
