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

import java.util.List;

public class AllCustomersActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_all_customers);

//            et_Name = findViewById(R.id.et_Name);
//            et_Age = findViewById(R.id.et_Age);
//            sw_Active = findViewById(R.id.sw_Active);
//            btn_ViewAll = findViewById(R.id.btn_ViewAll);
//            btn_Add = findViewById(R.id.btn_Add);
            lv_CustomerList = findViewById(R.id.lv_CustomerList);
            registerForContextMenu(lv_CustomerList);   //register context menu to listView

//            dataBaseHelper = new DataBaseHelper(AllCustomersActivity.this);
//            customerArrayAdapter = new ArrayAdapter<CustomerModel>(AllCustomersActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomers());


            //Create list of all customers on create
            dataBaseHelper = new DataBaseHelper(AllCustomersActivity.this);
            List<CustomerModel> allCustomers = dataBaseHelper.getAllCustomers();
            customerArrayAdapter = new ArrayAdapter<CustomerModel>(AllCustomersActivity.this, android.R.layout.simple_list_item_1, allCustomers);
            updateListData();
            //Toast.makeText(MainActivity.this, allCustomers.toString(), Toast.LENGTH_SHORT).show();


            //Create list of all customers names on create
            //dataBaseHelper = new DataBaseHelper(AllCustomersActivity.this);
//            List<CustomerModel> customersName = dataBaseHelper.getAllCustomersName();
//            customerArrayAdapter = new ArrayAdapter<CustomerModel>(AllCustomersActivity.this, android.R.layout.simple_list_item_1, customersName);
//            updateListData();










            //method used to show all customers from database in the listview
            //showCustomersOnListView(dataBaseHelper);


            //set listener on btn
//            btn_Add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    CustomerModel customerModel;
//
//                    try{
//                        customerModel = new CustomerModel(-1, et_Name.getText().toString(), Integer.parseInt(et_Age.getText().toString()), sw_Active.isChecked());
//                        //Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                    catch (Exception e){
//                        Toast.makeText(AllCustomersActivity.this, "Error creating customer", Toast.LENGTH_SHORT).show();
//                        customerModel = new CustomerModel(-1, "Error", 0, false);
//                    }
//
//                    DataBaseHelper dataBaseHelper = new DataBaseHelper(AllCustomersActivity.this);
//
//                    boolean success = dataBaseHelper.addOne(customerModel);
//                    Toast.makeText(AllCustomersActivity.this, "Customer added: " + success, Toast.LENGTH_SHORT).show();
//                    customerArrayAdapter = new ArrayAdapter<CustomerModel>(AllCustomersActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomers());
//                    updateListData();
//
//                }
//            });

            //set listener on btn
//            btn_ViewAll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dataBaseHelper = new DataBaseHelper(AllCustomersActivity.this);
//                    List<CustomerModel> allCustomers = dataBaseHelper.getAllCustomers();
//                    customerArrayAdapter = new ArrayAdapter<CustomerModel>(AllCustomersActivity.this, android.R.layout.simple_list_item_1, allCustomers);
//                    updateListData();
//                    //Toast.makeText(MainActivity.this, allCustomers.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });




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

        private void showCustomersOnListView() {
            customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomers());
            lv_CustomerList.setAdapter(customerArrayAdapter);
        }

//    private void showCustomerOwnInfo() {
//        customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, dataBaseHelper.showCustomerOwnInfo());
//        lv_CustomerList.setAdapter(customerArrayAdapter);
//    }

    private void getAllCustomersNameToListView() {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllCustomersName());
        lv_CustomerList.setAdapter(customerArrayAdapter);
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
            Intent intentAboutApp = new Intent(AllCustomersActivity.this, AboutActivity.class);
            startActivity(intentAboutApp);
        }
        public void createAPI(){
            Toast.makeText(this, "Skapa API ...", Toast.LENGTH_SHORT).show();
            Intent intentAPI = new Intent(AllCustomersActivity.this, ApiActivity.class);
            startActivity(intentAPI);
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
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            switch (item.getItemId()) {
                case R.id.item_info:
                    Intent intentItemInfo = new Intent(AllCustomersActivity.this, ItemInfoActivity.class);
                    //intentItemInfo.putExtra("item_info", (Parcelable) info);
                    startActivity(intentItemInfo);

                    boolean status1 = dataBaseHelper.deleteOneCustomer((CustomerModel) customerArrayAdapter.getItem(info.position));
                    Toast.makeText(AllCustomersActivity.this, "Info visas: " + status1, Toast.LENGTH_SHORT).show();
                    //showCustomerOwnInfo();
                    break;
                //customerArrayAdapter.remove(customerArrayAdapter.getItem(info.position));
                //updateAutoCompView();

//--------------------------------------------
                case R.id.delete_item:
                    boolean status = dataBaseHelper.deleteOneCustomer((CustomerModel) customerArrayAdapter.getItem(info.position));
                    Toast.makeText(AllCustomersActivity.this, "Raderad: " + status, Toast.LENGTH_SHORT).show();
                    showCustomersOnListView();
                    break;
                //customerArrayAdapter.remove(customerArrayAdapter.getItem(info.position));
                //updateAutoCompView();

//--------------------------------------------
                case R.id.edit_item:
                    //code to edit klicked customer
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.edit_dialog,null);

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


//-------------------------------------------------------------------
                    //Setters for current values
                    dialog_edit_name.setText(tempCustomer.getName());

//-------------------------------------------------------------------


                    //int ageVal = Integer.parseInt(tempCustomer.getAge().toString());

                    //int ageVal = Integer.parseInt(dialog_edit_age.getText().toString());

                    //int ageValue = Integer.parseInt(tempCustomer.getAge().toString());

                    //String string_Age = dialog_edit_age.getText().toString();
                    //int int_Age = Integer.parseInt(string_Age);
                    //dialog_edit_age.setText(int_Age);



                    //dialog_edit_age.setText(tempCustomer.getAge());     //dialog_edit_age.setText(tempCustomer.getAge());

//---------------------------------------

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
                            Toast.makeText(AllCustomersActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            showCustomersOnListView();

                            updateDialog.hide();
                            //updateAutoCompView();
                        }
                    });

//-----------------------------



            }
            return super.onContextItemSelected(item);


//                return true;
//            case R.id.edit_item:
//                String itemValue = (String) customerArrayAdapter.getItem(info.position); //value to edit
//                editSelectedItem(itemValue);
//                return true;
            //default:
            //    return super.onContextItemSelected(item);






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














