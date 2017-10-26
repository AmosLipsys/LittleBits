package mossy.littlebits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Rating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Locale;

public class GameMenu extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences global_preferences;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        global_preferences = getSharedPreferences("global_preferences", MODE_PRIVATE);
        username = global_preferences.getString("username", "");
        preferences = getSharedPreferences(username, MODE_PRIVATE);

        Button tutorial = (Button) findViewById(R.id.if_and_else_butt);
        Button beginner_butt = (Button) findViewById(R.id.beginner_butt);
        Button advanced_butt = (Button) findViewById(R.id.advanced_butt);
        Button expert_butt = (Button) findViewById(R.id.expert_butt);
        TextView tutorial_text_view = (TextView) findViewById(R.id.tutorial_text_view);
        TextView beginner_text_view = (TextView) findViewById(R.id.beginner_text_view);
        TextView advanced_text_view = (TextView) findViewById(R.id.advanced_text_view);
        TextView expert_text_view = (TextView) findViewById(R.id.expert_text_view);
        RatingBar rating_bar = (RatingBar) findViewById(R.id.rating_bar);

        float tutorial_percentage = preferences.getFloat("tutorial_percentage", 0);
        float beginner_percentage = preferences.getFloat("beginner_percentage", 0);
        float advanced_percentage = preferences.getFloat("advanced_percentage", 0);
        float expert_percentage = preferences.getFloat("expert_percentage", 0);
        int rating = Math.round(tutorial_percentage + beginner_percentage + advanced_percentage + expert_percentage) / 100;
        rating_bar.setProgress(rating*2);

        tutorial_text_view.setText(String.format(Locale.ENGLISH,"%.0f%%",tutorial_percentage));
        beginner_text_view.setText(String.format(Locale.ENGLISH,"%.0f%%",beginner_percentage));
        advanced_text_view.setText(String.format(Locale.ENGLISH,"%.0f%%",advanced_percentage));
        expert_text_view.setText(String.format(Locale.ENGLISH,"%.0f%%",expert_percentage));


        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameMenu.this,Tutorial.class));
            }
        });
        beginner_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameMenu.this,Beginner.class));
            }
        });
        advanced_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameMenu.this,Advanced.class));
            }
        });
        expert_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameMenu.this,Expert.class));
            }
        });

    }

}
