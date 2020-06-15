package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    //private static String MY_LISTVIEW_STATE = "LISTVIEW_STATE";
    EditText et_Name;
    EditText et_Age;
    Switch sw_Active;
    Button btn_ViewAll;
    Button btn_Add;
    ListView lv_CustomerList;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Name = findViewById(R.id.et_Name);
        et_Age = findViewById(R.id.et_Age);
        sw_Active = findViewById(R.id.sw_Active);
        btn_ViewAll = findViewById(R.id.btn_ViewAll);
        btn_Add = findViewById(R.id.btn_Add);
        lv_CustomerList = findViewById(R.id.lv_CustomerList);
        registerForContextMenu(lv_CustomerList);   //register context menu to listView

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomers());

        //method used to show all customers from database in the listview
        //showCustomersOnListView(dataBaseHelper);


        //set listener on btn
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;

                try{
                    customerModel = new CustomerModel(-1, et_Name.getText().toString(), Integer.parseInt(et_Age.getText().toString()), sw_Active.isChecked());
                    //Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "Error", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Customer added: " + success, Toast.LENGTH_SHORT).show();
                customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomers());
                updateListData();

            }
        });

        //set listener on btn
        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<CustomerModel> allCustomers = dataBaseHelper.getAllCustomers();
                customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, allCustomers);
                updateListData();
                //Toast.makeText(MainActivity.this, allCustomers.toString(), Toast.LENGTH_SHORT).show();
            }
        });




        //set listener on item in listView
//        lv_CustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CustomerModel customerClicked = (CustomerModel) parent.getItemAtPosition(position);
//                dataBaseHelper.deleteOneCustomer(customerClicked);
//                showCustomersOnListView(dataBaseHelper);
//                Toast.makeText(MainActivity.this, "Deleted: " + customerClicked.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });



//        if(savedInstanceState!=null) {
//            listViewState = savedInstanceState.getParcelable(MY_LISTVIEW_STATE);
//        }

    }



    private void updateListData() {
        lv_CustomerList.setAdapter(customerArrayAdapter);
    }

    private void showCustomersOnListView(DataBaseHelper dataBaseHelper2) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getAllCustomers());
        updateListData();
    }


    //ActionBar menu in Main
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }
    // create action for each item in ActionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.about:
                aboutApp();
                return(true);
            case R.id.api:
                createAPI();
                return(true);
            case R.id.exit:
                Toast.makeText(this, "Avslutar app ...", Toast.LENGTH_SHORT).show();
                finish();
                //return(true);
        }
        return(super.onOptionsItemSelected(item));
    }




    public void aboutApp(){
        Intent intentAboutApp = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intentAboutApp);
    }
    public void createAPI(){
        Toast.makeText(this, "Skapa API ...", Toast.LENGTH_SHORT).show();
        Intent intentAPI = new Intent(MainActivity.this, ApiActivity.class);
        startActivity(intentAPI);
    }

    //create context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    //Create action to perform on each item in list
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_item:
                customerArrayAdapter.remove(customerArrayAdapter.getItem(info.position));
                return true;
//            case R.id.edit_item:
//                String itemValue = (String) customerArrayAdapter.getItem(info.position); //value to edit
//                editSelectedItem(itemValue);
//                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }








//    //set listener on item in listView
//        lv_CustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            CustomerModel customerClicked = (CustomerModel) parent.getItemAtPosition(position);
//            dataBaseHelper.deleteOneCustomer(customerClicked);
//            showCustomersOnListView(dataBaseHelper);
//            Toast.makeText(MainActivity.this, "Deleted: " + customerClicked.toString(), Toast.LENGTH_SHORT).show();
//        }
//    });








//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelable("MY_LISTVIEW_STATE", lv_CustomerList.onSaveInstanceState());
//    }

}































