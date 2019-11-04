package minseon.dodeok.Teacher;

import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;
import static minseon.dodeok.Teacher.Home2.myClassname;
import static minseon.dodeok.Teacher.ManageMission.mission_grade;
import static minseon.dodeok.Teacher.ManageMission.mission_unit;


public class ManageMission2 extends AppCompatActivity {

    TextView textview_missiongrade, textview_missionunit;
    Button btn_setbasicmission, btn_changemission, btn_getbasicmission;
    int resID, final_grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_mission2);

        textview_missiongrade = (TextView)findViewById(R.id.mission2_text1);
        textview_missionunit = (TextView)findViewById(R.id.mission2_text2);
        btn_changemission = (Button)findViewById(R.id.mission2_btn1);
        btn_setbasicmission = (Button)findViewById(R.id.mission2_btn2);
        btn_getbasicmission = (Button)findViewById(R.id.mission2_btn3);

        textview_missiongrade.setText(mission_grade);
        textview_missionunit.setText(mission_unit);

        System.out.println("missiongrade === " + mission_grade);
        int grade = 0;
        if(mission_grade.equals("3학년")) grade = 3;
        if(mission_grade.equals("4학년")) grade = 4;
        if(mission_grade.equals("5학년")) grade = 5;
        if(mission_grade.equals("6학년")) grade = 6;
        String csvfile = "@raw/grade" + grade;
        String pack = this.getPackageName();
        resID = getResources().getIdentifier(csvfile, "raw", pack);
        final_grade = grade;

    }

    public void onClickMission2Button1(View view){
//                InputStream inputStream = null;
//                FileOutputStream fileOutputStream = null;
//                byte[] buf = new byte[100];
//
//                try{
//                    File emulated = Environment.getExternalStorageDirectory();
//                    File dir = new File(emulated.getAbsolutePath() + "/dodeok/");
//                    inputStream = new URL(MainURL+"grade3.csv").openStream();
//                    String filename = "grade3.csv";
//                    fileOutputStream = openFileOutput(filename, MODE_APPEND);
//                    int cnt = 0;
//                    while((cnt = inputStream.read(buf))!= -1){
//                        fileOutputStream.write(buf,0,cnt);
//                        fileOutputStream.flush();
//                    }
//                }catch (MalformedURLException e){
//                }catch (IOException e){
//                }finally {
//                    try{
//                        if(inputStream !=null) inputStream.close();
//                        if(fileOutputStream != null)fileOutputStream.close();
//                    } catch (IOException e){
//                    }
//                }
        DeleteData();

        String temp = "";
        String resultContents="";
        try {
            File emulated = Environment.getExternalStorageDirectory();
            File dir = new File(emulated.getAbsolutePath() + "/dodeok/grade"+final_grade+".csv");

            FileInputStream fis = new FileInputStream(dir);
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis));
            CSVReader read = new CSVReader(bufferReader);

            String[] record = null;
            String nowType = null;
            while (true){
                try {
                    if ((record = read.readNext()) != null) {
                        // record[1] = 단원
                        // record[2] = 단원명
                        // record[3] = 번호
                        // record[4] = 유형
                        // record[5] = 미션
                        // record[6] = url
                        // record[7] = 날짜
                        if(record[4].equals("서술")) nowType = "describe";
                        if(record[4].equals("평점")||record[4].equals("읽기자료")) nowType = "rating";
                        if(record[4].equals("영상자료")) nowType = "movie";

                        InsertData(record[1],record[2],record[3],nowType,record[5],record[6],record[7]);
                        System.out.println("now : " + record[1] + record[3] + nowType + record[5] + record[6] + record[7]);
                    }
                    else {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("error occured!!!!");
        }
        System.out.println("results = "+resultContents);
        Toast.makeText(getApplicationContext(), "미션을 변경했습니다.", Toast.LENGTH_LONG).show();
//                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                    System.out.println("Environment : "+Environment.getExternalStorageDirectory());
//                    File emulated = Environment.getExternalStorageDirectory();
//                    File dir = new File(emulated.getAbsolutePath() + "/dodeok");
//                    dir.mkdirs();
//                    File file = new File(dir, "now.txt");
//                    try {
//                        FileWriter fw = new FileWriter(file, false);
//                        fw.write("Hello, World!");
//                        fw.close();
//                        System.out.println("파일 출력함");
//                    } catch(IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else {
//                    System.out.println("External Storage is not ready");
//                }
    }

    public void onClickMission2Button2(View view){
        DeleteData();
        CSVRead(resID);
        NumbersData();
        Toast.makeText(getApplicationContext(), mission_grade+ " 기본 미션을 추가했습니다.", Toast.LENGTH_LONG).show();
    }

    public void onClickMission2Button3(View view){
        DownloadData(final_grade);
        Toast.makeText(ManageMission2.this ,final_grade+"학년 기본 미션을 다운로드 했습니다.",Toast.LENGTH_LONG).show();
    }

    public void CSVRead(int resID){
        InputStreamReader is = new InputStreamReader(getResources().openRawResource(resID));

        BufferedReader reader = new BufferedReader(is);
        CSVReader read = new CSVReader(reader);
        String[] record = null;
        String nowType = null;
        while (true){
            try {
                if ((record = read.readNext()) != null) {
                    // record[1] = 단원
                    // record[3] = 번호
                    // record[4] = 유형
                    // record[5] = 미션
                    // record[6] = url
                    // record[7] = 날짜
                    if(record[4].equals("서술")) nowType = "describe";
                    if(record[4].equals("평점")||record[4].equals("읽기자료")) nowType = "rating";
                    if(record[4].equals("영상자료")) nowType = "movie";

                    InsertData(record[1],record[2],record[3],nowType,record[5],record[6],record[7]);
                    System.out.println("now : " + record[1] + record[3] + nowType + record[5] + record[6] + record[7]);
                }
                else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void InsertData(String _unit,String _unitname, String _number, String _type, String _mission, String _url, String _date) {
        String errorString = null;
        String mJsonString = null;
        String Mission_URL = MainURL + "insertmission.php";

        String serverURL = "classname=" + myClassname + "&unit=" + _unit + "&unitname="+ _unitname+ "&number=" + _number
                + "&mission=" + _mission + "&type=" + _type + "&url=" + _url + "&ready=" + _date;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(Mission_URL);
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

    }

    private void NumbersData() {
        String errorString = null;
        String mJsonString = null;
        String Mission_URL = MainURL + "insertnumbers.php";

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(Mission_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            String serverURL = "classname=" + myClassname;

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

    }

    private void DeleteData() {
        String errorString = null;
        String mJsonString = null;
        String Mission_URL = MainURL + "deletemission.php";

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(Mission_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            String serverURL = "classname=" + myClassname;

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

    }

    void DownloadData(int grade){

        StringBuilder html = new StringBuilder();
        try{
            URL url = new URL("http://3.0.98.134/grade"+grade+".csv");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setConnectTimeout(10000);
            conn.setUseCaches(false);
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                File emulated = Environment.getExternalStorageDirectory();
                File dir = new File(emulated.getAbsolutePath() + "/dodeok");
                dir.mkdirs();
                File file = new File(dir, "grade"+grade+".csv");

                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = conn.getInputStream();

                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;

                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }
                fileOutput.close();

                System.out.println("파일을 다운로드 했습니다!!");
            }
            conn.disconnect();
        }catch(Exception ex){;}
    }


}
