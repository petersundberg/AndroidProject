package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
                gotoApi();
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
        Intent intentAddCustomer = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intentAddCustomer);
        Toast.makeText(this, "Lägg till kund", Toast.LENGTH_SHORT).show();
    }

    public void viewAllCustomers(){
        Intent intentViewAllCustomers = new Intent(StartActivity.this, AllCustomersActivity.class);
        startActivity(intentViewAllCustomers);
        Toast.makeText(this, "Alla kunder ...", Toast.LENGTH_SHORT).show();
    }

    public void gotoApi(){
        Intent intentAPI = new Intent(StartActivity.this, ApiActivity.class);
        startActivity(intentAPI);
        Toast.makeText(this, "Skapa API ...", Toast.LENGTH_SHORT).show();
    }

    public void aboutApp(){
        Intent intentAboutApp = new Intent(StartActivity.this, AboutActivity.class);
        startActivity(intentAboutApp);
        Toast.makeText(this, "Om appen ...", Toast.LENGTH_SHORT).show();
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
                aboutApp_FromActionBar();
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
    public void aboutApp_FromActionBar(){
        Intent intentAboutApp = new Intent(StartActivity.this, AboutActivity.class);
        startActivity(intentAboutApp);
    }
    //show activity to create API
    public void createAPI(){
        Toast.makeText(this, "Skapa API ...", Toast.LENGTH_SHORT).show();
        Intent intentAPI = new Intent(StartActivity.this, ApiActivity.class);
        startActivity(intentAPI);
    }






}
