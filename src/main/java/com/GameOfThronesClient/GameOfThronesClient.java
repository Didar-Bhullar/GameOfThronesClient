package com.GameOfThronesClient;

import com.GameOfThronesClient.models.Book;
import com.GameOfThronesClient.models.Character;
import com.GameOfThronesClient.models.House;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameOfThronesClient {
    private static GameOfThronesClient gameofThronesClient = null;
    private static Retrofit retrofit = null;
    private static GameOfThronesAPI gameOfThronesAPI = null;

    private GameOfThronesClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL.BASE.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gameOfThronesAPI = retrofit.create(GameOfThronesAPI.class);
    }

    private GameOfThronesClient(File cacheFile, int cacheSize){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(cacheFile, cacheSize))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL.BASE.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        gameOfThronesAPI = retrofit.create(GameOfThronesAPI.class);
    }

    public static GameOfThronesClient getClient(){
        if (gameofThronesClient == null){
           gameofThronesClient = new GameOfThronesClient();
        }
        return gameofThronesClient;
    }

    public static GameOfThronesClient getClientWithCache(File cacheFile, int cacheSize){
        if (gameofThronesClient == null){
            gameofThronesClient = new GameOfThronesClient(cacheFile, cacheSize);
        }
        return gameofThronesClient;
    }

    private static Optional<String> getNextUrl(Response response) {
        Headers header = response.headers();
        String link = response.headers().get("link");
        if (link.contains("next")) {
            String nextURL = link.split(";")[0].replaceAll("[<>\\[\\]]", "");
            return Optional.of(nextURL);
        }
        return Optional.empty();
    }

    public List<Book> getAllBooks() throws IOException {
        List<Book> bookList = new ArrayList<>();
        Call<List<Book>> call = gameOfThronesAPI.getAllBooks(URL.BOOKS.getUrl());
        Response<List<Book>> response = call.execute();
        bookList.addAll(response.body());

        Optional<String> nextUrlOptional = getNextUrl(response);
        while(nextUrlOptional.isPresent()){
            String url = nextUrlOptional.get();
            call = gameOfThronesAPI.getAllBooks(url);
            response = call.execute();
            bookList.addAll(response.body());
            nextUrlOptional = getNextUrl(response);
        }
        return bookList;
    }

    public List<Character> getAllCharacters() throws IOException{
        List<Character> characterList = new ArrayList<>();
        Call<List<Character>> call = gameOfThronesAPI.getAllCharacters(URL.CHARACTERS.getUrl());
        Response<List<Character>> response = call.execute();
        characterList.addAll(response.body());

        Optional<String> nextUrlOptional = getNextUrl(response);
        while(nextUrlOptional.isPresent()){
            String url = nextUrlOptional.get();
            call = gameOfThronesAPI.getAllCharacters(url);
            response = call.execute();
            characterList.addAll(response.body());
            nextUrlOptional = getNextUrl(response);
        }
        return characterList;
    }

    public List<House> getAllHouses() throws IOException{
        List<House> houseList = new ArrayList<>();
        Call<List<House>> call = gameOfThronesAPI.getAllHouses(URL.HOUSES.getUrl());
        Response<List<House>> response = call.execute();
        houseList.addAll(response.body());

        Optional<String> nextUrlOptional = getNextUrl(response);
        while(nextUrlOptional.isPresent()){
            String url = nextUrlOptional.get();
            call = gameOfThronesAPI.getAllHouses(url);
            response = call.execute();
            houseList.addAll(response.body());
            nextUrlOptional = getNextUrl(response);
        }
        return houseList;
    }

    public Book getBook(int id) throws IOException {
        Call<Book> call = gameOfThronesAPI.getBook(id);
        Response<Book> response = call.execute();
        return response.body();
    }

    public Character getCharacter(int id) throws IOException {
        Call<Character> call = gameOfThronesAPI.getCharacter(id);
        Response<Character> response = call.execute();
        return response.body();
    }

    public House getHouse(int id) throws IOException {
        Call<House> call = gameOfThronesAPI.getHouse(id);
        Response<House> response = call.execute();
        return response.body();
    }

    public List<Book> getBooksByPage(int page, int pageSize) throws IOException {
        Call<List<Book>> call = gameOfThronesAPI.getBooksByPage(page, pageSize);
        Response<List<Book>> response = call.execute();
        return response.body();
    }

    public List<Character> getCharactersByPage(Integer page, Integer pageSize) throws IOException {
        Call<List<Character>> call = gameOfThronesAPI.getCharactersByPage(page, pageSize);
        Response<List<Character>> response = call.execute();
        return response.body();
    }

    public List<House> getHousesByPage(Integer page, Integer pageSize) throws IOException {
        Call<List<House>> call = gameOfThronesAPI.getHousesByPage(page, pageSize);
        Response<List<House>> response = call.execute();
        return response.body();
    }

    public List<Book> searchBooks(String name, String fromReleasedDate, String toReleasedDate) throws IOException {
        List<Book> bookList = new ArrayList<>();
        Call<List<Book>> call = gameOfThronesAPI.searchBooks(name, fromReleasedDate, toReleasedDate);
        Response<List<Book>> response = call.execute();
        bookList.addAll(response.body());

        Optional<String> nextUrlOptional = getNextUrl(response);
        while(nextUrlOptional.isPresent()){
            String url = nextUrlOptional.get();
            call = gameOfThronesAPI.getAllBooks(url);
            response = call.execute();
            bookList.addAll(response.body());
            nextUrlOptional = getNextUrl(response);
        }
        return bookList;
    }

    public List<Character> searchCharacters(String name, String gender, String culture, String born, String died, Boolean isAlive) throws IOException {
        List<Character> characterList = new ArrayList<>();
        Call<List<Character>> call = gameOfThronesAPI.searchCharacters(name, gender, culture, born, died, isAlive);
        Response<List<Character>> response = call.execute();
        characterList.addAll(response.body());

        Optional<String> nextUrlOptional = getNextUrl(response);
        while(nextUrlOptional.isPresent()){
            String url = nextUrlOptional.get();
            call = gameOfThronesAPI.getAllCharacters(url);
            response = call.execute();
            characterList.addAll(response.body());
            nextUrlOptional = getNextUrl(response);
        }
        return characterList;
    }

    public List<House> searchHouses(String name, String region, String words, Boolean hasWords, Boolean hasTitles, Boolean hasSeats, Boolean hasDiedOut, Boolean hasAncestralWeapons) throws IOException {
        List<House> houseList = new ArrayList<>();
        Call<List<House>> call = gameOfThronesAPI.searchHouses(name, region, words, hasWords, hasTitles, hasSeats, hasDiedOut, hasAncestralWeapons);
        Response<List<House>> response = call.execute();
        houseList.addAll(response.body());

        Optional<String> nextUrlOptional = getNextUrl(response);
        while(nextUrlOptional.isPresent()){
            String url = nextUrlOptional.get();
            call = gameOfThronesAPI.getAllHouses(url);
            response = call.execute();
            houseList.addAll(response.body());
            nextUrlOptional = getNextUrl(response);
        }
        return houseList;
    }

    public static void main(String[] args) throws IOException {
        Boolean a = true;
        int cacheSize = 10 * 1024 * 1024;
        GameOfThronesClient gameOfThronesClient = GameOfThronesClient.getClientWithCache(new File("cache"), cacheSize);
        List<House> chars = gameOfThronesClient.searchHouses(null, null, null, null, null, null, true, null);
        for (House chara: chars){
            System.out.println(chara.getUrl());
        }
    }

    private enum URL {
        BASE("https://www.anapioficeandfire.com/api/"),
        BOOKS("https://www.anapioficeandfire.com/api/books?page=1&pageSize=50"),
        HOUSES("https://www.anapioficeandfire.com/api/houses?page=1&pageSize=50"),
        CHARACTERS("https://www.anapioficeandfire.com/api/characters?page=1&pageSize=50");

        private String url;

        URL(String url) {
            this.url = url;
        }

        public String getUrl(){
            return url;
        }
    }

}
