package com.example.sangloveslinh.todoapp;

public class DataListModel {

    byte[] image;
    String title;
    String description;
    String deadline;
    Integer checked;

    public DataListModel(byte[] image, String title, String description, String deadline, Integer checked) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.checked = checked;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}
