package minseon.dodeok.Teacher;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Teacher.Home2.myClassname;

public class Manage extends AppCompatActivity {

    Button btn_managegame, btn_managestudent, btn_getmaterials, btn_getemotioncards;
    TextView text1, text2;
    String classcode;
    String URL_material = "https://drive.google.com/file/d/1zHY5pbp8Ty-fdcPTIYj-3PjwES30SoKB/view?usp=sharing";
    String URL_emotioncard = "https://drive.google.com/file/d/1bClKQy8M2lY0SDEbUg6vH0hB_IGI0gWW/view?usp=sharing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        btn_managegame = (Button)findViewById(R.id.manage_btn1);
        btn_managestudent = (Button)findViewById(R.id.manage_btn2);
        btn_getmaterials = (Button)findViewById(R.id.manage_btn3);
        btn_getemotioncards = (Button)findViewById(R.id.manage_btn4);
        text1 = (TextView)findViewById(R.id.manage_text1);
        text2 = (TextView)findViewById(R.id.manage_text2);
        text2.setText(myClassname);

        ClassCodeData();
        text1.setText("학급코드 : "+classcode);
    }

    public void onClickManageButton1(View view){
        Intent intent = new Intent(Manage.this, ManageMission.class);
        startActivity(intent);
    }

    public void onClickManageButton2(View view){
        Intent intent = new Intent(Manage.this, ManageStudent.class);
        startActivity(intent);
    }

    public void onClickManageButton3(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_material));
        startActivity(intent);
    }

    public void onClickManageButton4(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_emotioncard));
        startActivity(intent);
    }

    private void ClassCodeData() {
        String errorString = null;
        String mJsonString = null;
        String ClassCode_URL = MainURL + "classcode.php";

        String serverURL = "classname=" + myClassname + "&id=" + userID;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(ClassCode_URL);
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
                classcode = item.getString("classcode");
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }

    }

}
