package DoublyLinkedList;

public class DNode{
    protected Object item;
    protected DNode next, prev;

    public DNode(Object item, DNode prev, DNode next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    public DNode(Object item) {
        this(item, null, null);
    }

    //getters
    public Object getItem() {
        return item;
    }

    public DNode getNext() {
        return next;
    }

    public DNode getPrev(){
        return prev;
    }
    
    //setters
    public void setItem(Object item) {
        this.item = item;
    }

    public void setNext(DNode next) {
        this.next = next;
    }

    public void setPrev(DNode prev){
        this.prev = prev;
    }
}