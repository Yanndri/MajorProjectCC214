public class HashTable {
    Node[] slots = new Node[10];

    public void storeItem(Object item) {
        if (item instanceof Integer == false)
            return;
        int key = (Integer) item; // convert Object to Int
        int slot = key % slots.length; // formula for encryption

        if (slots[slot] != null) { // check collision
            separateChaning(key, slot);
        } else
            slots[slot] = new Node(key);
    }

    public void separateChaning(int key, int slot) {
        Node tempNode = slots[slot];
        while (tempNode.getLink() != null) {
            tempNode = tempNode.getLink();
        }
        tempNode.setLink(new Node(key));
    }

    public boolean findItem(Object item) {
        if (item instanceof Integer == false)
            return false;
        int key = (Integer) item; // convert Object to Int
        int slot = key % slots.length; // formula for encryption

        // if slot is empty
        if (slots[slot] == null)
            return false;

        // if slot has the key for the item
        if (item.equals(slots[slot].getItem()))
            return true;

        // if slot has a chain
        Node tempNode = slots[slot];
        while (tempNode.getLink() != null) {
            tempNode = tempNode.getLink();
            if (tempNode.getItem().equals(item)) { // key found
                return true;
            }
        }
        return false;
    }

    public void deleteItem(Object item) {
        if (!findItem(item))
            return;
        int key = (Integer) item; // convert Object to Int
        int slot = key % slots.length; // formula for encryption

        // if slot has the key for the item
        if (item.equals(slots[slot].getItem())) {
            if (slots[slot].getLink() != null) // if has a chain replace the slot key with it's link
                slots[slot] = slots[slot].getLink();
            else
                slots[slot] = null;
        } else {
            // if slot has a chain
            Node tempNode = slots[slot];
            while (tempNode.getLink() != null) {
                if (tempNode.getLink().getItem().equals(item)) { // key found
                    tempNode.setLink(tempNode.getLink().getLink()); // replace the key with it's link
                    return;
                }
                tempNode = tempNode.getLink();
            }
        }

    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < slots.length; i++) {
            sb.append("\nindex[" + i + "]: ");
            if (slots[i] != null) {
                Node tempNode = slots[i];
                sb.append(tempNode.getItem() + " ");
                while (tempNode.getLink() != null) {
                    tempNode = tempNode.getLink();
                    sb.append(tempNode.getItem() + " ");
                }
            } else
                sb.append("null ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.storeItem(12);
        hashTable.storeItem(10);
        hashTable.storeItem(2);
        hashTable.storeItem(100);
        hashTable.storeItem(99);
        hashTable.storeItem(199);
        hashTable.storeItem(50);
        hashTable.storeItem(22);
        System.out.println(hashTable);
        System.out.println(hashTable.findItem(55));
        System.out.println(hashTable.findItem(12));
        System.out.println(hashTable.findItem(10));
        System.out.println(hashTable.findItem(2));
        System.out.println(hashTable.findItem(1020));
        System.out.println(hashTable.findItem(99));
        System.out.println(hashTable.findItem(19));
        System.out.println(hashTable.findItem(50));
        System.out.println(hashTable.findItem(22));
        System.out.println(hashTable);
        hashTable.deleteItem(12);
        System.out.println("deleted 12");
        hashTable.deleteItem(100);
        System.out.println("deleted 100");
        hashTable.deleteItem(199);
        System.out.println("deleted 199");
        hashTable.deleteItem(23);
        System.out.println("deleted 23");
        System.out.println(hashTable);
    }
}
