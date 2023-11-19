package Warehouse;

import Books.Book;
import DataStructures.BST;

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

        if (!(name >= 61 && name <= 122))
        {
            if (alphabet[26] == null)
                alphabet[26] = new BST();
            alphabet[26].insert(book);
        }
        else
        {
            if (alphabet[name - 65] == null)
                alphabet[name - 65] = new BST();
            alphabet[name - 65].insert(book);
        }
    }

    public Book getBookbyISBN(int ISBN) {
        return booklist.get(ISBN);
    }

    public Book getBookByName(String name)
    {
        // Bruh what?
//        char n = name.toUpperCase().charAt(0);
//
//        if (!(n >= 61 && n <= 122))
//        {
//            if (alphabet[26] == null)
//                return null;
//            return alphabet[26].findName(name).getPointer();
//        }
//        else
//            if (alphabet[n - 65] == null)
//                return null;
//            return alphabet[n - 65].findName(name).getPointer();
        char n = name.toUpperCase().charAt(0);

        if (n >= 65 && n <= 90)
        {
            if (alphabet[n - 65] == null)
                return null;
            return alphabet[n - 65].findName(name).getPointer();
        }
        System.out.println("bruh it didn't work");
        return null;
    }

    public void listBooks()
    {
        for (int i = 0; i < alphabet.length; i++)
            System.out.println(alphabet[i]);
    }
}
