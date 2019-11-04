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
import android.widget.EditText;
import android.widget.ImageButton;

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

public class newClass extends AppCompatActivity {

    ImageButton btn_home2;
    EditText edittext_classcode, edittext_nickname;
    Button btn_newclass;

    String classcode, nickname;
    public static String getClasscode, getClassname, getNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);

        btn_home2 = (ImageButton)findViewById(R.id.btn_home2);
        edittext_classcode = (EditText)findViewById(R.id.edittext_classcode);
        edittext_nickname = (EditText)findViewById(R.id.edittext_nickname);
        btn_newclass = (Button)findViewById(R.id.btn_newclass);

    }
    public void onClickNewClassButton1(View view){
        Intent intentHome = new Intent(newClass.this, Home.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentHome);
    }

    public void onClickNewClassButton2(View view){
        classcode = edittext_classcode.getText().toString().trim();
        getNickname = edittext_nickname.getText().toString().trim();
        GetnewClassData task = new GetnewClassData();
        task.execute(classcode,nickname);
    }

    private class GetnewClassData extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        String errorString = null;
        String mJsonString;


        public String newClass_URL = MainURL + "newclass.php";

        @Override
        protected String doInBackground(String... strings) {

            String serverURL = "classcode=" + classcode;
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            try {
                URL url = new URL(newClass_URL);
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

            progressDialog = ProgressDialog.show(newClass.this, "Please Wait", null, true, true);
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
                System.out.println(classcode);
            }
        }

        private void showResult() {

            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    getClasscode = item.getString("classcode");
                    getClassname = item.getString("classname");
                    System.out.println("classcode = " + getClasscode + ", getClassname = " + getClassname);
                }

                if (getClasscode!=null&&getClasscode.equals(classcode)) {
                    System.out.print("success");
                    Intent intent = new Intent(newClass.this, newClassadd.class);
                    startActivity(intent);
                    //Toast.makeText(getApplicationContext(), getClassname+"에 가입요청을 했습니다.", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Log.d("TAG", "show result : ", e);
            }
        }

    }
}
