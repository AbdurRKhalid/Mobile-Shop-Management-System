/*
    This Whole Package Will Contain Classes that Will Be used in order to Create
    Database Connections, Retrieve Data, Call Views, Call Procedures and Othre Works too.
 */
package mobile.shop.management.system.project.database.HelperMethods;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hp
 */
public class HelperMethods {
    //Method to Return a Connection Object After Creating Connection to the Database.
    public Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=db_MobileShopManagementSystem","sa","PHYSICS12");
            return con;
        } catch (java.lang.UnsatisfiedLinkError | Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    //Methods for Viewing and Searching in the Customer Records.
    public ObservableList<customerRecord> getCustomerRecords() {
        ObservableList<customerRecord> customersRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_CustomersDetails");
            while(resultSet.next()){
                String receiptNo = Integer.toString(resultSet.getInt("Receipt_No"));
                String name = resultSet.getString("Name");
                BigDecimal mobileNo = resultSet.getBigDecimal("Mobile_No");
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
                String total = Integer.toString(resultSet.getInt("Total"));
                customersRecord.add(new customerRecord(receiptNo,name, mobileNo.toString(),date, time, total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customersRecord;
    }
    
        public ObservableList<customerRecord> getCustomerRecordByReceiptNo(int ReceiptNo) {
        ObservableList<customerRecord> customersRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_CustomersDetails WHERE Receipt_No ="+ReceiptNo);
            while(resultSet.next()){
                String receiptNo = Integer.toString(resultSet.getInt("Receipt_No"));
                String name = resultSet.getString("Name");
                BigDecimal mobileNo = resultSet.getBigDecimal("Mobile_No");
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
                String total = Integer.toString(resultSet.getInt("Total"));
                customersRecord.add(new customerRecord(receiptNo,name, mobileNo.toString(),date, time, total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customersRecord;
    }
    
        
        public ObservableList<customerRecord> getCustomerRecordsByName(String Name) {
        ObservableList<customerRecord> customersRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_CustomersDetails WHERE Name =" +'\'' + Name +'\'');
            while(resultSet.next()){
                String receiptNo = Integer.toString(resultSet.getInt("Receipt_No"));
                String name = resultSet.getString("Name");
                BigDecimal mobileNo = resultSet.getBigDecimal("Mobile_No");
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
                String total = Integer.toString(resultSet.getInt("Total"));
                customersRecord.add(new customerRecord(receiptNo,name, mobileNo.toString(),date, time, total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customersRecord;
    }
        
        public ObservableList<customerRecord> getCustomerRecordsByMobileNo(BigDecimal MobileNo) {
        ObservableList<customerRecord> customersRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_CustomersDetails WHERE Mobile_No =" + MobileNo);
            while(resultSet.next()){
                String receiptNo = Integer.toString(resultSet.getInt("Receipt_No"));
                String name = resultSet.getString("Name");
                BigDecimal mobileNo = resultSet.getBigDecimal("Mobile_No");
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
                String total = Integer.toString(resultSet.getInt("Total"));
                customersRecord.add(new customerRecord(receiptNo,name, mobileNo.toString(),date, time, total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customersRecord;
    }
            //Searching and Display Methods Ends Here.
        
        
        
        
        
        //Starting Methods to Fetch and Search the Records.
        public ObservableList<accessoryDetail> getAccessoryRecords() {
        ObservableList<accessoryDetail> accessoryRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_AccessoryDetails;");
            while(resultSet.next()){
                String category = resultSet.getString("Category");
                String name = resultSet.getString("Name");
                String pricePerUnit = Integer.toString(resultSet.getInt("Price"));
                String stock = Integer.toString(resultSet.getInt("Stock"));
                accessoryRecord.add(new accessoryDetail(category,name,pricePerUnit,stock));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accessoryRecord;
    }
        
        public ObservableList<accessoryDetail> getAccessoryRecordsByTypes(String Type) {
        ObservableList<accessoryDetail> accessoryRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_AccessoryDetails WHERE Category =" +'\'' + Type +'\'');
            while(resultSet.next()){
                String category = resultSet.getString("Category");
                String name = resultSet.getString("Name");
                String pricePerUnit = Integer.toString(resultSet.getInt("Price"));
                String stock = Integer.toString(resultSet.getInt("Stock"));
                accessoryRecord.add(new accessoryDetail(category,name,pricePerUnit,stock));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accessoryRecord;
    }
        
        public ObservableList<accessoryDetail> getAccessoryRecordsByPricePerUnit(int Price) {
        ObservableList<accessoryDetail> accessoryRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_AccessoryDetails WHERE Price =" + Price);
            while(resultSet.next()){
                String category = resultSet.getString("Category");
                String name = resultSet.getString("Name");
                String pricePerUnit = Integer.toString(resultSet.getInt("Price"));
                String stock = Integer.toString(resultSet.getInt("Stock"));
                accessoryRecord.add(new accessoryDetail(category,name,pricePerUnit,stock));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accessoryRecord;
    }
        //Ending the Methods for Fetching and Searching the Accessory Data.
        
        
        
        //Starting the Methods for Fetching and Searching the Easy Load Retailers.
        public ObservableList<easyLoadRetailerDetails> getEasyLoadRetailerRecord() {
        ObservableList<easyLoadRetailerDetails> easyLoadRetailerRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Easy_Load_Retailer_Details");
            while(resultSet.next()){
                String name = resultSet.getString("Name");
                String amount = Integer.toString(resultSet.getInt("Amount")); 
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
                String telecomCompany = resultSet.getString("Company");
                easyLoadRetailerRecord.add(new easyLoadRetailerDetails(name,amount,date,time,telecomCompany));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return easyLoadRetailerRecord;
    }
        public ObservableList<easyLoadRetailerDetails> getEasyLoadRetailerRecordByRetailerName(String Name) {
        ObservableList<easyLoadRetailerDetails> easyLoadRetailerRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Easy_Load_Retailer_Details WHERE Name =" +'\'' + Name +'\'');
            while(resultSet.next()){
                String name = resultSet.getString("Name");
                String amount = Integer.toString(resultSet.getInt("Amount")); 
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
                String telecomCompany = resultSet.getString("Company");
                easyLoadRetailerRecord.add(new easyLoadRetailerDetails(name,amount,date,time,telecomCompany));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return easyLoadRetailerRecord;
    }
        
        public ObservableList<easyLoadRetailerDetails> getEasyLoadRetailerRecordByCompany(String Company) {
        ObservableList<easyLoadRetailerDetails> easyLoadRetailerRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Easy_Load_Retailer_Details WHERE Company =" +'\'' + Company +'\'');
            while(resultSet.next()){
                String name = resultSet.getString("Name");
                String amount = Integer.toString(resultSet.getInt("Amount")); 
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
                String telecomCompany = resultSet.getString("Company");
                easyLoadRetailerRecord.add(new easyLoadRetailerDetails(name,amount,date,time,telecomCompany));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return easyLoadRetailerRecord;
    }
        
        //Ending Methods for Easy Load Retailer Fetching and Searching.
        
        
        //Starting Methods for Easy Load Record Fetching and Searching.
        public ObservableList<easyLoadDetail> getEasyLoadRecord() {
        ObservableList<easyLoadDetail> easyLoadRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Easy_Load_Details;");
            while(resultSet.next()){
                String name = resultSet.getString("Company");
                String amount = Integer.toString(resultSet.getInt("Amount")); 
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
               BigDecimal mobileNumber = resultSet.getBigDecimal("Mobile");
                easyLoadRecord.add(new easyLoadDetail(name, amount, date, time, mobileNumber.toString()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return easyLoadRecord;
    }
        
        public ObservableList<easyLoadDetail> getEasyLoadRecordByMobileNumber(BigDecimal Number) {
        ObservableList<easyLoadDetail> easyLoadRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Easy_Load_Details WHERE Mobile =" + Number);
            while(resultSet.next()){
                String name = resultSet.getString("Company");
                String amount = Integer.toString(resultSet.getInt("Amount")); 
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
               BigDecimal mobileNumber = resultSet.getBigDecimal("Mobile");
                easyLoadRecord.add(new easyLoadDetail(name, amount, date, time, mobileNumber.toString()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return easyLoadRecord;
    }
        
        
        
        public ObservableList<easyLoadDetail> getEasyLoadRecordByCompany(String Company) {
        ObservableList<easyLoadDetail> easyLoadRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Easy_Load_Details WHERE Company =" + '\'' + Company +'\'');
            while(resultSet.next()){
                String name = resultSet.getString("Company");
                String amount = Integer.toString(resultSet.getInt("Amount")); 
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
               BigDecimal mobileNumber = resultSet.getBigDecimal("Mobile");
                easyLoadRecord.add(new easyLoadDetail(name, amount, date, time, mobileNumber.toString()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return easyLoadRecord;
    }
        //Ending of Methods for fetching and Searching the Easy Load Records.
        
        
        
        //Starting Methods for fetching and searching Repair Request Records.
        
        public ObservableList<repairRequestDetails> getRepairRequestRecords() {
        ObservableList<repairRequestDetails> repairRequestRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from v_RepairRequest;");
            while(resultSet.next()){
                String receivedDate = resultSet.getString("Received_Date");
                String returnedDate = resultSet.getString("Returned_Date");
                String deviceName = resultSet.getString("Device");
                String charges = Integer.toString(resultSet.getInt("Charges"));
                repairRequestRecord.add(new repairRequestDetails(receivedDate, returnedDate, deviceName, charges));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repairRequestRecord;
    }
        
        public ObservableList<repairRequestDetails> getRepairRequestRecordsByDeviceName(String device) {
        ObservableList<repairRequestDetails> repairRequestRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from v_RepairRequest WHERE Device=" + '\'' + device +'\'' );
            while(resultSet.next()){
                String receivedDate = resultSet.getString("Received_Date");
                String returnedDate = resultSet.getString("Returned_Date");
                String deviceName = resultSet.getString("Device");
                String charges = Integer.toString(resultSet.getInt("Charges"));
                repairRequestRecord.add(new repairRequestDetails(receivedDate, returnedDate, deviceName, charges));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repairRequestRecord;
    }
        
        
        public ObservableList<repairRequestDetails> getRepairRequestRecordsByReceivedDate(String date) {
        ObservableList<repairRequestDetails> repairRequestRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from v_RepairRequest WHERE Returned_Date=" + '\'' + date +'\'' );
            while(resultSet.next()){
                String receivedDate = resultSet.getString("Received_Date");
                String returnedDate = resultSet.getString("Returned_Date");
                String deviceName = resultSet.getString("Device");
                String charges = Integer.toString(resultSet.getInt("Charges"));
                repairRequestRecord.add(new repairRequestDetails(receivedDate, returnedDate, deviceName, charges));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repairRequestRecord;
    }
        //Methods for Fetching and Searching of Repair Requests Ends Here.
        
        
        
        
        //Methods for Mobiles Fetching and Searching Starts Here.
        
        public ObservableList<mobileDetails> getMobileRecord() {
        ObservableList<mobileDetails> mobiletRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Mobile_Detail;");
            while(resultSet.next()){
                String vendorName = resultSet.getString("vendor_Name");
                String model = resultSet.getString("Model");
                BigDecimal imeiNO =  new BigDecimal(resultSet.getString("IMEI"));
                String condition = resultSet.getString("Condition");
                BigDecimal boughtPrice = resultSet.getBigDecimal("Bought_Price");
                BigDecimal sellerCnicNo = resultSet.getBigDecimal("Seller_CNIC_No");
                String sellerName = resultSet.getString("Seller_Name");
                String sellerAddress = resultSet.getString("Seller_Address");
                mobiletRecord.add(new mobileDetails(vendorName, model, imeiNO.toString(), condition, boughtPrice.toString(), sellerCnicNo.toString(), sellerName, sellerAddress));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mobiletRecord;
    }
        
        public ObservableList<mobileDetails> getMobileRecordByCompany(String Company) {
        ObservableList<mobileDetails> mobiletRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Mobile_Detail WHERE vendor_Name = " + '\'' + Company +'\'' );
            while(resultSet.next()){
                String vendorName = resultSet.getString("vendor_Name");
                String model = resultSet.getString("Model");
                BigDecimal imeiNO =  new BigDecimal(resultSet.getString("IMEI"));
                String condition = resultSet.getString("Condition");
                BigDecimal boughtPrice = resultSet.getBigDecimal("Bought_Price");
                BigDecimal sellerCnicNo = resultSet.getBigDecimal("Seller_CNIC_No");
                String sellerName = resultSet.getString("Seller_Name");
                String sellerAddress = resultSet.getString("Seller_Address");
                mobiletRecord.add(new mobileDetails(vendorName, model, imeiNO.toString(), condition, boughtPrice.toString(), sellerCnicNo.toString(), sellerName, sellerAddress));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mobiletRecord;
    }
        
        public ObservableList<mobileDetails> getMobileRecordByIMEINo(BigDecimal imei) {
        ObservableList<mobileDetails> mobiletRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Mobile_Detail WHERE IMEI = " + imei);
            while(resultSet.next()){
                String vendorName = resultSet.getString("vendor_Name");
                String model = resultSet.getString("Model");
                BigDecimal imeiNO =  new BigDecimal(resultSet.getString("IMEI"));
                String condition = resultSet.getString("Condition");
                BigDecimal boughtPrice = resultSet.getBigDecimal("Bought_Price");
                BigDecimal sellerCnicNo = resultSet.getBigDecimal("Seller_CNIC_No");
                String sellerName = resultSet.getString("Seller_Name");
                String sellerAddress = resultSet.getString("Seller_Address");
                mobiletRecord.add(new mobileDetails(vendorName, model, imeiNO.toString(), condition, boughtPrice.toString(), sellerCnicNo.toString(), sellerName, sellerAddress));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mobiletRecord;
    }
        
        public ObservableList<mobileDetails> getMobileRecordByModel(String Model) {
        ObservableList<mobileDetails> mobiletRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Mobile_Detail WHERE Model = " + '\'' + Model +'\'');
            while(resultSet.next()){
                String vendorName = resultSet.getString("vendor_Name");
                String model = resultSet.getString("Model");
                BigDecimal imeiNO =  new BigDecimal(resultSet.getString("IMEI"));
                String condition = resultSet.getString("Condition");
                BigDecimal boughtPrice = resultSet.getBigDecimal("Bought_Price");
                BigDecimal sellerCnicNo = resultSet.getBigDecimal("Seller_CNIC_No");
                String sellerName = resultSet.getString("Seller_Name");
                String sellerAddress = resultSet.getString("Seller_Address");
                mobiletRecord.add(new mobileDetails(vendorName, model, imeiNO.toString(), condition, boughtPrice.toString(), sellerCnicNo.toString(), sellerName, sellerAddress));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mobiletRecord;
    }
        
        public ObservableList<mobileDetails> getMobileRecordByCnicNo(BigDecimal CNIC) {
        ObservableList<mobileDetails> mobiletRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Mobile_Detail WHERE Seller_CNIC_No = " + CNIC);
            while(resultSet.next()){
                String vendorName = resultSet.getString("vendor_Name");
                String model = resultSet.getString("Model");
                BigDecimal imeiNO =  new BigDecimal(resultSet.getString("IMEI"));
                String condition = resultSet.getString("Condition");
                BigDecimal boughtPrice = resultSet.getBigDecimal("Bought_Price");
                BigDecimal sellerCnicNo = resultSet.getBigDecimal("Seller_CNIC_No");
                String sellerName = resultSet.getString("Seller_Name");
                String sellerAddress = resultSet.getString("Seller_Address");
                mobiletRecord.add(new mobileDetails(vendorName, model, imeiNO.toString(), condition, boughtPrice.toString(), sellerCnicNo.toString(), sellerName, sellerAddress));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mobiletRecord;
    }
        
        public ObservableList<mobileDetails> getMobileRecordBySellerName(String Seller) {
        ObservableList<mobileDetails> mobiletRecord = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From v_Mobile_Detail WHERE Seller_Name = " + '\'' + Seller +'\'');
            while(resultSet.next()){
                String vendorName = resultSet.getString("vendor_Name");
                String model = resultSet.getString("Model");
                BigDecimal imeiNO =  new BigDecimal(resultSet.getString("IMEI"));
                String condition = resultSet.getString("Condition");
                BigDecimal boughtPrice = resultSet.getBigDecimal("Bought_Price");
                BigDecimal sellerCnicNo = resultSet.getBigDecimal("Seller_CNIC_No");
                String sellerName = resultSet.getString("Seller_Name");
                String sellerAddress = resultSet.getString("Seller_Address");
                mobiletRecord.add(new mobileDetails(vendorName, model, imeiNO.toString(), condition, boughtPrice.toString(), sellerCnicNo.toString(), sellerName, sellerAddress));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mobiletRecord;
    }
        
        //Methods for Fethcing and Searching the Mobile Records Ends Here.
        
        
        
        //Methods for Insertion in Table of Customer and Repair Request.
        
        public void insertCustomerRepairRequest(String dateReceived, String dateReturned, String deviceName, int charges) {
            int latestID = -1;
        try {
            //Calling the Procedure to Enter the Customer in the Customer Table.
            Connection connection = getConnection();
            CallableStatement callableStatement_InsertCustomerFromRepair = connection.prepareCall("{call p_InsertCustomerFromRepair}");
            callableStatement_InsertCustomerFromRepair.execute();
            ResultSet result = callableStatement_InsertCustomerFromRepair.getResultSet();
            callableStatement_InsertCustomerFromRepair.close();
            //Ending the Call Here.
                        
            
            //Calling the Query in Order to Get the ID of Latest Added Customer.
            Statement statement = connection.createStatement();
            ResultSet resultSet_Getting_ID_Latest_Added_Customer = statement.executeQuery("SELECT MAX(customer_ID) AS customer_ID FROM tb_Customer");
            while (resultSet_Getting_ID_Latest_Added_Customer.next()) {
               latestID = resultSet_Getting_ID_Latest_Added_Customer.getInt("customer_ID");
            }
            //Ending the Call Here
           
            //Calling the Procedure to Store the Repair Request Details in the Repair Request Table.
            CallableStatement callableStatement_InsertRepairRequest = connection.prepareCall("{call p_InsertRepairRequest(?,?,?,?,?)};");
            callableStatement_InsertRepairRequest.setInt(1, latestID);
            callableStatement_InsertRepairRequest.setString(2, dateReceived);
            callableStatement_InsertRepairRequest.setString(3, dateReturned);
            callableStatement_InsertRepairRequest.setString(4, deviceName);
            callableStatement_InsertRepairRequest.setInt(5, charges);
            callableStatement_InsertRepairRequest.execute();
            callableStatement_InsertRepairRequest.close();
            //Ending the Call here.
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
        //Methods to Add Easy Load Stock are starting from here.
        
        
        //Getting the List of All Telecom Companies.
        public ObservableList<String> getTelecomCompanies(){
            ObservableList<String> companies = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select Distinct telecom_Company AS Company From tb_Easy_Load_Stock");
            while(resultSet.next()){
                companies.add(resultSet.getString("Company"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
    }
        
        //Getting the Stock Detail of Selected Company.
        public int getStock(String company) {
        int stock = 0;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select stock_left AS Stock From tb_Easy_Load_Stock WHERE telecom_Company = " + '\'' + company +'\'');
            while(resultSet.next()){
                stock = resultSet.getInt("Stock");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
        }
        
        //Inserting all values from Easy Load Window to all other Related Tables.
        
        public void insertEasyLoadRecord(String easyLoadCompany, int easyLoadAmount, String easyLoadDate, String easyLoadTime, BigDecimal mobileNo) {
                    int latestID = -1;
                    int stock = -1;
        try {
            //Calling the Procedure to Enter the Customer in the Customer Table.
            Connection connection = getConnection();
            CallableStatement callableStatement_InsertCustomerFromEasyLoadWindow = connection.prepareCall("{call p_InsertCustomerFromEasyLoadWindow(?)}");
            callableStatement_InsertCustomerFromEasyLoadWindow.setBigDecimal(1, mobileNo);
            callableStatement_InsertCustomerFromEasyLoadWindow.execute();
            ResultSet result = callableStatement_InsertCustomerFromEasyLoadWindow.getResultSet();
            callableStatement_InsertCustomerFromEasyLoadWindow.close();
            //Ending the Call Here.
                        
            
            //Calling the Query in Order to Get the ID of Latest Added Customer.
            Statement statement = connection.createStatement();
            ResultSet resultSet_Getting_ID_Latest_Added_Customer = statement.executeQuery("SELECT MAX(customer_ID) AS customer_ID FROM tb_Customer");
            while (resultSet_Getting_ID_Latest_Added_Customer.next()) {
               latestID = resultSet_Getting_ID_Latest_Added_Customer.getInt("customer_ID");
            }
            //Ending the Call Here
           
            //Calling the Procedure to Store the Repair Request Details in the Repair Request Table.
            CallableStatement callableStatement_InsertEasyLoadRecord = connection.prepareCall("{call p_InsertEasyLoadRecord(?,?,?,?,?)};");
            callableStatement_InsertEasyLoadRecord.setString(1, easyLoadCompany);
            callableStatement_InsertEasyLoadRecord.setInt(2, easyLoadAmount);
            callableStatement_InsertEasyLoadRecord.setString(3, easyLoadDate);
            callableStatement_InsertEasyLoadRecord.setString(4, easyLoadTime);
            callableStatement_InsertEasyLoadRecord.setBigDecimal(5, mobileNo);
            callableStatement_InsertEasyLoadRecord.execute();
            callableStatement_InsertEasyLoadRecord.close();
            //Ending the Call here.
            
            //Getting the Current Stock of the Specific Easy Load company and Updating it.
            Statement stockGet = connection.createStatement();
            ResultSet stockResult = stockGet.executeQuery("Select stock_left AS Stock From tb_Easy_Load_Stock Where telecom_Company =" + '\'' + easyLoadCompany +'\'' );
            while(stockResult.next()) {
                stock = stockResult.getInt("Stock");
                System.out.print(stock);
            }
            CallableStatement callableStatement_updateStock = connection.prepareCall("{call v_UpdateStockFromEasyLoadWindow(?,?)}");
            int newStock = stock - easyLoadAmount;
            callableStatement_updateStock.setInt(1, newStock);
            callableStatement_updateStock.setString(2, easyLoadCompany);
            callableStatement_updateStock.execute();
            callableStatement_updateStock.close();
            //Ending the Updation of Stock Here.
            
            
            //Inserting the Customer Id to the Orders Table.
            CallableStatement callableStatement_InsertIntoOrders = connection.prepareCall("{call p_InsertionInOrderFromEasyLoadWindow(?,?,?,?)}");
            callableStatement_InsertIntoOrders.setInt(1,latestID);
            callableStatement_InsertIntoOrders.setString(2, easyLoadDate);
            callableStatement_InsertIntoOrders.setString(3, easyLoadTime);
            callableStatement_InsertIntoOrders.setInt(4, easyLoadAmount);
            callableStatement_InsertIntoOrders.execute();
            callableStatement_InsertIntoOrders.close();
            //Ending the Insertion Here.
            
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        //Methods For Inserting the Easy Load Record into Database Ends Here.
        
        
        
        //Methods for Adding Retailer Record to the Database Starts from Here.
        public void addEasyLoadRetailerRecord(String retailerName, int amountRecharged, String loadDate, String loadTime, String company){
            int stock = -1;
        try {
            Connection connection = getConnection();
            //Saving all the Information Related to the Retailer.
            CallableStatement callableStatement_InsertIntoEasyLoadRetailer;
            callableStatement_InsertIntoEasyLoadRetailer = connection.prepareCall("{call p_AddRetailerInformation(?,?,?,?,?)}");
            callableStatement_InsertIntoEasyLoadRetailer.setString(1, retailerName);
            callableStatement_InsertIntoEasyLoadRetailer.setInt(2, amountRecharged);
            callableStatement_InsertIntoEasyLoadRetailer.setString(3, loadDate);
            callableStatement_InsertIntoEasyLoadRetailer.setString(4, loadTime);
            callableStatement_InsertIntoEasyLoadRetailer.setString(5, company);
            callableStatement_InsertIntoEasyLoadRetailer.execute();
            callableStatement_InsertIntoEasyLoadRetailer.close();
            //Saving of Information about Retailer Ends Here.
            
            
            //Updating the Stock of Specific Telecom Company.
            Statement stockGet = connection.createStatement();
            ResultSet stockResult = stockGet.executeQuery("Select stock_left AS Stock From tb_Easy_Load_Stock Where telecom_Company =" + '\'' + company +'\'' );
            while(stockResult.next()) {
                stock = stockResult.getInt("Stock");
            }
            CallableStatement callableStatement_updateStock = connection.prepareCall("{call v_UpdateStockFromEasyLoadWindow(?,?)}");
            int newStock = stock + amountRecharged;
            callableStatement_updateStock.setInt(1, newStock);
            callableStatement_updateStock.setString(2, company);
            callableStatement_updateStock.execute();
            callableStatement_updateStock.close();
            //Ending the Updation of Stock Here.
            
            
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
        //Ending of Method for the Easy Load Retilaer Adding Record.
        
        
        
        //Methods for Getting Categories of Accessories and Updating Stock of Accessory or Adding new Accessory Starting From Here.
        
        public ObservableList<String> getCategories() {
            ObservableList<String> categories = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select category_Name AS Category From tb_Category\n" +
                                                         "Where category_ID IN (Select Distinct category_ID From tb_Accessory);");
            while(resultSet.next()){
                categories.add(resultSet.getString("Category"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
        }
        
        
        public void updateAccessoryStockOrAddNewAccessory(String accsssoryName, int newStockToAdd, int pricePerUnit, String boughtDate, String Category) {
            int stock = -1;
            try {
            Connection connection = getConnection();
            Statement stockGet = connection.createStatement();
            //Getting the Current Stock of Accessory Present in the Record.
            ResultSet stockResult = stockGet.executeQuery("Select accessory_Stock As Stock From tb_Accessory Where accessory_Name = " + '\'' + accsssoryName +'\'');
            while(stockResult.next()) {
                stock = stockResult.getInt("Stock");
            }
                if (stock!=-1) {
            CallableStatement callableStatement_updateStockAccessory = connection.prepareCall("{call p_UpdateStockAccessories(?,?)}");
            int newStock = stock + newStockToAdd;
            callableStatement_updateStockAccessory.setString(1, accsssoryName);
            callableStatement_updateStockAccessory.setInt(2, newStock);
            callableStatement_updateStockAccessory.execute();
            callableStatement_updateStockAccessory.close();
            } else{
                    Statement getCategoryID = connection.createStatement();
                    ResultSet categoryIDGetter = getCategoryID.executeQuery("Select category_ID as ID from tb_Category Where category_Name = " + '\'' + Category +'\'');
                    int idHolder = 0;
                    while (categoryIDGetter.next()) {
                        idHolder = categoryIDGetter.getInt("ID");
                    }
                    CallableStatement callableStatement_NewAccessory = connection.prepareCall("{call v_AddNewAccessory(?,?,?,?,?)}");
                    callableStatement_NewAccessory.setInt(1, idHolder);
                    callableStatement_NewAccessory.setString(2, accsssoryName);
                    callableStatement_NewAccessory.setInt(3, newStockToAdd);
                    callableStatement_NewAccessory.setInt(4, pricePerUnit);
                    callableStatement_NewAccessory.setString(5, boughtDate);
                    callableStatement_NewAccessory.execute();
                    callableStatement_NewAccessory.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        //The Method for Updating and Adding New Accessory Ends Here.
        
        
        //The Method for Storing the All New Mobile Information is Starting from here.
        public ObservableList<String> getVendors() {
            ObservableList<String> companies = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement companiesGet = connection.createStatement();
            ResultSet resultSet = companiesGet.executeQuery("Select vendor_Name AS Company From tb_Vendor;");
            while(resultSet.next()){
                companies.add(resultSet.getString("Company"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  companies;
        }
        
        
        
        
        
        public void addNewMobile(String sellerName, BigDecimal sellerCnicNo, String sellerAddress, String buyDate, String buyTime, 
               String model, BigDecimal imei, BigDecimal price,String condition,String company ){
            int vendorId = -1;
            int sellerId = -1;
            try {
            Connection connection = getConnection();
                if (sellerName.isEmpty() && sellerAddress.isEmpty()) {
                    sellerId = 1;
                    Statement getVendorID = connection.createStatement();
                    ResultSet resultSet = getVendorID.executeQuery("Select vendor_ID from tb_Vendor Where vendor_Name = " + '\'' + company +'\'');
                    while(resultSet.next()){
                     vendorId = resultSet.getInt("vendor_ID");
                    }
                    CallableStatement callableStatement_AddNewMobile = connection.prepareCall("{call p_AddMobileInformation(?,?,?,?,?,?)}");
                    callableStatement_AddNewMobile.setBigDecimal(1, imei);
                    callableStatement_AddNewMobile.setInt(2, sellerId);
                    callableStatement_AddNewMobile.setInt(3, vendorId);
                    callableStatement_AddNewMobile.setString(4, model);
                    callableStatement_AddNewMobile.setString(5,condition);
                    callableStatement_AddNewMobile.setBigDecimal(6, price);
                    callableStatement_AddNewMobile.execute();
                    callableStatement_AddNewMobile.close();
                } else{
                    Statement getVendorID = connection.createStatement();
                    ResultSet resultSet = getVendorID.executeQuery("Select vendor_ID from tb_Vendor Where vendor_Name = " + '\'' + company +'\'');
                    while(resultSet.next()){
                     vendorId = resultSet.getInt("vendor_ID");
                    }
                    //Adding the Information of the Seller.
                    CallableStatement callableStatement_AddNewSeller = connection.prepareCall("{call p_AddSellerInformation(?,?,?,?,?)}");
                    callableStatement_AddNewSeller.setString(1, sellerName);
                    callableStatement_AddNewSeller.setBigDecimal(2, sellerCnicNo);
                    callableStatement_AddNewSeller.setString(3, sellerAddress);
                    callableStatement_AddNewSeller.setString(4, buyDate);
                    callableStatement_AddNewSeller.setString(5, buyTime);
                    callableStatement_AddNewSeller.execute();
                    callableStatement_AddNewSeller.close();
                    //Ending Here.
                    
                    //Adding the Information of Mobile.
                    int latestID = -1;
                    Statement getLatestAddedSellerID = connection.createStatement();
                    ResultSet resultSet1 = getLatestAddedSellerID.executeQuery("SELECT MAX(seller_ID) AS seller_ID FROM tb_Seller");
                    while(resultSet1.next()){
                        latestID = resultSet1.getInt("seller_ID");
                    }
                    
                    Statement vendorIDWithSellerInfo = connection.createStatement();
                    ResultSet getVendorIDWithSellerInfo = vendorIDWithSellerInfo.executeQuery("Select vendor_ID from tb_Vendor Where vendor_Name = " + '\'' + company +'\'');
                    while(getVendorIDWithSellerInfo.next()){
                        vendorId = getVendorIDWithSellerInfo.getInt("vendor_ID");
                    }
                    CallableStatement callableStatement_AddNewMobileWithSellerInfo = connection.prepareCall("{call p_AddMobileInformation(?,?,?,?,?,?)}");
                    callableStatement_AddNewMobileWithSellerInfo.setBigDecimal(1, imei);
                    callableStatement_AddNewMobileWithSellerInfo.setInt(2, latestID);
                    callableStatement_AddNewMobileWithSellerInfo.setInt(3, vendorId);
                    callableStatement_AddNewMobileWithSellerInfo.setString(4, model);
                    callableStatement_AddNewMobileWithSellerInfo.setString(5, condition);
                    callableStatement_AddNewMobileWithSellerInfo.setBigDecimal(6, price);
                    callableStatement_AddNewMobileWithSellerInfo.execute();
                    callableStatement_AddNewMobileWithSellerInfo.close();
                    //Ending Here.
                }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
        //Methods for Adding New Mobile Phone to the Database Has Been Ended Here.
        
        
        
        //Methods for Making the Receipt for Accessory are Starting From Here.
        
        public ObservableList<String> modelsGetter(String category){
            int categoryID = -1;
            ObservableList<String> models = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT category_ID as ID from tb_Category WHERE category_Name=" + '\'' + category +'\'');
            while(resultSet.next()){
                categoryID = resultSet.getInt("ID");
            }
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("SELECT accessory_Name AS Name from tb_Accessory Where category_ID=" + categoryID);
            while(r.next()){
            models.add(r.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return models;
        }
        
        
        public int getAccessoryStock(String accessoryName){
            int stock = -1;
            try {
            Connection connection = getConnection();
            Statement companiesGet = connection.createStatement();
            ResultSet resultSet = companiesGet.executeQuery("Select accessory_Stock as Stock From tb_Accessory Where accessory_Name = " + '\'' + accessoryName +'\'');
            while(resultSet.next()){
                stock = resultSet.getInt("Stock");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
            return stock;
        }
        
        public int getAccessoryPricePerUnit(String accessoryName) {
            int pricePerUnit = -1;
            try {
            Connection connection = getConnection();
            Statement companiesGet = connection.createStatement();
            ResultSet resultSet = companiesGet.executeQuery("Select accessory_Price_Per_Unit AS Price From tb_Accessory Where accessory_Name = " + '\'' + accessoryName +'\'');
            while(resultSet.next()){
                pricePerUnit = resultSet.getInt("Price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
            return pricePerUnit;
        }
        
        
        public void updateAccessoryStockFromReceiptWindow(ArrayList<String> items, ArrayList<Integer>Units) {
            int stock = -1;
            try {
            Connection connection = getConnection();
            Statement stockGet = connection.createStatement();
            //Getting the Current Stock of Accessory Present in the Record.
                for (int iterator = 0; iterator <= items.size() - 1; iterator++) {
                    ResultSet stockResult = stockGet.executeQuery("Select accessory_Stock As Stock From tb_Accessory Where accessory_Name = " + '\'' + items.get(iterator) +'\'');
                while(stockResult.next()) {
                stock = stockResult.getInt("Stock");
            }
            CallableStatement callableStatement_updateStockAccessory = connection.prepareCall("{call p_UpdateStockAccessories(?,?)}");
            int newStock = stock - Units.get(iterator);
            callableStatement_updateStockAccessory.setString(1, items.get(iterator));
            callableStatement_updateStockAccessory.setInt(2, newStock);
            callableStatement_updateStockAccessory.execute();
            callableStatement_updateStockAccessory.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void insertCustomerFromReceipt(String customerName, BigDecimal number, int total){
            int latestID = -1;
            try{
                //Inserting the Customer from the Receipt Window.
            Connection connection = getConnection();
            CallableStatement callableStatement_InsertCustomerFromReceipt = connection.prepareCall("{call p_InsertCustomerFromReceiptWindow(?,?)}");
            callableStatement_InsertCustomerFromReceipt.setString(1, customerName);
            callableStatement_InsertCustomerFromReceipt.setBigDecimal(2, number);
            callableStatement_InsertCustomerFromReceipt.execute();
            callableStatement_InsertCustomerFromReceipt.getResultSet();
            callableStatement_InsertCustomerFromReceipt.close();
            //Ending the Call Here.
                        
            
            //Calling the Query in Order to Get the ID of Latest Added Customer.
            Statement statement = connection.createStatement();
            ResultSet resultSet_Getting_ID_Latest_Added_Customer = statement.executeQuery("SELECT MAX(customer_ID) AS customer_ID FROM tb_Customer");
            while (resultSet_Getting_ID_Latest_Added_Customer.next()) {
               latestID = resultSet_Getting_ID_Latest_Added_Customer.getInt("customer_ID");
            }
            
            Statement insertionInOrder = connection.createStatement();
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();
            CallableStatement callableStatement_InsertIntoOrders = connection.prepareCall("{call p_InsertionInOrderFromEasyLoadWindow(?,?,?,?)}");
            callableStatement_InsertIntoOrders.setInt(1,latestID);
            callableStatement_InsertIntoOrders.setString(2, currentDate.toString());
            callableStatement_InsertIntoOrders.setString(3, currentTime.toString());
            callableStatement_InsertIntoOrders.setInt(4, total);
            callableStatement_InsertIntoOrders.execute();
            callableStatement_InsertIntoOrders.close();
            
            }catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        //Methods for Making receipt for Acccesoory are ending here.
        
        
        
        //Methods for making receipt for mobile are starting from below.
        public ObservableList<String> getCompanies(){
            ObservableList<String> companies = FXCollections.observableArrayList();
            try{
                Connection connection = getConnection();
                Statement getComapniesName = connection.createStatement();
                ResultSet resultSet = getComapniesName.executeQuery("Select vendor_Name AS Name From tb_Vendor");
                while (resultSet.next()) {
                    companies.add(resultSet.getString("Name"));
                }
            }catch(SQLException ex){
                
            }
            return companies;
        }
        
        public ObservableList<String> getMobileModels(String companyName){
                        int companyID = -1;
                        ObservableList<String> models = FXCollections.observableArrayList();
            try{
                //Procedure to get ID of the Vendor so That we can Select Mobile Models Associated to that Comapny.
                Connection connection = getConnection();
                Statement getComapanyID = connection.createStatement();
                ResultSet resultSet = getComapanyID.executeQuery("Select vendor_ID AS ID From tb_Vendor Where vendor_Name=" + '\'' + companyName +'\'');
                while (resultSet.next()) {
                    companyID = resultSet.getInt("ID");
                    System.out.print(companyID);
                }
                //The Procedure of Getting ID ends here.
                
                //The Procedure of Getting Models.
                Statement getModels = connection.createStatement();
                ResultSet modelsGetter = getModels.executeQuery("Select mobile_Model From tb_Mobile Where vendor_ID =" + companyID + "And mobile_Sell_Price IS NULL And buyer_CNIC IS NULL");
                while (modelsGetter.next()) {
                    models.add(modelsGetter.getString("mobile_Model"));
                }
                //The Procedure of Getting the Models Ends Here.
            }catch(SQLException ex){
                
            }
            return models;
        }
        
        public BigDecimal getMobileBoughtPrice(String mobileModel){
                BigDecimal boughtPrice = null;
        try {
            Connection connection = getConnection();
            Statement getboughtPrice = connection.createStatement();
            ResultSet boughtPriceGetter = getboughtPrice.executeQuery("Select mobile_Bought_Price From tb_Mobile Where mobile_Model = " + '\'' + mobileModel +'\'');
            while(boughtPriceGetter.next()){
                boughtPrice = boughtPriceGetter.getBigDecimal("mobile_Bought_Price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boughtPrice;
        }
        
        
        public void updateMobileRecord(ArrayList<Integer>bought, ArrayList<Integer>sell, BigDecimal buyerCNIC){
             BigDecimal boughtPrice = null;
             BigDecimal sellPrice = null;
        try {
            Connection connection = getConnection();
            Statement updateSellPrice = connection.createStatement();
            
            for(int iterator = 0; iterator<=bought.size()-1; iterator++){
                CallableStatement callableStatement_updateMobileSellPrie = connection.prepareCall("{call p_UpdateMobileRecord(?,?,?)}");
                callableStatement_updateMobileSellPrie.setBigDecimal(1, new BigDecimal(bought.get(iterator)));
                callableStatement_updateMobileSellPrie.setBigDecimal(2, new BigDecimal(sell.get(iterator)));
                callableStatement_updateMobileSellPrie.setBigDecimal(3, buyerCNIC);
                callableStatement_updateMobileSellPrie.execute();
                callableStatement_updateMobileSellPrie.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        //Method for Getting sales of Current Month is as Below.
        
        public int getCurrentMothSales() {
            int sales = 0;
        try {
            LocalDate currentDate = LocalDate.now();
            int currentMonth = currentDate.getMonthValue();
            int currentYear = currentDate.getYear();
            Connection connection = getConnection();
            Statement getMonthlySales = connection.createStatement();
            ResultSet monthlySalesGetter = getMonthlySales.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" +currentMonth + "And Year(tb_Orders.order_Date) = " + currentYear);
            while(monthlySalesGetter.next()){
               sales = monthlySalesGetter.getInt("Sales");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sales;
        }
        
        //Method for Getting CurrentDay Sales.
        
        public int getCurrentDaySales() {
            int sales = 0;
            
        try {
            LocalDate currentDate = LocalDate.now();
            Connection connection = getConnection();
            Statement getCurrentDaySales = connection.createStatement();
            ResultSet currentDaySalesGetter = getCurrentDaySales.executeQuery("Select SUM(tb_Orders.order_Total) As Sales FROM tb_Orders Where tb_Orders.order_Date =" + '\'' + currentDate.toString() +'\'');
            while (currentDaySalesGetter.next()) {
                sales = currentDaySalesGetter.getInt("Sales");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sales;
        }
        
        //Method for Getting the Sales of All Previous Month of Current Year.
        
        public ArrayList<Integer> getMonthSlaes() {
             ArrayList<Integer> monthSales = new ArrayList();
            
        try {
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            Connection connection = getConnection();
            Statement statementJanuary = connection.createStatement();
            Statement statementFeburary = connection.createStatement();
            Statement statementMarch = connection.createStatement();
            Statement statementAprail = connection.createStatement();
            Statement statementMay = connection.createStatement();
            Statement statementJune = connection.createStatement();
            Statement statementJuly = connection.createStatement();
            Statement statementAugust = connection.createStatement();
            Statement statementSeptember = connection.createStatement();
            Statement statementOctober = connection.createStatement();
            Statement statementNovember = connection.createStatement();
            Statement statementDecember = connection.createStatement();
            
            ResultSet januarySalesGetter = statementJanuary.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 1 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet feburarySalesGetter = statementFeburary.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 2 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet marchSalesGetter = statementMarch.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 3 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet aprailSalesGetter = statementAprail.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 4 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet maySalesGetter = statementMay.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 5 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet juneSalesGetter = statementJune.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 6 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet julySalesGetter = statementJuly.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 7 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet augustSalesGetter = statementAugust.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 8 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet septemberSalesGetter = statementSeptember.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 9 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet octoberSalesGetter = statementOctober.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 10 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet novemberSalesGetter = statementNovember.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 11 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            ResultSet decemberSalesGetter = statementDecember.executeQuery("Select SUM(tb_Orders.order_Total) AS Sales FROM tb_Orders WHERE MONTH(tb_Orders.order_Date)=" + 12 +" AND Year(tb_Orders.order_Date) = " + currentYear);
            
            while (januarySalesGetter.next()) {
                monthSales.add(januarySalesGetter.getInt("Sales"));
                System.out.print(monthSales.get(0));
            }
            System.out.print(monthSales.get(0));
            
            while (feburarySalesGetter.next()) {
                monthSales.add(feburarySalesGetter.getInt("Sales"));
            }
            
            while (marchSalesGetter.next()) {
                monthSales.add(marchSalesGetter.getInt("Sales"));
            }            
            
            while (aprailSalesGetter.next()) {
                monthSales.add(aprailSalesGetter.getInt("Sales"));
            }
            
            while (maySalesGetter.next()) {
                monthSales.add(maySalesGetter.getInt("Sales"));
            }            
            
            while (juneSalesGetter.next()) {
                monthSales.add(juneSalesGetter.getInt("Sales"));
            }            
            
            while (julySalesGetter.next()) {
                monthSales.add(julySalesGetter.getInt("Sales"));
            }
            
            while (augustSalesGetter.next()) {
                monthSales.add(augustSalesGetter.getInt("Sales"));
            }

            while (septemberSalesGetter.next()) {
                monthSales.add(septemberSalesGetter.getInt("Sales"));
            }
            
            while (octoberSalesGetter.next()) {
                monthSales.add(octoberSalesGetter.getInt("Sales"));
            }

            while ( novemberSalesGetter.next()) {
                monthSales.add(novemberSalesGetter.getInt("Sales"));
            }
            
            while (decemberSalesGetter.next()) {
                monthSales.add(decemberSalesGetter.getInt("Sales"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(HelperMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        return monthSales;
        }
        
        //Getting the Last Order No Added in the Orders Table.
        public String receiptNo() {
            int latestOrderNo = -1;
            Connection connection = getConnection();
            try{
            Statement statement = connection.createStatement();
            ResultSet resultSet_Getting_ID_Latest_Added_Customer = statement.executeQuery("SELECT MAX(order_ID) AS Order_No FROM tb_Orders");
            while (resultSet_Getting_ID_Latest_Added_Customer.next()) {
               latestOrderNo = resultSet_Getting_ID_Latest_Added_Customer.getInt("Order_No");
            }
            }catch(SQLException exception){
                System.out.println(exception.getMessage());
            }
                    
            return Integer.toString(latestOrderNo);
        
        }
}
