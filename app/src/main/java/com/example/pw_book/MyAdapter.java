package com.example.pw_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private int num;
    int j=0;
    private Context mcontext;
    ArrayList<Map<String,String>> mresultList;
    private LayoutInflater layoutInflater;
    public  MyAdapter(Context context, ArrayList<Map<String,String>> resultList){
        this.mcontext=context;
        this.mresultList=resultList;
        layoutInflater= LayoutInflater.from(context);
        this.num=mresultList.size();
    }
    @Override
    public int getCount() {
        return num;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    static class ViewHolder{
        public TextView mtvID,mtvName,mtvPW;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder=null;
        if (view == null){
            view= layoutInflater.inflate(R.layout.layout_list_item,null);
            holder=new ViewHolder();
            holder.mtvID=view.findViewById(R.id.tv_id);
            holder.mtvName=view.findViewById(R.id.tv_name);
            holder.mtvPW=view.findViewById(R.id.tv_pw);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

            holder.mtvID.setText(mresultList.get(i).get("_id"));
            holder.mtvName.setText(mresultList.get(i).get("name"));
            holder.mtvPW.setText(mresultList.get(i).get("pw"));

        return view;
    }
}
