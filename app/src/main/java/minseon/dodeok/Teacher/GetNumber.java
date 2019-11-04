package minseon.dodeok.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import minseon.dodeok.R;

public class GetNumber extends AppCompatActivity {

    public static String answerunit, answernumber;
    public static int ansunit, ansnum;

    Spinner spinner_answerunit, spinner_answernumber;
    Button btn_select2, btn_getemotion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_number);

        spinner_answerunit = (Spinner)findViewById(R.id.getnumber_spinner1);
        spinner_answernumber = (Spinner)findViewById(R.id.getnumber_spinner2);
        btn_select2 = (Button)findViewById(R.id.getnumber_btn1);
        btn_getemotion2 = (Button)findViewById(R.id.getnumber_btn2);

    }

    public void onClickGetNumberButton1(View view){
        answerunit = spinner_answerunit.getSelectedItem().toString();
        answernumber = spinner_answernumber.getSelectedItem().toString();
        if(answerunit.equals("1단원")) ansunit = 1;
        if(answerunit.equals("2단원")) ansunit = 2;
        if(answerunit.equals("3단원")) ansunit = 3;
        if(answerunit.equals("4단원")) ansunit = 4;
        if(answerunit.equals("5단원")) ansunit = 5;
        if(answerunit.equals("6단원")) ansunit = 6;
        if(answernumber.equals("1번문제")) ansnum = 1;
        if(answernumber.equals("2번문제")) ansnum = 2;
        if(answernumber.equals("3번문제")) ansnum = 3;
        if(answernumber.equals("4번문제")) ansnum = 4;
        if(answernumber.equals("5번문제")) ansnum = 5;
        if(answernumber.equals("6번문제")) ansnum = 6;
        if(answernumber.equals("7번문제")) ansnum = 7;
        if(answernumber.equals("8번문제")) ansnum = 8;
        if(answernumber.equals("9번문제")) ansnum = 9;
        if(answernumber.equals("10번문제")) ansnum = 10;
        System.out.println("ansunit"+ansunit+", ansnum"+ansnum);
        Intent intent = new Intent(GetNumber.this, CheckAnswer2.class);
        startActivity(intent);
    }

    public void onClickGetNumberButton2(View view){
        answerunit = spinner_answerunit.getSelectedItem().toString();
        answernumber = spinner_answernumber.getSelectedItem().toString();
        if(answerunit.equals("1단원")) ansunit = 1;
        if(answerunit.equals("2단원")) ansunit = 2;
        if(answerunit.equals("3단원")) ansunit = 3;
        if(answerunit.equals("4단원")) ansunit = 4;
        if(answerunit.equals("5단원")) ansunit = 5;
        if(answerunit.equals("6단원")) ansunit = 6;
        if(answernumber.equals("1번문제")) ansnum = 1;
        if(answernumber.equals("2번문제")) ansnum = 2;
        if(answernumber.equals("3번문제")) ansnum = 3;
        if(answernumber.equals("4번문제")) ansnum = 4;
        if(answernumber.equals("5번문제")) ansnum = 5;
        if(answernumber.equals("6번문제")) ansnum = 6;
        if(answernumber.equals("7번문제")) ansnum = 7;
        if(answernumber.equals("8번문제")) ansnum = 8;
        if(answernumber.equals("9번문제")) ansnum = 9;
        if(answernumber.equals("10번문제")) ansnum = 10;
        System.out.println("ansunit"+ansunit+", ansnum"+ansnum);
        Intent intent = new Intent(GetNumber.this, EmotionNumber.class);
        startActivity(intent);
    }
}
