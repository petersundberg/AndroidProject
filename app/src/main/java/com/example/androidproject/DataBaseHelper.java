package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    //This "onCreate" is called the first time database is accessed. Put code here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableStatement);
    }

    //This "onUpgrade" is called if the database version number changes.
    // It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add customer to database
    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();     //"ContentValues" is like "putExtra" in an Intent (Key/Value-pairs)
        //don't put in ID here, it's autoincremented
        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }


    //show selected customer's info from database
    public boolean showCustomerInfo(CustomerModel customerModel){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_NAME + " = " + customerModel.getName();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    //delete one customer from database
    public boolean deleteOneCustomer(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + customerModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }



    public void updateCustomer(CustomerModel customerToUpdate){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID,customerToUpdate.getId());
        cv.put(COLUMN_CUSTOMER_NAME,customerToUpdate.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerToUpdate.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerToUpdate.isActive());

        db.update(CUSTOMER_TABLE,cv,COLUMN_ID + " = " + customerToUpdate.getId(),null);

    }


    //create list out of all customers
    public List<CustomerModel> getAllCustomers(){
        List<CustomerModel> returnList = new ArrayList<>();

        //get data from database
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through cursor result, create new object, put it into returnList
            do{
                int customerID = cursor.getInt(0);  // int position in table: 0
                String customerName = cursor.getString(1);  // String position in table: 1
                int customerAge = cursor.getInt(2); // int position in table: 2
                boolean customerActive = cursor.getInt(3) == 1 ? true: false; // boolean (int) position in table: 3

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAge, customerActive);
                returnList.add(newCustomer);

            }while (cursor.moveToNext());
        }
        else{
            //if it fails, don't add anything to list
        }

        //close cursor and db
        cursor.close();
        db.close();
        return returnList;
    }


    //create list out of all customers name
    public List<CustomerModel> getAllCustomersName(){
        List<CustomerModel> returnList = new ArrayList<>();

        //get data from database
        //String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        //String queryNameString = "SELECT COLUMN_CUSTOMER_NAME FROM " + CUSTOMER_TABLE;
        //String queryNameString = "SELECT * FROM " + CUSTOMER_TABLE + COLUMN_CUSTOMER_NAME;
        //String queryNameString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + customerModel.getName();
        //String queryNameString = "SELECT * FROM COLUMN_CUSTOMER_NAME " + CUSTOMER_TABLE;
        //String queryNameString = "SELECT * FROM " + CUSTOMER_TABLE + " COLUMN_CUSTOMER_NAME";
        String queryNameString =  "SELECT CUSTOMER_NAME FROM " + CUSTOMER_TABLE;


    //String queryNameString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_NAME;      //String queryNameString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_NAME;
        //String queryNameString = "SELECT FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_NAME + " = " + customerModel.getId();
        //String queryNameString = "SELECT * FROM  " + CUSTOMER_TABLE WHERE category =" + vg;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryNameString, null);
        if(cursor.moveToFirst()){
            //loop through cursor result, create new object, put it into returnList
            do{
                //int customerID = cursor.getInt(0);  // int position in table: 0
                String customerName = cursor.getString(1);  // String position in table: 1
                //int customerAge = cursor.getInt(2); // int position in table: 2
                //boolean customerActive = cursor.getInt(3) == 1 ? true: false; // boolean (int) position in table: 3

                CustomerModel newCustomer = new CustomerModel(customerName);   //CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAge, customerActive);
                returnList.add(newCustomer);

            }while (cursor.moveToNext());
        }
        else{
            //if it fails, don't add anything to list
        }

        //close cursor and db
        cursor.close();
        db.close();
        return returnList;
    }


}





















