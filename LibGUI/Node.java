package LibGUI;

public class Node {
    private Object item;
    private Node link;

    public Node(Object item, Node link) {
        this.item = item;
        this.link = link;
    }

    public Node(Object item) {
        this(item, null);
    }

    public Node() {
        this(null, null);
    }

    // getters
    public Object getItem() {
        return item;
    }

    public Node getLink() {
        return link;
    }

    // setters
    public void setItem(Object item) {
        this.item = item;
    }

    public void setLink(Node link) {
        this.link = link;
    }

}// end of the class
