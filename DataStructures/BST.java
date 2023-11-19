package DataStructures;
import Books.Book;

import java.util.*;

public class BST
{
    Node root;

    // Constructor
    public BST() {
        root = null;
    }

    public Node getRoot(){
        return this.root;
    }

    // Methods
    public void insert(Book book)
    {
        if (root == null)
        {
            root = new Node(book);
            return;
        }

        Node temp = root;
        Node prev = temp;

        while (temp != null)
        {
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

    }

    public Node findName(String name)
    {
        Node temp = root;

        while (temp.pointer.getName().compareTo(name) != 0)
        {
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
    public void searchAll(ArrayList arr, Node node, String s){
        //Go the left subtree and add all matching strings there
        if (node.left != null) searchAll(arr, node.left, s);

        //If first substring matches, then add to list
        if (node.getPointer().getName().toUpperCase().startsWith(s.toUpperCase())) {
            arr.add(node.getPointer());
        }

        //Go to right subtree and add all matching strings there
        if (node.right != null) searchAll(arr, node.right, s);
    }

    public void lnr(Node node)
    {
        if (node != null)
        {
            lnr(node.left);
            System.out.println(node.getPointer());
            lnr(node.right);
        }
    }

    @Override
    public String toString()
    {
        lnr(root);
        return "";
    }
}