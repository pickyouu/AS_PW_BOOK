package com.example.pw_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText mEdName,mEdPW,mEdDesc;
    private Button mBtnAdd;
    private DBOpenHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mEdName=findViewById(R.id.ed_name);
        mEdPW= findViewById(R.id.ed_pw);
        mEdDesc=findViewById(R.id.ed_desc);
        mBtnAdd=findViewById(R.id.btn_add);
        dbOpenHelper = new DBOpenHelper(AddActivity.this,"db_keys",null,1);//实例化DBOpenHelper创建数据库

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= mEdName.getText().toString();
                String pw = mEdPW.getText().toString();
                String describe = mEdDesc.getText().toString();
                if (name.equals("") || pw.equals("")){
                    Toast.makeText(AddActivity.this, "名称或密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    insertData(dbOpenHelper.getReadableDatabase(),name,pw,describe);
                    Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(AddActivity.this,MenuActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void insertData(SQLiteDatabase sqLiteDatabase,String name,String pw,String describe){
        ContentValues values= new ContentValues();
        values.put("name",name);
        values.put("pw",pw);
        values.put("describe",describe);
        sqLiteDatabase.insert("tb_keys","describe",values);
    }
    protected void onDestroy(){
        super.onDestroy();
        if (dbOpenHelper!=null){
            dbOpenHelper.close();
        }
    }
}