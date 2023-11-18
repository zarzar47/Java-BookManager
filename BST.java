public class BST {
    Node root;
    public BST() {
        root = null;
    }

    public void insert(Book book) {


        if (root == null) {
            root = new Node(book);
            return;
        }

        Node temp = root;
        Node prev = temp;

        while (temp != null) {
            prev = temp;
            if (book.compareTo(temp.pointer) < 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }

        if (book.compareTo(prev.pointer) < 0)
            prev.left = new Node(book);
        else
            prev.right = new Node(book);

    }

    public Node findName(String name) {
        Node temp = root;
        while (temp.pointer.name.compareTo(name) != 0) {
            if (name.compareTo(temp.pointer.name) < 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }

            if (temp == null)
                return null;
        }
        return temp;
    }
}