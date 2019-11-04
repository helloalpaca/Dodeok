package minseon.dodeok.Student;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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
import static minseon.dodeok.Student.Game.clickedNum;
import static minseon.dodeok.Student.Game.emotions;
import static minseon.dodeok.Student.Game.nowUnit;
import static minseon.dodeok.Student.Game.answer;

import static minseon.dodeok.Student.Home.myClassname;

public class Emotion2 extends AppCompatActivity {

    ImageButton emotion_1, emotion_2, emotion_3, emotion_4;
    ImageButton emotion_5, emotion_6, emotion_7, emotion_8;
    ImageButton emotion_9, emotion_10, emotion_11, emotion_12;
    ImageButton emotion_13, emotion_14, emotion_15, emotion_16;
    ImageButton emotion_17, emotion_18, emotion_19, emotion_20;
    ImageButton emotion_21, emotion_22, emotion_23, emotion_24;
    ImageButton emotion_25, emotion_26, emotion_27, emotion_28;
    ImageButton emotion_29, emotion_30, emotion_31, emotion_32;
    ImageButton emotion_33, emotion_34, emotion_35, emotion_36;

    static String game_emotion = "";
    String ttoday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion2);

        emotion_1 = (ImageButton)findViewById(R.id.emotion2_img1);
        emotion_2 = (ImageButton)findViewById(R.id.emotion2_img2);
        emotion_3 = (ImageButton)findViewById(R.id.emotion2_img3);
        emotion_4 = (ImageButton)findViewById(R.id.emotion2_img4);

        emotion_5 = (ImageButton)findViewById(R.id.emotion2_img5);
        emotion_6 = (ImageButton)findViewById(R.id.emotion2_img6);
        emotion_7 = (ImageButton)findViewById(R.id.emotion2_img7);
        emotion_8 = (ImageButton)findViewById(R.id.emotion2_img8);

        emotion_9 = (ImageButton)findViewById(R.id.emotion2_img9);
        emotion_10 = (ImageButton)findViewById(R.id.emotion2_img10);
        emotion_11 = (ImageButton)findViewById(R.id.emotion2_img11);
        emotion_12 = (ImageButton)findViewById(R.id.emotion2_img12);

        emotion_13 = (ImageButton)findViewById(R.id.emotion2_img13);
        emotion_14 = (ImageButton)findViewById(R.id.emotion2_img14);
        emotion_15 = (ImageButton)findViewById(R.id.emotion2_img15);
        emotion_16 = (ImageButton)findViewById(R.id.emotion2_img16);

        emotion_17 = (ImageButton)findViewById(R.id.emotion2_img17);
        emotion_18 = (ImageButton)findViewById(R.id.emotion2_img18);
        emotion_19 = (ImageButton)findViewById(R.id.emotion2_img19);
        emotion_20 = (ImageButton)findViewById(R.id.emotion2_img20);

        emotion_21 = (ImageButton)findViewById(R.id.emotion2_img21);
        emotion_22 = (ImageButton)findViewById(R.id.emotion2_img22);
        emotion_23 = (ImageButton)findViewById(R.id.emotion2_img23);
        emotion_24 = (ImageButton)findViewById(R.id.emotion2_img24);

        emotion_25 = (ImageButton)findViewById(R.id.emotion2_img25);
        emotion_26 = (ImageButton)findViewById(R.id.emotion2_img26);
        emotion_27 = (ImageButton)findViewById(R.id.emotion2_img27);
        emotion_28 = (ImageButton)findViewById(R.id.emotion2_img28);

        emotion_29 = (ImageButton)findViewById(R.id.emotion2_img29);
        emotion_30 = (ImageButton)findViewById(R.id.emotion2_img30);
        emotion_31 = (ImageButton)findViewById(R.id.emotion2_img31);
        emotion_32 = (ImageButton)findViewById(R.id.emotion2_img32);

        emotion_33 = (ImageButton)findViewById(R.id.emotion2_img33);
        emotion_34 = (ImageButton)findViewById(R.id.emotion2_img34);
        emotion_35 = (ImageButton)findViewById(R.id.emotion2_img35);
        emotion_36 = (ImageButton)findViewById(R.id.emotion2_img36);
    }

    public void onClickEmotion2Button1(View view){ Button(1); }
    public void onClickEmotion2Button2(View view){ Button(2); }
    public void onClickEmotion2Button3(View view){ Button(3); }
    public void onClickEmotion2Button4(View view){ Button(4); }
    public void onClickEmotion2Button5(View view){ Button(5); }
    public void onClickEmotion2Button6(View view){ Button(6); }
    public void onClickEmotion2Button7(View view){ Button(7); }
    public void onClickEmotion2Button8(View view){ Button(8); }
    public void onClickEmotion2Button9(View view){ Button(9); }
    public void onClickEmotion2Button10(View view){ Button(10); }
    public void onClickEmotion2Button11(View view){ Button(11); }
    public void onClickEmotion2Button12(View view){ Button(12); }
    public void onClickEmotion2Button13(View view){ Button(13); }
    public void onClickEmotion2Button14(View view){ Button(14); }
    public void onClickEmotion2Button15(View view){ Button(15); }
    public void onClickEmotion2Button16(View view){ Button(16); }
    public void onClickEmotion2Button17(View view){ Button(17); }
    public void onClickEmotion2Button18(View view){ Button(18); }
    public void onClickEmotion2Button19(View view){ Button(19); }
    public void onClickEmotion2Button20(View view){ Button(20); }
    public void onClickEmotion2Button21(View view){ Button(21); }
    public void onClickEmotion2Button22(View view){ Button(22); }
    public void onClickEmotion2Button23(View view){ Button(23); }
    public void onClickEmotion2Button24(View view){ Button(24); }
    public void onClickEmotion2Button25(View view){ Button(25); }
    public void onClickEmotion2Button26(View view){ Button(26); }
    public void onClickEmotion2Button27(View view){ Button(27); }
    public void onClickEmotion2Button28(View view){ Button(28); }
    public void onClickEmotion2Button29(View view){ Button(29); }
    public void onClickEmotion2Button30(View view){ Button(30); }
    public void onClickEmotion2Button31(View view){ Button(31); }
    public void onClickEmotion2Button32(View view){ Button(32); }
    public void onClickEmotion2Button33(View view){ Button(33); }
    public void onClickEmotion2Button34(View view){ Button(34); }
    public void onClickEmotion2Button35(View view){ Button(35); }
    public void onClickEmotion2Button36(View view){ Button(36); }
    public void onClickEmotion2Button37(View view){ Button(36); }
    public void onClickEmotion2Button38(View view){ Button(36); }
    public void onClickEmotion2Button39(View view){ Button(36); }
    public void onClickEmotion2Button40(View view){ Button(36); }

    public void Button(int n){
        game_emotion="emotion_"+n;
        InsertData();

        Intent intent = new Intent(Emotion2.this, Game.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void InsertData() {
        String errorString = null;
        String mJsonString = null;
        String Game_URL = MainURL + "insert.php";

        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        final long now = System.currentTimeMillis();
        Date today = new Date(now);
        ttoday = format.format(today);

        String serverURL = "id=" + userID + "&classname=" + myClassname + "&unit=" + nowUnit + "&number=" + clickedNum
                + "&answer=" + answer + "&emotion=" + game_emotion + "&solved="+ttoday;
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
}
