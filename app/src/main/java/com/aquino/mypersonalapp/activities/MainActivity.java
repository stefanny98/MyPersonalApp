package com.aquino.mypersonalapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aquino.mypersonalapp.R;
import com.aquino.mypersonalapp.model.User;
import com.aquino.mypersonalapp.repository.UserRepository;

import org.polaric.colorful.Colorful;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private EditText usernameInput;
    private EditText passwordInput;
    private ProgressBar progressBar;
    private View loginPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Colorful.init(this);

        usernameInput = (EditText) findViewById(R.id.username_input);
        passwordInput = (EditText) findViewById(R.id.password_input);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loginPanel = findViewById(R.id.login_panel);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // username remember
        String username = sharedPreferences.getString("username", null);
        if (username != null) {
            usernameInput.setText(username);
            passwordInput.requestFocus();
        }

        // islogged remember
        if (sharedPreferences.getBoolean("islogged", false)) {
            // Go to Dashboard
            goDashboard();
        }

    }

    public void callLogin(View view) {


        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }


        User user = UserRepository.login(username, password);

        if(user==null){
            passwordInput.setText("");
            passwordInput.requestFocus();
            Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        }else {

            loginPanel.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();

            // Save to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean success = editor
                    .putString("username", user.getUsername())
                    .putString("fullname", user.getFullname())
                    .putBoolean("islogged", true)
                    .putString("theme", "dark")
                    .putString("fonts", "default")
                    .commit();

            // Go to Dashboard
            goDashboard();
        }
    }

    private void goDashboard(){
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

}
