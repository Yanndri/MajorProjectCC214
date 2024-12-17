

import Activity.Exercise_6.MyLinkedList;

public class QueueLinkedList extends MyLinkedList{
 public void enqueue(Object item){
  addLast(item);
 }
 
 public Object dequeue(){
  Object item = getFirstElement();
  deleteFront();
  return item;
 }
 
 public Object qFront(){
  return getFirstElement();
  }
}