package mossy.littlebits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private LoginDAOHelper loginDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText edit_username = (EditText) findViewById(R.id.edit_username);
        final EditText edit_password = (EditText) findViewById(R.id.edit_password);
        CheckBox check_show_password = (CheckBox) findViewById(R.id.check_show_password);
        Button butt_register = (Button) findViewById(R.id.butt_register);
        final ProgressBar password_progress = (ProgressBar) findViewById(R.id.progress_bar);
        loginDAO = new LoginDAOHelper(this);


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

        edit_password.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                int progress = 5;
                String password = edit_password.getText().toString().trim();

                // Password is larger than 5
                if (password.length() >= 5) {
                    progress += 35;
                }
                // Password larger than 10
                if (password.length() >= 10) {
                    progress += 15;
                }
                // If password contains a number
                if (password.matches(getString(R.string.number))) {
                    progress += 15;
                }
                ;
                // If password contains a lowercase
                if (password.matches(getString(R.string.lowerCase))) {
                    progress += 15;
                }
                ;
                // If password contains a uppercase
                if (password.matches(getString(R.string.upperCase))) {
                    progress += 15;
                }
                ;
                int colour;
                if (progress < 45) {

                    password_progress.setProgress(progress);
                }
            }
        });


        butt_register.setOnClickListener(new View.OnClickListener() {
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
                // Password contains username
                if (password.contains(username) || username.contains(password)) {
                    edit_password.requestFocus();
                    show_keyboard();
                    error_message = error_message + "\nPassword contains your username";
                }
                // Password is too big
                if (password.length() > 30) {
                    edit_password.requestFocus();
                    show_keyboard();
                    error_message = error_message + "\nPassword is too big > 30";
                }
                // Password is too small
                if (password.length() < 5) {
                    edit_password.requestFocus();
                    show_keyboard();
                    error_message = error_message + "\nPassword is too small: < 5";
                }
                // Show Error Message
                if (!error_message.toString().isEmpty()) {
                    error_message = "Would you kindly fix the following problems: \n" + error_message;
                    make_toast(error_message);
                }
                else {
                    add_user(username, password);
                    CharSequence message;

                    Cursor cursor = loginDAO.getReadableDatabase()
                            .rawQuery("select * from users", null);
                    while (cursor.moveToNext()) {
                        message = ("id: " + cursor.getString(0)
                                + "username: " + cursor.getString(1)+ "password" + cursor.getString(2));
                        make_toast(message);
                    }
                    cursor.close();
                }
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void show_keyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Register.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void add_user(String username, String password) {
        SQLiteDatabase db = loginDAO.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        db.insert("users", null, contentValues);
    }

    public void make_toast(CharSequence message){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }
    public void make_toast(CharSequence message, int duration){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }


}