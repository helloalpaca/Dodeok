package minseon.dodeok.Student;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Student.Game.emotions;
import static minseon.dodeok.Student.Home.myClassname;
import static minseon.dodeok.Student.Unit.str_emotiontime;

public class Emotion extends AppCompatActivity {

    ImageButton emotion_1, emotion_2, emotion_3, emotion_4;
    ImageButton emotion_5, emotion_6, emotion_7, emotion_8;
    ImageButton emotion_9, emotion_10, emotion_11, emotion_12;
    ImageButton emotion_13, emotion_14, emotion_15, emotion_16;
    ImageButton emotion_17, emotion_18, emotion_19, emotion_20;
    ImageButton emotion_21, emotion_22, emotion_23, emotion_24;
    ImageButton emotion_25, emotion_26, emotion_27, emotion_28;
    ImageButton emotion_29, emotion_30, emotion_31, emotion_32;
    ImageButton emotion_33, emotion_34, emotion_35, emotion_36;

    String ttoday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);

        emotion_1 = (ImageButton)findViewById(R.id.emotion_1);
        emotion_2 = (ImageButton)findViewById(R.id.emotion_2);
        emotion_3 = (ImageButton)findViewById(R.id.emotion_3);
        emotion_4 = (ImageButton)findViewById(R.id.emotion_4);
        emotion_5 = (ImageButton)findViewById(R.id.emotion_5);
        emotion_6 = (ImageButton)findViewById(R.id.emotion_6);
        emotion_7 = (ImageButton)findViewById(R.id.emotion_7);
        emotion_8 = (ImageButton)findViewById(R.id.emotion_8);
        emotion_9 = (ImageButton)findViewById(R.id.emotion_9);
        emotion_10 = (ImageButton)findViewById(R.id.emotion_10);
        emotion_11 = (ImageButton)findViewById(R.id.emotion_11);
        emotion_12 = (ImageButton)findViewById(R.id.emotion_12);
        emotion_13 = (ImageButton)findViewById(R.id.emotion_13);
        emotion_14 = (ImageButton)findViewById(R.id.emotion_14);
        emotion_15 = (ImageButton)findViewById(R.id.emotion_15);
        emotion_16 = (ImageButton)findViewById(R.id.emotion_16);
        emotion_17 = (ImageButton)findViewById(R.id.emotion_17);
        emotion_18 = (ImageButton)findViewById(R.id.emotion_18);
        emotion_19 = (ImageButton)findViewById(R.id.emotion_19);
        emotion_20 = (ImageButton)findViewById(R.id.emotion_20);
        emotion_21 = (ImageButton)findViewById(R.id.emotion_21);
        emotion_22 = (ImageButton)findViewById(R.id.emotion_22);
        emotion_23 = (ImageButton)findViewById(R.id.emotion_23);
        emotion_24 = (ImageButton)findViewById(R.id.emotion_24);
        emotion_25 = (ImageButton)findViewById(R.id.emotion_25);
        emotion_26 = (ImageButton)findViewById(R.id.emotion_26);
        emotion_27 = (ImageButton)findViewById(R.id.emotion_27);
        emotion_28 = (ImageButton)findViewById(R.id.emotion_28);
        emotion_29 = (ImageButton)findViewById(R.id.emotion_29);
        emotion_30 = (ImageButton)findViewById(R.id.emotion_30);
        emotion_31 = (ImageButton)findViewById(R.id.emotion_31);
        emotion_32 = (ImageButton)findViewById(R.id.emotion_32);
        emotion_33 = (ImageButton)findViewById(R.id.emotion_33);
        emotion_34 = (ImageButton)findViewById(R.id.emotion_34);
        emotion_35 = (ImageButton)findViewById(R.id.emotion_35);
        emotion_36 = (ImageButton)findViewById(R.id.emotion_36);

        // Calculate the current date and time
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        final long now = System.currentTimeMillis();
        Date today = new Date(now);
        ttoday = format.format(today);
    }

    public void onClickEmotionButton1(View view){ Button(1); }
    public void onClickEmotionButton2(View view){ Button(2); }
    public void onClickEmotionButton3(View view){ Button(3); }
    public void onClickEmotionButton4(View view){ Button(4); }
    public void onClickEmotionButton5(View view){ Button(5); }
    public void onClickEmotionButton6(View view){ Button(6); }
    public void onClickEmotionButton7(View view){ Button(7); }
    public void onClickEmotionButton8(View view){ Button(8); }
    public void onClickEmotionButton9(View view){ Button(9); }
    public void onClickEmotionButton10(View view){ Button(10); }
    public void onClickEmotionButton11(View view){ Button(11); }
    public void onClickEmotionButton12(View view){ Button(12); }
    public void onClickEmotionButton13(View view){ Button(13); }
    public void onClickEmotionButton14(View view){ Button(14); }
    public void onClickEmotionButton15(View view){ Button(15); }
    public void onClickEmotionButton16(View view){ Button(16); }
    public void onClickEmotionButton17(View view){ Button(17); }
    public void onClickEmotionButton18(View view){ Button(18); }
    public void onClickEmotionButton19(View view){ Button(19); }
    public void onClickEmotionButton20(View view){ Button(20); }
    public void onClickEmotionButton21(View view){ Button(21); }
    public void onClickEmotionButton22(View view){ Button(22); }
    public void onClickEmotionButton23(View view){ Button(23); }
    public void onClickEmotionButton24(View view){ Button(24); }
    public void onClickEmotionButton25(View view){ Button(25); }
    public void onClickEmotionButton26(View view){ Button(26); }
    public void onClickEmotionButton27(View view){ Button(27); }
    public void onClickEmotionButton28(View view){ Button(28); }
    public void onClickEmotionButton29(View view){ Button(29); }
    public void onClickEmotionButton30(View view){ Button(30); }
    public void onClickEmotionButton31(View view){ Button(31); }
    public void onClickEmotionButton32(View view){ Button(32); }
    public void onClickEmotionButton33(View view){ Button(33); }
    public void onClickEmotionButton34(View view){ Button(34); }
    public void onClickEmotionButton35(View view){ Button(35); }
    public void onClickEmotionButton36(View view){ Button(36); }
    public void onClickEmotionButton37(View view){ Button(36); }
    public void onClickEmotionButton38(View view){ Button(36); }
    public void onClickEmotionButton39(View view){ Button(36); }
    public void onClickEmotionButton40(View view){ Button(36); }

    public void Button(int n){
        //Game.emotion.setImageResource(R.drawable.emotion_1);
        //Game.textview_emotion.setText(R.string.emotion_1);
        emotions = "emotion_"+n;
        ChangeEmotion();

        Intent intent = new Intent(Emotion.this, Game.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    private void ChangeEmotion() {
        String errorString = null;
        String mJsonString = null;
        String Game_URL = MainURL + "changeemotion.php/";

        String serverURL = "id=" + userID + "&classname=" + myClassname + "&emotion=" + emotions + "&emotiontime=" + ttoday;
        //System.out.println("emotiontime="+ttoday);
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

            System.out.println("emotion in ChangeEmotion : "+emotions);

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

        }
    }
}
