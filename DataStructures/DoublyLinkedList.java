package DataStructures;

import Books.Book;

public class DoublyLinkedList {
    Node head;
    Node tail;
    int count = 0;
    public DoublyLinkedList(){
        head = null;
        tail = null;
    }

    public void Insert(Book book){
        if (head == null) {
            head  = new Node(book);
            tail = head;
            return;
        }

        Node node = new Node(book);
        node.prev = tail;
        tail.next = node;
        tail = tail.next;
    }

    public void delete(){
        if (head == null) {
            System.out.println("There are no books in the database");
            return;
        }
        tail = tail.prev;
    }

    public void delete(int isbn){
        if (head == null) {
            System.out.println("There are no books in the database");
            return;
        }
        if(head.pointer.getISBN() == isbn){
            head = head.next;
            return;
        }
        Node curr = head;
        while (curr.next.pointer.getISBN() != isbn && curr.next != null){
            curr = curr.next;
        }
        if (curr.next != null){
            curr.next = curr.next.next;
        }
    }

    public void display(){
        Node temp = head;
        while (temp!=null) {
            System.out.println(temp+"<->");
            temp = temp.next;
        }
    }

    public Book Search(int ISBN){
        Node temp = head;
        while (temp.pointer.getISBN() != ISBN) {
            temp = temp.next;
        }
        return temp.pointer;
    }

    public Book getLatest(){
        return tail.getPointer();
    }
}
