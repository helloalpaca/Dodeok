package minseon.dodeok.Both;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
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

import minseon.dodeok.Student.Home;
import minseon.dodeok.Teacher.Home2;

import static minseon.dodeok.Both.Login.userID;
import static minseon.dodeok.Both.Login.userPW;

public class GetLoginData extends AsyncTask<String,Void,String> {

    Context context;

    ProgressDialog progressDialog;
    String errorString = null;

    GetLoginData(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        String serverURL = strings[1];

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL(strings[0]);
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
        progressDialog = ProgressDialog.show(context, "Please Wait", null, true, true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressDialog.dismiss();

        if (result != null) {
            showResult(result);
        }
    }

    private void showResult(String result) {

        String id = null, pw = null, job = null;

        try {
            JSONObject jsonObject = new JSONObject(result);
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
                Intent intent = new Intent(context, Home.class);
                context.startActivity(intent);
            }

            else if(userID.equals(id) && userPW.equals(pw) && job.equals("teacher")){
                System.out.print("success");
                Intent intent = new Intent(context, Home2.class);
                context.startActivity(intent);
            }
            else{
                Toast.makeText(context,"아이디 또는 비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            Log.d("TAG", "show result : ", e);
        }
    }
}
