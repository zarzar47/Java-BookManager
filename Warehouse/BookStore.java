package Warehouse;

import Books.Book;
import DataStructures.BST;
import DataStructures.DoublyLinkedList;
import DataStructures.DynamicArray;
import DataStructures.LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStore {
    private DoublyLinkedList booklist;
    private final DynamicArray<BST>[] dataField;
    //TODO  change HashMap to custom implementation
    private HashMap<String, Integer> genreList;
    private LinkedList<Book> currentBookList;
    private static BookStore bookStore;
    private int numOfBooks;

    // Constructor
    private BookStore() {
        //   alphabet = new BST[27];
        numOfBooks = 0;
        currentBookList = new LinkedList<>();
        dataField = new DynamicArray[27];
        booklist = new DoublyLinkedList();
        genreList = new HashMap<>();
    }

    // Instantiation
    public static BookStore getInstance() {
        if (bookStore == null)
            bookStore = new BookStore();
        return bookStore;
    }

    // Methods
    public void insert(Book book) {
        //TODO make this more presentable
        booklist.Insert(book);
        Book ref = booklist.getLatest();
        String genre = ref.getGenre().toUpperCase();

        if (!genreList.containsKey(genre)) {
            genreList.put(genre, genreList.size());
        }

        int genreInt = genreList.get(genre);
        char name = ref.getName().toUpperCase()
                .charAt(0);

        int index;
        if (!(name >= 'A' && name <= 'Z'))  //Not an alphabet, so insert in a completely different tree
            index = 26;
        else
            index = name - 'A';

        if (dataField[index] == null)
            dataField[index] = new DynamicArray<>();
        if (dataField[index].find(genreInt) == null)
            dataField[index].insertAt(new BST(), genreInt);

        dataField[index].find(genreInt).insert(ref);
        numOfBooks++;
    }

    public LinkedList<Book> getBooks(String name, String genre) {
        //TODO make this more presentable
        LinkedList<Book> list = new LinkedList<>();
        char letter = name.toUpperCase().charAt(0);
        if (dataField[letter - 'A'] == null || (!genreList.containsKey(genre.toUpperCase()) && !genre.equalsIgnoreCase("All")))
            return currentBookList;
        if (genre.equalsIgnoreCase("All")) {
            for (int i = 0; i < genreList.size(); i++) {
                DynamicArray<BST> DArray = dataField[letter - 'A'];
                DArray.find(i).searchAll(list, name);
            }
        } else {
            int i = genreList.get(genre.toUpperCase());
            DynamicArray<BST> DArray = dataField[letter - 'A'];
            DArray.find(i).searchAll(list, name);
        }

        return list;
    }

    public void updateList(String name, String genre) {
        currentBookList = getBooks(name, genre);
    }

    public LinkedList<Book> getCurrentBookList() {
        return currentBookList;
    }

    public String[] getGenres() {
        String[] genres = new String[genreList.size()];
        //TODO change this to a custom way of implementing Hashing
        genreList.keySet().toArray(genres);
        return genres;
    }
}