package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ApiActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button btn_get, btn_post, btn_put, btn_delete;
    private TextView tv_api_result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        btn_get = findViewById(R.id.btn_get);
        btn_post = findViewById(R.id.btn_post);
        btn_put = findViewById(R.id.btn_put);
        btn_delete = findViewById(R.id.btn_delete);

        tv_api_result = findViewById(R.id.tv_api_result);

        //updateViews();

        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_put.setOnClickListener(this);
        btn_delete.setOnClickListener(this);









    }

    @Override
    public void onClick(View v) {

    }






}
