package minseon.dodeok.Both;


import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minseon.dodeok.R;
import minseon.dodeok.Student.Home;
import minseon.dodeok.Teacher.Home2;

public class Login extends AppCompatActivity {

    EditText edittext1_id, edittext2_pw;
    CheckBox check1;
    TextView text1;
    Button btn1_login;

    public static String userID, userPW, userJob;
    final static public String MainURL = "http://3.0.98.134/dodeok/"; //AWS SERVER
    //final static public String MainURL = "http://172.30.1.43:8080/dodeok/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edittext1_id = (EditText)findViewById(R.id.login_edittext1);
        edittext2_pw = (EditText)findViewById(R.id.login_edittext2);
        text1 = (TextView)findViewById(R.id.login_text1);
        btn1_login = (Button)findViewById(R.id.login_btn1);

        String str1 = "개인정보 처리방침";
        text1.setText(str1);

        Linkify.TransformFilter mTransform = new Linkify.TransformFilter(){
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };

        Pattern pattern = Pattern.compile(str1);
        Linkify.addLinks(text1, pattern, MainURL+"privacypolicy.php",null,mTransform);

        MediaPlayer mediaPlayer = MediaPlayer.create(Login.this, R.raw.login_music);
        mediaPlayer.start();
    }

    public void onClickLoginButton1(View view){
        userID = edittext1_id.getText().toString().trim();
        userPW = edittext2_pw.getText().toString().trim();
        GetLoginData task = new GetLoginData();
        task.execute(userID, userPW);
    }

    public void onClickLoginButton2(View view){
        Intent intent = new Intent(Login.this, Signup_1.class);
        startActivity(intent);
    }

    private class GetLoginData extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        String errorString = null;
        String mJsonString;
        String id, pw, job;
        public String LoginURL = MainURL + "login.php";

        @Override
        protected String doInBackground(String... strings) {

            String serverURL = "id=" + userID + "&pw=" + userPW;
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            try {
                URL url = new URL(LoginURL);
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
                }
                bufferedReader.close();
                return sb.toString().trim();
            } catch (Exception e) {
                Log.d("TAG", "InsertData : Error", e);
                errorString = e.toString();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Login.this, "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if (result == null) {
                //mTextViewResult.setText(errorString);
            } else {
                mJsonString = result;
                showResult();
            }
        }

        private void showResult() {

            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    id = item.getString("id");
                    pw = item.getString("pw");
                    job = item.getString("job");
                    //System.out.println("id = " + id + ", pw = " + pw);
                }

                if (userID.equals(id) && userPW.equals(pw) && job.equals("student")) {
                    System.out.print("success");
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                }

                else if(userID.equals(id) && userPW.equals(pw) && job.equals("teacher")){
                    System.out.print("success");
                    Intent intent = new Intent(Login.this, Home2.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this,"아이디 또는 비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Log.d("TAG", "show result : ", e);
            }
        }

    }

}
