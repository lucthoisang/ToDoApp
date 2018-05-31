package com.example.sangloveslinh.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sangloveslinh.todoapp.database.AppToDo;
import com.example.sangloveslinh.todoapp.database.DaoSession;
import com.example.sangloveslinh.todoapp.database.User;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    EditText edtemail, edtpassword, edtrepassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AnhXa();
        XuLy();
    }

    private void XuLy() {
        final DaoSession database = ((AppToDo) getApplication()).getDaoSession();
        edtrepassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtrepassword.getText().toString().equals(edtpassword.getText().toString()))
                    Toast.makeText(SignUpActivity.this, "Mật khẩu khớp", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = checkExistEntity(edtemail.getText().toString());
                if(isExist == false)
                {
                    User user = new User();
                    user.setEmail(edtemail.getText().toString());
                    user.setPassword(edtpassword.getText().toString());
                    database.getUserDao().insert(user);
                    Toast.makeText(SignUpActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();

                    Intent homeIntent = new Intent(SignUpActivity.this,HomeActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
                else
                    Toast.makeText(SignUpActivity.this, "Email bị trùng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkExistEntity(String s) {
        List<User> userList = ((AppToDo)getApplication()).getDaoSession().getUserDao().loadAll();
        if(userList == null)
            return false;
        for(User user:userList) {
            if(user.getEmail().equals(s))
                return true;
        }

        return false;
    }

    private void AnhXa() {
        edtemail = findViewById(R.id.edittextemail_signup);
        edtpassword = findViewById(R.id.edittextpassword_signup);
        edtrepassword = findViewById(R.id.edittextrepassword_signup);
        btnSignUp = findViewById(R.id.buttonsignup);
    }
}
