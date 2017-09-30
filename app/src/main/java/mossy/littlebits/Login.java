package mossy.littlebits;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private LoginDAOHelper loginDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText edit_username = (EditText) findViewById(R.id.edit_username);
        final EditText edit_password = (EditText) findViewById(R.id.edit_password);
        CheckBox check_show_password = (CheckBox) findViewById(R.id.check_show_password);
        Button butt_login = (Button) findViewById(R.id.butt_login);
        loginDAO = new LoginDAOHelper(this);

        // Show Password
        check_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edit_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edit_password.setInputType(129);
                }
            }
        });

        butt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence error_message = "";
                String password = edit_password.getText().toString().trim();
                String username = edit_username.getText().toString().trim();

                // No Username
                if (username.isEmpty()) {
                    edit_username.requestFocus();
                    show_keyboard();
                    error_message = error_message + "\nEnter a Username";
                }
                // No Password
                if (password.isEmpty()) {
                    edit_password.requestFocus();
                    show_keyboard();
                    error_message = error_message + "\nEnter a Password";
                }

                // Correct Password
                Boolean password_is_correct = false;
                Cursor cursor = loginDAO.getReadableDatabase()
                        .rawQuery("SELECT password FROM users WHERE username = ?", new String[] {username});
                while (cursor.moveToNext()) {
                    if(cursor.getString(0).equals(password)){
                        password_is_correct = true;
                    }
                }
                if (!password_is_correct){
                    edit_password.requestFocus();
                    show_keyboard();
                    error_message = error_message + "\nUsername or Password doesn't match our database  ";
                }
                cursor.close();

                // Show Error Message
                if (!error_message.toString().isEmpty()) {
                    error_message = "Would you kindly fix the following problems: \n" + error_message;
                    make_toast(error_message);
                }
                else {
                    CharSequence message;

                    // Print usernames;
                    cursor = loginDAO.getReadableDatabase()
                            .rawQuery("select * from users", null);
                    while (cursor.moveToNext()) {
                        message = ("id: " + cursor.getString(0)
                                + "\nusername: " + cursor.getString(1)+ "\npassword:" + cursor.getString(2));
                        make_toast(message);
                    }
                    cursor.close();
            }
            }
        });



    }

    public void show_keyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Register.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
    public void make_toast(CharSequence message){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

}
