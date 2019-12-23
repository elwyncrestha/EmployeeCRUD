package com.elvin.esoftwaricaalias.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.elvin.esoftwaricaalias.R;
import com.elvin.esoftwaricaalias.core.AppConstant;

public class LoginActivity extends AppCompatActivity {

    private EditText etLoginUsername, etLoginPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // bind view controls
        this.etLoginUsername = findViewById(R.id.etLoginUsername);
        this.etLoginPassword = findViewById(R.id.etLoginPassword);
        this.btnLogin = findViewById(R.id.btnLogin);

        // configure event listeners
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etLoginUsername.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    etLoginUsername.setError("Field username is required!!!");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    etLoginPassword.setError("Field password is required!!!");
                    return;
                }

                if (!(username.equals(AppConstant.LOGIN_USERNAME) && password.equals(AppConstant.LOGIN_PASSWORD))) {
                    Toast.makeText(LoginActivity.this, "Invalid username or password!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
