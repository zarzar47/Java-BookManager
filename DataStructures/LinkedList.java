package DataStructures;
import Books.Book;

import java.util.List;

public class LinkedList<E extends Comparable<Book>>
{
    public class ListNode
    {
        private Book pointer;
        private ListNode next;

        // Constructor
        public ListNode(Book book)
        {
            this.pointer = book;
            this.next = null;
        }

        // Getters
        public Book getPointer()
        {
            return pointer;
        }
        public ListNode getNext()
        {
            return next;
        }

        // toString
        @Override
        public String toString()
        {
            return "" + pointer;
        }
    }

    ListNode head;
    ListNode tail;

    // Methods
    public void insertBook(Book book)
    {
        ListNode newNode = new ListNode(book);

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
        ListNode newNode = new ListNode(book);
        if (head == null && tail == null)
        {
            head = newNode;
            tail = newNode;
            return;
        }

        ListNode current = head;
        ListNode previous = null;

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
        ListNode newNode = new ListNode(book);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        ListNode current = head;
        ListNode previous = null;

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

    public void insertPopularityAscendingOrder(Book book)
    {

    }

    // Getters
    public ListNode getHead()
    {
        return head;
    }

    // toString
    @Override
    public String toString()
    {
        ListNode current = head;
        while (current != null)
        {
            System.out.println((current.pointer.toString()));
            current = current.next;
        }
        return "\n";
    }
}