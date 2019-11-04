package minseon.dodeok.Student;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Student.Game.clickedNum;
import static minseon.dodeok.Student.Game.nowUnit;
import static minseon.dodeok.Student.Home.myClassname;

public class AnswerDescriptive extends AppCompatActivity {

    ImageButton btn_descriptive_back2;
    TextView textview_descriptive_problem2, textview_descriptive_answer;
    Button btn_descriptive_modify;

    String type, mission, url, answer, solved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_descriptive);

        btn_descriptive_back2 = (ImageButton) findViewById(R.id.btn_descriptive_back2);
        textview_descriptive_problem2 = (TextView) findViewById(R.id.textview_descriptive_problem2);
        textview_descriptive_answer = (TextView) findViewById(R.id.textview_descriptive_answer);
        btn_descriptive_modify = (Button) findViewById(R.id.btn_descriptive_modify);

        ReminderData();

        textview_descriptive_problem2.setText(mission);
        textview_descriptive_answer.setText("나의 답변 : "+answer);
    }

    public void onClickAnswerDescriptiveButton1(View view){
        Intent intent = new Intent(AnswerDescriptive.this, Game.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickAnswerDescriptiveButton2(View view){
        if(!solved.equals(null)){
            int compare = Select();
            if( compare < 0) {
                Intent intent = new Intent(AnswerDescriptive.this, Descriptive.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "수정할 수 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "수정할 수 없습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private void ReminderData() {
        String errorString = null;
        String mJsonString = null;
        String Reminder_URL = MainURL + "reminder.php";

        String serverURL = "id="+userID+"&classname=" + myClassname + "&unit=" + nowUnit + "&number=" + clickedNum;
        System.out.println("myClassname : "+myClassname+", unit : "+ nowUnit+ ", number : "+clickedNum);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(Reminder_URL);
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
                type = item.getString("type");
                mission = item.getString("mission");
                url = item.getString("url");
                answer = item.getString("answer");
                solved = item.getString("solved");
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }

    public int Select(){
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date today = null;
        Date _solved = null;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        try {
            today = format.parse(format.format(cal.getTime()));
            System.out.println("[AnswerDescriptive] solved : " + solved);
            _solved = format.parse(solved);
            System.out.println("[AnswerDescriptive] _solved : " + _solved);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int compare = today.compareTo(_solved);
        if(compare >= 0){
            System.out.println("[AnswerDescriptive] today >= _solved");
        } else{
            System.out.println("[AnswerDescriptive] today < _solved");
        }
        return compare;
    }
}
