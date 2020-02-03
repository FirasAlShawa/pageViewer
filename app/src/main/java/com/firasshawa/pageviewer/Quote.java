package com.firasshawa.pageviewer;

import com.google.gson.annotations.SerializedName;

public class Quote {

    private String text;
    private int id;
    private boolean like;

    public Quote(String text, int id, boolean like) {
        this.text = text;
        this.id = id;
        this.like = like;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "text='" + text + '\'' +
                ", id=" + id +
                ", like=" + like +
                '}';
    }
}
