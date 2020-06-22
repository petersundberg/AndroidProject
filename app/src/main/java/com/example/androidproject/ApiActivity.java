package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiActivity extends AppCompatActivity  implements View.OnClickListener {


//--------------------------------------------------------

    private static final String TAG = "ApiActivity";

//--------------------------------------------------------

    private Button btn_get,btn_post,btn_put,btn_delete;
    private TextView tv_api_result;
    private ListView lv_api;
    //private ImageView iv_api_avatar;
    private ArrayAdapter adp;
    private ArrayList<UserModel> userArrayList;

    private ArrayAdapter userArrayAdapter;
    //private DataBaseHelper dataBaseHelper;

    private final static String SERVER_URL = "https://reqres.in/api/";
    private final static String SERVER_URL_DELETE = "https://jsonplaceholder.typicode.com/posts/1";


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

        //iv_api_avatar = findViewById(R.id.iv_api_avatar);
        userArrayList = new ArrayList();

        //assign context menu to listview
        registerForContextMenu(lv_api);
        updateViews();


        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_put.setOnClickListener(this);
        btn_delete.setOnClickListener(this);


//--------------------------------------------------------------
        //restore state, from landscape mode
        if(savedInstanceState != null) {
            String savedStateText = savedInstanceState.getString("apiStringData");
            tv_api_result.setText(savedStateText);  //restore state for TextView "tv_api_result"

        }
//--------------------------------------------------------------

    }


    private void updateListData() {
        lv_api.setAdapter(userArrayAdapter);
    }


    private void updateViews() {
        adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,userArrayList);
        lv_api.setAdapter(adp);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //API GET
            case R.id.btn_get:
                Toast.makeText(this, "GET", Toast.LENGTH_SHORT).show();

                JsonObjectRequest myGetReq = new JsonObjectRequest(Request.Method.GET, SERVER_URL + "users?page=2", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //txt_result.setText(response.toString());
                        userArrayList.clear(); //first clear userArrayList from earlier requests
                        try {
                            JSONArray dataObject = response.getJSONArray("data");
                            for(int i=0; i < dataObject.length();i++){
                                JSONObject userObject = dataObject.getJSONObject(i);

                                UserModel tempUser = new UserModel(userObject.getInt("id"),
                                        userObject.getString("email"),
                                        userObject.getString("first_name"),
                                        userObject.getString("last_name"),
                                        userObject.getString("avatar"));

                                userArrayList.add(tempUser);

                                //Picasso.get().load(userObject.getString("avatar")).into(iv_api_avatar);
                                updateViews();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ApiActivity.this, "Fel: " + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myGetReq);
                break;

            //API POST
            case R.id.btn_post:
                Toast.makeText(this, "POST", Toast.LENGTH_SHORT).show();

                JSONObject postData = new JSONObject();

                try {
                    postData.put("Namn","Peter");
                    postData.put("Kurs", "Android-utveckling");
                    postData.put("Id","1");
                    postData.put("Email", "peter.sundberg@ecutbildning.se");

                    JsonObjectRequest myPostReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL + "users", postData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            tv_api_result.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

                    VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myPostReq);


                } catch (JSONException e){
                    e.printStackTrace();
                }
                break;

            //API PUT
            case R.id.btn_put:
                Toast.makeText(this, "PUT", Toast.LENGTH_SHORT).show();

                JSONObject putData = new JSONObject();

                try {
                    putData.put("Namn", "Peter Sundberg");
                    putData.put("Företag", "Peter Sundberg AB");

                    JsonObjectRequest myPutReq = new JsonObjectRequest(Request.Method.PUT, SERVER_URL + "users/2", putData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            tv_api_result.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

                    VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myPutReq);

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                break;

            //API DELETE
            case R.id.btn_delete:
                Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();

                JsonObjectRequest myDeleteReq = new JsonObjectRequest(Request.Method.DELETE, SERVER_URL_DELETE, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tv_api_result.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv_api_result.setText(error.toString());
                        error.printStackTrace();
                    }
                });

                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myDeleteReq);
                break;
        }
    }

        //create context menu for user listView
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            getMenuInflater().inflate(R.menu.user_context_menu, menu);
        }
        //Create action to perform on each clicked item in list (context menu)
        @Override
        public boolean onContextItemSelected(@NonNull MenuItem item) {
            //get selected item from ListView
            final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            switch (item.getItemId()) {
                //view details of chosen user
                case R.id.view_details:

                    //code to see info about klicked user
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.user_dialog,null);

                    dialogBuilder.setView(dialogView);


                    //Declare views in dialog
                    final ImageView dialog_user_avatar;
                    final TextView dialog_user_id;
                    final TextView dialog_user_fullname;
                    final TextView dialog_user_email;
                    //initiate views in dialog
                    dialog_user_avatar = dialogView.findViewById(R.id.dialog_user_avatar);
                    dialog_user_id = dialogView.findViewById(R.id.dialog_user_id);
                    dialog_user_fullname = dialogView.findViewById(R.id.dialog_user_fullname);
                    dialog_user_email = dialogView.findViewById(R.id.dialog_user_email);

                    //Setters for current values
                    //dialog_user_avatar.set
                    Picasso.get().load(userArrayList.get(info.position).getAvatar()).into(dialog_user_avatar);
                    dialog_user_id.setText(String.valueOf(userArrayList.get(info.position).getId()));
                    dialog_user_fullname.setText(userArrayList.get(info.position).getFirstname() + " " + userArrayList.get(info.position).getLastname());
                    dialog_user_email.setText(userArrayList.get(info.position).getEmail());

                    final AlertDialog updateDialog = dialogBuilder.create();
                    updateDialog.show();
                    updateDialog.getWindow().setLayout(800,900); //set size of dialog


            }
            return super.onContextItemSelected(item);
        }



        //save state, for landscape mode
        @Override
        protected void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);

            outState.putString("apiStringData", tv_api_result.getText().toString()); //save state for TextView "tv_api_result"

        }



}























