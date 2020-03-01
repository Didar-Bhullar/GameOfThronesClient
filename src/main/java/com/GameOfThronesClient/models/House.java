package com.GameOfThronesClient.models;

import java.util.List;

/**
 * Models a house retrieved from the GoT API.
 */
public class House {
    private String url;
    private String name;
    private String region;
    private String coatOfArms;
    private String words;
    private List<String> titles;
    private List<String> seats;
    private String currentLord;
    private String heir;
    private String overlord;
    private String founded;
    private String founder;
    private String diedOut;
    private List<String> ancestralWeapons;
    private List<String> cadetBranches;
    private List<String> swornMembers;

    /**
     * @return hypermedia URL of in which this house resource was retrieved
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the name of this house
     */
    public String getName() {
        return name;
    }

    /**
     * @return the region that this house resides in
     */
    public String getRegion() {
        return region;
    }

    /**
     * @return text describing the coat of arms of this house
     */
    public String getCoatOfArms() {
        return coatOfArms;
    }

    /**
     * @return the words of this house
     */
    public String getWords() {
        return words;
    }

    /**
     * @return the titles that this house holds
     */
    public List<String> getTitles() {
        return titles;
    }

    /**
     * @return the seats that this house holds
     */
    public List<String> getSeats() {
        return seats;
    }

    /**
     * @return the Character resource URL of this house's current lord
     */
    public String getCurrentLord() {
        return currentLord;
    }

    /**
     * @return the Character resource URL of this house's heir
     */
    public String getHeir() {
        return heir;
    }

    /**
     * @return the House resource URL that this house answers to
     */
    public String getOverlord() {
        return overlord;
    }

    /**
     * @return the year that this house was founded
     */
    public String getFounded() {
        return founded;
    }

    /**
     * @return the Character resource URL that founded this house
     */
    public String getFounder() {
        return founder;
    }

    /**
     * @return the year that this house died out
     */
    public String getDiedOut() {
        return diedOut;
    }

    /**
     * @return an array of names of the noteworthy weapons that this house owns
     */
    public List<String> getAncestralWeapons() {
        return ancestralWeapons;
    }

    /**
     * @return an array of House resource URLs that was founded from this house
     */
    public List<String> getCadetBranches() {
        return cadetBranches;
    }

    /**
     * @return an array of Character resource URLs that are sworn to this house
     */
    public List<String> getSwornMembers() {
        return swornMembers;
    }

}
