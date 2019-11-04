package minseon.dodeok.Student;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
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

public class Home extends AppCompatActivity {

    public String classname[] = new String[6];
    public String status[] = new String[6];

    Button btn_1,btn_2, btn_3, btn_4, btn_5, btn_6, btn_join;
    final static public String Class_URL = MainURL+"class.php";

    static public String myClassname;
    public int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        btn_1 = (Button)findViewById(R.id.home_btn1);
        btn_2 = (Button)findViewById(R.id.home_btn2);
        btn_3 = (Button)findViewById(R.id.home_btn3);
        btn_4 = (Button)findViewById(R.id.home_btn4);
        btn_5 = (Button)findViewById(R.id.home_btn5);
        btn_6 = (Button)findViewById(R.id.home_btn6);
        btn_join = (Button)findViewById(R.id.home_btn_join);

        btn_1.setVisibility(View.GONE);
        btn_2.setVisibility(View.GONE);
        btn_3.setVisibility(View.GONE);
        btn_4.setVisibility(View.GONE);
        btn_5.setVisibility(View.GONE);
        btn_6.setVisibility(View.GONE);

        GetClassData task = new GetClassData();
        task.execute(userID);

        for(int i=0;i<6;++i){
            System.out.println(i+" : "+classname[i]+","+status[i]);
        }
    }

    public void onClickHomeButton1(View view) {
        System.out.println("btn_1 내부의 classname[0] 값 : "+classname[0]);
        if(classname[0]!=null&&status[0].equals("가입완료")) {
            myClassname = classname[0];
            Intent intent = new Intent(Home.this, Unit.class);
            startActivity(intent);
        }
        if(classname[0]!=null&&status[0].equals("승인대기")){
            Toast.makeText(getApplicationContext(), "승인 대기중입니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickHomeButton2(View view) {
        if(classname[1]!=null&&status[1].equals("가입완료")) {
            myClassname = classname[1];
            Intent intent = new Intent(Home.this, Unit.class);
            startActivity(intent);
        }
        if(classname[1]!=null&&status[1].equals("승인대기")){
            Toast.makeText(getApplicationContext(), "승인 대기중입니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickHomeButton3(View view) {
        if(classname[2]!=null&&status[2].equals("가입완료")) {
            myClassname = classname[2];
            Intent intent = new Intent(Home.this, Unit.class);
            startActivity(intent);
        }
        if(classname[2]!=null&&status[2].equals("승인대기")){
            Toast.makeText(getApplicationContext(), "승인 대기중입니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickHomeButton4(View view) {
        if(classname[3]!=null&&status[3].equals("가입완료")) {
            myClassname = classname[3];
            Intent intent = new Intent(Home.this, Unit.class);
            startActivity(intent);
        }
        if(classname[3]!=null&&status[3].equals("승인대기")){
            Toast.makeText(getApplicationContext(), "승인 대기중입니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickHomeButton5(View view) {
        if(classname[4]!=null&&status[4].equals("가입완료")) {
            myClassname = classname[4];
            Intent intent = new Intent(Home.this, Unit.class);
            startActivity(intent);
        }
        if(classname[4]!=null&&status[4].equals("승인대기")){
            Toast.makeText(getApplicationContext(), "승인 대기중입니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickHomeButton6(View view) {
        if(classname[5]!=null&&status[5].equals("가입완료")) {
            myClassname = classname[5];
            Intent intent = new Intent(Home.this, Unit.class);
            startActivity(intent);
        }
        if(classname[5]!=null&&status[5].equals("승인대기")){
            Toast.makeText(getApplicationContext(), "승인 대기중입니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickHomeButtonJoin(View view) {
        if(k<6) {
            Intent intent = new Intent(Home.this, newClass.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "학급 개수를 초과했습니다.", Toast.LENGTH_LONG).show();
        }
    }

    public class GetClassData extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        String errorString = null;
        String mJsonString;


        @Override
        protected String doInBackground(String... strings) {

            String serverURL = "id="+userID;
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            try {
                URL url = new URL(Class_URL);
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

            progressDialog = ProgressDialog.show(Home.this, "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if (result == null) {
                //mTextViewResult.setText(errorString);
            }
            else {
                mJsonString = result;
                showResult();
                for(int i=0;i<6;++i){
                    System.out.println("after showResult "+i+" : "+classname[i]+","+status[i]);
                }
            }
        }

        public void showResult() {

            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String _classname = item.getString("classname");
                    String _status = item.getString("status");
                    System.out.println(_classname + ", " + _status);
                    //System.out.println("id = " + id + ", pw = " + pw);
                    classname[k] = _classname;
                    status[k] = _status;
                    ++k;
                }

                System.out.println("k : "+k);
                if(k>=1) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setBackgroundResource(R.drawable.img_class1);
                    //btn_1.setLayoutParams(new RelativeLayout.LayoutParams(1920,450));
                }
                if(k>=2) {
                    btn_2.setBackgroundResource(R.drawable.img_class2);
                    btn_2.setVisibility(View.VISIBLE);
                    //btn_2.setLayoutParams(new RelativeLayout.LayoutParams(1920,450));
                }
                if(k>=3) {
                    btn_3.setBackgroundResource(R.drawable.img_class1);
                    btn_3.setVisibility(View.VISIBLE);
                    //btn_3.setLayoutParams(new RelativeLayout.LayoutParams(1920,450));
                }
                if(k>=4) {
                    btn_4.setBackgroundResource(R.drawable.img_class2);
                    btn_4.setVisibility(View.VISIBLE);
                    //btn_4.setLayoutParams(new RelativeLayout.LayoutParams(1920,450));
                }
                if(k>=5) {
                    btn_5.setBackgroundResource(R.drawable.img_class1);
                    btn_5.setVisibility(View.VISIBLE);
                    //btn_5.setLayoutParams(new RelativeLayout.LayoutParams(1920,450));
                }
                if(k>=6) {
                    btn_6.setBackgroundResource(R.drawable.img_class2);
                    btn_6.setVisibility(View.VISIBLE);
                    //btn_6.setLayoutParams(new RelativeLayout.LayoutParams(1920,450));
                }
                btn_1.setText(classname[0]);
                btn_2.setText(classname[1]);
                btn_3.setText(classname[2]);
                btn_4.setText(classname[3]);
                btn_5.setText(classname[4]);
                btn_6.setText(classname[5]);

            } catch (JSONException e) {
                Log.d("TAG", "show result : ", e);
            }
        }

    }
}
