package minseon.dodeok.Both;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minseon.dodeok.R;

public class Login extends AppCompatActivity {

    EditText edittext1_id, edittext2_pw;
    CheckBox check1;
    TextView text1;
    Button btn1_login;

    public static String userID, userPW, userJob;
    // final static public String MainURL = "http://3.0.98.134/dodeok/"; //AWS SERVER
    final static public String MainURL = "http://172.30.1.36:8080/dodeok/";
    public String loginURL = MainURL + "login.php";

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

        GetLoginData task = new GetLoginData(this);
        String serverURL = "id=" + userID + "&pw=" + userPW;
        task.execute(loginURL, serverURL);
    }

    public void onClickLoginButton2(View view){
        Intent intent = new Intent(Login.this, Signup.class);
        startActivity(intent);
    }
}
