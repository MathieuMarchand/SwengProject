package com.github.mmarchan.roger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.mmarchan.roger.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToMeteo(View View){
        Intent intent = new Intent(this, WeatherActivity.class);

        startActivity(intent);
    }

    public void sendMessage(View view){
       Intent intent = new Intent(this, GreetingActivity.class);

        EditText nomView =  findViewById(R.id.mainName);
        String nom = nomView.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, nom);
        startActivity(intent);
        /*
        EditText nomView = (EditText)findViewById(R.id.mainName);
        String nom = nomView.getText().toString();

        setContentView(R.layout.activity_greeting);
        TextView bvn = (TextView)findViewById(R.id.welcomeView);
        bvn.setText("Bonjour " + nom);
        */
    }
}