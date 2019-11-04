package minseon.dodeok.Teacher;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Teacher.Home2.myClassname;

public class ManageStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

    }

    public void onClickManage2Button1(View view){
        Intent intent = new Intent(ManageStudent.this, JoinStudent.class);
        startActivity(intent);
    }

    public void onClickManage2Button2(View view){
        Intent intent = new Intent(ManageStudent.this, StatusStudent.class);
        startActivity(intent);
    }

    public void onClickManage2Button3(View view){
        EmotionData();
    }

    private void EmotionData() {

        long now = System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000;
        Date today = new Date(now);
        SimpleDateFormat transFormat;
        transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String to = transFormat.format(today);

        System.out.println("today : " + to);

        String errorString = null;
        String mJsonString = null;
        String Emotion_URL = MainURL + "emotion.php";

        String serverURL = "classname=" + myClassname + "&id=a" + "&time="+to;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(Emotion_URL);
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

        File emulated = Environment.getExternalStorageDirectory();
        File dir = new File(emulated.getAbsolutePath() + "/dodeok");
        dir.mkdirs();
        File file = new File(dir, myClassname + "_emotions.csv");

        try {
            FileWriter fw = new FileWriter(file, false);

            fw.write("이름,감정,시간\n");
            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String id = item.getString("id");
                    String emotion = item.getString("emotion");
                    String emotiontime = item.getString("emotiontime");
                    //System.out.println("id = " + id + ", emotion = " + emotion + ", emotiontime = "+emotiontime);

                    String pack = this.getPackageName();
                    String str = "@string/" + emotion;
                    int resID = getResources().getIdentifier(str,"string",pack);

                    fw.write(id+","+getString(resID)+","+emotiontime+"\n");
                }
            } catch (JSONException e) {
                Log.d("TAG", "show result : ", e);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
