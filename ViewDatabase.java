package com.tatla.ggfitness.Database;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.R.layout;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tatla.ggfitness.R;
import com.tatla.ggfitness.model.User;


import java.util.ArrayList;

public class ViewDatabase extends AppCompatActivity {

    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public ListView listView;
    public static final String TAG="ViewDatabase";
    User user;

    //Oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database);
        listView=findViewById(R.id.list);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Error",databaseError.toString());
            }
        });
    }
    //Retriving data
    private void showData(DataSnapshot dataSnapshot) {

        ArrayList<String> array = new ArrayList<String>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            User user = ds.getValue(User.class);

            if (user != null) {

                Log.i(TAG, "user is not null" );
                array = new ArrayList<String>();
                array.add(user.getFname());

                array.add(user.getAddress());
                array.add(user.getDate());
                array.add(user.getEmail());
                array.add(user.getGender());
                array.add(user.getZone());
                array.add(user.getLname());
                array.add(user.getPhone());


            } else {
                Log.i(TAG, "user is  null" );

            }



        }
        ArrayAdapter adapter = new ArrayAdapter(this, layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

    }

}
