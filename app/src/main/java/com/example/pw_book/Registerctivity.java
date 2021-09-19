package com.example.pw_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registerctivity extends AppCompatActivity {
    private EditText mEdRegister;
    private Button mBtnRegister;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerctivity);
        mEdRegister = findViewById(R.id.ed_register);
        mBtnRegister = findViewById(R.id.btn_register);
        mSharedPreferences = getSharedPreferences("keys",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString("mainkey", "");
        mEditor.apply();

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditor.putString("mainkey", mEdRegister.getText().toString());
                mEditor.apply();
                Toast.makeText(Registerctivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                intent = new Intent(Registerctivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}