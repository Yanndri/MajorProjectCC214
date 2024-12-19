public class MyLinkedList {

    public Node head, tail;
    public int count = 0;

    public MyLinkedList(Object item) {
        if (item != null) {
            addLast(item);
        }
    }

    public MyLinkedList() {
        this(null);
    }

    // adders
    public void addFront(Object item) {
        if (isEmpty())
            head = tail = new Node(item);
        else {
            head = new Node(item, head);
        }
        count++;
    }

    public void addLast(Object item) {
        if (isEmpty())
            head = tail = new Node(item);
        else {
            tail.setLink(tail = new Node(item));
        }
        count++;
    }

    // deleters
    public void deleteFront() {
        if (!isEmpty()) {
            if (head == tail)
                head = tail = null;
            else {
                Node p = head;
                head = p.getLink();
                p.setLink(null);
            }
            count--;
        }
    }

    public void deleteLast() {

        if (!isEmpty()) {
            if (head == tail)
                head = tail = null;
            else {
                Node p = head;
                while (p.getLink() != tail)
                    p = p.getLink();
                p.setLink(null);
                tail = p;
            }
            count--;
        }

    }

    public void deleteItemAt(int pos) {
        if (!isEmpty()) {
            if (pos >= 1 && pos <= count) {
                if (head == tail) {
                    head = tail = null;
                    count--;
                } else if (pos == 1)
                    deleteFront();
                else if (pos == count)
                    deleteLast();
                else {
                    Node p = head;
                    for (int i = 1; i < pos - 1; i++) {
                        p = p.getLink();
                    }
                    Node remove = p.getLink();
                    p.setLink(remove.getLink());
                    remove.setLink(null);
                    count--;
                }
            }
        }
    }

    public void insertItemAt(int pos, Object item) {
        if (pos >= 1 && pos <= count) {
            if (pos == 1)
                addFront(item);
            else {
                Node p = head;
                for (int i = 1; i < pos - 1; i++) {
                    p = p.getLink();
                }
                p.setLink(new Node(item, p.getLink()));
                count++;
            }
        }
    }

    // evaluators
    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFound(Object item) {
        boolean result = false;
        if (!isEmpty()) {
            if (head.getItem().equals(item))
                result = true;
            else if (tail.getItem().equals(item))
                result = true;
            else {
                Node p = head;
                while (p != null) {
                    if (p.getItem().equals(item))
                        return true;
                    p = p.getLink();
                }

            }
        }
        return result;
    }

    public Object getItemAt(int pos) {
        if (!isEmpty()) {
            if (pos >= 1 && pos <= count) {
                if (head == tail && pos == 1) {
                    return head.getItem();
                } else if (pos == count)
                    return tail.getItem();
                else {
                    Node p = head;
                    for (int i = 1; i < pos; i++) {
                        p = p.getLink();
                    }
                    return p.getItem();
                }
            }
        }
        return null;
    }

    public int getPosition(Object item) {
        if (!isEmpty()) {
            if (head.getItem().equals(item)) {
                return 1;
            } else if (tail.getItem().equals(item)) {
                return count;
            } else {
                Node p = head;
                int pos = 1;
                while (p != null) {
                    if (p.getItem().equals(item)) {
                        return pos;
                    }
                    p = p.getLink();
                    pos++;
                }
            }
        }
        return 0;
    }

    public Object getFirstElement() {
        return head.getItem();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node p = head;
        if (p != null) {
            while (p.getLink() != null) {
                sb.append(p.getItem() + " --> ");
                p = p.getLink();
            }
            if (head == tail)
                sb.append(p.getItem() + " --> ");
            else
                sb.append(p.getItem() + " --> ");
        }
        sb.append("null");

        return sb.toString();
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.addFront(1);
        list.addLast(2);
        list.insertItemAt(2, 4);
        list.addLast(3);
        list.addFront(6);
        System.out.println(list);

        list.deleteItemAt(3);
        System.out.println(list);

        list.deleteFront();
        System.out.println(list);

        list.deleteLast();
        System.out.println(list);

        // System.out.println("Is the Nexted list empty? " + list.isEmpty());
        // System.out.println("List contains: " + list);
        // // System.out.println("count: " + list.count);

        // System.out.println("\nAdd front: 4");
        // list.addFront(4);
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nAdd last: 5");
        // list.addLast(5);
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nInsert item 3 at position 2: ");
        // list.insertItemAt(2, 3);
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nAdd front: 4");
        // list.addFront(4);
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nInsert item 1 at position 1: ");
        // list.insertItemAt(1, 1);
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // // System.out.println("\nInsert item 6 at position 6: ");
        // // list.insertItemAt(6, 6);
        // // System.out.println("List contains: " + list);
        // // System.out.println("count: " + list.count);
        // // System.out.println("head: " + list.head.getItem());
        // // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nGet the position of item 5: " + list.getPosition(5));
        // System.out.println("List contains: " + list);
        // System.out.println("\nIs item 2 found? : " + list.isFound(2));
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nDelete front.");
        // list.deleteFront();
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nDelete last");
        // list.deleteLast();
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nDelete item at position 3");
        // list.deleteItemAt(3);
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nIs the Nexted list empty? " + list.isEmpty());
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nGet the position of item 1: " + list.getPosition(1));
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());

        // System.out.println("\nIs item 5 found? : " + list.isFound(5));
        // System.out.println("List contains: " + list);
        // System.out.println("count: " + list.count);
        // System.out.println("head: " + list.head.getItem());
        // System.out.println("tail: " + list.tail.getItem());
    }

}