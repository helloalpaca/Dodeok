package minseon.dodeok.Student;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import static minseon.dodeok.Student.Game.clickedNum;
import static minseon.dodeok.Student.Game.emotions;
import static minseon.dodeok.Student.Game.mission;
import static minseon.dodeok.Student.Game.nowNum;
import static minseon.dodeok.Student.Game.nowUnit;
import static minseon.dodeok.Student.Home.myClassname;
import static minseon.dodeok.Student.Game.answer;

public class Descriptive extends AppCompatActivity {

    ImageButton btn1;
    Button btn2;
    EditText edittext1;
    TextView text1, text2, text3;
    View view1;
    String[] nick;
    boolean flag = false;
    String ttoday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptive);

        btn1 = (ImageButton)findViewById(R.id.descriptive_btn1);
        btn2 = (Button)findViewById(R.id.descriptive_btn2);
        edittext1 = (EditText)findViewById(R.id.descriptive_edittext1);
        text1 = (TextView)findViewById(R.id.descriptive_text1);
        text2 = (TextView)findViewById(R.id.descriptive_text2);
        text3 = (TextView)findViewById(R.id.descriptive_text3);
        view1 = (View)findViewById(R.id.descriptive_view1);

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

    }

    public void onClickDescriptiveButton1(View view){
        Intent intent = new Intent(Descriptive.this, Game.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void onClickDescriptiveButton2(View view){
        if(!flag) {
            answer = edittext1.getText().toString();
            Intent intent = new Intent(Descriptive.this, Emotion2.class);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "답변을 제출했습니다.", Toast.LENGTH_LONG).show();
            flag = true;
        }
        else{
            Toast.makeText(getApplicationContext(), "이미 답변을 제출했습니다.", Toast.LENGTH_LONG).show();
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
            System.out.println("inside try!!!!");
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
