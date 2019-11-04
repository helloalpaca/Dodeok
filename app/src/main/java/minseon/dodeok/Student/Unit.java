package minseon.dodeok.Student;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Student.Home.myClassname;

public class Unit extends AppCompatActivity {

    static int nowUnit, nowNum, classUnit;
    static String unitName;
    RelativeLayout relativelayout_unit;
    TextView textview_unit;
    Button btn_unit;
    static String str_emotiontime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        MetaData();
        GameData();

        relativelayout_unit = (RelativeLayout)findViewById(R.id.relativelayout_unit);
        textview_unit = (TextView)findViewById(R.id.textview_unit);
        btn_unit = (Button)findViewById(R.id.btn_unit);
//        System.out.println("Unit : " + nowUnit);
//
//        if(classUnit!=0) {
//            String resName = "@drawable/unit" + nowUnit;
//            String packName = this.getPackageName();
//            int resID = getResources().getIdentifier(resName, "drawable", packName);
//            System.out.println("resID = " + resID);
//            relativelayout_unit.setBackgroundResource(resID);
//        }
        if(classUnit!=0) {
            textview_unit.setText(nowUnit + ". " + unitName);
        } else{
            textview_unit.setText("미션이 없습니다.");
        }
//        String strName = "@string/unit" + nowUnit;
//        int strID = getResources().getIdentifier(strName,"strings", packName);
//        textview_unit.setText(strID);
    }

    public void onClickUnitButton1(View view) {
        int nextintent = NextIntent();
        if (classUnit != 0) {
            System.out.println("NextIntent : "+nextintent);
            if(nextintent<0){
                Intent intent = new Intent(Unit.this, Game.class);
                startActivity(intent);
            } else{
                Intent intent = new Intent(Unit.this, Emotion.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(getApplicationContext(), "선생님이 미션을 추가하지 않았습니다.", Toast.LENGTH_LONG).show();
        }
    }

    public int NextIntent(){
        // Calculate the current date and time
        System.out.println("str_emotiontime : " + str_emotiontime);

        if(str_emotiontime !="null") {
            long now = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
            Date today = new Date(now);
            System.out.println("today : " + today);

            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date to = null;
            try {
                to = transFormat.parse(str_emotiontime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int compare = today.compareTo(to);
            //만약 compare<0이면 to가 24시간 이내이기 때문에, Game화면으로 바로 넘어가기.

            if (compare > 0) {
                System.out.println("[Game] today-24 >= missionday");
            } else {
                System.out.println("[Game] today-24 < missionday");
            }
            return compare;
        }
        else {
            System.out.println("str_emotion은 null값이다.");
            return 0;
        }
    }

    private void MetaData() {
        String errorString = null;
        String mJsonString;

        String Meta_URL = MainURL + "meta.php";

        String serverURL = "id=" + userID + "&classname=" + myClassname;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(Meta_URL);
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

            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    nowUnit = item.getInt("nowUnit");
                    nowNum = item.getInt("nowNum");
                    classUnit = item.getInt("classunit");
                    str_emotiontime = item.getString("emotiontime");
                }

            } catch (JSONException e) {
                Log.d("TAG", "show result : ", e);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void GameData() {
        String errorString = null;
        String mJsonString = null;
        String Game_URL = MainURL + "game.php";

        String serverURL = "classname=" + myClassname + "&unit=" + nowUnit + "&number=" + nowNum;
        System.out.println("myClassname : "+myClassname+", unit : "+nowUnit + ", number : "+nowNum);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(Game_URL);
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
                unitName = item.getString("unitname");
                //System.out.println("id = " + id + ", pw = " + pw);
                System.out.println("unitNmae === " + unitName);
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }
}
