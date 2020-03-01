/**
 * Warning this is not the best way to test this client. Should be mocking the API calls instead
 * of actually calling it. However since the data for the API is so static, we can except the data retrieved
 * from inside the API to stay fairly consistent. For a small API like this, just use the real API.
 */

package com.GameOfThronesClient;

import com.GameOfThronesClient.models.Book;
import com.GameOfThronesClient.models.Character;
import com.GameOfThronesClient.models.House;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class TestGameOfThronesClient {
    private GameOfThronesClient gameOfThronesClient = null;

    @Before
    public void setupGameOfThronesClient() {
        gameOfThronesClient = GameOfThronesClient.getClient();
    }

    @Test
    public void testGetAllBooks() throws IOException {
        List<Book> bookList = gameOfThronesClient.getAllBooks();
        Book firstBook = bookList.get(0);
        Book lastBook = bookList.get(bookList.size() - 1);

        assertEquals(false, bookList.isEmpty());
        assertEquals("https://www.anapioficeandfire.com/api/books/1", firstBook.getUrl());
        assertEquals("https://www.anapioficeandfire.com/api/books/12", lastBook.getUrl());
        assertEquals(12, bookList.size());
    }

    @Test
    public void testGetAllCharacters() throws IOException {
        List<Character> characterList = gameOfThronesClient.getAllCharacters();
        Character firstCharacter = characterList.get(0);
        Character lastCharacter = characterList.get(characterList.size() - 1);

        assertEquals(false, characterList.isEmpty());
        assertEquals("https://www.anapioficeandfire.com/api/characters/1", firstCharacter.getUrl());
        assertEquals("https://www.anapioficeandfire.com/api/characters/2138", lastCharacter.getUrl());
        assertEquals(2134, characterList.size());
    }

    @Test
    public void testGetAllHouses() throws IOException {
        List<House> houseList = gameOfThronesClient.getAllHouses();
        House firstHouse = houseList.get(0);
        House lastHouse = houseList.get(houseList.size() - 1);

        assertEquals(false, houseList.isEmpty());
        assertEquals("https://www.anapioficeandfire.com/api/houses/1", firstHouse.getUrl());
        assertEquals("https://www.anapioficeandfire.com/api/houses/444", lastHouse.getUrl());
        assertEquals(444, houseList.size());
    }

    @Test
    public void testGetBook() throws IOException {
        Book book = gameOfThronesClient.getBook(1);

        assertThat(book, instanceOf(Book.class));
        assertEquals("https://www.anapioficeandfire.com/api/books/1", book.getUrl());
    }

    @Test
    public void testGetCharacter() throws IOException {
        Character character = gameOfThronesClient.getCharacter(1);

        assertThat(character, instanceOf(Character.class));
        assertEquals("https://www.anapioficeandfire.com/api/characters/1", character.getUrl());
    }

    @Test
    public void getHouse() throws IOException {
        House house = gameOfThronesClient.getHouse(1);

        assertThat(house, instanceOf(House.class));
        assertEquals("https://www.anapioficeandfire.com/api/houses/1", house.getUrl());
    }

    @Test
    public void testGetBooksByPage() throws IOException {
        List<Book> bookList = gameOfThronesClient.getBooksByPage(1, 2);
        Book firstBook = bookList.get(0);
        Book secondBook = bookList.get(1);

        assertEquals(2, bookList.size());
        assertEquals("https://www.anapioficeandfire.com/api/books/1", firstBook.getUrl());
        assertEquals("https://www.anapioficeandfire.com/api/books/2", secondBook.getUrl());
    }

    @Test
    public void testGetCharactersByPage() throws IOException {
        List<Character> characterList = gameOfThronesClient.getCharactersByPage(1, 2);
        Character firstCharacter = characterList.get(0);
        Character secondCharacter = characterList.get(1);

        assertEquals(2, characterList.size());
        assertEquals("https://www.anapioficeandfire.com/api/characters/1", firstCharacter.getUrl());
        assertEquals("https://www.anapioficeandfire.com/api/characters/2", secondCharacter.getUrl());
    }

    @Test
    public void testGetHousesByPage() throws IOException {
        List<House> houseList = gameOfThronesClient.getHousesByPage(1, 2);
        House firstHouse = houseList.get(0);
        House secondHouse = houseList.get(1);

        assertEquals(2, houseList.size());
        assertEquals("https://www.anapioficeandfire.com/api/houses/1", firstHouse.getUrl());
        assertEquals("https://www.anapioficeandfire.com/api/houses/2", secondHouse.getUrl());
    }

    @Test
    public void testSearchBooks() throws IOException {
        List<Book> bookList = gameOfThronesClient.searchBooks("A Game Of Thrones", null, null);
        Book book = bookList.get(0);

        assertEquals(1, bookList.size());
        assertEquals("https://www.anapioficeandfire.com/api/books/1", book.getUrl());
    }

    @Test
    public void testSearchCharacters() throws IOException {
        List<Character> characterList = gameOfThronesClient.searchCharacters("Jon Snow", null, null, null, null, true);
        Character character = characterList.get(0);

        assertEquals(1, characterList.size());
        assertEquals("https://www.anapioficeandfire.com/api/characters/583", character.getUrl());

    }

    @Test
    public void testSearchHouses() throws IOException {
        List<House> houseList = gameOfThronesClient.searchHouses("House Algood", null, null,
                null, null, null, null,
                null);
        House house = houseList.get(0);

        assertEquals(1, houseList.size());
        assertEquals("https://www.anapioficeandfire.com/api/houses/1", house.getUrl());
    }

}





