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

        Button butt_1 = (Button) findViewById(R.id.butt_1);
        Button butt_2 = (Button) findViewById(R.id.butt_2);
        Button butt_3 = (Button) findViewById(R.id.butt_3);
        Button butt_4 = (Button) findViewById(R.id.butt_4);
        Button butt_5 = (Button) findViewById(R.id.butt_5);
        Button butt_6 = (Button) findViewById(R.id.butt_6);

        Button[] butt_group =  {butt_1, butt_2, butt_3, butt_4,butt_5,butt_6};
        int max_level = preferences.getInt("Beginner", 1);
        int rating = Math.min(max_level, butt_group.length) * 2;
        rating_bar.setProgress(rating);

        // Disable and Enable Butts
        for (Button button :butt_group){
            button.setEnabled(false);
        }
        for (int i = 0; i < max_level && i < butt_group.length; i++){
             butt_group[i].setEnabled(true);
        }

        butt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit()
                        .putInt("level", 1)
                        .apply();
                startActivity(new Intent(Beginner.this,Game.class));
            }
        });
        butt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit()
                        .putInt("level", 2)
                        .apply();
                startActivity(new Intent(Beginner.this,Game.class));
            }
        });
        butt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit()
                        .putInt("level", 3)
                        .apply();
                startActivity(new Intent(Beginner.this,Game.class));
            }
        });
        butt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit()
                        .putInt("level", 4)
                        .apply();
                startActivity(new Intent(Beginner.this,Game.class));
            }
        });
        butt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit()
                        .putInt("level", 5)
                        .apply();
                startActivity(new Intent(Beginner.this,Game.class));
            }
        });
        butt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit()
                        .putInt("level", 6)
                        .apply();
                startActivity(new Intent(Beginner.this,Game.class));
            }
        });

    }

}
