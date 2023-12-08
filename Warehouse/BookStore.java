package Warehouse;


import Books.Book;
import DataStructures.BST;
import DataStructures.DoublyLinkedList;
import DataStructures.DynamicArray;
import DataStructures.LinkedList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.IDN;
import java.util.HashMap;
import User.User;

public class BookStore {
    private DoublyLinkedList booklist;
    private final DynamicArray<BST>[] dataField;
    //TODO  change HashMap to custom implementation
    private HashMap<String, Integer> genreList;
    // private LinkedList<Book> currentBookList;
    private DynamicArray<Book> currentBookList;
    private static BookStore bookStore;
    private HashMap<Integer, User> userList;
    private int numOfBooks;

    // Constructor
    private BookStore() {
        //   alphabet = new BST[27];
        numOfBooks = 0;
        currentBookList = new DynamicArray<>();
        dataField = new DynamicArray[27];
        booklist = new DoublyLinkedList();
        genreList = new HashMap<>();
        userList = new HashMap<>();
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

    public DynamicArray<Book> getBooks(String name, String genre) {
        //TODO make this more presentable
        DynamicArray<Book> list = new DynamicArray<>();

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

    public DynamicArray<Book> getCurrentBookList() {
        return currentBookList;
    }

    public String[] getGenres() {
        String[] genres = new String[genreList.size()];
        //TODO change this to a custom way of implementing Hashing
        genreList.keySet().toArray(genres);
        return genres;
    }



    public boolean passwordStrong(String password){
        if (password.length() < 8 || password.length() > 24) return false;
        int lowercase = 0;
        int uppercase = 0;
        int number = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') uppercase++;
            else if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') lowercase++;
            else if (password.charAt(i) >= '0' && password.charAt(i) <= '9') number++;
        }
        if (lowercase > 0 && uppercase > 0 && number > 0) return true;
        return false;
    }

    public void writeUsersToFile(){
        String filename = "./User/users.txt";
        File file = new File(filename);
        try {
            // If the file doesn't exist, create it
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + filename);
            }
            // Write to the file using FileWriter
            FileWriter writer = new FileWriter(file, false); // true for append mode
            for (User user: userList.values()) {
                writer.write(user.toString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Content successfully written to the file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSavedUsers() {
        File file = new File("./User/users.txt");
        try {
            if (file.exists()) {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line;
                User currentUser = null;
                while ((line = in.readLine()) != null) {
                    if (!line.isEmpty()) {
                        String[] parts = line.split(":");
                        if (parts.length == 4) {
                            // If line contains "User ID", create a new user
                            int userID = Integer.parseInt(line.split(" ")[2].substring(0, line.split(" ")[2].length()-1));
                            String password = line.split(" ")[4].substring(0, line.split(" ")[4].length()-1);
                            currentUser = new User(userID, password);
                            userList.put(userID, currentUser);
                        } else {
                            // If not, assume it's a book line and parse it
                            String[] bookParts = line.split(" ");
                            if (currentUser != null) {
                                //currentUser.addBook(booklist.get(Integer.parseInt(bookParts[bookParts.length-1].trim())));
                                //booklist.get(Integer.parseInt(bookParts[bookParts.length-1].trim())).decrement();
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /*public void buyBook(int isbn){
        Scanner in = new Scanner(System.in);
        System.out.println("enter user ID: ");
        int id = in.nextInt();
        System.out.println("Enter password");
        String password  =in.next();
        if (!userList.containsKey(id)){
            System.out.println("User not found, would you like to create a new account?");
            if (in.next().equalsIgnoreCase("yes")){
                id = User.getCurrentId();
                System.out.println("Your ID is: " + id +", please enter your password: ");
                password = in.next();
                while (!passwordStrong(password)){
                    System.out.println("Password not strong, please enter a better password: ");
                    password = in.next();
                }
                userList.put(id, new User(password));
            }
        }
        if (!userList.get(id).authenticatePassword(password)) {
            System.out.println("Wrong password, access denied");
        } else {
            if (booklist.containsKey(isbn)) {
                if (booklist.get(isbn).isInStock()) {
                    if (userList.get(id).hasBook(booklist.get(isbn))){
                        System.out.println("User already has book, cant buy same copy unless you return it.");
                    }
                    else {
                        System.out.println("Transaction successful, happy reading!");
                        booklist.get(isbn).decrement();
                        userList.get(id).addBook(booklist.get(isbn));
                    }
                } else System.out.println("Sorry, book is out of stock.");
            } else System.out.println("This isbn doesn't exist.");
        }
    }

    public void returnBook(int isbn){
        Scanner in = new Scanner(System.in);
        System.out.println("enter user ID: ");
        int id = in.nextInt();
        System.out.println("Enter password");
        String password = in.next();
        if (!userList.containsKey(id)){
            System.out.println("User account doesn't exist, please make an account before attempting a transaction.");
        }else if (!userList.get(id).authenticatePassword(password)) {
            System.out.println("Wrong password, access denied.");
        }
        else {
            if (booklist.containsKey(isbn)) {
                if (userList.get(id).hasBook(booklist.get(isbn))){
                    System.out.println("Return successful, thank you for reading!");
                    booklist.get(isbn).increment();
                    userList.get(id).returnBook(booklist.get(isbn));
                }
            } else System.out.println("This isbn doesn't exist.");
        }
    }*/
}