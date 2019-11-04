package minseon.dodeok.Teacher;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import minseon.dodeok.R;

public class ListViewAdapterStatus extends BaseAdapter {
    private ArrayList<ListViewItemStatus> listViewItemList = new ArrayList<ListViewItemStatus>() ;

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

    public String getEmotion(int position) { return listViewItemList.get(position).getEmotion(); }

    public int getNow(int position) { return listViewItemList.get(position).getNow();}

    public void addItem(String name,String emotion,int now){
        ListViewItemStatus item = new ListViewItemStatus();

        item.setName(name);
        item.setEmotion(emotion);
        item.setNow(now);
        listViewItemList.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_status, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView nameTextView = (TextView) convertView.findViewById(R.id.status_name);
        TextView emotionTextView = (TextView) convertView.findViewById(R.id.status_emotion);
        ImageView img1 = (ImageView) convertView.findViewById(R.id.status_img1);
        ImageView img2 = (ImageView) convertView.findViewById(R.id.status_img2);
        ImageView img3 = (ImageView) convertView.findViewById(R.id.status_img3);


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItemStatus listViewItem = listViewItemList.get(position);
        if(listViewItem.getNow()<3){
            img1.setImageResource(R.drawable.light_green);
        } else if(listViewItem.getNow()<5){
            img2.setImageResource(R.drawable.light_yellow);
        } else{
            img3.setImageResource(R.drawable.light_red);
        }


        // 아이템 내 각 위젯에 데이터 반영
        nameTextView.setText(listViewItem.getName());
        emotionTextView.setText(listViewItem.getEmotion());

        return convertView;
    }
}
