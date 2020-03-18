# GameOfThronesClient
[![Build Status](https://travis-ci.com/Didar-Bhullar/GameOfThronesClient.svg?branch=master)](https://travis-ci.com/Didar-Bhullar/GameOfThronesClient) [![codecov](https://codecov.io/gh/Didar-Bhullar/GameOfThronesClient/branch/master/graph/badge.svg)](https://codecov.io/gh/Didar-Bhullar/GameOfThronesClient) 
[![License](https://img.shields.io/badge/License-BSD%203--Clause-green.svg)](https://opensource.org/licenses/BSD-3-Clause)


Adds a java wrapper around the [GoT API](https://anapioficeandfire.com)


## Getting Started

Get the instance of the gameOfThronesClient <br>
```Java
 public static void main(String[] args){
        GameOfThronesClient gotClient = getClient();
    }
```
You can get a version of the client with automatic caching instead if you want.<br>
This will cache all the calls to whatever directory you supply as the cacheFile.
```Java
    public static void main(String[] args){
        File cacheFile = new File("cacheFile");
        int cacheSize = 10 * 1024 * 1024; // 10 MB

        GameOfThronesClient gotClient = getClientWithCache(cacheFile, cacheSize);
    }
```
Those two clients are interchangable. All the method calls to them work exactly the same

### Hitting the endpoints
Every endpoint of the GoT API is available through these methods.<br>
Take a look at the [gameOfThronesClient javadocs](https://didar-bhullar.github.io/GameOfThronesClient/docs/com/GameOfThronesClient/GameOfThronesClient.html) for the methods available to you.<br>
<br>
Whenever you use one of those methods, your either going get a list Models or just a Model itself.<br>
These models have various getters on them for easily retreiving information.<br>
Take a look at these [model javadocs](https://didar-bhullar.github.io/GameOfThronesClient/docs/com/GameOfThronesClient/models/package-summary.html)

#### Example: Getting all Books
```Java
public static void main(String[] args) throws IOException {
        GameOfThronesClient gotClient = getClient();

        List<Book> allBooks = gotClient.getAllBooks();  //Returns a list of Book objects

        // loop through each book
        // you have access to multiple getters to get information
        for(Book book: allBooks){
            System.out.println(book.getName());
            System.out.println(book.getCharacters());
            // and so on...
        }
    }
```
#### Example: Getting Character by id
```Java
public static void main(String[] args) throws IOException {
        GameOfThronesClient gotClient = getClient();

        Character character = gotClient.getCharacter(100);  //Returns a single character

        // use various getters to get information about this character
        System.out.println(character.getBorn());
        System.out.println(character.getAllegiances());

    }
```
#### Example: Searching for Books
*Pass in <b>null</b> to search values you do not care for. Applies to all House, and Character searches as well.* <br>
<br>
In this case I only cared that the book Im searching for was made after 1990-01-01 and before 2002-01-01.<br>
I did not care for the name. <br>
```Java
    public static void main(String[] args) throws IOException {
        GameOfThronesClient gotClient = getClient();

        List<Book> bookList = gotClient.searchBooks(null, "1990-01-01", "2002-01-01"); 
        for(Book book: bookList){
            System.out.println(book.getName());
            System.out.println(book.getReleased());
        }

    }
 ```
 ### Java Docs 
 Javadocs link can be found [here](https://didar-bhullar.github.io/GameOfThronesClient/docs/)
