package com.example.sangloveslinh.todoapp.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "todolist")
public class ToDoList {

    @Id(autoincrement = true)
    private Long toDoId;

    @Property(nameInDb = "todoname")
    private String toDoName;

    @Property(nameInDb = "tododescription")
    private String toDoDescription;

    @Property(nameInDb = "duedate")
    private String dueDate;

    @Property(nameInDb = "iscomplete")
    private boolean isComplete;

    @Property(nameInDb = "istodonotcomplete")
    private boolean isToDoNotComplete;

    @Property(nameInDb = "isaddtomydaytab")
    private boolean isAddToMyDayTab;

    @Property(nameInDb = "isaddtotodolisttab")
    private boolean isAddToToDoListTab;

    @Generated(hash = 1322255604)
    public ToDoList(Long toDoId, String toDoName, String toDoDescription,
            String dueDate, boolean isComplete, boolean isToDoNotComplete,
            boolean isAddToMyDayTab, boolean isAddToToDoListTab) {
        this.toDoId = toDoId;
        this.toDoName = toDoName;
        this.toDoDescription = toDoDescription;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
        this.isToDoNotComplete = isToDoNotComplete;
        this.isAddToMyDayTab = isAddToMyDayTab;
        this.isAddToToDoListTab = isAddToToDoListTab;
    }

    @Generated(hash = 707050199)
    public ToDoList() {
    }

    public Long getToDoId() {
        return this.toDoId;
    }

    public void setToDoId(Long toDoId) {
        this.toDoId = toDoId;
    }

    public String getToDoName() {
        return this.toDoName;
    }

    public void setToDoName(String toDoName) {
        this.toDoName = toDoName;
    }

    public String getToDoDescription() {
        return this.toDoDescription;
    }

    public void setToDoDescription(String toDoDescription) {
        this.toDoDescription = toDoDescription;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getIsToDoNotComplete() {
        return this.isToDoNotComplete;
    }

    public void setIsToDoNotComplete(boolean isToDoNotComplete) {
        this.isToDoNotComplete = isToDoNotComplete;
    }

    public boolean getIsAddToMyDayTab() {
        return this.isAddToMyDayTab;
    }

    public void setIsAddToMyDayTab(boolean isAddToMyDayTab) {
        this.isAddToMyDayTab = isAddToMyDayTab;
    }

    public boolean getIsAddToToDoListTab() {
        return this.isAddToToDoListTab;
    }

    public void setIsAddToToDoListTab(boolean isAddToToDoListTab) {
        this.isAddToToDoListTab = isAddToToDoListTab;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

}
