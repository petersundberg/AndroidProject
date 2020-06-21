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

    private TextView tv_name;
    private TextView tv_age;
    private TextView tv_active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

//        lv_CustomerList = findViewById(R.id.lv_CustomerList);
//        registerForContextMenu(lv_CustomerList);   //register context menu to listView
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        tv_active = findViewById(R.id.tv_active);

        //recieveTextView = findViewById(R.id.recieveTextView);
        Intent itemIntent = getIntent();
        String name = itemIntent.getStringExtra("name");
        int ageInt = itemIntent.getIntExtra("age", 0);
        boolean active = getIntent().getExtras().getBoolean("active");

        tv_name.setText(name);
        tv_age.setText(String.valueOf(ageInt));
        tv_active.setText(String.valueOf(active));

        Toast.makeText(this, "Info för: " + name, Toast.LENGTH_SHORT).show();

    }

    private void updateListData() {
        lv_CustomerList.setAdapter(customerArrayAdapter);
    }

}
