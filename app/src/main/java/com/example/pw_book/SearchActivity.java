package com.example.pw_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    private Button mBtnSearch;
    private ListView mLv;
    private EditText medName;
    private DBOpenHelper dbOpenHelper;//声明DBOpenhelper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mBtnSearch=findViewById(R.id.btn_search);
        medName=findViewById(R.id.ed_name);
        mLv=findViewById(R.id.lv_search);


        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("main1", "onClick: ");
                if(medName.getText().toString().equals("")){
                    Toast.makeText(SearchActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    dbOpenHelper = new DBOpenHelper(SearchActivity.this,"db_keys",null,1);//实例化DBOpenHelper创建数据库
                    Cursor cursor= dbOpenHelper.getReadableDatabase().query("tb_keys",null,"name=?",new String[]{medName.getText().toString()},null,null,null);//查询语句
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
                        Toast.makeText(SearchActivity.this, "名称输入错误,未匹配到", Toast.LENGTH_SHORT).show();
                    }else {
                        mLv.setAdapter(new MyAdapter(SearchActivity.this,resultList));
                        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                TextView tvID = view.findViewById(R.id.tv_id);
                                String _id= tvID.getText().toString();
                                Intent itent = new Intent(SearchActivity.this,ShowActivity.class);
                                itent.putExtra("_id",_id);
                                startActivity(itent);
                            }
                        });
                    }
                }
            }


        });
    }


    protected void onDestroy(){
        super.onDestroy();
        if (dbOpenHelper!=null){
            dbOpenHelper.close();
        }
    }
}