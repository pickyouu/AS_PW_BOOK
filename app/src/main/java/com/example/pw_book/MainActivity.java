package com.example.pw_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText mEdLogin;
    private Button mBtnLogin;
    private String a,b;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences("keys",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEdLogin = findViewById(R.id.ed_login);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=mSharedPreferences.getString("mainkey","");
                b=mEdLogin.getText().toString();

                if(a.equals(b)){
                    intent = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
                    //Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "输入错误,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}