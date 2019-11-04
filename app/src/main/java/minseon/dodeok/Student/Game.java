package minseon.dodeok.Student;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.Locale;

import minseon.dodeok.R;
import minseon.dodeok.Teacher.ListViewAdapterStudent;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Student.Home.myClassname;

public class Game extends AppCompatActivity {

    RelativeLayout game_background;
    Button btn_day_1, btn_day_2, btn_day_3, btn_day_4, btn_day_5;
    Button btn_day_6, btn_day_7, btn_day_8, btn_day_9, btn_day_10;
    Button btn_showemotions;
    static TextView textview_number, textview_emotion;
    static ImageView image_day_1, image_day_2, image_day_3, image_day_4, image_day_5;
    static ImageView image_day_6, image_day_7, image_day_8, image_day_9, image_day_10;
    static ImageView image_star_1, image_star_2, image_star_3, image_star_4, image_star_5;
    static ImageView image_star_6, image_star_7, image_star_8, image_star_9, image_star_10;
    static Button emotion;
    static int nowUnit, nowNum;
    static String nick, emotions;
    static String type, mission, url, ready;
    static int clickedNum;
    ListView listView;
    LinearLayout linearLayout;
    ImageView img1, img2;
    boolean flag;

    TextView drawer_text1, drawer_text2, drawer_feedtext1, drawer_feedtext2, drawer_feedtext3, drawer_feedtext4, drawer_feedtext5 ;
    ImageView drawer_img1;

    static String answer="";
    final ListViewAdapterFeedback adapter=new ListViewAdapterFeedback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //game_background = (RelativeLayout) findViewById(R.id.game_background);
        textview_number = (TextView) findViewById(R.id.textview_number);
        btn_day_1 = (Button) findViewById(R.id.game_btn1);
        btn_day_2 = (Button) findViewById(R.id.game_btn2);
        btn_day_3 = (Button) findViewById(R.id.game_btn3);
        btn_day_4 = (Button) findViewById(R.id.game_btn4);
        btn_day_5 = (Button) findViewById(R.id.game_btn5);
        btn_day_6 = (Button) findViewById(R.id.game_btn6);
        btn_day_7 = (Button) findViewById(R.id.game_btn7);
        btn_day_8 = (Button) findViewById(R.id.game_btn8);
        btn_day_9 = (Button) findViewById(R.id.game_btn9);
        btn_day_10 = (Button) findViewById(R.id.game_btn10);
        emotion = (Button) findViewById(R.id.btn_emotion_card);
        //textview_emotion = (TextView) findViewById(R.id.textview_emotion);
        image_day_1 = (ImageView) findViewById(R.id.image_day_1);
        image_day_2 = (ImageView) findViewById(R.id.image_day_2);
        image_day_3 = (ImageView) findViewById(R.id.image_day_3);
        image_day_4 = (ImageView) findViewById(R.id.image_day_4);
        image_day_5 = (ImageView) findViewById(R.id.image_day_5);
        image_day_6 = (ImageView) findViewById(R.id.image_day_6);
        image_day_7 = (ImageView) findViewById(R.id.image_day_7);
        image_day_8 = (ImageView) findViewById(R.id.image_day_8);
        image_day_9 = (ImageView) findViewById(R.id.image_day_9);
        image_day_10 = (ImageView) findViewById(R.id.image_day_10);
        btn_showemotions = (Button)findViewById(R.id.btn_showemotions);
        image_star_1 = (ImageView) findViewById(R.id.image_star_1);
        image_star_2 = (ImageView) findViewById(R.id.image_star_2);
        image_star_3 = (ImageView) findViewById(R.id.image_star_3);
        image_star_4 = (ImageView) findViewById(R.id.image_star_4);
        image_star_5 = (ImageView) findViewById(R.id.image_star_5);
        image_star_6 = (ImageView) findViewById(R.id.image_star_6);
        image_star_7 = (ImageView) findViewById(R.id.image_star_7);
        image_star_8 = (ImageView) findViewById(R.id.image_star_8);
        image_star_9 = (ImageView) findViewById(R.id.image_star_9);
        image_star_10 = (ImageView) findViewById(R.id.image_star_10);
        listView =(ListView)findViewById(R.id.listview_feed);

        img1 = (ImageView)findViewById(R.id.game_img1);
        img2 = (ImageView)findViewById(R.id.game_img2);

        drawer_text1 = (TextView)findViewById(R.id.drawer_text1);
        drawer_text2 = (TextView)findViewById(R.id.drawer_text2);
        drawer_feedtext1 = (TextView)findViewById(R.id.drawer_feedtext1);
        drawer_feedtext2 = (TextView)findViewById(R.id.drawer_feedtext2);
        drawer_feedtext3 = (TextView)findViewById(R.id.drawer_feedtext3);
        drawer_feedtext4 = (TextView)findViewById(R.id.drawer_feedtext4);
        drawer_feedtext5 = (TextView)findViewById(R.id.drawer_feedtext5);
        drawer_img1 = (ImageView)findViewById(R.id.drawer_img1);

        flag = true;

        MetaData();
        EmotionData();

        String emotionCard = "@drawable/png_" + emotions;
        String pack = this.getPackageName();
        int resID = getResources().getIdentifier(emotionCard, "drawable", pack);
        img2.setImageResource(resID);

        // Set Emotion Image&Text in Game
//        System.out.println("[Game] emotions : " + emotions);
//        String emotionCard = "@drawable/" + emotions;
//        String strName = "@string/" + emotions;
//        String pack = this.getPackageName();
//        int resID = getResources().getIdentifier(emotionCard, "drawable", pack);
//        int strID = getResources().getIdentifier(strName, "strings", pack);
//        emotion.setImageResource(resID);
//        textview_emotion.setText(strID);

        // Calculate the current date and time

        Drawer();

        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        final long now = System.currentTimeMillis();
        final Date today = new Date(now);
        //System.out.println("[Game] today : "+today);

        // Take the start date of the mission and compare
        GameData(nowNum);
        //System.out.println("[Game] ready : "+ready);
        int compare = Select();
        //System.out.println("[Game] compare : "+compare);

        if(compare >= 0){

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
            builder.setSmallIcon(R.mipmap.dodeok_round);
            builder.setContentTitle("미션을 수행할 수 있습니다.");
            builder.setContentText(nowUnit+"단원 "+nowNum+"일째 미션을 수행할 수 있습니다.");

//            Intent notificationIntent = new Intent(this, NotificationSomething.class);
//            notificationIntent.putExtra("notificationId", 1);
//            notificationIntent.putExtra("extraString", "Hello PendingIntent");
//            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Send push alarm
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
            }
            // id : 각 알람마다 정의해야하는 고유한 int 값
            notificationManager.notify(1, builder.build());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Game.this, Unit.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        // 코드 작성
        super.onBackPressed();
    }

    public void GameButton(int n){
        //System.out.println("clickedNum="+n);
        clickedNum = n;
        GameData(n);
        int compare = Select();

        if(nowNum > n){
            if(type != null && type.equals("movie")){
                Intent intent = new Intent(Game.this, AnswerMovie.class);
                startActivity(intent);
            }
            if(type != null && type.equals("rating")){
                Intent intent = new Intent(Game.this, AnswerGrade.class);
                startActivity(intent);
            }
            if(type != null && type.equals("describe")){
                Intent intent = new Intent(Game.this, AnswerDescriptive.class);
                startActivity(intent);
            }
        }
        if(nowNum == n && compare >= 0){
            if(type != null && type.equals("movie")){
                Intent intent = new Intent(Game.this, Movie.class);
                startActivity(intent);
            }
            if(type != null && type.equals("rating")){
                Intent intent = new Intent(Game.this, Grade.class);
                startActivity(intent);
            }
            if(type != null && type.equals("describe")){
                Intent intent = new Intent(Game.this, Descriptive.class);
                startActivity(intent);
            }
        }
        if(nowNum == n && compare < 0){
            Toast.makeText(Game.this,"아직 미션을 수행할 수 없습니다.",Toast.LENGTH_LONG).show();
        }
    }
    public void onCLickGameButton1(View view) { GameButton(1); }
    public void onCLickGameButton2(View view) { GameButton(2); }
    public void onCLickGameButton3(View view) { GameButton(3); }
    public void onCLickGameButton4(View view) { GameButton(4); }
    public void onCLickGameButton5(View view) { GameButton(5); }
    public void onCLickGameButton6(View view) { GameButton(6); }
    public void onCLickGameButton7(View view) { GameButton(7); }
    public void onCLickGameButton8(View view) { GameButton(8); }
    public void onCLickGameButton9(View view) { GameButton(9); }
    public void onCLickGameButton10(View view) { GameButton(10); }

    public void onClickGameButton11(View view) {
//        Intent intent = new Intent(Game.this, Emotion.class);
//        startActivity(intent);
        if(flag){
            img1.setVisibility(View.GONE);
            img2.setVisibility(View.GONE);
            flag = false;
        } else {
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);

            String emotionCard = "@drawable/png_" + emotions;
            String pack = this.getPackageName();
            int resID = getResources().getIdentifier(emotionCard, "drawable", pack);
            img2.setImageResource(resID);
            flag = true;
        }
    }
    public void onClickGameButton12(View view) {
        Intent intent = new Intent(Game.this, ShowEmotion.class);
        startActivity(intent);
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
                    emotions = item.getString("emotion");
                    nick = item.getString("nick");
                    //System.out.println("nowUnit == " + nowUnit);
                    //System.out.println("nowNum == " + nowNum);
                    //System.out.println("id = " + id + ", pw = " + pw);
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

    private void GameData(int num) {
        String errorString = null;
        String mJsonString = null;
        String Game_URL = MainURL + "game.php";

        String serverURL = "classname=" + myClassname + "&unit=" + nowUnit + "&number=" + num;
        //System.out.println("myClassname : "+myClassname+", unit : "+ nowUnit + ", number : "+ num);
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
                //System.out.println(line);
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
                type = item.getString("type");
                mission = item.getString("mission");
                url = item.getString("url");
                ready = item.getString("ready");
//                System.out.println("type === " + type);
//                System.out.println("mission === " + mission);
//                System.out.println("url === " + url);
//                System.out.println("ready === " + ready);
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }

    private void EmotionData() {
        String errorString = null;
        String mJsonString = null;
        String Game_URL = MainURL + "emotiondata.php";

        String serverURL = "classname=" + myClassname + "&id=" + userID;
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
                //System.out.println(line);
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
                String emotiondata = item.getString("emotion");
                int unit = item.getInt("unit");
                int number = item.getInt("number");
                String type = item.getString("type");
                String answer = item.getString("answer");
                //System.out.println("id = " + id + ", pw = " + pw);
                //System.out.println("emotion === " + emotiondata);
                if (unit == nowUnit && emotiondata != null) {
                    String emotionCard = "@drawable/png_" + emotiondata;
                    String pack = this.getPackageName();
                    int resID = getResources().getIdentifier(emotionCard, "drawable", pack);
                    if (number == 1) {
                        image_day_1.setImageResource(resID);
                        btn_day_1.setText("");
                        if(!type.equals("rating")) image_star_1.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_1.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_1.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_1.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_1.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_1.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_1.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 2) {
                        image_day_2.setImageResource(resID);
                        btn_day_2.setText("");
                        if(!type.equals("rating")) image_star_2.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_2.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_2.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_2.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_2.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_2.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_2.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 3) {
                        image_day_3.setImageResource(resID);
                        btn_day_3.setText("");
                        if(!type.equals("rating")) image_star_3.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_3.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_3.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_3.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_3.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_3.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_3.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 4) {
                        image_day_4.setImageResource(resID);
                        btn_day_4.setText("");
                        if(!type.equals("rating")) image_star_4.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_4.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_4.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_4.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_4.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_4.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_4.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 5) {
                        image_day_5.setImageResource(resID);
                        btn_day_5.setText("");
                        if(!type.equals("rating")) image_star_5.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_5.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_5.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_5.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_5.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_5.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_5.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 6) {
                        image_day_6.setImageResource(resID);
                        btn_day_6.setText("");
                        if(!type.equals("rating")) image_star_6.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_6.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_6.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_6.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_6.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_6.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_6.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 7) {
                        image_day_7.setImageResource(resID);
                        btn_day_7.setText("");
                        if(!type.equals("rating")) image_star_7.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_7.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_7.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_7.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_7.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_7.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_7.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 8) {
                        image_day_8.setImageResource(resID);
                        btn_day_8.setText("");
                        if(!type.equals("rating")) image_star_8.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_8.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_8.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_8.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_8.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_8.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_8.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 9) {
                        image_day_9.setImageResource(resID);
                        btn_day_9.setText("");
                        if(!type.equals("rating")) image_star_9.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_9.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_9.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_9.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_9.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_9.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_9.setImageResource(R.drawable.star3);
                        }
                    }
                    if (number == 10) {
                        image_day_10.setImageResource(resID);
                        btn_day_10.setText("");
                        if(!type.equals("rating")) image_star_10.setImageResource(R.drawable.star33);
                        else{
                            if(answer.equals("0.5")) image_star_10.setImageResource(R.drawable.star05);
                            if(answer.equals("1.0")) image_star_10.setImageResource(R.drawable.star1);
                            if(answer.equals("1.5")) image_star_10.setImageResource(R.drawable.star15);
                            if(answer.equals("2.0")) image_star_10.setImageResource(R.drawable.star2);
                            if(answer.equals("2.5")) image_star_10.setImageResource(R.drawable.star25);
                            if(answer.equals("3.0")) image_star_10.setImageResource(R.drawable.star3);
                        }
                    }
                }
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }

    public int Select(){
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        final long now = System.currentTimeMillis();
        final Date today = new Date(now);
        Date missionday = null;

        try {
            missionday = format.parse(ready);
            //System.out.println("[Game] missionday : " + missionday);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int compare = today.compareTo(missionday);
        if(compare >= 0){
            //System.out.println("[Game] today >= missionday");
        } else{
            //System.out.println("[Game] today < missionday");
        }
        return compare;
    }

    public void Drawer(){
        listView.setAdapter(adapter);

//        adapter.addListItem("emotion_2","권민선","힘내요(1)","좋아요(7)","나도 그래!(1)","궁금해(8)","넌 특별해(3)");
//        adapter.addListItem("emotion_4","나미","힘내요 (2)",null);
//        adapter.addListItem("emotion_5","없어야함",null,null);
        MyFeedBackData();
        FeedBackData();
    }

    private void FeedBackData() {
        String errorString = null;
        String mJsonString;

        String FeedBack_URL = MainURL + "feedback.php";

        String serverURL = "id=" + userID + "&classname=" + myClassname;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(FeedBack_URL);
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
                    String id = item.getString("id");
                    String emotion = item.getString("emotion");
                    String emotiontime = item.getString("emotiontime");
                    String feedback = item.getString("feedback");
                    String feedtime = item.getString("feedtime");
                    String nick = item.getString("nick");

                    System.out.println(id+" : "+emotion+" : "+emotiontime+" : "+feedback+" : "+feedtime);

                    if(feedback.equals("null")){
                        adapter.addListItem(emotion,nick,id,null,null,null,null,null);
                    }
                    else{
                        FeedBack2Data(id,emotion,nick);
                    }
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

    private void MyFeedBackData() {
        String errorString = null;
        String mJsonString;

        String FeedBack_URL = MainURL + "myfeedback.php";

        String serverURL = "id=" + userID + "&classname=" + myClassname;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(FeedBack_URL);
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
                int good = 0;
                int fighting = 0;
                int metoo = 0;
                int curious = 0;
                int special = 0;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String id = item.getString("id");
                    String feedback = item.getString("feedback");
                    String feedtime = item.getString("feedtime");

                    if(feedback.equals("좋아요")) good++;
                    if(feedback.equals("힘내요")) fighting++;
                    if(feedback.equals("나도 그래!")) metoo++;
                    if(feedback.equals("궁금해")) curious++;
                    if(feedback.equals("넌 특별해")) special++;

                    //System.out.println(id+" : "+emotion+" : "+emotiontime+" : "+feedback+" : "+feedtime);

                }
                String str = "@drawable/png_" + emotions;
                String pack = this.getPackageName();
                int res1 = getResources().getIdentifier(str, "drawable", pack);
                String str2 = "@string/"+emotions;
                int res2 = getResources().getIdentifier(str2,"string",pack);
                System.out.println("str : "+str+", res1 : "+res1);
                System.out.println("str2 : "+str2+", res2 : "+res2);

                drawer_img1.setImageResource(res1);
                drawer_text1.setText(nick);
                drawer_text2.setText(getString(res2));
                drawer_feedtext1.setBackgroundResource(R.drawable.feedback_background);
                drawer_feedtext2.setBackgroundResource(R.drawable.feedback_background);
                drawer_feedtext3.setBackgroundResource(R.drawable.feedback_background);
                drawer_feedtext4.setBackgroundResource(R.drawable.feedback_background);
                drawer_feedtext5.setBackgroundResource(R.drawable.feedback_background);
                drawer_feedtext1.setText("힘내요("+fighting+")");
                drawer_feedtext2.setText("좋아요("+good+")");
                drawer_feedtext3.setText("나도 그래!("+metoo+")");
                drawer_feedtext4.setText("궁금해("+curious+")");
                drawer_feedtext5.setText("넌 특별해("+special+")");

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

    private void FeedBack2Data(String feedid, String emotion, String nick) {
        String errorString = null;
        String mJsonString;

        String FeedBack_URL = MainURL + "feedback2.php";

        String serverURL = "id=" + feedid + "&classname=" + myClassname;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(FeedBack_URL);
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

            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");

                int good = 0;
                int fighting = 0;
                int metoo = 0;
                int curious = 0;
                int special = 0;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String id = item.getString("id");
                    String feedback = item.getString("feedback");
                    System.out.println("[Game]feedback : "+feedback);
                    if(feedback.equals("좋아요")) good++;
                    if(feedback.equals("힘내요")) fighting++;
                    if(feedback.equals("나도 그래!")) metoo++;
                    if(feedback.equals("궁금해")) curious++;
                    if(feedback.equals("넌 특별해")) special++;
                }

                String str1 = "힘내요("+fighting+")";
                String str2 = "좋아요("+good+")";
                String str3 = "나도 그래!("+metoo+")";
                String str4 = "궁금해("+curious+")";
                String str5 = "넌 특별해("+special+")";
                /*
                if(fighting==0) adapter.addListItem(emotion,nick,null,"좋아요 ("+good+")");
                else if(good==0) adapter.addListItem(emotion,nick,"힘내요 ("+fighting+")",null);
                else adapter.addListItem(emotion,nick,"힘내요 ("+fighting+")","좋아요 ("+good+")");
                 */
                adapter.addListItem(emotion,nick,feedid,str1,str2,str3,str4,str5);

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
}
