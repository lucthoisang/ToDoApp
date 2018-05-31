package com.example.sangloveslinh.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sangloveslinh.todoapp.database.AppToDo;
import com.example.sangloveslinh.todoapp.database.DaoSession;
import com.example.sangloveslinh.todoapp.database.User;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Button btnSignUpNow, btnLogin;
    EditText edtemail, edtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AnhXa();
        XuLy();
    }

    private void XuLy() {
        final DaoSession database = ((AppToDo) getApplication()).getDaoSession();
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
                boolean match = true;
                List<User> userList = database.getUserDao().loadAll();
                for(User user:userList){
                    if(user.getEmail().equals(edtemail.getText().toString()) && user.getPassword().equals(edtpassword.getText().toString())){
                        match = false;
                        Intent loginIntent = new Intent(HomeActivity.this,ToDoActivity.class);
                        startActivity(loginIntent);
                        match = false;
                    }
                }
                if(match == true)
                    Toast.makeText(HomeActivity.this, "Tài khoản chưa đăng ký", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void AnhXa() {
        btnSignUpNow = findViewById(R.id.buttonSignUpNow);
        btnLogin = findViewById(R.id.buttonlogin);
        edtemail = findViewById(R.id.edittextemail_login);
        edtpassword = findViewById(R.id.edittextpassword_login);
    }
}
