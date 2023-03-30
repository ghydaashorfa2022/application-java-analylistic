package com.example.myapplicationanalystic.model;

public class nots {
    String header;
    String category_id;
    String id;
    String body;
    String image;
    public nots() {
    }

    public nots(String header,String body,String id ,String image) {
      this.header=header;
      this.body=body;
      this.id=id;
      this.image=image;
    }
    public String getHeader () {

        return header;
    }
    public String getbody() {

        return body;
    }
    public class ResponseProduct{
        String id;

    }
    public String getcatagoryId() {

        return category_id;
    }
    public String getId() {

        return id;
    }
    public String getImage() {

        return image;
    }
}
