package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
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
    private EditText et_Name;
    private EditText et_Age;
    private Switch sw_Active;
    private Button btn_ViewAll;
    private Button btn_Add;
    private ListView lv_CustomerList;
    private ArrayAdapter customerArrayAdapter;
    private DataBaseHelper dataBaseHelper;

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


        //set listener on btn to add new customer
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;

                try{
                    customerModel = new CustomerModel(-1, et_Name.getText().toString(), Integer.parseInt(et_Age.getText().toString()), sw_Active.isChecked());
                    //Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Något blev fel ...", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "Fel", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Kund tillagd: " + success, Toast.LENGTH_SHORT).show();

            }
        });

        //set listener on btn
        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentViewAllCustomers = new Intent(MainActivity.this, AllCustomersActivity.class);
                startActivity(intentViewAllCustomers);

            }
        });

    }


    private void updateListData() {
        lv_CustomerList.setAdapter(customerArrayAdapter);
    }

    private void showCustomersOnListView() {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomers());
        lv_CustomerList.setAdapter(customerArrayAdapter);
    }


    //ActionBar menu
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


    //show activity with info about this app
    public void aboutApp(){
        Intent intentAboutApp = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intentAboutApp);
    }
    //show activity to create API
    public void createAPI(){
        Toast.makeText(this, "Skapa API ...", Toast.LENGTH_SHORT).show();
        Intent intentAPI = new Intent(MainActivity.this, ApiActivity.class);
        startActivity(intentAPI);
    }


}














