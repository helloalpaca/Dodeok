package minseon.dodeok.Student;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Student.newClass.getClassname;
import static minseon.dodeok.Student.newClass.getNickname;

public class newClassadd extends AppCompatActivity {

    TextView textview_newclassadd;
    Button btn_yes, btn_no;

    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_classadd);

        textview_newclassadd = (TextView)findViewById(R.id.newclassadd_text1);
        btn_yes = (Button)findViewById(R.id.newclassadd_btn1);
        btn_no = (Button)findViewById(R.id.newclassadd_btn2);

        textview_newclassadd.setText("'"+getClassname+"'"+"에\n 가입하시겠습니까?");
    }

    public void onClickNewClassAddButton1(View view){
        GetnewClassaddData task = new GetnewClassaddData();
        task.execute(userID,getClassname,getNickname);

        if(num==0){
            Toast.makeText(getApplicationContext(), getClassname+"에 가입요청을 했습니다.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), getClassname+"에 이미 가입했습니다.", Toast.LENGTH_LONG).show();
        }

        Intent intentHome = new Intent(newClassadd.this, Home.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentHome);
    }

    public void onClickNewClassAddButton2(View view){
        onBackPressed();
    }

    private class GetnewClassaddData extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        String errorString = null;
        String mJsonString;


        public String newClassadd_URL = MainURL + "newclassadd.php";

        @Override
        protected String doInBackground(String... strings) {

            String serverURL = "id="+userID+"&classname="+getClassname+"&nick="+getNickname;
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            try {
                URL url = new URL(newClassadd_URL);
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

            progressDialog = ProgressDialog.show(newClassadd.this, "Please Wait", null, true, true);
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

                num = jsonArray.length();
                System.out.println("num : "+num);

            } catch (JSONException e) {
                Log.d("TAG", "show result : ", e);
            }
        }
    }
}
