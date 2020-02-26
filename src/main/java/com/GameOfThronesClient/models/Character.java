package com.GameOfThronesClient.models;

import java.util.List;

public class Character {
    private String url;
    private String name;
    private String gender;
    private String culture;
    private String born;
    private Boolean died;
    private List<String> titles;
    private List<String> aliases;
    private List<String> allegiances;
    private String father;
    private String mother;
    private String spouse;
    private List<String> books;
    private List<String> povBoks;
    private List<String> tvSeries;
    private List<String> playedBy;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCulture() {
        return culture;
    }

    public String getBorn() {
        return born;
    }

    public Boolean getDied() {
        return died;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public List<String> getAllegiances() {
        return allegiances;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public List<String> getBooks() {
        return books;
    }

    public List<String> getPovBoks() {
        return povBoks;
    }

    public List<String> getTvSeries() {
        return tvSeries;
    }

    public List<String> getPlayedBy() {
        return playedBy;
    }
}
