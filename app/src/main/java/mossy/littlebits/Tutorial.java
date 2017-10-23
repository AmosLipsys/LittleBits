package mossy.littlebits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Tutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button if_and_else_tutorial_butt = (Button) findViewById(R.id.if_and_else_butt);
        Button for_loops_tutorial_butt = (Button) findViewById(R.id.if_and_else_butt);
        Button while_loops_tutorial_butt = (Button) findViewById(R.id.if_and_else_butt);

        if_and_else_tutorial_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutorial.this,IfAndElseTutorial.class));
            }
        });
    }

}
