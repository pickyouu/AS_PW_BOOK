package com.example.pw_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ShowActivity extends AppCompatActivity {
    private EditText medName,medPW,medDesc;
    private Button mbtnChange,mbtnDEL;
    private DBOpenHelper dbOpenHelper;//声明DBOpenhelper


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent = getIntent();
        String _id = (intent.getStringExtra("_id"));
        Log.d("main1", _id+"");
        dbOpenHelper = new DBOpenHelper(ShowActivity.this,"db_keys",null,1);//实例化DBOpenHelper创建数据库
        medName = findViewById(R.id.ed_name);
        medPW = findViewById(R.id.ed_pw);
        medDesc = findViewById(R.id.ed_desc);
        mbtnChange=findViewById(R.id.btn_change);
        mbtnDEL=findViewById(R.id.btn_del);
//
        Cursor cursor= dbOpenHelper.getReadableDatabase().query("tb_keys",null,"_id=?",new String[]{_id},null,null,null);//查询语句
        ArrayList<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
        mbtnDEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbOpenHelper.getWritableDatabase().delete("tb_keys", "_id=?", new String[]{_id});
                Toast.makeText(ShowActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                Intent intent3= new Intent(ShowActivity.this,MenuActivity.class);
                startActivity(intent3);
            }
        });

        mbtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (medName.getText().toString().equals("") || medPW.getText().toString().equals("")){
                    Toast.makeText(ShowActivity.this, "名称或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    ContentValues values=new ContentValues();
                    values.put("name",medName.getText().toString());
                    values.put("pw",medPW.getText().toString());
                    values.put("describe",medDesc.getText().toString());
                    dbOpenHelper.getWritableDatabase().update("tb_keys", values, "_id=?", new String[] { _id});
                    Toast.makeText(ShowActivity.this, "已修改", Toast.LENGTH_SHORT).show();
                    Intent intent2=new Intent(ShowActivity.this,MenuActivity.class);
                    startActivity(intent2);
                }


            }
        });
        //保存数据
        while (cursor.moveToNext()){
            Map<String,String> map= new HashMap<String,String>();
            map.put("describe",cursor.getString(3));
            map.put("name",cursor.getString(1));
            map.put("pw",cursor.getString(2));
            resultList.add(map);//[{pw=aaa, name=aaa}, {pw=www, name=www}, {pw=iii, name=rrr}]
        }
        Log.d("main1",resultList+"");
        medName.setText(resultList.get(0).get("name").toString());
        medPW.setText(resultList.get(0).get("pw").toString());
        medDesc.setText(resultList.get(0).get("describe").toString());

    }
    protected void onDestroy(){
        super.onDestroy();
        if (dbOpenHelper!=null){
            dbOpenHelper.close();
        }
    }
}