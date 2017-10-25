package mossy.littlebits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences global_preferences;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        global_preferences = getSharedPreferences("global_preferences", MODE_PRIVATE);
        username = global_preferences.getString("username", "");
        preferences = getSharedPreferences(username, MODE_PRIVATE);
        String current_difficultly = preferences.getString("difficulty", "");
        int level = preferences.getInt("level", 1);

        TextView difficulty_text_view = (TextView) findViewById(R.id.difficulty_text_view);
        TextView level_text_view = (TextView) findViewById(R.id.level_text_view);

        Button button_1 = (Button) findViewById(R.id.a_butt);
        Button button_2 = (Button) findViewById(R.id.b_butt);
        Button button_3 = (Button) findViewById(R.id.c_butt);
        Button button_4 = (Button) findViewById(R.id.d_butt);
        Button back_butt = (Button) findViewById(R.id.back_butt);

        difficulty_text_view.setText(current_difficultly);
        level_text_view.setText("Level: "+ Integer.toString(level));

        ImageView game_image_view = (ImageView) findViewById(R.id.game_image);

        final int correct_button;

        if (current_difficultly.matches("Beginner")) {
            switch (level) {
                case 1:
                    game_image_view.setImageResource(R.drawable.beginner_level_1);
                    correct_button = 3;
                    break;
                case 2:
                    game_image_view.setImageResource(R.drawable.beginner_level_2);
                    correct_button = 4;
                    break;
                case 3:
                    game_image_view.setImageResource(R.drawable.beginner_level_3);
                    correct_button = 2;
                    break;
                case 4:
                    game_image_view.setImageResource(R.drawable.beginner_level_4);
                    correct_button = 1;
                    break;
                case 5:
                    game_image_view.setImageResource(R.drawable.beginner_level_5);
                    correct_button = 2;
                    break;
                case 6:
                    game_image_view.setImageResource(R.drawable.beginner_level_6);
                    correct_button = 2;
                    break;
                default:
                    game_image_view.setImageResource(R.drawable.beginner_level_1);
                    correct_button = 3;
            }
        }
        else if (current_difficultly.matches("Advanced")) {
            switch (level) {
                case 1:
                    game_image_view.setImageResource(R.drawable.advanced_level_1);
                    correct_button = 4;
                    break;
                case 2:
                    game_image_view.setImageResource(R.drawable.advanced_level_2);
                    correct_button = 2;
                    break;
                case 3:
                    game_image_view.setImageResource(R.drawable.advanced_level_3);
                    correct_button = 3;
                    break;
                case 4:
                    game_image_view.setImageResource(R.drawable.advanced_level_4);
                    correct_button = 1;
                    break;
                case 5:
                    game_image_view.setImageResource(R.drawable.advanced_level_5);
                    correct_button = 2;
                    break;
                case 6:
                    game_image_view.setImageResource(R.drawable.advanced_level_6);
                    correct_button = 1;
                    break;
                default:
                    game_image_view.setImageResource(R.drawable.advanced_level_1);
                    correct_button = 4;
            }
        }
        else {
            switch (level) {
                case 1:
                    game_image_view.setImageResource(R.drawable.expert_level_1);
                    correct_button = 4;
                    break;
                case 2:
                    game_image_view.setImageResource(R.drawable.expert_level_2);
                    correct_button = 4;
                    break;
                case 3:
                    game_image_view.setImageResource(R.drawable.expert_level_3);
                    correct_button = 3;
                    break;
                case 4:
                    game_image_view.setImageResource(R.drawable.expert_level_4);
                    correct_button = 3;
                    break;
                case 5:
                    game_image_view.setImageResource(R.drawable.expert_level_5);
                    correct_button = 1;
                    break;
                case 6:
                    game_image_view.setImageResource(R.drawable.expert_level_6);
                    correct_button = 2;
                    break;
                default:
                    game_image_view.setImageResource(R.drawable.expert_level_1);
                    correct_button = 4;
            }
        }

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct_button == 1){
                    correct_answer();
                }
                else{
                    incorrect_answer();
                }
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct_button == 2){
                    correct_answer();
                }
                else{
                    incorrect_answer();
                }
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct_button == 3){
                    correct_answer();
                }
                else{
                    incorrect_answer();
                }
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct_button == 4){
                    correct_answer();
                }
                else{
                    incorrect_answer();
                }
            }
        });

        back_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences(username, MODE_PRIVATE);
                String current_difficultly = preferences.getString("difficulty", "");
                if (current_difficultly.matches("Beginner")){
                    startActivity(new Intent(Game.this,Beginner.class));
                }
                else if (current_difficultly.matches("Advanced")){
                    startActivity(new Intent(Game.this,Advanced.class));
                }
                else {
                    startActivity(new Intent(Game.this,Expert.class));
                }

            }
        });
    }

    private void correct_answer(){
        preferences = getSharedPreferences(username, MODE_PRIVATE);
        int level = preferences.getInt("level", 1);
        String current_difficultly = preferences.getString("difficulty", "");
        int max_level = preferences.getInt(current_difficultly, 1);
        level += 1;
        if (level > max_level){
            max_level = level;
        }

        preferences.edit()
                .putInt("level",level)
                .putInt(current_difficultly, max_level)
                .apply();
        String toast_message = "Now up to level " + Integer.toString(preferences.getInt(current_difficultly, level)) + "at difficultly level " + current_difficultly;
        Toast.makeText(this, toast_message , Toast.LENGTH_LONG).show();

        startActivity(new Intent(Game.this,Game.class));
    }
    private void incorrect_answer(){
        Toast.makeText(this, "Incorrect :(", Toast.LENGTH_LONG).show();
        startActivity(new Intent(Game.this,Game.class));
    }

}
