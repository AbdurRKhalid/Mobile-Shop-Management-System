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
public class accessoryDetail extends RecursiveTreeObject<accessoryDetail> {
    public StringProperty category;
    public StringProperty Name;
    public StringProperty pricePerUnit;
    public StringProperty stock;
    
    public accessoryDetail(String category, String Name, String pricePerUnit, String stock){
        this.category = new SimpleStringProperty(category);
        this.Name = new SimpleStringProperty(Name);
        this.pricePerUnit = new SimpleStringProperty(pricePerUnit);
        this.stock = new SimpleStringProperty(stock);
    }
}
