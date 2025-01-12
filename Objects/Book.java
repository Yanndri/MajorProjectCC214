package Objects;

import DataStructures.DLinkedList;
import DataStructures.DNode;
import DataStructures.QueueLinkedList;
import LibGUI.Login;

public class Book {
    private String title, description, publicationDate;
    private int noOfCopies, borrowedCopies;
    private QueueLinkedList requesters;
    private DLinkedList<String> authors;
    private DLinkedList<Integer> borrowers;
    private final int totalCopies; // Max copies

    public Book(DLinkedList<String> authors, String title, String description, String publicationDate, int noOfCopies,
            DLinkedList<Integer> borrowers) {
        this.authors = authors;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.noOfCopies = noOfCopies;
        this.totalCopies = noOfCopies; // Set the Maximun copies
        this.borrowers = borrowers;
    }

    public Book() {
        this(null, null, null, null, 0, null);
    }

    // setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(DLinkedList<String> authors){
        this.authors = authors;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    // when user borrows a book or returns a book, pass 1 or -1
    // if no books return false
    public void setNoOfCopies(int value) {
        this.noOfCopies += value;
        if (this.noOfCopies < 0)
            this.noOfCopies = 0;
        else if (this.noOfCopies > this.totalCopies)
            this.noOfCopies = this.totalCopies;
    }

    public String addAuthor(String author) {
        if (authors == null) {
            authors = new DLinkedList<>(); // ensure authors list is initialized
        }
        authors.addLast(author);
        return authors.tail.getItem(); // the object is already a string so no need cast to STring
    }

    public Object removeAuthor(String author) {
        if (!authors.isFound(author)) {
            return "Author Not Found";
        } else {
            int pos = authors.getItemPosition(author);
            Object removedAuthor = authors.getItemAt(pos);
            authors.deleteItemAt(pos);
            return removedAuthor;
        }
    }

    // getters
    public String getAuthors() {
        if (authors == null || authors.isEmpty()) {
            return "\tNo authors";
        } else
            return authors.toString();
    }

    public DLinkedList<String> getAuthorsList() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public DLinkedList<User> getBorrowers() {
        Login login = new Login();
        DLinkedList<User> borrowersList = new DLinkedList<>();
        if (borrowers.isEmpty()) {
            return null;
        } else {
            DNode<Integer> currNode = borrowers.head;
            while (currNode != null) {
                User user = login.accounts.getUser(currNode.getItem());
                borrowersList.addLast(user);
                currNode = currNode.getNext();
            }
            return borrowersList;
        }
    }

    public String getBorrowersKeys() {
        if (borrowers == null || borrowers.isEmpty()) {
            return "No Borrower/s";
        } else {
            return borrowers.toString();
        }
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    // queue
    public void bookRequest(User borrower) {
        if (!isAvailable())
            requesters.enqueue(borrower);
    }

    public void addBorrower(User borrower) {
        if (isAvailable())
            borrowers.addFront(borrower.getKey());
    }

    public boolean isAvailable() {
        return borrowers.count < noOfCopies;
    }

    // public String bookReturned(Borrower borrower) {
    // if (borrowedCopies <= noOfCopies && borrowers != null) {
    // if (!borrowers.isFound(borrower)) {
    // return "Borrower Not Found.";
    // } else {
    // borrowers.deleteItemAt(borrowers.getPosition(borrower));
    // borrowedCopies--;
    // return borrower + " Returned Book.";
    // }
    // }
    // return "No Borrowed Book/s.";
    // }

    // toString
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getAuthors() + ":" + getTitle() + "//" + getDescription() + "//" + getPublicationDate() + "//"
                + getTotalCopies());

        return sb.toString();
    }

}
