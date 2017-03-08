package com.example.mouath.fit;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SigninActivity extends AppCompatActivity {

    private String url;
    private String finalURL;
    private String name;
    private String mail;
    private String pass;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final EditText username = (EditText) findViewById(R.id.editText3);
        final EditText email = (EditText) findViewById(R.id.editText6);
        final EditText password = (EditText) findViewById(R.id.editText5);


        Button signin = (Button) findViewById(R.id.buttonSignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = username.getText().toString();
                mail = email.getText().toString();
                pass = password.getText().toString();


                url = "http://aam.16mb.com/Mouath/submitName.php";

                String nameKey = "?username=";
                String emailKey = "&email=";
                String passwordKey = "&password=";

                try {
                    finalURL = url + nameKey + URLEncoder.encode(name, "UTF-8") + emailKey + URLEncoder.encode(mail, "UTF-8") + passwordKey + URLEncoder.encode(pass, "UTF-8");
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
                                    if (result.equals("welcome")) {
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

       /* Button signin = (Button) findViewById(R.id.buttonSignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SigninActivity.this, Gyms.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"WELCOME",Toast.LENGTH_SHORT).show();
            }
        });*/


    }


}
