package com.example.fahrtenbuch.entities;

public class CategoryDrive {
    private Integer categoryId;
    private Integer driveId;

    public CategoryDrive(Integer categoryId, Integer driveId) {
        this.categoryId = categoryId;
        this.driveId = driveId;
    }

    public CategoryDrive() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDriveId() {
        return driveId;
    }

    public void setDriveId(Integer driveId) {
        this.driveId = driveId;
    }
}
