package mossy.littlebits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class Beginner extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences global_preferences;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        global_preferences = getSharedPreferences("global_preferences", MODE_PRIVATE);
        username = global_preferences.getString("username", "");
        preferences = getSharedPreferences(username, MODE_PRIVATE);
        preferences.edit()
                .putString("difficulty", "Beginner")
                .apply();

        RatingBar rating_bar = (RatingBar) findViewById(R.id.rating_bar) ;

        Button butt_back = (Button) findViewById(R.id.back_butt);
        Button butt_1 = (Button) findViewById(R.id.butt_1);
        Button butt_2 = (Button) findViewById(R.id.butt_2);
        Button butt_3 = (Button) findViewById(R.id.butt_3);
        Button butt_4 = (Button) findViewById(R.id.butt_4);
        Button butt_5 = (Button) findViewById(R.id.butt_5);
        Button butt_6 = (Button) findViewById(R.id.butt_6);

        Button[] butt_group =  {butt_1, butt_2, butt_3, butt_4,butt_5,butt_6};
        int max_level = preferences.getInt("Beginner", 1);
        int rating = Math.min(max_level-1, butt_group.length) ;
        rating_bar.setProgress(rating* 2);
        float percent_rating = rating*100/rating_bar.getNumStars();
        preferences.edit().putFloat("beginner_percentage", percent_rating).apply();

        // Disable and Enable Butts
        for (Button button :butt_group){
            button.setEnabled(false);
        }
        for (int i = 0; i < max_level && i < butt_group.length; i++){
             butt_group[i].setEnabled(true);
        }
        butt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Beginner.this,GameMenu.class));
            }
        });
        butt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_to_game(1);
            }
        });
        butt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_to_game(2);
            }
        });
        butt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_to_game(3);
            }
        });
        butt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_to_game(4);
            }
        });
        butt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_to_game(5);
            }
        });
        butt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_to_game(6);
            }
        });

    }

    private void on_to_game(int level){
        preferences.edit()
                .putInt("level", level)
                .apply();
        startActivity(new Intent(Beginner.this,Game.class));
    }

}
