package com.GameOfThronesClient.models;

import java.util.List;

/**
 * Models a book retrieved from the GoT API.
 */
public class Book {
    private String url;
    private String name;
    private String isbn;
    private List<String> authors;
    private int numberOfPages;
    private String publisher;
    private String country;
    private String mediaType;
    private String released;
    private List<String> characters;
    private List<String> povCharacters;

    /**
     * @return hypermedia URL of in which this book resource was retrieved
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the name of this book
     */
    public String getName() {
        return name;
    }

    /**
     * @return the International Standard Book Number (ISBN-13) that uniquely identifies this book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @return an array of names of the authors that wrote this book
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * @return the number of pages in this book.
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * @return the company that published this book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @return the country that this book was published in
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the type of media this book was released in
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * @return the date (ISO 8601) when this book was released
     */
    public String getReleased() {
        return released;
    }

    /**
     * @return an array of Character resource URLs that has been in this book
     */
    public List<String> getCharacters() {
        return characters;
    }

    /**
     * @return an array of Character resource URLs that has had a POV-chapter in this book
     */
    public List<String> getPovCharacters() {
        return povCharacters;
    }
}
