package minseon.dodeok.Teacher;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import minseon.dodeok.R;

public class ListViewAdapterStudent extends BaseAdapter {
    private ArrayList<ListViewItemStudent> listViewItemList = new ArrayList<ListViewItemStudent>() ;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getNick(int position) { return listViewItemList.get(position).getName(); }

    public String getStatus(int position) { return listViewItemList.get(position).getStatus(); }

    public String getId(int position) { return listViewItemList.get(position).getId();}

    public String getPw(int position) { return listViewItemList.get(position).getPw();}


    public void addItem(String name,String status,String id, String pw){
        ListViewItemStudent item = new ListViewItemStudent();

        item.setName(name);
        item.setStatus(status);
        item.setId(id);
        item.setPw(pw);
        item.setFlag(false);

        listViewItemList.add(item);
    }

    public void addItem(String a, String name,String status,String id, String pw){
        ListViewItemStudent item = new ListViewItemStudent();

        item.setName(name);
        item.setStatus(status);
        item.setId(id);
        item.setPw(pw);
        item.setFlag(true);

        listViewItemList.add(item);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_student, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView nameTextView = (TextView) convertView.findViewById(R.id.student_name);
        TextView statusTextView = (TextView) convertView.findViewById(R.id.student_status);
        TextView idTextView = (TextView) convertView.findViewById(R.id.student_id);
        TextView pwTextView = (TextView) convertView.findViewById(R.id.student_pw);


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItemStudent listViewItem = listViewItemList.get(position);
        if(listViewItem.getFlag()==true){
            nameTextView.setTextColor(Color.RED);
            statusTextView.setTextColor(Color.RED);
            idTextView.setTextColor(Color.RED);
            pwTextView.setTextColor(Color.RED);
        }

        // 아이템 내 각 위젯에 데이터 반영
        nameTextView.setText(listViewItem.getName());
        statusTextView.setText(listViewItem.getStatus());
        idTextView.setText(listViewItem.getId());
        pwTextView.setText(listViewItem.getPw());

        return convertView;
    }

}
