package com.GameOfThronesClient.models;

import java.util.List;

/**
 * Models a character retrieved from the GoT API.
 */
public class Character {
    private String url;
    private String name;
    private String gender;
    private String culture;
    private String born;
    private String died;
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

    /**
     * @return hypermedia URL of in which this character resource was retrieved
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the name of this character
     */
    public String getName() {
        return name;
    }

    /**
     * @return the gender of this character
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the culture that this character belongs to
     */
    public String getCulture() {
        return culture;
    }

    /**
     * @return textual representation of when and where this character was born
     */
    public String getBorn() {
        return born;
    }

    /**
     * @return textual representation of when and where this character died.
     */
    public String getDied() {
        return died;
    }

    /**
     * @return the titles that this character holds
     */
    public List<String> getTitles() {
        return titles;
    }

    /**
     * @return the aliases that this character goes by
     */
    public List<String> getAliases() {
        return aliases;
    }



    /**
     * @return character resource URL of this character's father
     */
    public String getFather() {
        return father;
    }

    /**
     * @return the character resource URL of this character's mother
     */
    public String getMother() {
        return mother;
    }

    /**
     * @return the character resource URL of this character's spouse
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     * @return array of House resource URLs that this character is loyal to.
     */
    public List<String> getAllegiances() {
        return allegiances;
    }

    /**
     * @return an array of Book resource URLs that this character has been in
     */
    public List<String> getBooks() {
        return books;
    }

    /**
     * @return an array of Book resource URLs that this character has had a POV-chapter in
     */
    public List<String> getPovBoks() {
        return povBoks;
    }

    /**
     * @return array of names of the seasons of Game of Thrones that this character has been in.
     */
    public List<String> getTvSeries() {
        return tvSeries;
    }

    /**
     * @return array of actor names that has played this character in the TV show Game Of Thrones.
     */
    public List<String> getPlayedBy() {
        return playedBy;
    }
}
