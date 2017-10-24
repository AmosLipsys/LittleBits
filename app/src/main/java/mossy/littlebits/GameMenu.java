package mossy.littlebits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class GameMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button tutorial = (Button) findViewById(R.id.if_and_else_butt);
        Button beginner_butt = (Button) findViewById(R.id.beginner_butt);
        Button advanced_butt = (Button) findViewById(R.id.advanced_butt);
        Button expert_butt = (Button) findViewById(R.id.expert_butt);

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
