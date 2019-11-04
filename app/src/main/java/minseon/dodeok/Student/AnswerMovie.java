package minseon.dodeok.Student;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import minseon.dodeok.R;

import static minseon.dodeok.Student.Game.mission;
import static minseon.dodeok.Student.Game.url;

public class AnswerMovie extends AppCompatActivity {

    ImageButton btn_movie_back2;
    Button btn_movie2;
    TextView textview_movie_problem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_movie);

        btn_movie_back2 = (ImageButton)findViewById(R.id.btn_movie_back2);
        btn_movie2 = (Button)findViewById(R.id.btn_movie2);
        textview_movie_problem2 = (TextView)findViewById(R.id.textview_movie_problem2);

        textview_movie_problem2.setText(mission);
    }

    public void onClickAnswerMovieButton1(View view){
        Intent intent = new Intent(AnswerMovie.this, Game.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickAnswerMovieButton2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
