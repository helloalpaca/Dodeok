package minseon.dodeok.Teacher;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Both.Login.userID;

public class MakeClass extends AppCompatActivity {

    EditText edittext_classname;
    Spinner spinner_setgrade;
    Button btn_newclasscode, btn_makeclass;
    TextView textview_newclasscode;
    ImageButton btn_home3;

    String newclassgrade;
    String newclasscode;
    String newclassname;
    boolean flag = false;
    int classgradeint;

    final static public String makeClassURL = MainURL + "makeclass.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_class);

        btn_home3 = (ImageButton)findViewById(R.id.makeclass_btn1);
        edittext_classname = (EditText)findViewById(R.id.makeclass_edittext1);
        spinner_setgrade = (Spinner)findViewById(R.id.makeclass_spinner1);
        btn_newclasscode = (Button)findViewById(R.id.makeclass_btn2);
        textview_newclasscode = (TextView)findViewById(R.id.makeclass_text1);
        btn_makeclass = (Button)findViewById(R.id.makeclass_btn3);

        newclassgrade = spinner_setgrade.getSelectedItem().toString();
        edittext_classname.getText().toString().trim();

    }

    public void onClickMakeClassButton1(View view){
        Intent intentHome = new Intent(MakeClass.this, Home2.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intentHome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentHome);
    }

    public void onClickMakeClassButton2(View view){
        newclasscode = getRandomPassword();
        textview_newclasscode.setText(newclasscode);
        flag = true;
    }

    public void onClickMakeClassButton3(View view){
        if(flag){
            newclassgrade = spinner_setgrade.getSelectedItem().toString();
            newclassname = edittext_classname.getText().toString().trim();
            newclasscode = textview_newclasscode.getText().toString().trim();
            if(newclassgrade.equals("3학년")) classgradeint = 3;
            if(newclassgrade.equals("4학년")) classgradeint = 4;
            if(newclassgrade.equals("5학년")) classgradeint = 5;
            if(newclassgrade.equals("6학년")) classgradeint = 6;

            String serverURL = "id=" + userID + "&classname=" + newclassname + "&grade=" + classgradeint + "&classcode=" + newclasscode;
            //System.out.println(userID+", "+newclassname+", "+newclassgrade+", "+newclasscode);
            MakeClass(serverURL);
            Toast.makeText(getApplicationContext(), "학급이 생성되었습니다.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "학급 코드를 생성해주십시오.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        //this.finish();
        Intent intent= new Intent(MakeClass.this, Home2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }

    public String getRandomPassword(){

        char[] charaters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();
        Random rn = new Random();
        for( int i = 0 ; i < 6 ; i++ ){
            sb.append( charaters[ rn.nextInt( charaters.length ) ] );
        }
        return sb.toString();
    }

    private void MakeClass(String serverURL) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(makeClassURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            System.out.println("HTTPURLCONNETCION 위");

            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(1000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            System.out.println("OUTPUTSTREAM 위");

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(serverURL.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            System.out.println("OUTPUTSTREAM 아래");

            int responseStatusCode = httpURLConnection.getResponseCode();
            //Log.d(TAG,"response - "+responseStatusCode);
            //System.out.println("statuscode : "+responseStatusCode);

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

            System.out.println("line 아래");
            //System.out.println(bufferedReader.read());

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                System.out.println(line);
                //System.out.println("inside while");
            }

            bufferedReader.close();

            //mJsonString = sb.toString().trim();

        } catch (Exception e) {
            Log.d("TAG", "InsertData : Error", e);
            //System.out.println("Inside Error");

        }
    }

}
