package Warehouse;

import Books.Book;
import DataStructures.BST;

import java.util.ArrayList;
import java.util.HashMap;

public class BookStore
{
    private HashMap<Integer, Book> booklist;
    private BST[] alphabet;
    private static BookStore bookStore;

    // Constructor
    private BookStore()
    {
        alphabet = new BST[27];
        booklist = new HashMap<>();
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
        booklist.put(book.getISBN(), book);

        char name = book.getName().toUpperCase().charAt(0);

        if (!(name >= 'A' && name <= 'Z'))  //Not an alphabet, so insert in a completely different tree
        {
            if (alphabet[26] == null)
                alphabet[26] = new BST();
            alphabet[26].insert(book);
        }
        else
        {
            if (alphabet[name - 'A'] == null)
                alphabet[name - 'A'] = new BST();
            alphabet[name - 'A'].insert(book);
        }
    }

    public Book getBook(int ISBN) {
        return booklist.get(ISBN);
    }

    public Book getBookByName(String name)
    {
        char n = name.toUpperCase().charAt(0);

        if (!(n >= 'A' && n <= 'Z'))
        {
            if (alphabet[26] == null)
                return null;
            return alphabet[26].findName(name).getPointer();
        }
        else
            if (alphabet[n - 'A'] == null)
                return null;
            return alphabet[n - 'A'].findName(name).getPointer();
    }

    public ArrayList<Book> getBooks(String name){
        char n = name.toUpperCase().charAt(0);
        ArrayList<Book> books = new ArrayList<>();

        if (!(n >= 'A' && n <= 'Z')){
            if (alphabet[26] == null)
                return null;
            alphabet[26].searchAll(books, alphabet[26].getRoot(), name);
        }
        else
        if (alphabet[n - 'A'] == null)
            return null;
        alphabet[n - 'A'].searchAll(books, alphabet[n - 'A'].getRoot(), name);
        //System.out.println(books.size());
        return books;
    }

    public void listBooks()
    {
        for (int i = 0; i < alphabet.length; i++)
            System.out.println(alphabet[i]);
    }


}
