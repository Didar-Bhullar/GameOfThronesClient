package com.GameOfThronesClient;

import com.GameOfThronesClient.models.Book;
import com.GameOfThronesClient.models.Character;
import com.GameOfThronesClient.models.House;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

import java.util.List;

public interface GameOfThronesAPI {abstract


    // Book
    @GET
    Call<List<Book>> getAllBooks(@Url String url);

    @GET("books/{id}")
    Call<Book> getBook(@Path("id") int bookId);

    @GET("books")
    Call<List<Book>> getBooksByPage(@Query("page") Integer page,
                                    @Query("pageSize") Integer pageSize);

    @GET("books")
    Call<List<Book>> searchBooks(@Query("name") String name,
                                 @Query("fromReleaseDate") String fromDate,
                                 @Query("toReleaseDate") String toDate);

    // Character
    @GET
    Call<List<Character>> getAllCharacters(@Url String url);

    @GET("characters/{id}")
    Call<Character> getCharacter(@Path("id") int characterId);

    @GET("characters")
    Call<List<Character>> getCharactersByPage(@Query("page") Integer page,
                                              @Query("pageSize") Integer pageSize);

    @GET("characters")
    Call<List<Character>> searchCharacters(@Query("name") String name,
                                      @Query("gender") String gender,
                                      @Query("culture") String culture,
                                      @Query("born") String born,
                                      @Query("died") String died,
                                      @Query("isAlive") Boolean isAlive);

    //House
    @GET
    Call<List<House>> getAllHouses(@Url String url);

    @GET("houses/{id}")
    Call<House> getHouse(@Path("id") int houseId);

    @GET("houses")
    Call<List<House>> getHousesByPage(@Query("page") int page,
                                      @Query("pageSize") int pageSize);

}
