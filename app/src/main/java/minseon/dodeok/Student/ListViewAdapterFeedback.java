package minseon.dodeok.Student;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import minseon.dodeok.Both.MainActivity;
import minseon.dodeok.R;
import minseon.dodeok.Teacher.ListItemInfo;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Student.Game.answer;
import static minseon.dodeok.Student.Game.clickedNum;
import static minseon.dodeok.Student.Game.nowUnit;
import static minseon.dodeok.Student.Home.myClassname;

public class ListViewAdapterFeedback extends BaseAdapter {
    private ArrayList<ListItemFeedback> list = new ArrayList<>();
    boolean[] flag = {false};

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

        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            final LayoutInflater systemService = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = systemService.inflate(R.layout.custom_listview2, parent, false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.listview2_img);
        TextView text1 = (TextView) convertView.findViewById(R.id.listview2_text1);
        TextView text2 = (TextView) convertView.findViewById(R.id.listview2_text2);
        TextView feedtext1 = (TextView) convertView.findViewById(R.id.listview2_feedtext1);
        TextView feedtext2 = (TextView) convertView.findViewById(R.id.listview2_feedtext2);
        TextView feedtext3 = (TextView) convertView.findViewById(R.id.listview2_feedtext3);
        TextView feedtext4 = (TextView) convertView.findViewById(R.id.listview2_feedtext4);
        TextView feedtext5 = (TextView) convertView.findViewById(R.id.listview2_feedtext5);
        Button btn1 = (Button) convertView.findViewById(R.id.listview2_btn1);
        Button btn2 = (Button) convertView.findViewById(R.id.listview2_btn2);
        Button btn3 = (Button) convertView.findViewById(R.id.listview2_btn3);
        Button btn4 = (Button) convertView.findViewById(R.id.listview2_btn4);
        Button btn5 = (Button) convertView.findViewById(R.id.listview2_btn5);

        final ListItemFeedback listViewItem = list.get(position);

        String emotionCard = "@drawable/png_" + listViewItem.getEmotion();
        String pack = context.getPackageName();
        String strName = "@string/" + listViewItem.getEmotion();
        int resID = context.getResources().getIdentifier(emotionCard, "drawable", pack);
        int strID = context.getResources().getIdentifier(strName, "strings", pack);

        img.setImageResource(resID);
        text1.setText(listViewItem.getNick());
        text2.setText(strID);

        String str1 = listViewItem.getFeed_fighting();
        String str2 = listViewItem.getFeed_good();
        String str3 = listViewItem.getFeed_metoo();
        String str4 = listViewItem.getFeed_curious();
        String str5 = listViewItem.getFeed_special();

        if(!(str1==null)||!(str2==null)||!(str3==null)||!(str4==null)||!(str5==null)){
            System.out.println(listViewItem.getNick()+"는 감정표시!!!");
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btn5.setVisibility(View.GONE);
            if(!(str1==null)){
                feedtext1.setText(str1);
                feedtext1.setBackgroundResource(R.drawable.feedback_background);
            }
            if(!(str2==null)){
                feedtext2.setText(str2);
                feedtext2.setBackgroundResource(R.drawable.feedback_background);
            }
            if(!(str3==null)){
                feedtext3.setText(str3);
                feedtext3.setBackgroundResource(R.drawable.feedback_background);
            }
            if(!(str4==null)){
                feedtext4.setText(str4);
                feedtext4.setBackgroundResource(R.drawable.feedback_background);
            }
            if(!(str5==null)){
                feedtext5.setText(str5);
                feedtext5.setBackgroundResource(R.drawable.feedback_background);
            }

        } else {
            System.out.println(listViewItem.getNick()+"는 Button!!!");
            feedtext1.setVisibility(View.GONE);
            feedtext2.setVisibility(View.GONE);
            feedtext3.setVisibility(View.GONE);
            feedtext4.setVisibility(View.GONE);
            feedtext5.setVisibility(View.GONE);
        }

/*
        feedtext1.setText(listViewItem.getFeed_fighting());
        feedtext2.setText(listViewItem.getFeed_good());

        System.out.println(feedtext1.getText()+" : "+feedtext2.getText());

        if(!fighting.getText().equals("")||!good.getText().equals("")) {
            System.out.println(listViewItem.getStudent_id()+"는 감정표시!!!");
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            if(!fighting.getText().equals("")) fighting.setBackgroundResource(R.drawable.feedback_background);
            if(!good.getText().equals("")) good.setBackgroundResource(R.drawable.feedback_background);
        }
        else{
            System.out.println(listViewItem.getStudent_id()+"는 Button!!!");
            fighting.setVisibility(View.GONE);
            good.setVisibility(View.GONE);
        }
*/
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listViewItem.getStudent_id()+"Button1", Toast.LENGTH_SHORT).show();
                InsertFeedBack(listViewItem.getStudent_id(),"힘내요");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listViewItem.getStudent_id()+"Button2", Toast.LENGTH_SHORT).show();
                InsertFeedBack(listViewItem.getStudent_id(),"좋아요");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listViewItem.getStudent_id()+"Button3", Toast.LENGTH_SHORT).show();
                InsertFeedBack(listViewItem.getStudent_id(),"나도 그래!");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listViewItem.getStudent_id()+"Button4", Toast.LENGTH_SHORT).show();
                InsertFeedBack(listViewItem.getStudent_id(),"궁금해");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listViewItem.getStudent_id()+"Button5", Toast.LENGTH_SHORT).show();
                InsertFeedBack(listViewItem.getStudent_id(),"넌 특별해");
            }
        });

        return convertView;
    }

    public void addListItem(String emotion, String nick ,String student_id, String feedtext1, String feedtext2, String feedtext3, String feedtext4, String feedtext5) {
        ListItemFeedback item = new ListItemFeedback();
        item.setEmotion(emotion);
        item.setNick(nick);
        item.setStudent_id(student_id);
        item.setFeed_fighting(feedtext1);
        item.setFeed_good(feedtext2);
        item.setFeed_metoo(feedtext3);
        item.setFeed_curious(feedtext4);
        item.setFeed_special(feedtext5);

        list.add(item);
    }

    private void InsertFeedBack(String id, String feedback) {
        String errorString = null;
        String mJsonString = null;
        String InsertFeedback_URL = MainURL + "insertfeedback.php";


        String serverURL = "id=" + userID + "&classname=" + myClassname + "&feedid=" + id + "&feedback="+feedback;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(InsertFeedback_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(1000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(serverURL.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();
            //Log.d(TAG,"response - "+responseStatusCode);

            InputStream inputStream;
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                //System.out.println(line);
            }

            bufferedReader.close();

            mJsonString = sb.toString().trim();

        } catch (Exception e) {
            Log.d("TAG", "InsertData : Error", e);
            errorString = e.toString();

            //return null;
        }

    }
}
