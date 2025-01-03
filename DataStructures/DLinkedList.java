package DataStructures;
// Doubly Linked List

public class DLinkedList<B> {
	
	public DNode<B> head, tail;
	public int count = 0;

	// add
	public void addFront(B item) {
		if (isEmpty())
			tail = head = new DNode<>(item);
		else {
			DNode<B> node = new DNode<>(item, null, head);
			head.setPrev(node); // setting the head's prev to node
			head = node; // assigning new node to head
		}
		count++;
	}

	public void addLast(B item) {
		if (isEmpty())
			tail = head = new DNode<>(item);
		else {
			DNode<B> node = new DNode<>(item, tail, null);
			tail.setNext(node); // same logic with addFront
			tail = node;
		}
		count++;
	}

	public void insertAt(B item, int pos) {
		if (pos < 1 || pos > count + 1) {
			throw new IndexOutOfBoundsException("Invalid position: " + pos);
		}
		if (!isEmpty()) {
			if (pos == 1) // addFront
				addFront(item);
			else if (pos == count) // addLast
				addLast(item);
			else {
				DNode<B> curr;
				if (pos <= count / 2) { // near head
					curr = head;
					for (int j = 1; j < pos; j++)
						curr = curr.getNext();
				} else { // near tail
					curr = tail;
					for (int i = count; i > pos; i--)
						curr = curr.getPrev();
				}
				DNode<B> node = new DNode<>(item, curr.getPrev(), curr);
				// curr node is the Node in the pos (its links to the new node)
				curr.getPrev().setNext(node);
				// the previous node of the current node is setting it's next to the new node
				curr.setPrev(node); // the current node will be shifted to the right
				count++;
			}
		}
	}

	// delete
	public void deleteFront() {
		DNode<B> curr = head;
		head = head.getNext();
		curr.setNext(null);
		count--;
	}

	public void deleteLast() {
		DNode<B> curr = tail;
		tail = tail.getPrev();
		curr.setPrev(null);
		count--;
	}

	public void deleteItemAt(int pos) {
		DNode<B> curr;
		if (!isEmpty()) {
			if (pos == 1) {
				deleteFront();
			} else if (pos == count) {
				deleteLast();
			} else {
				if (pos <= count / 2) { // position is lesser or equal (near head)
					curr = head;
					for (int i = 1; i < pos; i++) {
						curr = curr.getNext();
						System.out.println("Curr Item: " + curr.item);
					}
				} else { // position is greater (near tail)
					curr = tail;
					for (int i = count; i > pos; i--) {
						curr = curr.getPrev();
					}
				}
				curr.getPrev().setNext(curr.getNext());
				curr.getNext().setPrev(curr.getPrev());
				count--;
			}
		}
	}

	public boolean isFound(B item) {
		DNode<B> curr;
		if (!isEmpty()) {
			if (head.getItem() == item) {
				return true;
			} else if (tail.getItem() == item) {
				return true;
			} else { // position is greater (near tail)
					curr = head;
					while(curr != null) {
						if(curr.getItem() == item){
							return true;
						}
						curr = curr.getNext();
					}
				}
			}
			return false;
		}
	
	public int getItemPosition(B item) {
		DNode<B> curr;
		if (!isEmpty()) {
			if (head.getItem() == item) {
				return 1;
			} else if (tail.getItem() == item) {
				return count;
			} else { // position is greater (near tail)
					curr = head;
					int pos = 1;
					while(curr != null) {
						if(curr.getItem() == item){
							return pos;
						}
						curr = curr.getNext();
						pos++;
					}
				}
			}
			return 0;
		}

	public boolean isEmpty() {
		return head == null;
	}

	public Object getItemAt(int pos) {
        if (!isEmpty()) {
            if (pos >= 1 && pos <= count) {
                if (head == tail && pos == 1) {
                    return head.getItem();
                } else if (pos == count)
                    return tail.getItem();
                else {
                    DNode<B> curr = head;
                    for (int i = 1; i < pos; i++) {
                        curr = curr.getNext();
                    }
                    return curr.getItem();
                }
            }
        }
        return null;
    }

	// display
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		DNode<B> p;
        
		for (p = head; p != null; p = p.getNext()){
			sb.append(p.getItem()).append(", ");
	    if(p.getNext() == tail){
		    sb.append(" & ");
		    sb.append(p.getItem());
			break;
		}
		}
		return sb.toString();
	}

	public String reverseToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (DNode<B> p = tail; p != null; p = p.getPrev())
			sb.append(p.getItem()).append(" ");
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) {
		/*
		 * MyLinkedList authortest = new MyLinkedList();
		 * Book book1 = new Book(authortest,"bc","cdas","deqe",1);
		 * 
		 * DLinkedList list = new DLinkedList();
		 * list.addFront(book1);
		 * list.addFront(6);
		 * list.addFront(7);
		 * list.addLast(80);
		 * list.addFront(420);
		 * System.out.println("COUNT: " + list.count);
		 * list.insertAt(69, 3);
		 * list.insertAt(75, 2);
		 * System.out.println("COUNT: " + list.count);
		 * System.out.println("List contains:" + list);
		 * System.out.println();
		 * list.deleteItemAt(3);
		 * System.out.println("List contains:" + list);
		 * System.out.println("POST COUNT: " + list.count);
		 * System.out.println("List in reverse contains:" + list.reverseToString());
		 */
	}
} // end of DLinkedList class & Start of DNode class
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// END END END END END END END END END END END END END END END END END END END
	// END END END END END
