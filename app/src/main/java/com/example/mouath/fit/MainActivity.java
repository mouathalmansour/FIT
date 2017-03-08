package com.example.mouath.fit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

 private String url ;
    private String finalURL;
    private String result;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //click();
            Button button1=(Button) findViewById(R.id.button1);
            Button button2=(Button) findViewById(R.id.button2);
            final EditText username = (EditText) findViewById(R.id.userNameField);
            final EditText password = (EditText) findViewById(R.id.passwordField);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    url = "http://aam.16mb.com/Mouath/Login.php";

                    String nameKey = "?username=";
                    String passwordKey = "&password=";

                    try {
                        finalURL = url + nameKey + URLEncoder.encode(username.getText().toString(), "UTF-8")  + passwordKey + URLEncoder.encode(password.getText().toString(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                    Runnable runnable = new Runnable() {
                        public void run() {
                            try {

                                URL insertUserUrl = new URL(finalURL);
                                HttpURLConnection insertConnection = (HttpURLConnection) insertUserUrl.openConnection();
                                InputStreamReader resultStreamReader = new InputStreamReader(insertConnection.getInputStream());
                                BufferedReader resultReader = new BufferedReader(resultStreamReader);
                                result = resultReader.readLine();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        if (result.equals("welcome"+" "+username.getText().toString())) {
                                            Intent i = new Intent(getApplicationContext(), Gyms.class);
                                            startActivity(i);
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        }else
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Thread thread = new Thread(runnable);
                    thread.start();


                }
            });


            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {// go to sgin-in activity
                    Intent i = new Intent(MainActivity.this, SigninActivity.class);
                    startActivity(i);
                }

            });
            //Toast.makeText(getApplicationContext(),"ON CREATE",Toast.LENGTH_SHORT).show();
    }

    /*@Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"ON STOP",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"ON START",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"ON DESTORY",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(),"ON RESUME",Toast.LENGTH_SHORT).show();
    }*/
}
