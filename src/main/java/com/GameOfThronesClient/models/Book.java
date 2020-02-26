package com.GameOfThronesClient.models;

import java.util.List;

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

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCountry() {
        return country;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getReleased() {
        return released;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public List<String> getPovCharacters() {
        return povCharacters;
    }
}
