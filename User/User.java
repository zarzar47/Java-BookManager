package User;

import Books.Book;
import DataStructures.LinkedList;
import DataStructures.intHashMap;

import java.util.HashMap;

public class User implements Comparable<User>{
    private static int id = 0;
    private int userID;
    private String password;
    private intHashMap<Book> books;


    public User(int fileID, String pass){
        password = pass;
        userID = fileID;
        books = new intHashMap<>();
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

    public intHashMap<Book> getBooks(){
        return books;
    }

    public void addBook(Book book){
        if(book == null) return;
        books.put(book.getISBN(), book);
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public boolean hasBook(Book book){
        return books.containsKey(book.getISBN());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: " + userID + ", Password: "  + password + ", Books User has: \n");
        LinkedList<Book> isbns = this.getBooks().values();
        for (int i = 0; i < isbns.size(); i++) {
            sb.append(isbns.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(User o) {
        return this.userID - o.userID;
    }
}

