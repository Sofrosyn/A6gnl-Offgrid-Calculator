package com.sofrosyn.a6gnlsolarcalculator.modals;

public class Notification {

private String title,subtitle;
private long date;

    public Notification() {
    }

    public Notification(String title, String subtitle, long date) {
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
