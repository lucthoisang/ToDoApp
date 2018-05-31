package com.example.sangloveslinh.todoapp.database;



import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user")
public class User {

    @Id(autoincrement = true)
    private Long userId;

    @Property(nameInDb = "email")
    private String email;

    @Property(nameInDb = "password")
    private String password;

    @Generated(hash = 1234308244)
    public User(Long userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
