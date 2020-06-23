package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private static final String MY_PREF = "SHAREDPREF";

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

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Något blev fel ...", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "Fel", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Kund tillagd: " + success, Toast.LENGTH_SHORT).show();
                et_Name.setText("");    //clear editText View after adding customer
                et_Age.setText("");     //clear age View after adding customer
                sw_Active.setChecked(false);    //set Active View to false after adding customer
                et_Name.requestFocus();     //set focus to name view

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

        loadData(); //call function to restore data to views
        et_Name.requestFocus(); //set focus to name View

    }


    private void updateListData() {
        lv_CustomerList.setAdapter(customerArrayAdapter);
    }

    private void showCustomersOnListView() {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomers());
        lv_CustomerList.setAdapter(customerArrayAdapter);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sh = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();

        editor.putString("name", et_Name.getText().toString());
        editor.putString("age", String.valueOf(et_Age.getText()));
        editor.putBoolean("active", sw_Active.isChecked());
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sh = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        String savedStateName = sh.getString("name","");
        et_Name.setText(savedStateName);
        //Toast.makeText(this, "GOT: " + savedStateName, Toast.LENGTH_SHORT).show();

        String savedStateAge = sh.getString("age","");
        et_Age.setText(savedStateAge);

        boolean savedStateActive = sh.getBoolean("active",false);
        sw_Active.setChecked(savedStateActive);
    }


}














