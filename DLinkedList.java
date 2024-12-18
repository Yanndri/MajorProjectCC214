package DoublyLinkedList;

public class DLinkedList {
	DNode head, tail;
	int count = 0;

	// add
	public void addFront(Object item) {
		if (isEmpty())
			tail = head = new DNode(item);
		else {
			DNode node = new DNode(item, null, head);
			head.setPrev(node); // setting the head's prev to node
			head = node; // assigning new node to head
		}
		count++;
	}

	public void addLast(Object item) {
		if (isEmpty())
			tail = head = new DNode(item);
		else {
			DNode node = new DNode(item, tail, null);
			tail.setNext(node); // same logic with addFront
			tail = node;
		}
		count++;
	}

	public void insertAt(Object item, int pos) {
		DNode curr;
		if (!isEmpty()) {
			if (pos == 1) //addFront
				addFront(item);
			else if (pos == count) //addLast
				addLast(item);
			else {
				if (pos <= count / 2) { // near head
					curr = head;
					for (int j = 1; j < pos; j++)
						curr = curr.getNext();
				} else { // near tail
					curr = tail;
					for (int i = count; i > pos; i--)
						curr = curr.getPrev();
				}
				DNode node = new DNode(item, curr.getPrev(), curr); // curr node is the Node in the pos (its links to the new node)
				curr.getPrev().setNext(node); // the previous node of the current node is setting it's next to the new node
				curr.setPrev(node); // the current node will be shifted to the right
				count++;
			}
		}
	}

	// delete
	public void deleteFront() {
		DNode curr = head;
		head = head.getNext();
		curr.setNext(null);
		count--;
	}

	public void deleteLast() {
		DNode curr = tail;
		tail = tail.getPrev();
		curr.setPrev(null);
		count--;
	}

	public void deleteItemAt(int pos) {
		DNode curr;
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

	public boolean isEmpty() {
		return head == null;
	}

	// display
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (DNode p = head; p != null; p = p.getNext())
			sb.append(p.item + " ");
		sb.append("}");
		return sb.toString();
	}

	public String reverseToString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (DNode p = tail; p != null; p = p.getPrev())
			sb.append(p.item + " ");
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) {
		DLinkedList list = new DLinkedList();
		list.addFront(5);
		list.addFront(6);
		list.addFront(7);
		list.addLast(80);
		list.addFront(420);
		System.out.println("COUNT: " + list.count);
		list.insertAt(69, 3);
		list.insertAt(75, 2);
		System.out.println("COUNT: " + list.count);
		System.out.println("List contains:" + list);
		System.out.println();
		list.deleteItemAt(3);
		System.out.println("List contains:" + list);
		System.out.println("POST COUNT: " + list.count);
		System.out.println("List in reverse contains:" + list.reverseToString());
	}
} // end of DLinkedList class & Start of DNode class
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// END END END END END END END END END END END END END END END END END END END
	// END END END END END

class DNode {
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

	// getters
	public Object getItem() {
		return item;
	}

	public DNode getNext() {
		return next;
	}

	public DNode getPrev() {
		return prev;
	}

	// setters
	public void setItem(Object item) {
		this.item = item;
	}

	public void setNext(DNode next) {
		this.next = next;
	}

	public void setPrev(DNode prev) {
		this.prev = prev;
	}
}