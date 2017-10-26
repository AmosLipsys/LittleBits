package mossy.littlebits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class WinningScreen extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences global_preferences;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        global_preferences = getSharedPreferences("global_preferences", MODE_PRIVATE);
        username = global_preferences.getString("username", "");
        preferences = getSharedPreferences(username, MODE_PRIVATE);
        final String current_difficultly = preferences.getString("difficulty", "");
        final int level = preferences.getInt("level", 1) - 1;

        Button back_to_game_butt = (Button) findViewById(R.id.next_butt);
        Button  back_to_level_butt= (Button) findViewById(R.id.back_butt);
        TextView difficulty_level_text_view = (TextView) findViewById(R.id.difficulty_level_text_view);
        String difficulty_level_text = String.format(Locale.ENGLISH,"%s Level: %d",current_difficultly, level);
        difficulty_level_text_view.setText(difficulty_level_text);
        if(level>=6){
            back_to_game_butt.setText(R.string.Finished);
        }

        back_to_level_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_to_menu(current_difficultly);
            }
        });

        back_to_game_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level<6){
                    startActivity(new Intent(WinningScreen.this,Game.class));
                }
                else {
                    back_to_menu(current_difficultly);
                }
            }
        });

    }

    private void back_to_menu(String current_difficultly){
        if(current_difficultly.matches("Beginner")){
            startActivity(new Intent(WinningScreen.this,Beginner.class));
        }
        else if(current_difficultly.matches("Advanced")){
            startActivity(new Intent(WinningScreen.this,Advanced.class));
        }
        else{
            startActivity(new Intent(WinningScreen.this,Expert.class));
        }
    }





}
