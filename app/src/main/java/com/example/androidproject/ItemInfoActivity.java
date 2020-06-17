package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemInfoActivity extends AppCompatActivity {

    private ListView lv_CustomerList;
    private TextView tv_itemInfo;
    private ArrayAdapter customerArrayAdapter;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

//        lv_CustomerList = findViewById(R.id.lv_CustomerList);
//        registerForContextMenu(lv_CustomerList);   //register context menu to listView



        //recieveTextView = findViewById(R.id.recieveTextView);
        Intent recieveIntent = getIntent();
        String name = recieveIntent.getStringExtra("name");
        Toast.makeText(this, "info for: " + name, Toast.LENGTH_SHORT).show();
        //tv_itemInfo = findViewById(R.id.tv_itemInfo);
        //tv_itemInfo.setText("ID: " + itemInfo);
        //Parcelable[] itemInfo = recieveIntent.getParcelableArrayExtra("item_info");  //String text = recieveIntent.getStringExtra("item_info"); //String text = recieveIntent.getStringExtra("DATA");
        //lv_CustomerList.setText(itemInfo);
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();



//        boolean status = dataBaseHelper.showCustomerInfo((CustomerModel) customerArrayAdapter.getItem(info.position));
//        Toast.makeText(ItemInfoActivity.this, "Raderad: " + status, Toast.LENGTH_SHORT).show();
        //showCustomerInfo();




       //dataBaseHelper.showCustomerOwnInfo(CustomerModel customerModel);

////        private void showCustomerInfo() {
//        dataBaseHelper = new DataBaseHelper(ItemInfoActivity.this);
//        List<CustomerModel> customerInfo = dataBaseHelper.showCustomerInfo();
//        customerArrayAdapter = new ArrayAdapter<CustomerModel>(ItemInfoActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.customerInfo);
//        lv_CustomerList.setAdapter(customerArrayAdapter);

//        dataBaseHelper = new DataBaseHelper(ItemInfoActivity.this);
//        List<CustomerModel> oneCustomerInfo = dataBaseHelper.showCustomerInfo();
//        customerArrayAdapter = new ArrayAdapter<CustomerModel>(ItemInfoActivity.this, android.R.layout.simple_list_item_1, oneCustomerInfo);
//        updateListData();


//    }



//    dataBaseHelper = new DataBaseHelper(AllCustomersActivity.this);
//    List<CustomerModel> allCustomers = dataBaseHelper.getAllCustomers();
//    customerArrayAdapter = new ArrayAdapter<CustomerModel>(AllCustomersActivity.this, android.R.layout.simple_list_item_1, allCustomers);
//    updateListData();


    }

//        private void showCustomerInfo() {
//        customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, dataBaseHelper.showCustomerInfo());
//        lv_CustomerList.setAdapter(customerArrayAdapter);
//
//    }


    private void updateListData() {
        lv_CustomerList.setAdapter(customerArrayAdapter);
    }


}
