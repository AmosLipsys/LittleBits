package mossy.littlebits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences global_preferences;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        global_preferences = getSharedPreferences("global_preferences", MODE_PRIVATE);
        username = global_preferences.getString("username", "");
        preferences = getSharedPreferences(username, MODE_PRIVATE);
        int watched_if_and_else = preferences.getInt("watched_if_and_else", 0);
        int watched_for_loop = preferences.getInt("watched_for_loop", 0);
        int watched_while_loop =preferences.getInt("watched_while_loop", 0);

        RatingBar rating_bar = (RatingBar) findViewById(R.id.rating_bar);
        Button if_and_else_tutorial_butt = (Button) findViewById(R.id.if_and_else_butt);
        Button for_loops_tutorial_butt = (Button) findViewById(R.id.for_loops_butt);
        Button while_loops_tutorial_butt = (Button) findViewById(R.id.while_loops_butt);
        Button back_to_game_hub_butt = (Button) findViewById(R.id.back_to_game_hub_butt);
        TextView if_else_text_view = (TextView) findViewById(R.id.if_else_text_view);
        TextView for_loop_text_view = (TextView) findViewById(R.id.for_loop_text_view);
        TextView while_loop_text_view = (TextView) findViewById(R.id.while_loop_text_view);

        if (watched_if_and_else == 0){
            if_else_text_view.setText(R.string.unwatched);
        }
        else if_else_text_view.setText(R.string.watched);
        if (watched_for_loop == 0){
            for_loop_text_view.setText(R.string.unwatched);
        }
        else for_loop_text_view.setText(R.string.watched);
        if (watched_while_loop == 0){
            while_loop_text_view.setText(R.string.unwatched);
        }
        else while_loop_text_view.setText(R.string.watched);

        int rating = (watched_if_and_else+ watched_for_loop+ watched_while_loop);
        rating_bar.setProgress(rating*2);
        float percent_rating = Math.round(rating/rating_bar.getNumStars() *100);
        preferences.edit().putFloat("tutorial_percentage", percent_rating).apply();


        back_to_game_hub_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutorial.this,GameMenu.class));
            }
        });

        if_and_else_tutorial_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutorial.this,IfAndElseTutorial.class));
            }
        });

        for_loops_tutorial_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutorial.this,ForLoopTutorial.class));
            }
        });

        while_loops_tutorial_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutorial.this,WhileLoopTutorial.class));
            }
        });
    }

}
