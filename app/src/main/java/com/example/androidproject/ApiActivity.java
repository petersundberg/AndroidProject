package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ApiActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button btn_get, btn_post, btn_put, btn_delete;
    private TextView tv_api_result;
    private ListView lv_api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        btn_get = findViewById(R.id.btn_get);
        btn_post = findViewById(R.id.btn_post);
        btn_put = findViewById(R.id.btn_put);
        btn_delete = findViewById(R.id.btn_delete);

        tv_api_result = findViewById(R.id.tv_api_result);
        lv_api = findViewById(R.id.lv_api);

        //updateViews();

        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_put.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        lv_api = findViewById(R.id.lv_api);
        registerForContextMenu(lv_api);   //register context menu to listView

    }


    //create context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.user_context_menu, menu);
    }

    //Create action to perform on each clicked item in list (context menu)
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.view_details:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.edit_dialog,null);

                dialogBuilder.setView(dialogView);

                //Declare  variables
                final EditText dialog_edit_name, dialog_edit_age;
                final Switch dialog_sw_active;
                Button dialog_btn_update;


                Toast.makeText(this, "Details here ...", Toast.LENGTH_SHORT).show();



                //boolean status = dataBaseHelper.showCustomerInfo((CustomerModel) customerArrayAdapter.getItem(info.position));
                //Toast.makeText(AllCustomersActivity.this, "Visar info: " + status, Toast.LENGTH_SHORT).show();



        }
        return super.onContextItemSelected(item);


    }


    @Override
    public void onClick(View v) {

    }
}




