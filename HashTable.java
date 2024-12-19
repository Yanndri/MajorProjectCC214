public class HashTable {

    MyLinkedList[] accounts;

    public HashTable(int size) {
        accounts = new MyLinkedList[size];
    }

    public HashTable() {
        this(100);
    }

    public void insert(Object item) {

        int i = hash(item);

        MyLinkedList currentChain = accounts[i];

        if (currentChain == null)
            accounts[i] = new MyLinkedList(item);
        else 
            currentChain.addLast(item);
            
    }

    public boolean isFound(Object item) {
        int i = hash(item);

        MyLinkedList currentChain = accounts[i];

        return currentChain.isFound(item);
    }

    public boolean delete(Object item) {
        int i = hash(item);

        MyLinkedList currentChain = accounts[i];

        if (currentChain.isFound(item)) {
            Object existingAcc = currentChain.getItemAt(currentChain.getPosition(item));

            if (item == existingAcc)
                currentChain.deleteItemAt(currentChain.getPosition(item));
            return true;
        }
        return false;
    }

    public int hash(Object item) {
        return Integer.parseInt(item.toString()) % accounts.length;
    }

    public String display() {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < accounts.length; i++) {
            sb.append("\n\tChain " + (i + 1) + " : ");
            if (accounts[i] != null) {
                sb.append(accounts[i].toString());
            } else
                sb.append("Empty");
        }

        return sb.toString();
    }

    public Object getAccount(Object item){

        if(isFound(item)){
            int i = hash(item);
            
            MyLinkedList currentChain = accounts[i];

            return currentChain.getItemAt(currentChain.getPosition(item));

        }

        return null;
    }

    public static void main(String[] args) {
       HashTable ht = new HashTable();

        ht.insert(5);
        ht.insert(15);
        ht.insert(25);
        // ht.delete(15);
        System.out.print(ht.display());
    }
}
