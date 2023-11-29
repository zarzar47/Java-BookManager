package Warehouse;

import Books.Book;
import DataStructures.BST;
import DataStructures.DoublyLinkedList;
import DataStructures.LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStore
{
    private DoublyLinkedList booklist;
    private final BST[] alphabet;
    private HashMap<String, BST> genreList;
    private HashMap<String, Book> nameList;
    private HashMap<String, BST> authorList;
    private LinkedList<Book> currentBookList;
    private static BookStore bookStore;

    // Constructor
    private BookStore()
    {
        alphabet = new BST[27];
        booklist = new DoublyLinkedList();
        genreList = new HashMap<>();
        authorList = new HashMap<>();
        nameList =  new HashMap<>();
    }

    // Instantiation
    public static BookStore getInstance()
    {
        if (bookStore == null)
            bookStore = new BookStore();
        return bookStore;
    }

    // Methods
    public void insert(Book book)
    {
        booklist.Insert(book);

        //If genre is already present, then add to it, otherwise create new mapping for genre
        if (!genreList.containsKey(book.getGenre().toUpperCase())){
            genreList.put(book.getGenre().toUpperCase(), new BST());
        }
        genreList.get(book.getGenre().toUpperCase())
                .insert(book);

        //If author is already present, then add to it, otherwise create new mapping for author
        if (!authorList.containsKey(book.getAuthor().toUpperCase())){
            authorList.put(book.getAuthor().toUpperCase(), new BST());
        }
        authorList.get(book.getAuthor().toUpperCase())
                .insert(book);

        char name = book.getName().toUpperCase()
                .charAt(0);

        if (!(name >= 'A' && name <= 'Z'))  //Not an alphabet, so insert in a completely different tree
        {
            if (alphabet[26] == null)
                alphabet[26] = new BST();
            alphabet[26].insert(book);
        } else {
            if (alphabet[name - 'A'] == null)
                alphabet[name - 'A'] = new BST();
            alphabet[name - 'A'].insert(book);
        }
    }

    public String[] listAllGenre()
    {
        String[] list =new String[genreList.size()+1];
        int i = 0;
        list[i++] = "All";
        for (Map.Entry<String, BST> set :
                genreList.entrySet()) {
            list[i++] = set.getKey();
        }
        return list;
    }

    public Book getBook(String name) {
        return nameList.get(name);
    }

    public LinkedList<Book> getBooks(String name)
    {
        char n = name.toUpperCase().charAt(0);
        LinkedList<Book> books = new LinkedList<>();

        if (!(n >= 'A' && n <= 'Z'))
        {
            if (alphabet[26] == null)
                return null;
            alphabet[26].searchAll(books, alphabet[26].getRoot(), name);
        }
        else
        if (alphabet[n - 'A'] == null)
            return null;
        alphabet[n - 'A'].searchAll(books, alphabet[n - 'A'].getRoot(), name);

        currentBookList = books;
        return books;
    }

    public String listGenre(String genre)
    {
       // return genreList.get(genre.toUpperCase()).LNR(genreList.get(genre.toUpperCase()).getRoot(), "");
        return null;
    }

    public String listBooksByAuthor(String author)
    {
     //   return authorList.get(author.toUpperCase()).LNR(authorList.get(author.toUpperCase()).getRoot(), "");
        return null;
    }

    public void filterByGenre(String genre){
          // currentBookList = genreList.get(genre).getList();
    }

    public LinkedList<Book> sortListByPrice()
    {
        LinkedList output = new LinkedList();

        LinkedList.ListNode current = currentBookList.getHead();
        while (current != null)
        {
            output.insertPriceAscendingSorted(current.getPointer());
            current = current.getNext();
        }

        currentBookList = output;
        return output;
    }

    public LinkedList sortListByName()
    {
        LinkedList output = new LinkedList();

        LinkedList.ListNode current = currentBookList.getHead();
        while (current != null)
        {
            output.insertNameAscendingSorted(current.getPointer());
            current = current.getNext();
        }

        currentBookList = output;
        return output;
    }
}