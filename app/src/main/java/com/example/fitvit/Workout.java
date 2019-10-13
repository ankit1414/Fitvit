package com.example.fitvit;

class Workout {
    private String title;
    private String wod;

    public String getTitle() {
        return title;
    }

    public String getWod() {
        return wod;
    }

    public Workout() {
    }

    public Workout(String title, String wod) {
        this.title = title;
        this.wod = wod;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWod(String wod) {
        this.wod = wod;
    }
}
