public class UserAccounts {
    private Account[] accounts = new Account[10];

    public void SignUp(String gmail, String password) {
        if (checkGmail(gmail)) {
            System.out.println("Gmail already registered");
            return;
        }
        storeGmail(gmail);
    }

    // Use to check if Gmail is already registered
    // return true if Gmail is already registered
    private boolean checkGmail(String gmail) {
        int slot = encrypt(gmail) % accounts.length;
        if (accounts[slot] == null)
            return false;
        if (accounts[slot].gmail.equals(gmail))
            return true;
        if (accounts[slot].nextAccount != null && accounts[slot].nextAccount.gmail.equals(gmail))
            return true;
        else {
            Account tempNode = accounts[slot];
            while (!tempNode.nextAccount.gmail.equals(gmail)) { // iterate until false
                if (tempNode.nextAccount == null) // if reached null that means the gmail hasnt been registered
                    return false;
                tempNode = tempNode.nextAccount;
            }
        }
        return true;
    }

    // stores the Users Account to the Hash table
    private void storeGmail(String gmail) {
        int slot = encrypt(gmail) % accounts.length;

        if (accounts[slot] == null) { // insert if accounts is null
            accounts[slot] = new Account(gmail);
        } else if (accounts[slot].nextAccount == null) { // if accounts has no connected node
            accounts[slot].nextAccount.gmail = gmail;
        } else {
            Account tempNode = accounts[slot];
            while (tempNode.nextAccount != null) { // iterate until node link is null
                tempNode = tempNode.nextAccount;
            }
            tempNode.nextAccount.gmail = gmail;
        }
    }

    // encrypts a string so that it is unreadable
    private int encrypt(String unEncryptedString) {
        int key = 0;
        for (int i = 0; i < unEncryptedString.length(); i++) {
            int value = unEncryptedString.charAt(i); // replaces each character to int
            key += value; // and add them all together()
        }
        return key;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("{");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null)
                sb.append(accounts[i]);
            else
                sb.append(accounts[i].gmail);
            sb.append(" ,");
        }
        sb.append("}");

        return sb.toString();
    }

    public static void main(String[] args) {
        UserAccounts login = new UserAccounts();
        System.out.println(login);
        System.out.println("Gmail found = " + login.checkGmail("@gmail.com"));
        login.SignUp("@gmail.com", "password");
        System.out.println(login);
        System.out.println("Gmail found = " + login.checkGmail("@gmail.com"));
    }
}