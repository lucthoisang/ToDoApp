package com.example.sangloveslinh.todoapp;

public class DataListModel {

    Long id;
    byte[] image;
    String title;
    String description;
    String deadline;
    boolean isAddToMyDay;
    boolean checked;

    public DataListModel(Long id, byte[] image, String title, String description, String deadline, boolean isAddToMyDay, boolean checked) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isAddToMyDay = isAddToMyDay;
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isAddToMyDay() {
        return isAddToMyDay;
    }

    public void setAddToMyDay(boolean addToMyDay) {
        isAddToMyDay = addToMyDay;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
