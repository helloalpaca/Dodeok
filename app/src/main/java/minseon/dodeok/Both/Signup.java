package minseon.dodeok.Both;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minseon.dodeok.R;

import static minseon.dodeok.Both.Login.MainURL;

public class Signup extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText edittext_id, edittext_pw, edittext_pw2;
    CheckBox check1, check2;
    Button btn1;
    public static String job;
    String newID,newPW;
    final static public String signupURL = MainURL+"signup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        radioGroup = (RadioGroup)findViewById(R.id.signup_radiogroup);
        edittext_id = (EditText)findViewById(R.id.signup_edittext1);
        edittext_pw = (EditText)findViewById(R.id.signup_edittext2);
        edittext_pw2 = (EditText)findViewById(R.id.signup_edittext3);
        check1 = (CheckBox)findViewById(R.id.signup_check1);
        check2 = (CheckBox)findViewById(R.id.signup_check2);
        btn1 = (Button)findViewById(R.id.signup_btn1);

        String text1 = "회원약관";
        String text2 = "개인정보처리방침";
        String text3 = "에 동의합니다.";
        check1.setText("  "+text1+text3);
        check2.setText("  "+text2+text3);

        Linkify.TransformFilter mTransform = new Linkify.TransformFilter(){
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };
        Pattern pattern = Pattern.compile(text1);
        Pattern pattern2 = Pattern.compile(text2);
        Linkify.addLinks(check1, pattern, MainURL+"memberagreement.php",null,mTransform);
        Linkify.addLinks(check2, pattern2, MainURL+"privacypolicy.php",null,mTransform);
    }

    public void onClickSignupButton1(View view){
        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(id);
        String str = rb.getText().toString();
        if(str.equals("선생님")) job = "teacher";
        else job = "student";

        boolean checked = check1.isChecked();
        boolean checked2 = check2.isChecked();
        newID = edittext_id.getText().toString().trim();
        newPW = edittext_pw.getText().toString().trim();
        if(!checked & checked2)
            Toast.makeText(getApplicationContext(), "회원약관에 동의해주십시오.", Toast.LENGTH_LONG).show();
        if(!checked2 & checked)
            Toast.makeText(getApplicationContext(), "개인정보처리방침에 동의해주십시오.", Toast.LENGTH_LONG).show();
        if(!checked && !checked2)
            Toast.makeText(getApplicationContext(), "회원약관및개인정보처리방침에 동의해주십시오.", Toast.LENGTH_LONG).show();
        if(newID.isEmpty() && !newPW.isEmpty())
            Toast.makeText(getApplicationContext(), "아이디를 입력해 주십시오.", Toast.LENGTH_LONG).show();
        if(newPW.isEmpty() && !newID.isEmpty())
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해 주십시오.", Toast.LENGTH_LONG).show();
        if(newPW.isEmpty() && newID.isEmpty())
            Toast.makeText(getApplicationContext(), "아이디/비밀번호를 입력해 주십시오.", Toast.LENGTH_LONG).show();
        if(checked && checked2 && !newID.isEmpty() && !newPW.isEmpty()) {
            InsertSignupData task = new InsertSignupData(this);
            String serverURL = "id="+newID+"&pw="+newPW+"&job="+job;
            task.execute(signupURL, serverURL);
            Intent intent = new Intent(Signup.this, SignupSuccess.class);
            startActivity(intent);
        }
    }
}
