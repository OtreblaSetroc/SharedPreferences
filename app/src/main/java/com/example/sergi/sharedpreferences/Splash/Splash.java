package com.example.sergi.sharedpreferences.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.sergi.sharedpreferences.APP.SharedPrefer;
import com.example.sergi.sharedpreferences.Activities.Home;
import com.example.sergi.sharedpreferences.Activities.MainActivity;
import com.example.sergi.sharedpreferences.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private Timer timer;
    private TimerTask timerTask;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferences= getSharedPreferences("Preferences",MODE_PRIVATE);
        String email= SharedPrefer.getUsername(preferences);
        String pass = SharedPrefer.getPass(preferences);
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            Intent intent =new Intent(this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        init();







    }

    public void init(){
       // Stetho.initializeWithDefaults(this);
        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        timer = new Timer();
        timerTask = new TimerTask(){
            @Override
            public void run()
            {
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask,3000);

    }

    @Override
    public void onBackPressed(){

        super.onBackPressed();
        timer.cancel();

    }
}
