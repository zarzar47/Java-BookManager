package Warehouse;


import Books.Book;
import DataStructures.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.IDN;
import java.util.HashMap;

import User.User;

import javax.swing.*;

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

    public Book getBook(String name, String genre) {
        if (name.equalsIgnoreCase(""))
            return null;
        char letter = name.toUpperCase().charAt(0);
        int genreIndex = genreList.get(genre);
        if (dataField[letter - 'A'] == null || (!genreList.containsKey(genre.toUpperCase()) && !genre.equalsIgnoreCase("All")))
            return null;
        Node node = dataField[letter - 'A'].find(genreIndex).findName(name);
        if (node == null) {
            return null;
        }
        return node.getPointer();
    }

    public DynamicArray<Book> getBooks(String name, String genre) {
        //TODO make this more presentable
        DynamicArray<Book> list = new DynamicArray<>();

        if ((name.equals("Enter Book Name") || name.equals("")) && !genre.equalsIgnoreCase("All") && genreList.containsKey(genre.toUpperCase())){
            System.out.println("genre is " + genre);
            for (int i = 0; i < dataField.length; i++) {
                if (dataField[i].find(genreList.get(genre.toUpperCase())) != null)
                    dataField[i].find(genreList.get(genre.toUpperCase())).getList(list);
            }
            return list;
        }

        char letter = name.toUpperCase().charAt(0);
        if (dataField[letter - 'A'] == null || (!genreList.containsKey(genre.toUpperCase()) && !genre.equalsIgnoreCase("All")))
            return currentBookList;



        if (dataField[letter - 'A'] == null || (!genreList.containsKey(genre.toUpperCase()) && !genre.equalsIgnoreCase("All"))) {
            return currentBookList;
        }
        if (genre.equalsIgnoreCase("All")) {
            for (int i = 0; i < genreList.size(); i++) {
                System.out.println(letter);
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

    public void updateList(String name, String genre, boolean specificSearch) {
        if (genre.equals("All") && specificSearch) {
            JOptionPane.showMessageDialog(null, "Please select a specific genre to search with.");
            return;
        }
        if (specificSearch) {
            Book book = getBook(name, genre);
            if (book == null) {
                JOptionPane.showMessageDialog(null, "Sorry but there was no book with the name "+name);
                currentBookList.clearAll();
                return;
            }
            currentBookList.clearAll();
            currentBookList.insert(book);
        } else {
            currentBookList = getBooks(name, genre);
        }
    }

    public String buyBook(int isbn) {
        Book book = booklist.Search(isbn);
        if (book.getInStock() == 1) {
            booklist.delete(isbn);
            dataField[book.getName().toUpperCase().charAt(0) - 'A'].find(genreList.get(book.getGenre().toUpperCase())).delete(book.getName());
            //currentBookList
            //remove form datafield, booklist and currentbooklist
            //dataField[book.getName().toUpperCase().charAt(0) - 'A'].find(genreList.get(book.getGenre().toUpperCase())).
        }
        else{
            booklist.Search(isbn).decreaseStock();
            //dataField[book.getName().toUpperCase().charAt(0) - 'A'].find(genreList.get(book.getGenre().toUpperCase())).find(book.getName()).getPointer().decreaseStock();
        }
        return "Login successful! Thank you for Buying our Books!";
    }

    public DynamicArray<Book> getCurrentBookList() {
        return currentBookList;
    }

    public void ascSortByName()
    {
        currentBookList.ascSortByName(currentBookList, 0, currentBookList.getSize() - 1);
    }
    public void ascSortByPrice()
    {
        currentBookList.ascSortByPrice(currentBookList, 0, currentBookList.getSize() - 1);
    }
    public void ascSortByPopularity()
    {
        currentBookList.ascSortByPopularity(currentBookList, 0, currentBookList.getSize() - 1);
    }

    public String[] getGenres() {
        String[] genres = new String[genreList.size()];
        //TODO change this to a custom way of implementing Hashing
        genreList.keySet().toArray(genres);
        return genres;
    }



   /* public void writeUsersToFile() {
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
            for (User user : userList.values()) {
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
                            int userID = Integer.parseInt(line.split(" ")[2].substring(0, line.split(" ")[2].length() - 1));
                            String password = line.split(" ")[4].substring(0, line.split(" ")[4].length() - 1);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}