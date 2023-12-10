package DataStructures;

class GenericNode<T extends Comparable<T>> {
    T data;
    GenericNode next;

    GenericNode(T d){
        data = d;
    }
}

public class LinkedList<T extends Comparable<T>> {
    GenericNode head;
    GenericNode tail;
    int size;

    public void insertInOrder(T value){ //O(n)
        if(head == null) head = new GenericNode(value);
        else if(head.data.compareTo(value) == 0){
            GenericNode n = new GenericNode(value);
            n.next = head;
            head = n;
        }
        else if (head.next == null) head.next = new GenericNode(value);
        else{
            GenericNode current = head;
            while(current.next != null && current.next.data.compareTo(value) < 0){
                current = current.next;
            }
            GenericNode n = new GenericNode(value);
            n.next = current.next;
            current.next = n;
        }
        size++;
    }

    public int size(){return size;}

    public void insert(T value){
        GenericNode newNode = new GenericNode(value);

        if (head == null && tail == null){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public Comparable get(int index){
        int i = 0;
        GenericNode curr = head;
        while (curr != null && i < index){
            curr = curr.next;
            i++;
        }
        return curr.data;
    }

    public boolean find(T data){ //O(n)
        if(head == null) return false;
        if (head.data.compareTo(data) == 0 || head.next.data.compareTo(data) == 0) return true;
        GenericNode current = head;
        while(current.next != null){
            if (current.data == data) return true;
            current = current.next;
        }
        return false;
    }

    public void clear(){ //O(1)
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty(){ //O(1)
        return head == null;
    }

    public void remove(T value){//O(n)
        if ( head == null ) return ;
        if ( head.data.compareTo(value) == 0 ) {
            head = head.next ;
            size--;
            return ;
        }
        GenericNode current = head ;
        while ( current.next != null ) {
            if ( current.next.data.compareTo(value) == 0) {
                current.next = current.next.next ;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public void addAll(LinkedList l){//O(n)
        size += l.size;
        GenericNode current = head;
        while (current.next != null) current = current.next;
        GenericNode t = l.head;
        while(t != null){
            current.next = t;
            current = current.next;
            t = t.next;
        }
    }

    public void reverse(){ //O(n)
        GenericNode current = head;
        GenericNode prev = null;
        GenericNode next = null;
        while (current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public static String[] toArray(LinkedList<String> list){
        String[] arr =  new String[list.size()];
        GenericNode<String> curr = list.head;
        int i = 0;
        while(curr != null){
            arr[i++] =  curr.data;
            curr = curr.next;
        }
        return arr;
    }

    public String toString() { //O(n)
        String s = "[";
        if (head == null) return s;
        GenericNode current = head;
        while(current.next != null){
            s += current.data + ", ";
            current = current.next;
        }
        s += current.data + "]";
        return s;
    }
}