


package com.tatla.ggfitness.app;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tatla.ggfitness.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        handler.sendEmptyMessageDelayed(101,3000);

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 101)
            {
                Intent intent = new Intent(Splash.this,UserRegistration.class);
                startActivity(intent);
                finish();
            }
        }
    };


}
