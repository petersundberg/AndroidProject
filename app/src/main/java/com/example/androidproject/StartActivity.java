package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {


    private Button btn_add_customer, btn_view_all_customers, btn_api, btn_about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btn_add_customer = findViewById(R.id.btn_add_customer);
        btn_view_all_customers = findViewById(R.id.btn_view_all_customers);
        btn_api = findViewById(R.id.btn_api);
        btn_about = findViewById(R.id.btn_about);


        btn_add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomer();
            }
        });

        btn_view_all_customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllCustomers();
            }
        });


        btn_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiActivity();
            }
        });


        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutApp();
            }
        });


    }



    public void addCustomer(){
        Intent intentAddCustomer = new Intent(StartActivity.this, MainActivity.class); /////////////////////////////////////
        startActivity(intentAddCustomer);
    }


    public void viewAllCustomers(){
        Intent intentViewAllCustomers = new Intent(StartActivity.this, AllCustomersActivity.class); /////////////////////////////////////
        startActivity(intentViewAllCustomers);
    }

    public void apiActivity(){
        Toast.makeText(this, "Skapa API ...", Toast.LENGTH_SHORT).show();
        Intent intentAPI = new Intent(StartActivity.this, ApiActivity.class);
        startActivity(intentAPI);
    }

    public void aboutApp(){
        Intent intentAboutApp = new Intent(StartActivity.this, AboutActivity.class);
        startActivity(intentAboutApp);
    }

}
