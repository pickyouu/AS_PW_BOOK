package com.example.pw_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    private DBOpenHelper dbOpenHelper;//声明DBOpenhelper
    private ListView mLv;
    private Button mBtn,mBtnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mLv=findViewById(R.id.lv);//视图列表
        mBtn = findViewById(R.id.btn_add);
        mBtnSearch=findViewById(R.id.btn_search);
        dbOpenHelper = new DBOpenHelper(MenuActivity.this,"db_keys",null,1);//实例化DBOpenHelper创建数据库
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5=new Intent(MenuActivity.this,SearchActivity.class);
                startActivity(intent5);
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View view) {
                intent=new Intent(MenuActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        //查询数据
        Cursor cursor= dbOpenHelper.getReadableDatabase().query("tb_keys",null,null,null,null,null,null);//查询语句
        ArrayList<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
        //保存数据
        while (cursor.moveToNext()){
            Map<String,String> map= new HashMap<String,String>();
            map.put("_id",cursor.getString(0));
            map.put("name",cursor.getString(1));
            map.put("pw",cursor.getString(2));
            resultList.add(map);//[{pw=aaa, name=aaa}, {pw=www, name=www}, {pw=iii, name=rrr}]
        }

        if(resultList == null || resultList.size()==0){
            Toast.makeText(MenuActivity.this, "数据为空,请添加", Toast.LENGTH_SHORT).show();
        }else {
            mLv.setAdapter(new MyAdapter(MenuActivity.this,resultList));
            mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView tvID = view.findViewById(R.id.tv_id);
                    String _id= tvID.getText().toString();
                    Intent itent = new Intent(MenuActivity.this,ShowActivity.class);
                    itent.putExtra("_id",_id);
                    startActivity(itent);
                }
            });
        }


    }
    protected void onDestroy(){
        super.onDestroy();
        if (dbOpenHelper!=null){
            dbOpenHelper.close();
        }
    }
}