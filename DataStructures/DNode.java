package DataStructures;

public class DNode<B> {
    protected B item;
    protected DNode<B> next, prev;

    public DNode(B item, DNode<B> prev, DNode<B> next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    public DNode(B item) {
        this(item, null, null);
    }

    // getters
    public B getItem() {
        return item;
    }

    public DNode<B> getNext() {
        return next;
    }

    public DNode<B> getPrev() {
        return prev;
    }

    // setters
    public void setItem(B item) {
        this.item = item;
    }

    public void setNext(DNode<B> next) {
        this.next = next;
    }

    public void setPrev(DNode<B> prev) {
        this.prev = prev;
    }
}