// Coretico workspace only
public class Login {
    Node[] array = new Node[100];

    public void SignUp() {
        String gmail = "cyrandi639@gmail.com";

        if (checkGmail(gmail))
            System.out.println("Gmail already registered");
        else
            storeGmail(gmail);
    }

    // return true if Gmail is already registered
    public boolean checkGmail(String gmail) {
        int slot = encrypt(gmail) % array.length;
        if (array[slot] != null && array[slot].item.equals(gmail))
            return true;
        else if (array[slot].next != null && array[slot].next.item.equals(gmail))
            return true;
        else {
            Node tempNode = array[slot];
            while (!tempNode.next.item.equals(gmail)) { // iterate until false
                if (tempNode.next == null) // if reached null that means the gmail hasnt been registered
                    return false;
                tempNode = tempNode.next;
            }
        }
        return true;
    }

    public void storeGmail(String gmail) {
        int slot = encrypt(gmail) % array.length;

        if (array[slot] == null) { // insert if array is null
            array[slot] = new Node(gmail);
        } else if (array[slot].next == null) { // if array has no connected node
            array[slot].next = new Node(gmail);
        } else {
            Node tempNode = array[slot];
            while (tempNode.next != null) { // iterate until node link is null
                tempNode = tempNode.next;
            }
            tempNode.next = new Node(gmail);
        }
    }

    public int encrypt(String unEncryptedString) {
        int key = 0;
        for (int i = 0; i < unEncryptedString.length(); i++) {
            int value = unEncryptedString.charAt(i);
            key += value;
        }
        return key;
    }
}

class Node {
    Object item;
    Node next;

    Node(Object item) {
        this(item, null);
    }

    Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}
