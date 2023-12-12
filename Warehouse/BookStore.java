package Warehouse;


import Books.Book;
import Books.FileStorage;
import DataStructures.*;

import java.util.HashMap;

import User.User;

import javax.swing.*;

public class BookStore {
    private DoublyLinkedList booklist;
    private final DynamicArray<BST>[] dataField;
    //TODO  change HashMap to custom implementation
    private StringHashMap<Integer> genreList;
    // private LinkedList<Book> currentBookList;
    private DynamicArray<Book> currentBookList;
    private static BookStore bookStore;
    private intHashMap<User> userList;
    //private HashMap<Integer, User> userList;
    private boolean ascending;
    private int numOfBooks;

    // Constructor
    private BookStore() {
        //   alphabet = new BST[27];
        numOfBooks = 0;
        currentBookList = new DynamicArray<>();
        dataField = new DynamicArray[27];
        booklist = new DoublyLinkedList();
        genreList = new StringHashMap<>();
        userList = new intHashMap<>();
        // userList = UserHash.getUsers();
    }

    public void setUsers(){
        userList = UserHash.getUsers();
    }

    public intHashMap<User> getUsers(){
        return userList;
    }

    // Instantiation
    public static BookStore getInstance() {
        if (bookStore == null)
            bookStore = new BookStore();
//        bookStore.setUsers();
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
        int genreIndex = genreList.get(genre.toUpperCase());
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
            for (int i = 0; i < dataField.length; i++) {
                if (dataField[i].find(genreList.get(genre.toUpperCase())) != null)
                    dataField[i].find(genreList.get(genre.toUpperCase())).getList(list);
            }
            return list;
        }

        char letter = name.toUpperCase().charAt(0);
        if (dataField[letter - 'A'] == null || (!genreList.containsKey(genre.toUpperCase()) && !genre.equalsIgnoreCase("All")))
            return currentBookList;



//        if (dataField[letter - 'A'] == null || (!genreList.containsKey(genre.toUpperCase()) && !genre.equalsIgnoreCase("All"))) {
//            return currentBookList;
//        }
        if (genre.equalsIgnoreCase("All")) {
            for (int i = 0; i < genreList.size(); i++) {
                System.out.println(letter);
                DynamicArray<BST> DArray = dataField[letter - 'A'];
                try {
                    DArray.find(i).searchAll(list, name);
                } catch (NullPointerException e) {
                    System.out.println("Book not found");
                //    JOptionPane.showMessageDialog(null,"no book with this starting substring in this genre.");
                }
            }
        } else {
            int i = genreList.get(genre.toUpperCase());
            DynamicArray<BST> DArray = dataField[letter - 'A'];
            DArray.find(i).searchAll(list, name);
        }
        // Added this line below and changed return statement
        currentBookList = list;
        return currentBookList;
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
            // Added the following line
            // currentBookList.clearAll();
            currentBookList = getBooks(name, genre);
        }
    }

    public String buyBook(int isbn, int id) {
        Book book = booklist.Search(isbn);
        if (book == null) return "Sorry, we dont have this book available";
        if (book.getInStock() == 0){
            return "Sorry, we have run out of all the copies for this Book.";
        }
        booklist.Search(isbn).decreaseStock();
//        else if (book.getInStock() == 1) {
//            booklist.delete(isbn);
//            dataField[book.getName().toUpperCase().charAt(0) - 'A'].find(genreList.get(book.getGenre().toUpperCase())).delete(book.getName());
//        }
//        else{
//            booklist.Search(isbn).decreaseStock();
//        }
        //UserHash.getInstance().getUserCredentials().get(id).addBook(book);
        userList.get(id).addBook(book);
        FileStorage.saveToCsv(userList);
        return "Login successful! Thank you for Buying our Books!";
    }

    public String removeBook(int id, int isbn)
    {
        Book book = booklist.Search(isbn);
        addBook(isbn);
        userList.get(id).removeBook(book);
        FileStorage.saveToCsv(userList);
        return "The book was successfully removed from your account!";
    }

    // For Filing
    public void addBookToUser(Book book, int id){
        if (userList.containsKey(id)){
            userList.get(id).addBook(book);
            subtractBook(book.getISBN());
        }
    }

    public void addUser(User user){
        if(userList.containsKey(user.getUserID())) return;
        userList.put(user.getUserID(), user);
        LinkedList<Book> isbns = user.getBooks().values();
        for (int i = 0; i < isbns.size(); i++) {
            subtractBook(((Book)isbns.get(i)).getISBN());
        }
    }

    public void subtractBook(int isbn){
        //Book book = booklist.Search(isbn);
        booklist.Search(isbn).decreaseStock();
//        if (book.getInStock() == 1) {
//            booklist.delete(isbn);
//            dataField[book.getName().toUpperCase().charAt(0) - 'A'].find(genreList.get(book.getGenre().toUpperCase())).delete(book.getName());
//        }
//        else{
//            booklist.Search(isbn).decreaseStock();
//        }
    }

    public void addBook(int isbn) {
        booklist.Search(isbn).increaseStock();
    }

    public DynamicArray<Book> getCurrentBookList() {
        return currentBookList;
    }


    public boolean isAscending()
    {
        return ascending;
    }

    public void setAscending(boolean ascending)
    {
        this.ascending = ascending;
    }

    public void ascSortByName()
    {
        Book[] arr = currentBookList.toArr();
        DynamicArray.ascSortByName(arr, 0, currentBookList.getSize() - 1, ascending);
        currentBookList.toDyArr(arr);
    }
    public void ascSortByPrice()
    {
        Book[] arr = currentBookList.toArr();
        DynamicArray.ascSortByPrice(arr, 0, currentBookList.getSize() - 1, ascending);
        currentBookList.toDyArr(arr);
    }
    public void ascSortByPopularity()
    {
        Book[] arr = currentBookList.toArr();
        DynamicArray.ascSortByPopularity(arr, 0, currentBookList.getSize() - 1, ascending);
        currentBookList.toDyArr(arr);
    }

    public String[] getGenres() {
        String[] genres;
        //TODO change this to a custom way of implementing Hashing
        genres = LinkedList.toArray(genreList.keySet());
        return genres;
    }

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