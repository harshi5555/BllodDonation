package com.example.mapp.giveaway;

/**
 * Created by harshi on 01/09/17.
 */

public class Upload {

    public String name;
    public String category;
    public String url;
    public String contact;
    public String location;
    public String item;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Upload() {
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setLocation(String location) {
        this.location = location;

    }

    public Upload(String name, String url, String category, String contact , String location,String item) {
        this.name = name;
        this.url= url;
        this.category =category;
        this.contact=contact;
        this.location =location;
        this.item =item;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
