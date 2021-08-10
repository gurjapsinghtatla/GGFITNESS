package com.tatla.ggfitness.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tatla.ggfitness.R;
import com.tatla.ggfitness.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class user_adapter extends ArrayAdapter<User>{

    Context context;
    int resource;
    List objects;
    String TAG="USER ADAPTER";
    public user_adapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects)
    {
        super(context, resource, objects);
        Log.i(TAG,"User adapter executed");
        this.context=context;
        this.resource=resource;
        this.objects=objects;


    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Log.i(TAG,"View getView executed");
        View view= LayoutInflater.from(context).inflate(resource,parent,false);

        TextView firstname=view.findViewById(R.id.firstname);
        Log.i("FIRST NAME",firstname.toString());
        TextView lastName=view.findViewById(R.id.lastname);
        TextView gender=view.findViewById(R.id.gender);
        TextView zone=view.findViewById(R.id.zone);
        TextView address=view.findViewById(R.id.address);
        TextView date=view.findViewById(R.id.date);
        TextView phone=view.findViewById(R.id.phone);
        TextView email=view.findViewById(R.id.email);

         User user=(User)objects.get(position);

        firstname.setText(user.fname);
        Log.i("FIRST NAME",firstname.toString());

        lastName.setText(user.lname);
       gender.setText(user.gender);
        zone.setText(user.zone);
        Log.i("ZONE",zone.toString());
        address.setText(user.address);
        date.setText(user.date);
        phone.setText(user.phone);
        email.setText(user.email);
        Log.i("VIEW----",view.toString());
        return view;
    }
}


