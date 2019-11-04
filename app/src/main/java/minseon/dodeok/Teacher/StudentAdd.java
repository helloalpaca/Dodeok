package minseon.dodeok.Teacher;


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
import static minseon.dodeok.Teacher.Home2.myClassname;
import static minseon.dodeok.Teacher.JoinStudent.StudentName;
import static minseon.dodeok.Teacher.JoinStudent.StudentStatus;

public class StudentAdd extends AppCompatActivity {

    Button btn_yes2, btn_no2;
    TextView textview_studentadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);

        btn_yes2 = (Button)findViewById(R.id.studentadd_btn1);
        btn_no2 = (Button)findViewById(R.id.studentadd_btn2);
        textview_studentadd = (TextView)findViewById(R.id.studentadd_text1);
        textview_studentadd.setText("'"+StudentName+"'"+"학생을\n 승인하시겠습니까?");

    }

    public void onClickStudentAddButton1(View view){
        GetStudentAdd task = new GetStudentAdd();
        task.execute();

        Intent intentHome = new Intent(StudentAdd.this, ManageStudent.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intentHome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentHome);
    }

    public void onClickStudentAddButton2(View view){
        onBackPressed();
    }

    private class GetStudentAdd extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        String errorString = null;
        String mJsonString;


        public String StudentAdd_URL = MainURL + "studentadd.php/";

        @Override
        protected String doInBackground(String... strings) {

            String serverURL = "classname="+myClassname+"&nick="+StudentName;
            System.out.println("classname : "+myClassname+ ", nick : "+StudentName);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            try {
                URL url = new URL(StudentAdd_URL);
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

            progressDialog = ProgressDialog.show(StudentAdd.this, "Please Wait", null, true, true);
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

            } catch (JSONException e) {
                Log.d("TAG", "show result : ", e);
            }
        }
    }
}
