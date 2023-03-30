package com.example.myapplicationanalystic.model;

public class catagoryy {
    String category_name;

    String id;
    public catagoryy() {
    }
    public catagoryy(String category,String id) {
        this.category_name = category;

        this.id=id;
    }

    public String getName() {

        return category_name;
    }

    public String getId() {
        return id;
    }
}
