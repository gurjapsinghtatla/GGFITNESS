package com.tatla.ggfitness.Database;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.R.layout;
import android.widget.Toast;


import com.tatla.ggfitness.R;
import com.tatla.ggfitness.app.UserRegistration;
import com.tatla.ggfitness.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JsonAcitivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    StringBuffer response = new StringBuffer();
    UserFetch task;
    ArrayList<User> userlist = new ArrayList<>();
    ArrayList<String> userlist1 = new ArrayList<>();
    @BindView(R.id.list)
    ListView listView;
    // ArrayAdapter<String> adapter;
    user_adapter user_adapter;
    public static final String TAG = "JsonActivity";
    int Pos;
    User user;

    //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, " ON CREATE EXECUTED");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_acitivity);
        ButterKnife.bind(this);
        task = new UserFetch();
        task.execute();
    }

    //ITEM CLICK LISTENER
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i(TAG,"clickable executed");
        Pos = i;
        user = userlist.get(i);
        ShowOption();
    }

    //OPTION SHOWER
    void ShowOption() {
        Log.i(TAG,"SHOW OPTION EXECUTED");
        String[] items = {"Update", "Back"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Update();
                                break;
                            case 1:
                                finish();
                                break;

                        }
                    }
                }
        );
    builder.create().show();
    }

        //UPDATING
    void Update() {
        Intent intent=new Intent(JsonAcitivity.this, UserRegistration.class);
        intent.putExtra("keyUser",user);
        startActivity(intent);

    }


   /* //DELETION
    void Delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + user.fname);
        builder.setMessage("Are You Sure ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 deletion();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }
    void deletion(){

    }*/





        //FETCHING USER
        class UserFetch extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try{

                URL url=new URL("https://gg-fitness.firebaseio.com/.json");
                Log.i(TAG, "url is  copied" );
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(reader);
                String line = "";
                while((line=buffer.readLine())!= null)
                {
                    response.append(line+"\n");
                    Log.i("JSON OBJ",response.toString());

                    Log.i(TAG, "LINE  is  APPENDED" );
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
         //   Toast.makeText(JsonAcitivity.this,"RESPONSE"+response.toString(),Toast.LENGTH_LONG).show();

            try{

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject jObj = jsonObject.getJSONObject("User");

                Iterator<String> itr = jObj.keys();
                while(itr.hasNext()){
                    String key = itr.next();
                    Log.i("Key",key);

                    JSONObject obj = jObj.getJSONObject(key);
                    Log.i("JSON OBJ",jObj.toString());

                    User user=new User();
                    user.fname = obj.getString("fname");
                    user.lname = obj.getString("lname");
                    user.email = obj.getString("email");
                    user.phone = obj.getString("phone");
                    user.address = obj.getString("address");
                    user.zone = obj.getString("zone");
                    user.date = obj.getString("date");
//                    user.gender = obj.getString("gender");

                    userlist.add(user);
                    //userlist1.add(user.toString());
                }
                user_adapter=new user_adapter(JsonAcitivity.this,R.layout.datashow,userlist);

                //adapter = new ArrayAdapter<>(getApplicationContext(), layout.simple_list_item_1,userlist1);
                listView.setAdapter(user_adapter);
                listView.setOnItemClickListener(JsonAcitivity.this);
              //  Log.i("adapter being set",adapter.toString());

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


        }
    }

