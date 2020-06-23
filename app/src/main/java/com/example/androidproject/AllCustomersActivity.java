package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.Toolbar;

import java.util.List;

public class AllCustomersActivity extends AppCompatActivity {

        //private static String MY_LISTVIEW_STATE = "LISTVIEW_STATE";
        private EditText et_Name;
        private EditText et_Age;
        private Switch sw_Active;
        private Button btn_ViewAll;
        private Button btn_Add;
        private Button btn_Start;
        private ListView lv_CustomerList;
        private ArrayAdapter customerArrayAdapter;
        private DataBaseHelper dataBaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers);

            lv_CustomerList = findViewById(R.id.lv_CustomerList);
            btn_Add = findViewById(R.id.btn_Add);
            btn_Start = findViewById(R.id.btn_Start);
            registerForContextMenu(lv_CustomerList);   //register context menu to listView


            //Create list of all customers on create
            dataBaseHelper = new DataBaseHelper(AllCustomersActivity.this);
            List<CustomerModel> allCustomers = dataBaseHelper.getAllCustomers();
            customerArrayAdapter = new ArrayAdapter<CustomerModel>(AllCustomersActivity.this, android.R.layout.simple_list_item_1, allCustomers);
            updateListData();


            //set listener on btn_Add and go to activity to add new customer
            btn_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent addNewCustomer = new Intent(AllCustomersActivity.this, MainActivity.class);
                    startActivity(addNewCustomer);

                }
            });

            //set listener on btn_Start (go to StartActivity)
            btn_Start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToStartActivity = new Intent(AllCustomersActivity.this, StartActivity.class);
                    startActivity(goToStartActivity);

                }
            });



        }



        //update list
        private void updateListData() {
            lv_CustomerList.setAdapter(customerArrayAdapter);
        }

        //get all customers and show in listview
        private void showCustomersOnListView() {
            customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomers());
            lv_CustomerList.setAdapter(customerArrayAdapter);
        }



        private void getAllCustomersNameToListView() {
            customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomersName());
            lv_CustomerList.setAdapter(customerArrayAdapter);
        }




        //create context menu
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }

        //Create action to perform on each clicked item in list (context menu)
        @Override
        public boolean onContextItemSelected(@NonNull MenuItem item) {
            final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            switch (item.getItemId()) {

                case R.id.item_info:
                    CustomerModel customer = dataBaseHelper.showCustomerInfo((CustomerModel) customerArrayAdapter.getItem(info.position));

                    Intent itemIntent = new Intent(AllCustomersActivity.this, ItemInfoActivity.class);
                    itemIntent.putExtra("name",  customer.getName());
                    itemIntent.putExtra("age",  customer.getAge());
                    itemIntent.putExtra("active",  customer.isActive());

                    startActivity(itemIntent);
                break;
//--------------------------------------------
                //delete chosen item
                case R.id.delete_item:
                    //delete chosen customer
                    boolean status = dataBaseHelper.deleteOneCustomer((CustomerModel) customerArrayAdapter.getItem(info.position));
                    Toast.makeText(AllCustomersActivity.this, "Kund borttagen: " + status, Toast.LENGTH_SHORT).show();
                    showCustomersOnListView();
                    break;

//--------------------------------------------
                //edit chosen item
                case R.id.edit_item:
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                    LayoutInflater inflater = getLayoutInflater();
                    final View dialogView = inflater.inflate(R.layout.edit_dialog,null);

                    dialogBuilder.setView(dialogView);

                    //Declare  variables
                    final EditText dialog_edit_name, dialog_edit_age;
                    final Switch dialog_sw_active;
                    Button dialog_btn_update;

                    //initiate Views
                    dialog_edit_name = dialogView.findViewById(R.id.dialog_edit_name);
                    dialog_edit_age = dialogView.findViewById(R.id.dialog_edit_age);
                    dialog_sw_active = dialogView.findViewById(R.id.dialog_sw_active);
                    dialog_btn_update = dialogView.findViewById(R.id.dialog_btn_update);

                    final CustomerModel tempCustomer = (CustomerModel) customerArrayAdapter.getItem(info.position);

                    //Setters for current values
                    dialog_edit_name.setText(tempCustomer.getName());
                    dialog_edit_age.setText(String.valueOf(tempCustomer.getAge()));

                    dialog_sw_active.setChecked(tempCustomer.isActive());

                    final AlertDialog updateDialog = dialogBuilder.create();
                    updateDialog.show();

                    dialog_btn_update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            tempCustomer.setName(dialog_edit_name.getText().toString());

                            //parse age int to string
                            int ageValue = Integer.parseInt(dialog_edit_age.getText().toString());
                            tempCustomer.setAge(ageValue);  //age as String
                            tempCustomer.setActive(dialog_sw_active.isChecked());

                            dataBaseHelper.updateCustomer(tempCustomer);
                            Toast.makeText(AllCustomersActivity.this, "Uppdaterad", Toast.LENGTH_SHORT).show();
                            showCustomersOnListView();

                            updateDialog.hide();

                        }
                    });

            }
            return super.onContextItemSelected(item);

        }

    }






