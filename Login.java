// Coretico workspace only

import java.util.Scanner;

public class Login {
    Node[] array = new Node[100];
    Borrower[] borrowerAccounts = new Borrower[100];
    Librarian[] librarianAccounts = new Librarian[100];

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
        if (array[slot] != null && array[slot].getItem().equals(gmail))
            return true;
        else if (array[slot].getLink() != null && array[slot].getLink().getItem().equals(gmail))
            return true;
        else {
            Node tempNode = array[slot];
            while (!tempNode.getLink().getItem().equals(gmail)) { // iterate until false
                if (tempNode.getLink() == null) // if reached null that means the gmail hasnt been registered
                    return false;
                tempNode = tempNode.getLink();
            }
        }
        return true;
    }

    

    public void storeGmail(String gmail) {
        int slot = encrypt(gmail) % array.length;

        if (array[slot] == null) { // insert if array is null
            array[slot] = new Node(gmail);
        } else if (array[slot].getLink() == null) { // if array has no connected node
            array[slot].setLink(new Node(gmail));
        } else {
            Node tempNode = array[slot];
            while (tempNode.getLink() != null) { // iterate until node link is null
                tempNode = tempNode.getLink();
            }
            tempNode.setLink(new Node(gmail));
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

    public static void main(String[] args) {
        String username, password;
        int flg = 0;
        Scanner scan = new Scanner(System.in);

        while(flg != -1){

            System.out.println("\n\n\tLibrary Management System");
            System.out.println("\n\n\tLogin or Sign Up\n\n\tEnter 1 for Login, 2 for Sign up: ");
            flg = scan.nextInt();
            System.out.print("\n\n\tEnter Username or Email: ");
            username = scan.next();
            System.out.print("\n\n\tEnter Password: ");
            password = scan.next();


        }

    }
}
