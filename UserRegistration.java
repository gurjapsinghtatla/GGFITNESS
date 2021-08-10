package com.tatla.ggfitness.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.tatla.ggfitness.Database.JsonAcitivity;
import com.tatla.ggfitness.Database.ViewDatabase;
import com.tatla.ggfitness.Database.user_adapter;
import com.tatla.ggfitness.R;
import com.google.firebase.auth.FirebaseAuth;
import com.tatla.ggfitness.model.User;

import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserRegistration extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
@BindView(R.id.FName)
EditText Fname;
@BindView(R.id.LName)
EditText Lname;
@BindView(R.id.Email)
EditText Email;
@BindView(R.id.Phone)
EditText Phone;
@BindView(R.id.Address)
EditText Address;
@BindView(R.id.Male)
RadioButton Male;
@BindView(R.id.Female)
RadioButton Female;
@BindView(R.id.Date)
Button Date;
@BindView(R.id.Submit)
Button Submit;
@BindView(R.id.Spinner)
Spinner spinner;
User user;
public String Gender;
DatabaseReference databaseReference;
boolean update;
int mySpinner_selectedId;


    //DATE SHOWER

    DatePickerDialog datePickerDialog;
    int year,month,day;

    DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener()
    {
     @Override
     public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
     {
         Date.setText(i+"/"+i1+"/"+i2);
     }
    };
    View.OnClickListener clickListener=new View.OnClickListener()
    {

     @Override
     public void onClick(View view)
     {
         if(view.getId()==R.id.Date)
         {
             showDatePickerDialog();
         }
     }
    };
    public void showDatePickerDialog()
    {
    Calendar calendar = Calendar.getInstance();
    int dd = calendar.get(Calendar.DAY_OF_MONTH);
    int mm = calendar.get(Calendar.MONTH);
    int yy = calendar.get(Calendar.YEAR);
    datePickerDialog = new DatePickerDialog(this,dateSetListener,yy,mm,dd);
    datePickerDialog.show();
    }



    //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        ButterKnife.bind(this);

        User user =new User();

        databaseReference=FirebaseDatabase.getInstance().getReference("User");

        Date.setOnClickListener(clickListener);

        Intent rcv = getIntent();
        update = rcv.hasExtra("keyUser");

        if(update){

            user = (User)rcv.getSerializableExtra("keyUser");

            mySpinner_selectedId = spinner.getSelectedItemPosition();

            Fname.setText(user.fname);
            Lname.setText(user.lname);
            Email.setText(user.email);
            Phone.setText(user.phone);
            Address.setText(user.address);
            Date.setText(user.fname);
            spinner.setSelection(mySpinner_selectedId);
            Submit.setText("UPDATE");
            getSupportActionBar().setTitle("Update "+Fname);
            //Gender.setText(user.fname);

        }

    }

    //REGISTRING USER
    public void Register(View view) {
    addUser();
    }

    //ENTERING USER
public void addUser()
{
        String fname = Fname.getText().toString().trim();
        String lname = Lname.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String phone = Phone.getText().toString().trim();
        String address = Address.getText().toString().trim();
        String zone=spinner.getSelectedItem().toString();
        String date=Date.getText().toString().trim();
        String id =databaseReference.push().getKey();
        user=new User(fname,lname,email,phone,address,zone,date);
        databaseReference.child(id).setValue(user);
        Fname.setText("");
        Lname.setText("");
        Email.setText("");
        Phone.setText("");
        Address.setText("");
}



//GENDER
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {
        int id =compoundButton.getId();
        switch(id){
            case R.id.Female:
             Gender="Female";
            break;
            case R.id.Male:
             Gender= "Male";
             break;
        }
    }


    //Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,101,1,"ALL USERS");
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 101) {
           Intent intent = new Intent(UserRegistration.this,JsonAcitivity.class);
           startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }


}

