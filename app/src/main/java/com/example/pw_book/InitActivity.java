package com.example.pw_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class InitActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        mSharedPreferences = getSharedPreferences("keys",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
//        mEditor.putString("mainkey", "");
//        mEditor.apply();

        if(mSharedPreferences.getString("mainkey","")==""){
            intent = new Intent(this,Registerctivity.class);
            startActivity(intent);
            //Toast.makeText(this, "mainkey is null ", Toast.LENGTH_SHORT).show();
        }else{
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

    }
}