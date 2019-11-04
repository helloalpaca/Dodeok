package minseon.dodeok.Teacher;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import minseon.dodeok.R;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListItemInfo> list=new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        final Context context=parent.getContext();

        if(convertView==null){
            final LayoutInflater systemService = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=systemService.inflate(R.layout.custom_listview, parent, false);

        }

        TextView name = (TextView)convertView.findViewById(R.id.sname);
        TextView cnum=(TextView)convertView.findViewById(R.id.cnum);
        TextView qnum=(TextView)convertView.findViewById(R.id.qnumber);
        TextView question=(TextView)convertView.findViewById(R.id.question);
        TextView answer=(TextView)convertView.findViewById(R.id.answer);

        ListItemInfo listViewItem = list.get(position);

        name.setText(listViewItem.getSname());
        cnum.setText(Integer.toString(listViewItem.getCnum()));
        qnum.setText(Integer.toString(listViewItem.getQnumber()));
        question.setText(listViewItem.getQuestion());
        answer.setText(listViewItem.getAnswer());


        return convertView;
    }



    public void addListItem(String sname, int cnum, int qnumber, String question, String answer){
        ListItemInfo item=new ListItemInfo();
        item.setSname(sname);
        item.setCnum(cnum);
        item.setQnumber(qnumber);
        item.setQuestion(question);
        item.setAnswer(answer);

        list.add(item);
    }
}