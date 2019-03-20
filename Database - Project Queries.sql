--Creating a New Database for Mobile Phone Shop Management System.

CREATE DATABASE db_MobileShopManagementSystem;

--Creating a Table Name Customer in order to Store Record of Customers.
CREATE TABLE tb_Customer(
	customer_ID INT PRIMARY KEY IDENTITY(1,1),
	customer_Name VARCHAR(50) DEFAULT 'WALK IN CUSTOMER',
	customer_Mobile_Number BIGINT DEFAULT 00000000000
);

--Creating Table Name Repair_Request in order to Store Record of Items Came for Repairment.
CREATE TABLE tb_Repair_Request(
	repair_request_ID INT PRIMARY KEY IDENTITY(1,1),
	customer_ID INT FOREIGN KEY REFERENCES tb_Customer(customer_ID),
	received_Date DATE,
	return_Date DATE,
	device_Name VARCHAR(50),
	repair_Charges INT
);

--Creating Table Name Order in order to Save the Record of the Orders.
CREATE TABLE tb_Orders(
	order_ID INT PRIMARY KEY IDENTITY(1,1),
	customer_ID INT FOREIGN KEY REFERENCES tb_Customer(customer_ID),
	order_Date DATE,
	order_Time TIME,
	order_Total BIGINT
);

--Creating Table Name Easy_Load in order to Save the Record of Easy Loads.
CREATE TABLE tb_Easy_Load(
	easy_load_ID INT PRIMARY KEY IDENTITY(1,1),
	easy_load_Company VARCHAR(25),
	easy_load_Amount BIGINT,
	easy_load_Date DATE,
	easy_load_Time TIME
);

--Creating Table Name Easy Load Stock in order to Save the Stock of Easy Load Remaining.
CREATE TABLE tb_Easy_Load_Stock(
	easy_load_stock_ID INT PRIMARY KEY IDENTITY(1,1),
	telecom_Company VARCHAR(25),
	stock_left BIGINT
);

--Creating Table in order to Save the Record of Easy Load Retailer.
CREATE TABLE tb_Easy_Load_Retailer(
	easy_load_retailer_ID INT PRIMARY KEY IDENTITY(1,1),
	easy_load_retailer_Name VARCHAR(50) DEFAULT 'WALK IN RETAILER',
	easy_load_retailer_Amount_Recharge BIGINT,
	stock_load_Date DATE,
	stock_load_Time TIME
);

--Creating Table Vendor in Order to Save the Record of Different Companies.
CREATE TABLE tb_Vendor(
	vendor_ID INT PRIMARY KEY IDENTITY(1,1),
	vendor_Name VARCHAR(50)
);

--Creating Table Category in Order to Save the Types of Accessories.
CREATE TABLE tb_Category(
	category_ID INT PRIMARY KEY IDENTITY(1,1),
	category_Name VARCHAR(50)
);

--Creating Table Accessories in Order to Save Record of Accessories.
CREATE TABLE tb_Accessory(
	accessory_ID INT PRIMARY KEY IDENTITY(1,1),
	category_ID INT FOREIGN KEY REFERENCES tb_Category(category_ID),
	accessory_Name VARCHAR(50),
	accessory_Stock INT,
	accessory_Price_Per_Unit INT,
	accessory_Bought_Date DATE
);

--Creating Table Seller in Order to Save the information of Mobile Seller.
CREATE TABLE tb_Seller(
	seller_ID INT PRIMARY KEY IDENTITY(1,1),
	seller_Name VARCHAR(50) DEFAULT 'BOXED PACKED',
	seller_CNIC_No BIGINT DEFAULT 0000000000000,
	seller_Address VARCHAR(75) DEFAULT 'BOXED  PACKED',
	buy_Date DATE,
	buy_Time TIME
);


--Creating Table Mobile in Order to Save the Record of Mobile Phones.
CREATE TABLE tb_Mobile(
	mobile_IMEI_No BIGINT PRIMARY KEY,
	seller_ID INT FOREIGN KEY REFERENCES tb_Seller(seller_ID),
	vendor_ID INT FOREIGN KEY REFERENCES tb_Vendor(vendor_ID),
	mobile_Model VARCHAR(25),
	mobile_Condition VARCHAR(10),
	mobile_Bought_Price BIGINT,
	mobile_Sell_Price BIGINT
);

--Creating Table Requested Items in Order to Save the Record of Requested Items.
CREATE TABLE tb_Requested_Items(
	request_ID INT PRIMARY KEY IDENTITY(1,1),
	item_Name VARCHAR(25)
);

--Creating Table Order Accessories Details in Order to Keep the Record that Which Order Contains Which Accessories.
CREATE TABLE tb_Order_Accessory_Details(
	order_ID INT FOREIGN KEY REFERENCES tb_Orders(order_ID),
	accessory_ID INT FOREIGN KEY REFERENCES tb_Accessory(accessory_ID)
	PRIMARY KEY(order_ID,accessory_ID)
);

--Creating Table Order Easy Load Details in Order to Keep the Record that Which Order Contains Which Easy Load.
CREATE TABLE tb_Order_Easy_Load_Details(
	order_ID INT FOREIGN KEY REFERENCES tb_Orders(order_ID),
	easy_load_ID INT FOREIGN KEY REFERENCES tb_Easy_Load(easy_load_ID),
	PRIMARY KEY(order_ID,easy_load_ID)
);

--Creating Table Order Mobile Details in Order to Keep the Record that Which Order Contains Which Mobile Phone.
CREATE TABLE tb_Order_Mobile_Details(
	order_ID INT FOREIGN KEY REFERENCES tb_Orders(order_ID),
	mobile_IMEI_No BIGINT FOREIGN KEY REFERENCES tb_MObile(mobile_IMEI_NO),
	PRIMARY KEY(order_ID, mobile_IMEI_No)
);

--Creating Table Accessory Requested Item Details in Order to Keep the Record that Which Request Contains Which Accessory.
CREATE TABLE tb_Accessory_Requested_Items_Details(
	request_ID INT FOREIGN KEY REFERENCES tb_Requested_Items(request_ID),
	accessory_ID INT FOREIGN KEY REFERENCES tb_Accessory(accessory_ID),
	PRIMARY KEY(request_ID,accessory_ID)
);

--Adding New Column telecom_Company to the Table Easy_Load_Retailer Table.
ALTER TABLE tb_Easy_Load_Retailer ADD telecom_Company VARCHAR(25);

--Adding New Column Mobile Number in Easy Load Table.
ALTER TABLE tb_Easy_Load ADD Mobile_No BIGINT;
--Inserting 2 Dummy Records into Table Customers.
INSERT INTO tb_Customer VALUES('Muneeb Hamza',03164409484),('Abdur Rehman Khalid',03481483324);

--Inserting 2 More Dummy Recirds for Sake of Experiments.
INSERT INTO tb_Customer VALUES('Mahad Akbar',03204330753),('Bilal Asla',03104179753);

--Let's Suppose Bilal Has Given Request for Repair Mobile.
INSERT INTO tb_Repair_Request VALUES(4,'2018-11-18','2018-11-20','Samsung S8',250);

--Let's Suppose One Retailer Has Provided Easy Load Till Now.
INSERT INTO tb_Easy_Load_Retailer VALUES('Waqas Anwar',500,'2018-11-20','20:51:22','Zong');

--Let's Suppose there is Only Stock of Zong Till Now.
INSERT INTO tb_Easy_Load_Stock VALUES('Zong',500);

--Let's Suppose that there is Only One Company's Stock in Shop.
INSERT INTO tb_Vendor VALUES('Samsung');

--Let's Suppose there are Only Two Category Accessories Are Present.
INSERT INTO tb_Category VALUES('Handsfree'),('Data Cables');

--Let's Suppose There are Two Types of Handsfree and Two Types of Data Cables.
INSERT INTO tb_Accessory VALUES(1,'Data Cable 2.0',10,150,'2018-11-15'),(1,'Data Cable 3.0', 5, 180,'2018-11-16'),(2, 'A1', 15, 100,'2018-11-10'),(2,'A2',10,200,'2018-11-11');

--Let's Suppose there are only 2 Mobile Phones in Shop Till Now Both are Boxed Packs.
INSERT INTO tb_Mobile VALUES(374512362018125,NULL,1,'S8','NEW',60000,NULL);
INSERT INTO tb_Mobile VALUES(374512362018126,NULL,1,'S9','NEW',70000,NULL);

--Let's Suppose Muneeb Has Bought One Data Cable form the Shop.
INSERT INTO tb_Orders VALUES(1,'2018-11-21','12:19:12',150);
INSERT INTO tb_Order_Accessory_Details VALUES (1,1);

--Let's Suppose Mahad Has Bought Mobile From the Shop.
INSERT INTO tb_Orders VALUES(3,'2018-11-20','21:05:22',70000);
INSERT INTO tb_Order_Mobile_Details VALUES (2,374512362018126);

--Let's Abdur Rehman Khalid Has Bought Easy Load From the Shop.
INSERT INTO tb_Orders VALUES(2,'2018-11-18','11:50:15',100);
INSERT INTO tb_Easy_Load VALUES('Zong',100,'2018-11-18','11:50:15');
INSERT INTO tb_Order_Easy_Load_Details VALUES (3,1);

--Making an Entry in Seller table that will be related to mobiles that would be Bought new, as That mobile would have no seller.
INSERT INTO tb_Seller VALUES('Boxe Packed',00000000000,'Boxed Packed',NULL,NULL);


--The Following Section Contains The Views In Order to Displany Different Kind of Data on Software.

--Selecting Receipt No, Customer Name, Customer Mobile Number, Order Date, Order Time and Total of the Order By Means of a View.
CREATE VIEW v_CustomersDetails
AS
SELECT dbo.tb_Orders.order_ID AS Receipt_No, dbo.tb_Customer.customer_Name AS Name, dbo.tb_Customer.customer_Mobile_Number AS Mobile_No, dbo.tb_Orders.order_Date AS Date, dbo.tb_Orders.order_Time AS Time, dbo.tb_Orders.order_Total AS Total
FROM dbo.tb_Orders
INNER JOIN dbo.tb_Customer ON dbo.tb_Orders.customer_ID = dbo.tb_Customer.customer_ID;

--Demonstrating the Customer Details View.
Select * From v_CustomersDetails;

--Selecting Category of Accessory, Model of Accessory, Price Per Unit, Stock By Means of a View.
CREATE VIEW v_AccessoryDetails
AS
SELECT dbo.tb_Category.category_Name AS Category, dbo.tb_Accessory.accessory_Name AS Name, dbo.tb_Accessory.accessory_Price_Per_Unit AS Price, dbo.tb_Accessory.accessory_Stock AS Stock
FROM dbo.tb_Accessory
INNER JOIN dbo.tb_Category ON dbo.tb_Accessory.category_ID = dbo.tb_Category.category_ID;

--Demonstrating the Accessory Details View.
Select * From v_AccessoryDetails;

--Selecting Name of Retailer, Amount Recharged by Retailer, Stock Load Date, Stock Load Time, telecom Company Loaded By Means of a View.
CREATE VIEW v_Easy_Load_Retailer_Details
AS
SELECT dbo.tb_Easy_Load_Retailer.easy_load_retailer_Name AS Name, dbo.tb_Easy_Load_Retailer.easy_load_retailer_Amount_Recharge AS Amount, dbo.tb_Easy_Load_Retailer.stock_load_Date AS Date, dbo.tb_Easy_Load_Retailer.stock_load_Time AS Time, dbo.tb_Easy_Load_Retailer.telecom_Company AS Company
FROM dbo.tb_Easy_Load_Retailer;

--Demonstrating the Easy Load Retailer Details View.
Select * From v_Easy_Load_Retailer_Details;


--Selecting Easy Load Company, Easy Load Amount, Easy Load Date, Easy Load Time, Mobile Number by Means of a View.
CREATE VIEW v_Easy_Load_Details
AS
Select dbo.tb_Easy_Load.easy_load_Company AS Company, dbo.tb_Easy_Load.easy_load_Amount AS Amount, dbo.tb_Easy_Load.easy_load_Date AS Date, dbo.tb_Easy_Load.easy_load_Time AS Time, dbo.tb_Easy_Load.Mobile_No AS Mobile
FROM dbo.tb_Easy_Load;

--Demonstrating the Easy Load Details.
Select * From v_Easy_Load_Details;


--Selecting Received Date, Returned Date, Device Name and Repair Charges of Repair Request by Means of a View.
CREATE VIEW v_RepairRequest
AS
SELECT dbo.tb_Repair_Request.received_Date AS Received_Date, dbo.tb_Repair_Request.return_Date AS Returned_Date, dbo.tb_Repair_Request.device_Name AS Device, dbo.tb_Repair_Request.repair_Charges AS Charges
FROM dbo.tb_Repair_Request;

--Demonstrating the RepairRequest view.
Select * from v_RepairRequest;

--Selecting Mobile Model, IMEI No, Condition, Bought Price and Sell Price by the Mean of View.
CREATE VIEW v_Mobile_Detail
AS
Select dbo.tb_Vendor.vendor_Name,  dbo.tb_Mobile.mobile_Model AS Model, dbo.tb_Mobile.mobile_IMEI_No AS IMEI, dbo.tb_Mobile.mobile_Condition AS Condition, 
dbo.tb_Mobile.mobile_Bought_Price AS Bought_Price,
dbo.tb_Seller.seller_CNIC_No AS Seller_CNIC_No, dbo.tb_Seller.seller_Name AS Seller_Name, dbo.tb_Seller.seller_Address AS Seller_Address
FROM dbo.tb_Mobile
INNER JOIN dbo.tb_Vendor
ON dbo.tb_Mobile.vendor_ID = dbo.tb_Vendor.vendor_ID
INNER JOIN dbo.tb_Seller
ON dbo.tb_Mobile.seller_ID = dbo.tb_Seller.seller_ID;

--Demonstrating the Mobile Detail View.
Select * From v_Mobile_Detail;

--Creating Stored Procedure in Order to Insert the Customer in the Customer Table.
CREATE PROC p_InsertCustomerFromRepair
AS
INSERT INTO tb_Customer (customer_Name,customer_Mobile_Number) VALUES ('WALK IN CUSTOMER',000000000);

--Creating Stored Procedure in Order to Store the Repair Request Details.
CREATE PROC p_InsertRepairRequest
	@cusomterID int,
	@receivedDate date,
	@returnDate date,
	@deviceName Varchar(50),
	@repairCharges int
AS
INSERT INTO tb_Repair_Request (customer_ID, received_Date, return_Date, device_Name, repair_Charges) 
VALUES(@cusomterID, @receivedDate, @returnDate, @deviceName,@repairCharges);


--Creating the Stored Procedure to add customer from Easy Load Window.
CREATE PROC p_InsertCustomerFromEasyLoadWindow
	@easyLoadNumber bigint
AS
INSERT INTO tb_Customer (customer_Name,customer_Mobile_Number) VALUES ('WALK IN CUSTOMER',@easyLoadNumber);

--Creating Stored Procedure to add customer details into the Easy Load Record.
CREATE PROC p_InsertEasyLoadRecord
	@easyLoadCompany Varchar(25),
	@easyLoadAmount int,
	@easyLoadDate date,
	@easyLoadTime time,
	@mobileNo bigint
AS
INSERT INTO tb_Easy_Load (easy_load_Company, easy_load_Amount, easy_load_Date, easy_load_Time, Mobile_No)
VALUES(@easyLoadCompany,@easyLoadAmount,@easyLoadDate,@easyLoadTime,@mobileNo);


Select stock_left From tb_Easy_Load_Stock Where telecom_Company = 'Zong'




--Creating Stored Procedure in order to update the Stock of a Specific Easy Load Company.
--The Same Procedure Can be Used to Update Stock from Easy Load Retailer Window.
CREATE PROC v_UpdateStockFromEasyLoadWindow
	@toUpdate int,
	@company varchar(25)
AS
UPDATE tb_Easy_Load_Stock set stock_left = @toUpdate Where telecom_Company = @company;

--Creating a Procedure in order to Save Customer Id in Orders Table.
CREATE PROC p_InsertionInOrderFromEasyLoadWindow
	@customer int,
	@date date,
	@time time,
	@total int
AS
INSERT INTO tb_Orders (customer_ID, order_Date, order_Time, order_Total) VALUES (@customer, @date, @time, @total);

Select * From tb_Easy_Load_Retailer;

--Creating a Procedure in order to Save the Information of Easy Load Retailer.
CREATE PROC p_AddRetailerInformation
	@retailerName varchar(50),
	@amountRecharged int,
	@loadDate date,
	@loadTime time,
	@company varchar(25)
AS
INSERT INTO tb_Easy_Load_Retailer (easy_load_retailer_Name, easy_load_retailer_Amount_Recharge, stock_load_Date, stock_load_Time, telecom_Company)
VALUES (@retailerName, @amountRecharged, @loadDate, @loadTime, @company);



Select category_Name AS Category From tb_Category
Where category_ID IN (Select Distinct category_ID From tb_Accessory);

Select * From tb_Accessory;

Select * From tb_Category;

--Creating a Stored Procedure in order to update the Stock if Item is Present in the Accessories Already.
CREATE PROC p_UpdateStockAccessories
	@accessoryName varchar(50),
	@updatedStock int
AS
Update tb_Accessory Set accessory_Stock = @updatedStock Where accessory_Name = @accessoryName; 


--Creating a Stored Procedure In Order to Store a New Item in the Record.
CREATE PROC v_AddNewAccessory
	@categoryID int,
	@name varchar(50),
	@stock int,
	@pricePerUnit int,
	@boughtDate Date
AS
Insert Into tb_Accessory
VALUES(@categoryID, @name, @stock, @pricePerUnit, @boughtDate);

--Creating a Stored Procedure in Order to Save the Information of Seller.
CREATE PROC p_AddSellerInformation
	@sellerName varchar(50),
	@sellerCnicNo Bigint,
	@sellerAddress varchar(75),
	@buyDate Date,
	@buyTime Time
AS
Insert Into tb_Seller(seller_Name, seller_CNIC_No, seller_Address, buy_Date, buy_Time) VALUES(@sellerName, @sellerCnicNo, @sellerAddress, @buyDate, @buyTime);

--Creating a Stored Procedure in Order to Store Information Related to The Mobile.
CREATE PROC p_AddMobileInformation
	@imeiNo Bigint,
	@sellerID int,
	@venderID int,
	@mobileModel varchar(25),
	@mobileCondition varchar(25),
	@mobileBoughtPrice Bigint
AS
Insert into tb_Mobile(mobile_IMEI_No, seller_ID, vendor_ID, mobile_Model, mobile_Condition, mobile_Bought_Price)
VALUES(@imeiNo, @sellerID, @venderID, @mobileModel, @mobileCondition, @mobileBoughtPrice);

--Creating a Stored procedure in order to add customer from the Receipt Window.
CREATE PROC p_InsertCustomerFromReceiptWindow
	@customerName varchar(50),
	@customerMobileNo BigInt
AS
Insert Into tb_Customer(customer_Name, customer_Mobile_Number) Values(@customerName, @customerMobileNo);

--Creating a Procedure in order to set the Sell value of the Mobile which will indicate that the Mobile Has Been Sold.
CREATE PROC p_UpdateMobileRecord
	@boughtPrice Bigint,
	@sellPrice Bigint,
	@buyer_CNIC Bigint
AS
Update tb_Mobile set mobile_Sell_Price = @sellPrice, buyer_CNIC = @buyer_CNIC Where mobile_Bought_Price = @boughtPrice;

SELECT SUM (tb_Orders.order_Total) FROM tb_Orders 
WHERE MONTH(tb_Orders.order_Date)=12 AND YEAR(tb_Orders.order_Date) = 2018;

Insert into tb_Category Values('Battery'),('Wireless Mouse'),('Optical Mouse'),('Usb Hub'),('Power Cable'),
('Flash Drive'),('VGA Cable'),('Low Quality Power Cable'),('Laptop Cable'),('Power Bank'),('Charger');

Select * From tb_Category;

Insert into  tb_Accessory(category_ID, accessory_Name, accessory_Stock, accessory_Price_Per_Unit, accessory_Bought_Date) values (1,'Music U10 Handsfree',19,150,'2018-12-12');

Insert into  tb_Accessory(category_ID, accessory_Name, accessory_Stock, accessory_Price_Per_Unit, accessory_Bought_Date) values (4,'Dell 2.4G Wireless Mouse',5,350,'2018-10-11');

Insert into  tb_Accessory(category_ID, accessory_Name, accessory_Stock, accessory_Price_Per_Unit, accessory_Bought_Date) values (5,'Centroprorous Optical Mouse',7,300,'2018-9-5');

Insert into  tb_Accessory(category_ID, accessory_Name, accessory_Stock, accessory_Price_Per_Unit, accessory_Bought_Date) values (7,'Normal Quality Power Cable',5,125,'2018-10-18');
