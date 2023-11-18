public class Node {
    Book pointer;
    Node parent;
    Node left;
    Node right;

    public Node(Book data) {
        this.pointer = data;
        left = null;
        right = null;
        parent = null;
    }

    @Override
    public String toString() {
        return pointer.toString();
    }
}