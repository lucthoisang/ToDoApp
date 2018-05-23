package com.example.sangloveslinh.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btnSignUpNow, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AnhXa();
        XuLy();
    }

    private void XuLy() {
        btnSignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(HomeActivity.this,SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(HomeActivity.this,ToDoActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    private void AnhXa() {
        btnSignUpNow = findViewById(R.id.buttonSignUpNow);
        btnLogin = findViewById(R.id.buttonlogin);
    }
}
