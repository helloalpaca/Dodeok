package minseon.dodeok.Teacher;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Teacher.Home2.myClassname;

public class StatusStudent extends AppCompatActivity {

    String[] nick;
    String[] status;
    String[] userid;
    String[] emotions;

    int length = 0;

    int[] alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_student);

        GetClassList();

        final ListViewAdapterStatus adapter=new ListViewAdapterStatus();
        ListView listView =(ListView)findViewById(R.id.status_listview);

        listView.setAdapter(adapter);

        String pack = this.getPackageName();

        if(length>0){
            for(int i=0; i<length; i++){
                String str = "@string/" + emotions[i];
                int resID = getResources().getIdentifier(str,"string",pack);
                adapter.addItem(nick[i],getString(resID),alert[i]);
            }
        }
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
                emotions = new String[jsonArray.length()];
                alert = new int[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    nick[i] = item.getString("nick");
                    status[i] = item.getString("status");
                    userid[i] = item.getString("id");
                    emotions[i] = item.getString("emotion");

                    System.out.println("nick : " + nick + ", status : " + status);
                    EmotionData(userid[i],i);
                    System.out.println("id : "+userid[i]+", alert : " +alert[i]);
                }
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }

    private void EmotionData(String nowid, int point) {

        long now = System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000;
        Date today = new Date(now);
        SimpleDateFormat transFormat;
        transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String to = transFormat.format(today);

        System.out.println("today : " + to);

        String errorString = null;
        String mJsonString = null;
        String Emotion_URL = MainURL + "emotion2.php";

        String serverURL = "classname=" + myClassname + "&id="+ nowid + "&time="+to;
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

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("users");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String id = item.getString("id");
                String emotion = item.getString("emotion");
                String emotiontime = item.getString("emotiontime");
                System.out.println("Length : "+emotion.length());
                if(emotion.length()==9){
                    int tmp = emotion.charAt(8)-'0';
                    //7,11,15,24,28,29
                    if(tmp==7||tmp==11||tmp==15||tmp==24||tmp==28||tmp==29){
                        alert[point]++;
                    }
                }
                else{
                    String sub = emotion.substring(emotion.length()-2, emotion.length());
                    System.out.println("SUB : "+sub);
                    int tmp = Integer.parseInt(sub);
                    if(tmp==7||tmp==11||tmp==15||tmp==24||tmp==28||tmp==29){
                        alert[point]++;
                    }
                }
                //System.out.println("id = " + id + ", emotion = " + emotion + ", emotiontime = "+emotiontime);
            }
        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }
}
