package DataStructures;
import Books.Book;

public class Node
{
    Book pointer;
    Node parent;
    Node left;
    Node right;
    Node next;
    Node prev;

    // Constructor
    public Node(Book data)
    {
        this.pointer = data;
        left = null;
        right = null;
        parent = null;
    }

    public Book getPointer()
    {
        return pointer;
    }

    // toString
    @Override
    public String toString() {
        return pointer.toString();
    }
}