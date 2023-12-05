package User;

import Books.Book;

import java.util.HashMap;

public class User {
    private static int id = 10000;
    private int userID;
    private String password;
    private HashMap<Integer, Book> books;

    public User(String pass){
        userID = id++;
        password = pass;
        books = new HashMap<>();
    }

    public User(int fileID, String pass){
        password = pass;
        userID = fileID;
        books = new HashMap<>();
        id++;
    }

    public boolean authenticatePassword(String pass){
        return this.password.equals(pass);
    }

    public static int getCurrentId() {
        return id;
    }

    public static int getId() {
        return id;
    }

    public void addBook(Book book){
        books.put(book.getISBN(), book);
    }

    public void returnBook(Book book){
        books.remove(book.getISBN());
    }

    public boolean hasBook(Book book){
        return books.containsKey(book.getISBN());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: " + userID + ", Password: "  + password + ", Books User has: \n");
        for (Book book: books.values()
        ) {
            sb.append("Name: " + book.getName() + ", Author: " + book.getAuthor() + ", Popularity: " + book.getPopularity() +  ", ISBN: " + book.getISBN() + "\n");
        }
        return sb.toString();
    }
}

