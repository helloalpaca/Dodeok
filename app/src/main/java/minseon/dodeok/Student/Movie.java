package minseon.dodeok.Student;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Student.Game.emotions;
import static minseon.dodeok.Student.Game.mission;
import static minseon.dodeok.Student.Game.nowNum;
import static minseon.dodeok.Student.Game.nowUnit;
import static minseon.dodeok.Student.Game.url;
import static minseon.dodeok.Student.Home.myClassname;
import static minseon.dodeok.Student.Game.answer;

public class Movie extends AppCompatActivity {

    ImageButton btn_movie_back;
    Button btn_movie;
    View view1;
    TextView text1, text2, text3;
    boolean flag;
    String[] nick;
    Date today;
    String ttoday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        btn_movie_back = (ImageButton)findViewById(R.id.movie_btn1);
        btn_movie = (Button)findViewById(R.id.movie_btn2);
        text1 = (TextView)findViewById(R.id.movie_text1);
        text2 = (TextView)findViewById(R.id.movie_text2);
        text3 = (TextView)findViewById(R.id.movie_text3);
        view1 = (View)findViewById(R.id.movie_view1);

        answer = "확인";
        text1.setText(mission);

        GetStudent();

        String friends = "";
        if(nick!=null) {
            for (int i = 0; i < nick.length - 2; ++i) {
                friends += nick[i] + ", ";
            }
            friends += nick[nick.length-1];
            text3.setText(friends+"\n");
        } else {
            view1.setVisibility(View.GONE);
            text3.setVisibility(View.GONE);
        }

        flag = false;
    }

    public void onClickMovieButton1(View view){
        // if(flag) 영상을 클릭해서 보코, 확인을 눌러야 DB에 본게 저장이 되도록
        Intent intent = new Intent(Movie.this, Game.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickMovieButton2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        flag = true;
        System.out.println("flag : "+flag);
    }

    public void onClickMovieButton3(View view){
        if(flag) {
            answer = "확인";
            //Toast.makeText(getApplicationContext(), "영상을 봤습니다.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Movie.this, Emotion2.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "영상을 보지않았습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private void InsertData() {
        String errorString = null;
        String mJsonString = null;
        String Game_URL = MainURL + "insert.php";

        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        final long now = System.currentTimeMillis();
        Date today = new Date(now);
        ttoday = format.format(today);

        String serverURL = "id=" + userID + "&classname=" + myClassname + "&unit=" + nowUnit + "&number=" + nowNum
                + "&answer=" + answer +"&emotion="+emotions+"&solved="+ttoday;

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

    }

    private void GetStudent() {
        String errorString = null;
        String mJsonString = null;
        String GetStudent_URL = MainURL + "getstudent.php/";

        String serverURL = "id=" + userID + "&classname=" + myClassname + "&nowUnit=" + nowUnit + "&nowNum=" + nowNum;

        System.out.println("id : "+userID + ", classname : "+myClassname +", unit : "+nowUnit +", num : "+nowNum);
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
            if(jsonArray.length()>0) {
                nick = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    nick[i] = item.getString("nick");
                    System.out.println("nick : " + nick);
                }
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }

}

