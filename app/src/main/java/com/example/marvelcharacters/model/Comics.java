package com.example.marvelcharacters.model;

import java.util.ArrayList;

public class Comics {
    ArrayList<Object> items = new ArrayList<Object>();
    private float available;
    private String collectionURI;
    private float returned;


    // Getter Methods

    public float getAvailable() {
        return available;
    }

    public void setAvailable(float available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    // Setter Methods

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public float getReturned() {
        return returned;
    }

    public void setReturned(float returned) {
        this.returned = returned;
    }
}
