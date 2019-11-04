package minseon.dodeok.Teacher;

import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Teacher.AnswerStudent.StudentID;
import static minseon.dodeok.Teacher.AnswerStudent.StudentNick;
import static minseon.dodeok.Teacher.GetNumber.ansnum;
import static minseon.dodeok.Teacher.GetNumber.ansunit;
import static minseon.dodeok.Teacher.Home2.myClassname;

public class CheckAnswer2 extends AppCompatActivity {

    int length = 0;
    String[] nick;
    Integer[] unit;
    Integer[] number;
    String[] mission;
    String[] answer;

    Button btn_downloadanswers2;
    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answer2);

        listView2 =(ListView)findViewById(R.id.listview2);
        btn_downloadanswers2 = (Button)findViewById(R.id.check2_btn1);

        ListViewAdapter adapter=new ListViewAdapter();
        listView2.setAdapter(adapter);

        GetAnswer();

        if(length > 0){
            for(int i=0; i<length; i++){
                adapter.addListItem(nick[i],unit[i],number[i],mission[i],answer[i]);
            }
        }
    }

    public void onClickCheck2Button1(View view){
        File emulated = Environment.getExternalStorageDirectory();
        File dir = new File(emulated.getAbsolutePath() + "/dodeok");
        dir.mkdirs();
        File file = new File(dir, "Unit"+ansunit+"Number"+ansnum+ "_answers.csv");

        try {
            FileWriter fw = new FileWriter(file, false);
            if(length==0)
                Toast.makeText(CheckAnswer2.this ,"답변이 없습니다.",Toast.LENGTH_LONG).show();
            else{
                for(int i=0;i<length;++i) {
                    fw.write(nick[i] + "," + unit[i] + "," + number[i]+","+mission[i]+","+answer[i]+"\n");
                }
                Toast.makeText(CheckAnswer2.this ,"답변이 저장되었습니다.",Toast.LENGTH_LONG).show();
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void GetAnswer() {
        String errorString = null;
        String mJsonString = null;
        String GetStudent_URL = MainURL + "getanswer2.php/";

        String serverURL = "unit=" + ansunit + "&number=" + ansnum + "&classname=" + myClassname;

        System.out.println( "unit=" + ansunit + "&number=" + ansnum +", classname : "+myClassname);
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
                length = jsonArray.length();
                nick = new String[jsonArray.length()];
                unit = new Integer[jsonArray.length()];
                number = new Integer[jsonArray.length()];
                mission = new String[jsonArray.length()];
                answer = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    nick[i] = item.getString("nick");
                    unit[i] = item.getInt("unit");
                    number[i] = item.getInt("number");
                    mission[i] = item.getString("mission");
                    answer[i] = item.getString("answer");

                    System.out.println("nick : " + nick);
                }
            }
            System.out.println("after try!!!!");

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }
}
