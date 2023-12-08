package DataStructures;

import Books.Book;

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

    public void searchAll(DynamicArray<Book> arr,String name){
        searchAll(arr,root,name);
    }

    //Only works with initial substring
    public void searchAll(DynamicArray<Book> arr, Node node, String s) {
        //Go the left subtree and add all matching strings there
        if (node.left != null)
            searchAll(arr, node.left, s);

        //If first substring matches, then add to list
        if (node.getPointer().getName().toUpperCase().startsWith(s.toUpperCase()))
            arr.insert(node.getPointer());

        //Go to right subtree and add all matching strings there
        if (node.right != null)
            searchAll(arr, node.right, s);
    }

    public Node find(String key) {
        Node curr = root;
        while (curr != null){
            if (key.equalsIgnoreCase(curr.pointer.getName())){
                return curr;
            }
            else if (key.compareTo(curr.pointer.getName()) > 0){
                curr = curr.right;
            }
            else curr = curr.left;
        }
        return null;
    }

    public void deleteNoChild(Node t, Node p){
        if ((p.pointer).compareTo(t.pointer) > 0){
            p.left = null;
        }
        else p.right = null;
    }

    public void deleteOneChild(Node t, Node parent){
        if ((parent.pointer).compareTo(t.pointer) < 0){
            if (t.right == null) parent.right = t.left;
            else parent.right = t.right;
        }
        else{
            if (t.right == null) parent.left = t.left;
            else parent.left = t.right;
        }
    }

    public void delete(String key) {
        Node t = find(key);
        if (t.right == null && t.left == null) {
            deleteNoChild(t, t.parent);
        } else if (t.right == null || t.left == null){
            deleteOneChild(t, t.parent);
        }
        else{
            Node n = maximum(t);
            t.pointer = n.pointer;
            deleteNoChild(n, n.parent);
        }
    }

    public Node maximum(Node r){
        if (r == null) return null;
        Node curr = r;
        while (curr.right != null){
            curr = curr.right;
        }
        return curr;
    }

    public void getList(LinkedList<Book> books){
        toList(books, root);
    }

    public void toList(LinkedList<Book> books, Node node){
        if (node == null) return;
        toList(books, node.left);
        books.insertBook(node.pointer);
        toList(books, node.right);
    }
}