package com.example.sangloveslinh.todoapp.database;

import android.app.Application;

public class AppToDo extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(this,"app_todo.db").getWritableDb()).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
