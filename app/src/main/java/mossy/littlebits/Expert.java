package mossy.littlebits;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Expert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button if_and_else_butt = (Button) findViewById(R.id.if_and_else_butt);
        Button for_loop_butt = (Button) findViewById(R.id.for_loops_butt);
        Button while_loop_butt = (Button) findViewById(R.id.while_loops_butt);

        if_and_else_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Expert.this,ExpertIfAndElse.class));
            }
        });
        for_loop_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Expert.this,ExpertForLoops.class));
            }
        });
        while_loop_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Expert.this,ExpertWhileLoops.class));
            }
        });
    }

}
