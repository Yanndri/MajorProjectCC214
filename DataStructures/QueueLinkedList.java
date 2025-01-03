package DataStructures;

public class QueueLinkedList<B> extends DLinkedList {
  public void enqueue(B item) {
    addLast(item);
  }

  public Object dequeue() {
    Object item = head.getItem();
    deleteFront();
    return item;
  }

  public Object qFront() {
    return head;
  }
}