public class UserAccounts {
    Node[] array = new Node[10];

    public void SignUp(String gmail) {

        if (checkGmail(gmail))
            System.out.println("Gmail already registered");
        else
            storeGmail(gmail);
    }

    // return true if Gmail is already registered
    public boolean checkGmail(String gmail) {
        int slot = encrypt(gmail) % array.length;
        if (array[slot] == null)
            return false;
        if (array[slot].item.equals(gmail))
            return true;
        if (array[slot].next != null && array[slot].next.item.equals(gmail))
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

    // stores the Users Account to the Hash table
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

    // encrypts a string so that it is unreadable
    public int encrypt(String unEncryptedString) {
        int key = 0;
        for (int i = 0; i < unEncryptedString.length(); i++) {
            int value = unEncryptedString.charAt(i); // replaces each character to int
            key += value; // and add them all together
        }
        return key;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("{");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i] + " ,");
        }
        sb.append("}");

        return sb.toString();
    }

    public static void main(String[] args) {
        UserAccounts login = new UserAccounts();

        System.out.println(login);
        login.SignUp("@gmail.com");
        System.out.println(login);
    }
}