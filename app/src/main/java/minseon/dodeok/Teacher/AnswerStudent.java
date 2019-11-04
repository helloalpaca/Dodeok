package minseon.dodeok.Teacher;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Teacher.Home2.myClassname;

public class AnswerStudent extends AppCompatActivity {

    String[] nick;
    String[] status;
    String[] userid;
    String[] userpw;

    int length = 0;

    static String StudentID;
    static String StudentStatus;
    static String StudentNick;

    Button btn_select, btn_getemotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_student);

        btn_select = (Button)findViewById(R.id.answer_btn1);
        btn_getemotion = (Button)findViewById(R.id.answer_btn2);

        GetClassList();
        final ListViewAdapterStudent adapter=new ListViewAdapterStudent();
        ListView listView =(ListView)findViewById(R.id.listview_student);

        listView.setAdapter(adapter);

        GetClassList();

        if(length>0){
            for(int i=0; i<length; i++){
                adapter.addItem(nick[i],status[i],userid[i],userpw[i]);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentID = adapter.getId(position);
                StudentStatus = adapter.getStatus(position);
                StudentNick = adapter.getNick(position);
            }
        });
    }

    public void onClickAnswerStudentButton1(View view){
        Intent intent = new Intent(AnswerStudent.this, CheckAnswer.class);
        startActivity(intent);
    }

    public void onClickAnswerStudentButton2(View view){
        Intent intent = new Intent(AnswerStudent.this, EmotionStudent.class);
        startActivity(intent);
    }

    private void GetClassList() {
        String errorString = null;
        String mJsonString = null;
        String GetStudent_URL = MainURL + "getclasslist.php/";

        String serverURL = "classname=" + myClassname;

        System.out.println("classname : "+myClassname);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(GetStudent_URL);
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
                System.out.println(line);
            }

            bufferedReader.close();

            mJsonString = sb.toString().trim();

        } catch (Exception e) {
            Log.d("TAG", "InsertData : Error", e);
            errorString = e.toString();

            //return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("users");

            length = jsonArray.length();
            if(length>0) {
                nick = new String[jsonArray.length()];
                status = new String[jsonArray.length()];
                userid = new String[jsonArray.length()];
                userpw = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    nick[i] = item.getString("nick");
                    status[i] = item.getString("status");
                    userid[i] = item.getString("id");
                    userpw[i] = item.getString("pw");
                    System.out.println("nick : " + nick + ", status : " + status);
                }
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }
}
