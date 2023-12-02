package com.example.fahrtenbuch.entities;

public class Category_Drive {
    private Integer category_id;
    private Integer drive_id;

    public Category_Drive(Integer category_id, Integer drive_id) {
        this.category_id = category_id;
        this.drive_id = drive_id;
    }

    public Category_Drive() {
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(Integer drive_id) {
        this.drive_id = drive_id;
    }
}
