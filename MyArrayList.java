//Exercise 3

import java.util.Scanner;

public class MyArrayList implements MyList {
  // instance variables
  Object[] items; // items will hold the elements
  int count; // count monitors number of elements
  static Scanner scan = new Scanner(System.in);

  public MyArrayList(int size) {
    items = new Object[size];
    count = 0;
  }

  public MyArrayList() {
    this(10);
  }

  //Add options
  public void addFront(Object item) {
    if (isEmpty()) {
      items[0] = item;
      count++;
    } else {
      shiftRight(0);
      items[0] = item;
      count++;
    }
  }

  public void addLast(Object item) {
    if (isEmpty()) {
      addFront(item);
    } else if (!isFull()) {// if the array items is not full
      // add elements
      items[count++] = item;
    } else
      print("The Array List is currently full.");
  }

  public void insertItemAt(int pos, Object item) {
    if (!isFull())
      shiftRight(pos);
      items[pos] = item;
      count++;
  }

  //Delete options
  public void deleteFront() {
    if (!isEmpty()) {
      shiftLeft(0);
      items[--count] = null;
    }
  }

  public void deleteLast() {
    if (!isEmpty())
      items[--count] = null;
  }

  public void deleteItemAt(int pos) {
    if (pos >= 0 && pos < count) {
      shiftLeft(pos);
      items[--count] = null;
    }
  }

  public int searchItem(Object item) {
    for (int i = 0; i < count; i++) {
      if (items[i].equals(item)) {
        return i;
      }
    }
    return -1; // Not found
  }

  //Checkers
  public boolean isEmpty() {
    return count == 0;
  }

  public boolean isFull() {
    return count == items.length;
  }

  public int checkIfValidPosition() {
    int pos = -1;
    while (pos < 0 || pos >= count) {
      print("\n\tPlease Enter Position: ");
      pos = scan.nextInt();
      scan.nextLine();
      if (pos < 0 || pos >= count) {
        print("\n\n\tInvalid input entered. Please try again.\n");
      }
    }
    return pos;
  }

  //Shifters
  public void shiftRight(int pos) {
    int count = this.count;
    while (count > pos) {
      items[count] = items[--count];
    }
  }

  public void shiftLeft(int pos) {
    int count = this.count-1;
    while (count > pos) {
      items[pos] = items[++pos];
    }
  }

  //additional methods
  //Find position of item being searched
  public int getPosition(Object item){
  for(int i = 0; i < count; i++){
    if(items[i].equals(item)){
      return i;
    }
  }
   return -1;
 }

 //Get first element of array
 public Object getFirstElement(){
  return !isEmpty()?items[0]:null;
  //if the array is not empty return first element, else return null
 }

 public Object getLastElement(){
  return !isEmpty()?items[count-1]:null;
  //if the array is not empty return last element, else return null
 }

 public void resize(){
  Object[] temp = new Object[items.length * 2];
  for(int i = 0; i < count; i++){
    temp[i] = items[i];
  }
  items = temp;
 }

 public boolean isFound(Object item){
  for(int i = 0; i < count; i++){
    if(items[i].equals(item)){
      return true;
    }
  }
    return false;
 }

  // override toString(), assume that items are in numbers
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("{ ");
    for (int i = 0; i < count; i++)
      sb.append(items[i] + " ");
    sb.append("}");
    return sb.toString();
  }

  public void print(String message) {
    System.out.print(message);
  }

  // testing
  public static void main(String[] args) {
    MyArrayList list = new MyArrayList();
    int option = 0;
    list.print("\n\tThere are 10 slots in the arrray.\n");

    while (option != -1) {
      list.print("\n\n\tList contains: " + list);

      list.print(
          "\n\n\tPlease selection operation:\n\n\t1. Add Item\n\n\t2. Remove Item\n\n\t3. Search for Element\n\n\t4. Stop Program\n\n\tEnter Number of Selected Operation: ");
      option = scan.nextInt();
      scan.nextLine();
      int item;
      int pos;

      switch (option) {
        case 1:
          list.print(
              "\n\n\tPlease choose add options.\n\n\t1. Add at front.\n\n\t2. Add at last.\n\n\t3. Insert at position.\n\n\tEnter Number of Selected Operation: ");
          option = scan.nextInt();
          scan.nextLine();
          list.print("\n\tPlease enter item to be stored: ");
          item = scan.nextInt();
          scan.nextLine();
          if (option == 1)
            list.addFront(item);
          else if (option == 2)
            list.addLast(item);
          else if (option == 3) {
            pos = list.checkIfValidPosition();
            list.insertItemAt(pos, item);
          }
          break;
        case 2:
          if (list.isEmpty()) {
            list.print("\n\n\tThe list is empty.");
            break;
          }
          list.print(
              "\n\n\tPlease choose remove options.\n\n\t1. Remove at front.\n\n\t2. Remove at last.\n\n\t3. Remove at position.\n\n\tEnter Number of Selected Operation: ");
          option = scan.nextInt();
          scan.nextLine();
          if (option == 1)
            list.deleteFront();
          else if (option == 2)
            list.deleteLast();
          else if (option == 3) {
            pos = list.checkIfValidPosition();
            list.deleteItemAt(pos);
          }
          break;
        case 3:
          list.print("\n\tPlease enter item: ");
          item = scan.nextInt();
          scan.nextLine();
          int result = list.searchItem(item);
          if (result == -1)
            list.print("\n\tThe item is not found in the list.\n");
          else
            list.print("\n\tThe item " + item + " is found at index " + result + ".\n");
          break;
        case 4:
          option = -1;
          break;
        default:
          list.print("\n\tInvalid input please try again.\n");
      }
    }

  }
}