package DataStructures;

import Books.Book;

import java.util.*;

public class BST {
    private Node root;
    private int count = 0;
    private int size = 0;

    // Constructor
    public BST() {
        root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    // Methods
    public void insert(Book book) {
        if (root == null) {
            root = new Node(book);
            size++;
            return;
        }

        Node temp = root;
        Node prev = temp;

        while (temp != null) {
            prev = temp;
            if (book.compareTo(temp.pointer) < 0)
                temp = temp.left;
            else
                temp = temp.right;
        }

        if (book.compareTo(prev.pointer) < 0)
            prev.left = new Node(book);
        else
            prev.right = new Node(book);
        size++;

    }

    public Node findName(String name) {
        Node temp = root;

        while (temp.pointer.getName().compareTo(name) != 0) {
            if (name.compareTo(temp.pointer.getName()) < 0)
                temp = temp.left;
            else
                temp = temp.right;

            if (temp == null)
                return null;
        }
        return temp;
    }

    //Only works with initial substring
    public void searchAll(LinkedList<Book> arr, Node node, String s) {
        //Go the left subtree and add all matching strings there
        if (node.left != null)
            searchAll(arr, node.left, s);

        //If first substring matches, then add to list
        if (node.getPointer().getName().toUpperCase().startsWith(s.toUpperCase()))
            arr.insertBook(node.getPointer());

        //Go to right subtree and add all matching strings there
        if (node.right != null)
            searchAll(arr, node.right, s);
    }

    public Book[] getList() {
        count = 0;
        Book[] bookarray = new Book[size];
        LNR(root,bookarray);
        return bookarray;
    }

    public Book LNR(Node root, Book[] b) {
        if (root != null) {
            root.setPointer(LNR(root.left, b));
            b[count] = root.getPointer();
            root.setPointer(LNR(root.right,b));
        }
        return null;
    }
}