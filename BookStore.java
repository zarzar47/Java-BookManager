import java.util.ArrayList;
import java.util.HashMap;

public class BookStore {
    private HashMap<Integer, Book> booklist;
    private BST[] alphabet;
    private static BookStore bookStore;

    private BookStore() {
        alphabet = new BST[27];
        booklist = new HashMap<>();
    }

    public static BookStore getInstance() {
        if (bookStore == null) {
            bookStore = new BookStore();
        }
        return bookStore;
    }

    public void insert(Book book) {
        booklist.put(book.getISBN(), book);
        char name = book.name.toUpperCase().charAt(0);
        if (!(name >= 61 && name <= 122)) {
            if (alphabet[26]==null)
                alphabet[26]=new BST();
            alphabet[26].insert(book);
        } else {
            if (alphabet[name - 65] == null)
                alphabet[name - 65] = new BST();
            alphabet[name - 65].insert(book);
        }
    }

    public Book getBook(int ISBN) {
        return booklist.get(ISBN);
    }

    public Book getBookByName(String name) {
        char n = name.toUpperCase().charAt(0);
        if (!(n >= 61 && n <= 122)) {
            if (alphabet[26] == null)
                return null;
            return alphabet[26].findName(name).pointer;
        }
        else
            if (alphabet[n - 65] == null)
                return null;
            return alphabet[n - 65].findName(name).pointer;
    }


}
