package com.esper.webapplication.TicTacLearnHindi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SpashScreen extends AppCompatActivity {
    //private static int SPLASH_SCREEN_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i=new Intent(SpashScreen.this,
//                        MainActivity.class);
//                //Intent is used to switch from one activity to another.
//
//                startActivity(i);
//                //invoke the SecondActivity.
//
//                finish();
//                //the current activity will get finished.
//            }
//        }, SPLASH_SCREEN_TIME_OUT);


        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent = new Intent(SpashScreen.this, MainActivity.class);
                    startActivity(intent);
                   finish();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };thread.start();
    }
//    public void onDestroy() {
//        super.onDestroy();
//        Process.killProcess(Process.myPid());
//    }
}