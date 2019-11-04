package minseon.dodeok.Teacher;

import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
import static minseon.dodeok.Teacher.AnswerStudent.StudentID;
import static minseon.dodeok.Teacher.GetNumber.ansnum;
import static minseon.dodeok.Teacher.GetNumber.ansunit;
import static minseon.dodeok.Teacher.Home2.myClassname;

public class EmotionNumber extends AppCompatActivity {

    TextView textview_emotion_1, textview_emotion_2, textview_emotion_3, textview_emotion_4;
    TextView textview_emotion_5, textview_emotion_6, textview_emotion_7, textview_emotion_8;
    TextView textview_emotion_9, textview_emotion_10, textview_emotion_11, textview_emotion_12;
    TextView textview_emotion_13, textview_emotion_14, textview_emotion_15, textview_emotion_16;
    TextView textview_emotion_17, textview_emotion_18, textview_emotion_19, textview_emotion_20;
    TextView textview_emotion_21, textview_emotion_22, textview_emotion_23, textview_emotion_24;
    TextView textview_emotion_25, textview_emotion_26, textview_emotion_27, textview_emotion_28;
    TextView textview_emotion_29, textview_emotion_30, textview_emotion_31, textview_emotion_32;
    TextView textview_emotion_33, textview_emotion_34, textview_emotion_35, textview_emotion_36;

    int emotion[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_number);

        emotion = new int[36];
        for(int i=0;i<36;++i){
            emotion[i] = 0;
        }

        textview_emotion_1 = (TextView)findViewById(R.id.textview_emotion_1);
        textview_emotion_2 = (TextView)findViewById(R.id.textview_emotion_2);
        textview_emotion_3 = (TextView)findViewById(R.id.textview_emotion_3);
        textview_emotion_4 = (TextView)findViewById(R.id.textview_emotion_4);
        textview_emotion_5 = (TextView)findViewById(R.id.textview_emotion_5);
        textview_emotion_6 = (TextView)findViewById(R.id.textview_emotion_6);
        textview_emotion_7 = (TextView)findViewById(R.id.textview_emotion_7);
        textview_emotion_8 = (TextView)findViewById(R.id.textview_emotion_8);
        textview_emotion_9 = (TextView)findViewById(R.id.textview_emotion_9);
        textview_emotion_10 = (TextView)findViewById(R.id.textview_emotion_10);
        textview_emotion_11 = (TextView)findViewById(R.id.textview_emotion_11);
        textview_emotion_12 = (TextView)findViewById(R.id.textview_emotion_12);
        textview_emotion_13 = (TextView)findViewById(R.id.textview_emotion_13);
        textview_emotion_14 = (TextView)findViewById(R.id.textview_emotion_14);
        textview_emotion_15 = (TextView)findViewById(R.id.textview_emotion_15);
        textview_emotion_16 = (TextView)findViewById(R.id.textview_emotion_16);
        textview_emotion_17 = (TextView)findViewById(R.id.textview_emotion_17);
        textview_emotion_18 = (TextView)findViewById(R.id.textview_emotion_18);
        textview_emotion_19 = (TextView)findViewById(R.id.textview_emotion_19);
        textview_emotion_20 = (TextView)findViewById(R.id.textview_emotion_20);
        textview_emotion_21 = (TextView)findViewById(R.id.textview_emotion_21);
        textview_emotion_22 = (TextView)findViewById(R.id.textview_emotion_22);
        textview_emotion_23 = (TextView)findViewById(R.id.textview_emotion_23);
        textview_emotion_24 = (TextView)findViewById(R.id.textview_emotion_24);
        textview_emotion_25 = (TextView)findViewById(R.id.textview_emotion_25);
        textview_emotion_26 = (TextView)findViewById(R.id.textview_emotion_26);
        textview_emotion_27 = (TextView)findViewById(R.id.textview_emotion_27);
        textview_emotion_28 = (TextView)findViewById(R.id.textview_emotion_28);
        textview_emotion_29 = (TextView)findViewById(R.id.textview_emotion_29);
        textview_emotion_30 = (TextView)findViewById(R.id.textview_emotion_30);
        textview_emotion_31 = (TextView)findViewById(R.id.textview_emotion_31);
        textview_emotion_32 = (TextView)findViewById(R.id.textview_emotion_32);
        textview_emotion_33 = (TextView)findViewById(R.id.textview_emotion_33);
        textview_emotion_34 = (TextView)findViewById(R.id.textview_emotion_34);
        textview_emotion_35 = (TextView)findViewById(R.id.textview_emotion_35);
        textview_emotion_36 = (TextView)findViewById(R.id.textview_emotion_36);

        EmotionData();
        textview_emotion_1.setText(emotion[0]+"회");
        textview_emotion_2.setText(emotion[1]+"회");
        textview_emotion_3.setText(emotion[2]+"회");
        textview_emotion_4.setText(emotion[3]+"회");
        textview_emotion_5.setText(emotion[4]+"회");
        textview_emotion_6.setText(emotion[5]+"회");
        textview_emotion_7.setText(emotion[6]+"회");
        textview_emotion_8.setText(emotion[7]+"회");
        textview_emotion_9.setText(emotion[8]+"회");
        textview_emotion_10.setText(emotion[9]+"회");
        textview_emotion_11.setText(emotion[10]+"회");
        textview_emotion_12.setText(emotion[11]+"회");

        textview_emotion_13.setText(emotion[12]+"회");
        textview_emotion_14.setText(emotion[13]+"회");
        textview_emotion_15.setText(emotion[14]+"회");
        textview_emotion_16.setText(emotion[15]+"회");
        textview_emotion_17.setText(emotion[16]+"회");
        textview_emotion_18.setText(emotion[17]+"회");
        textview_emotion_19.setText(emotion[18]+"회");
        textview_emotion_20.setText(emotion[19]+"회");
        textview_emotion_21.setText(emotion[20]+"회");
        textview_emotion_22.setText(emotion[21]+"회");
        textview_emotion_23.setText(emotion[22]+"회");
        textview_emotion_24.setText(emotion[23]+"회");

        textview_emotion_25.setText(emotion[24]+"회");
        textview_emotion_26.setText(emotion[25]+"회");
        textview_emotion_27.setText(emotion[26]+"회");
        textview_emotion_28.setText(emotion[27]+"회");
        textview_emotion_29.setText(emotion[28]+"회");
        textview_emotion_30.setText(emotion[29]+"회");
        textview_emotion_31.setText(emotion[30]+"회");
        textview_emotion_32.setText(emotion[31]+"회");
        textview_emotion_33.setText(emotion[32]+"회");
        textview_emotion_34.setText(emotion[33]+"회");
        textview_emotion_35.setText(emotion[34]+"회");
        textview_emotion_36.setText(emotion[35]+"회");

        if(emotion[0]!=0) textview_emotion_1.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[1]!=0) textview_emotion_2.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[2]!=0) textview_emotion_3.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[3]!=0) textview_emotion_4.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[4]!=0) textview_emotion_5.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[5]!=0) textview_emotion_6.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[6]!=0) textview_emotion_7.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[7]!=0) textview_emotion_8.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[8]!=0) textview_emotion_9.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[9]!=0) textview_emotion_10.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[10]!=0) textview_emotion_11.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[11]!=0) textview_emotion_12.setBackgroundColor(Color.parseColor("#dca6c6"));

        if(emotion[12]!=0) textview_emotion_13.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[13]!=0) textview_emotion_14.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[14]!=0) textview_emotion_15.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[15]!=0) textview_emotion_16.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[16]!=0) textview_emotion_17.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[17]!=0) textview_emotion_18.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[18]!=0) textview_emotion_19.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[19]!=0) textview_emotion_20.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[20]!=0) textview_emotion_21.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[21]!=0) textview_emotion_22.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[22]!=0) textview_emotion_23.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[23]!=0) textview_emotion_24.setBackgroundColor(Color.parseColor("#dca6c6"));

        if(emotion[24]!=0) textview_emotion_25.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[25]!=0) textview_emotion_26.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[26]!=0) textview_emotion_27.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[27]!=0) textview_emotion_28.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[28]!=0) textview_emotion_29.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[29]!=0) textview_emotion_30.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[30]!=0) textview_emotion_31.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[31]!=0) textview_emotion_32.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[32]!=0) textview_emotion_33.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[33]!=0) textview_emotion_34.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[34]!=0) textview_emotion_35.setBackgroundColor(Color.parseColor("#dca6c6"));
        if(emotion[35]!=0) textview_emotion_36.setBackgroundColor(Color.parseColor("#dca6c6"));
    }

    private void EmotionData() {
        String errorString = null;
        String mJsonString = null;
        String Game_URL = MainURL + "emotionmission.php";

        String serverURL = "classname=" + myClassname + "&unit=" + ansunit + "&number=" + ansnum;
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
                String emotiondata = item.getString("emotion");
                String id = item.getString("id");
                String answer = item.getString("answer");
                System.out.println("emotion === " + emotiondata);
                if(emotiondata.equals("emotion_1")) emotion[0]++;
                if(emotiondata.equals("emotion_2")) emotion[1]++;
                if(emotiondata.equals("emotion_3")) emotion[2]++;
                if(emotiondata.equals("emotion_4")) emotion[3]++;
                if(emotiondata.equals("emotion_5")) emotion[4]++;
                if(emotiondata.equals("emotion_6")) emotion[5]++;
                if(emotiondata.equals("emotion_7")) emotion[6]++;
                if(emotiondata.equals("emotion_8")) emotion[7]++;
                if(emotiondata.equals("emotion_9")) emotion[8]++;
                if(emotiondata.equals("emotion_10")) emotion[9]++;
                if(emotiondata.equals("emotion_11")) emotion[10]++;
                if(emotiondata.equals("emotion_12")) emotion[11]++;

                if(emotiondata.equals("emotion_13")) emotion[12]++;
                if(emotiondata.equals("emotion_14")) emotion[13]++;
                if(emotiondata.equals("emotion_15")) emotion[14]++;
                if(emotiondata.equals("emotion_16")) emotion[15]++;
                if(emotiondata.equals("emotion_17")) emotion[16]++;
                if(emotiondata.equals("emotion_18")) emotion[17]++;
                if(emotiondata.equals("emotion_19")) emotion[18]++;
                if(emotiondata.equals("emotion_20")) emotion[19]++;
                if(emotiondata.equals("emotion_21")) emotion[20]++;
                if(emotiondata.equals("emotion_22")) emotion[21]++;
                if(emotiondata.equals("emotion_23")) emotion[22]++;
                if(emotiondata.equals("emotion_24")) emotion[23]++;

                if(emotiondata.equals("emotion_25")) emotion[24]++;
                if(emotiondata.equals("emotion_26")) emotion[25]++;
                if(emotiondata.equals("emotion_27")) emotion[26]++;
                if(emotiondata.equals("emotion_28")) emotion[27]++;
                if(emotiondata.equals("emotion_29")) emotion[28]++;
                if(emotiondata.equals("emotion_30")) emotion[29]++;
                if(emotiondata.equals("emotion_31")) emotion[30]++;
                if(emotiondata.equals("emotion_32")) emotion[31]++;
                if(emotiondata.equals("emotion_33")) emotion[32]++;
                if(emotiondata.equals("emotion_34")) emotion[33]++;
                if(emotiondata.equals("emotion_35")) emotion[34]++;
                if(emotiondata.equals("emotion_36")) emotion[35]++;

            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }
}
