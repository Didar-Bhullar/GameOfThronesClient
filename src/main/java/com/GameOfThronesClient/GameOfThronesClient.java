package com.GameOfThronesClient;

import com.GameOfThronesClient.models.Book;
import com.GameOfThronesClient.models.Character;
import com.GameOfThronesClient.models.House;
import okhttp3.Cache;
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

/**
 * Wraps around of the Game of Thrones API.
 * Has a wrapper for every single endpoint of the API.
 */
public class GameOfThronesClient {

    private static GameOfThronesClient gameofThronesClient = null;
    private static Retrofit retrofit = null;
    private static GameOfThronesAPI gameOfThronesAPI = null;

    /**
     * Initializes retrofit and the gameOfThronesAPI interface.
     */
    private GameOfThronesClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL.BASE.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gameOfThronesAPI = retrofit.create(GameOfThronesAPI.class);
    }

    /**
     * Initializes retrofit with caching and the gameOfThronesAPI interface.
     *
     * @param cacheFile the path to the cache file
     * @param cacheSize the size of the cache
     */
    private GameOfThronesClient(File cacheFile, int cacheSize) {
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

    /**
     * Gets the GameOfThronesClient object to start making calls.
     *
     * @return the GameOfThronesClient object
     */
    public static GameOfThronesClient getClient() {
        if (gameofThronesClient == null) {
            gameofThronesClient = new GameOfThronesClient();
        }
        return gameofThronesClient;
    }

    /**
     * Gets the GameOfThronesClient object with caching enabled.
     * Any single call made with this client will always try to use a cache first.
     * If not found within the cache, reach out to the API and store in the cache for next time.
     *
     * @param cacheFile the path to the cache file
     * @param cacheSize the size of the cache
     * @return the GameOfTHronesClient object
     */
    public static GameOfThronesClient getClientWithCache(File cacheFile, int cacheSize) {
        if (gameofThronesClient == null) {
            gameofThronesClient = new GameOfThronesClient(cacheFile, cacheSize);
        }
        return gameofThronesClient;
    }

    /**
     * Helper method for paginated results. Checks to see if there
     * is another page/url that needs to called.
     *
     * @param response the http response of the specific call
     * @return an Optional containing the next url for pagination, or an empty optional if no url is found
     */
    private static Optional<String> getNextUrl(Response<? extends List> response) {
        String link = response.headers().get("link");
        if (link.contains("next")) {
            String nextURL = link.split(";")[0].replaceAll("[<>\\[\\]]", "");
            return Optional.of(nextURL);
        }
        return Optional.empty();
    }

    /**
     * Gets all the results even if the specific api call was paginated.
     * Returns a generic list of models created from these results.
     *
     * @param call     the call to the specific api
     * @param category the specific URL category which can be BOOKS, CHARACTERS, HOUSES
     * @param <T>      a generic to represent the three different model types that can be returned
     * @return a generic list of models
     * @throws IOException
     */
    private static <T> List<T> getAll(Call<List<T>> call, URL category) throws IOException {
        List<T> modelList = new ArrayList<>();
        Response<List<T>> response = call.execute();
        modelList.addAll(response.body());

        Optional<String> nextUrlOptional = getNextUrl(response);
        while (nextUrlOptional.isPresent()) {
            String url = nextUrlOptional.get();
            if (category == URL.BOOKS) {
                Call<List<Book>> bookCall = gameOfThronesAPI.getAllBooks(url);
                Response<List<Book>> new_response = bookCall.execute();
                List<Book> books = new_response.body();
                modelList.addAll((List<T>) books);
                nextUrlOptional = getNextUrl(new_response);
            } else if (category == URL.CHARACTERS) {
                Call<List<Character>> characterCall = gameOfThronesAPI.getAllCharacters(url);
                Response<List<Character>> new_response = characterCall.execute();
                List<Character> characters = new_response.body();
                modelList.addAll((List<T>) characters);
                nextUrlOptional = getNextUrl(new_response);
            } else {
                Call<List<House>> houseCall = gameOfThronesAPI.getAllHouses(url);
                Response<List<House>> new_response = houseCall.execute();
                List<House> houseList = new_response.body();
                modelList.addAll((List<T>) houseList);
                nextUrlOptional = getNextUrl(new_response);
            }
        }
        return modelList;
    }

    /**
     * Returns all books.
     *
     * @return a list of Book objects
     * @throws IOException
     */
    public List<Book> getAllBooks() throws IOException {
        Call<List<Book>> call = gameOfThronesAPI.getAllBooks(URL.BOOKS.getUrl());
        List list = getAll(call, URL.BOOKS);
        List<Book> bookList = (List<Book>) (List<?>) list;
        return bookList;
    }

    /**
     * Returns all characters.
     *
     * @return a list of Character objects
     */
    public List<Character> getAllCharacters() throws IOException {
        Call<List<Character>> call = gameOfThronesAPI.getAllCharacters(URL.CHARACTERS.getUrl());
        List list = getAll(call, URL.CHARACTERS);
        List<Character> characterList = (List<Character>) (List<?>) list;
        return characterList;
    }

    /**
     * Returns all houses.
     *
     * @return a list of House objects
     * @throws IOException
     */
    public List<House> getAllHouses() throws IOException {
        Call<List<House>> call = gameOfThronesAPI.getAllHouses(URL.HOUSES.getUrl());
        List list = getAll(call, URL.HOUSES);
        List<House> houseList = (List<House>) (List<?>) list;
        return houseList;
    }

    /**
     * Returns a Book with the given id.
     *
     * @param id the id the book
     * @return a Book object, returns nothing if a given book does not exist with that id
     * @throws IOException
     */
    public Book getBook(int id) throws IOException {
        Call<Book> call = gameOfThronesAPI.getBook(id);
        Response<Book> response = call.execute();
        return response.body();
    }

    /**
     * Returns a Character with the given id.
     *
     * @param id The id of the character.
     * @return a Character object, returns nothing if a given character does not exist with that id
     * @throws IOException
     */
    public Character getCharacter(int id) throws IOException {
        Call<Character> call = gameOfThronesAPI.getCharacter(id);
        Response<Character> response = call.execute();
        return response.body();
    }

    /**
     * Returns a House with the given id.
     *
     * @param id the id of the house
     * @return a House object, returns nothing if a given house does not exist with that id
     * @throws IOException
     */
    public House getHouse(int id) throws IOException {
        Call<House> call = gameOfThronesAPI.getHouse(id);
        Response<House> response = call.execute();
        return response.body();
    }

    /**
     * Returns a list of Books from the given page and page size.
     *
     * @param page     the page for pagination
     * @param pageSize the page size for pagination
     * @return a list of Book objects
     * @throws IOException
     */
    public List<Book> getBooksByPage(int page, int pageSize) throws IOException {
        Call<List<Book>> call = gameOfThronesAPI.getBooksByPage(page, pageSize);
        Response<List<Book>> response = call.execute();
        return response.body();
    }

    /**
     * Returns a list of Characters from the given page and page size.
     *
     * @param page     the page for pagination
     * @param pageSize the page size for pagination
     * @return a list of Character objects
     * @throws IOException
     */
    public List<Character> getCharactersByPage(Integer page, Integer pageSize) throws IOException {
        Call<List<Character>> call = gameOfThronesAPI.getCharactersByPage(page, pageSize);
        Response<List<Character>> response = call.execute();
        return response.body();
    }

    /**
     * Returns a list of Houses from the given page and page size.
     *
     * @param page     the page for pagination
     * @param pageSize the page size for pagination
     * @return a list of House objects
     * @throws IOException
     */
    public List<House> getHousesByPage(Integer page, Integer pageSize) throws IOException {
        Call<List<House>> call = gameOfThronesAPI.getHousesByPage(page, pageSize);
        Response<List<House>> response = call.execute();
        return response.body();
    }

    /**
     * Returns a list of Books from the given query parameters.
     * Pass null to those parameters you do not want to include in the search.
     *
     * @param name             the name of the book
     * @param fromReleasedDate the books that were released after or on the given date
     * @param toReleasedDate   the books that were released before or on on the given date
     * @return a list of Book objects. Returns nothing if no books exist with the given query parameters
     * @throws IOException
     */
    public List<Book> searchBooks(String name, String fromReleasedDate, String toReleasedDate) throws IOException {
        Call<List<Book>> call = gameOfThronesAPI.searchBooks(name, fromReleasedDate, toReleasedDate);
        List list = getAll(call, URL.BOOKS);
        List<Book> bookList = (List<Book>) (List<?>) list;
        return bookList;
    }

    /**
     * Returns a list of Characters from the given query parameters.
     * Pass null to those parameters you do not want to include in the search.
     *
     * @param name    the name of the character
     * @param gender  the gender of the character. Can be "male" or "female"
     * @param culture the culture of the character
     * @param born    the year in which the character was born
     * @param died    the year in which the character has died
     * @param isAlive whether the given character is alive. Can be true or false
     * @return a list of Character objects. Returns nothing if no characters exist with the given query parameters
     * @throws IOException
     */
    public List<Character> searchCharacters(String name, String gender, String culture,
                                            String born, String died, Boolean isAlive) throws IOException {
        Call<List<Character>> call = gameOfThronesAPI.searchCharacters(name, gender, culture, born, died, isAlive);
        List list = getAll(call, URL.CHARACTERS);
        List<Character> characterList = (List<Character>) (List<?>) list;
        return characterList;
    }

    /**
     * Returns a list of Houses from the given query parameters.
     * Pass null to those parameters you do not want to include in the search.
     *
     * @param name                the name of the house
     * @param region              the region in which the house belongs to
     * @param words               the words of the house
     * @param hasWords            whether the house has words. Can be true or false
     * @param hasTitles           whether the house has titles. Can be true or false
     * @param hasSeats            whether the house has seats. Can be true or false
     * @param hasDiedOut          whether the house has died. Can be true or false
     * @param hasAncestralWeapons whether the house has ancestral weapons. Can be true or false
     * @return a list of House objects, returns nothing if no houses exist with the given query parameters
     * @throws IOException
     */
    public List<House> searchHouses(String name, String region, String words, Boolean hasWords, Boolean hasTitles,
                                    Boolean hasSeats, Boolean hasDiedOut, Boolean hasAncestralWeapons) throws IOException {
        Call<List<House>> call = gameOfThronesAPI.searchHouses(name, region, words, hasWords, hasTitles, hasSeats, hasDiedOut, hasAncestralWeapons);
        List list = getAll(call, URL.HOUSES);
        List<House> houseList = (List<House>) (List<?>) list;
        return houseList;
    }

    /**
     * Url's for the Game of Thrones API endpoints.
     */
    private enum URL {
        BASE("https://www.anapioficeandfire.com/api/"),
        BOOKS("https://www.anapioficeandfire.com/api/books?page=1&pageSize=50"),
        HOUSES("https://www.anapioficeandfire.com/api/houses?page=1&pageSize=50"),
        CHARACTERS("https://www.anapioficeandfire.com/api/characters?page=1&pageSize=50");

        private String url;

        URL(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
