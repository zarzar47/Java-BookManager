package DataStructures;
import Books.Book;

import java.util.List;

public class LinkedList<E extends Comparable<Book>>
{

    Node head;
    Node tail;

    // Methods
    public void insertBook(Book book)
    {
        Node newNode = new Node(book);

        if (head == null && tail == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void insertPriceAscendingSorted(Book book)
    {
        Node newNode = new Node(book);
        if (head == null && tail == null)
        {
            head = newNode;
            tail = newNode;
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null)
        {
            if (newNode.pointer.getPrice() < current.pointer.getPrice())
            {
                if (current == head)
                {
                    newNode.next = current;
                    head = newNode;
                    return;
                }
                previous.next = newNode;
                newNode.next = current;
                return;
            }

            previous = current;
            current = current.next;
        }

        previous.next = newNode;
    }

    public void insertNameAscendingSorted(Book book)
    {
        Node newNode = new Node(book);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null)
        {
            if (newNode.pointer.getName().compareTo(current.pointer.getName()) < 0)
            {
                if (current == head)
                {
                    newNode.next = current;
                    head = newNode;
                    return;
                }
                previous.next = newNode;
                newNode.next = current;
                return;
            }

            previous = current;
            current = current.next;
        }

        previous.next = newNode;
    }


    // Getters
    public Node getHead()
    {
        return head;
    }

    // toString
    @Override
    public String toString()
    {
        Node current = head;
        while (current != null)
        {
            System.out.println((current.pointer.toString()));
            current = current.next;
        }
        return "\n";
    }
}