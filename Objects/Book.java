package Objects;

import DataStructures.DLinkedList;
import DataStructures.Node;
import DataStructures.QueueLinkedList;
import LibGUI.Login;
import java.util.Objects;

public class Book {
    private String title, description, publicationDate;
    private int noOfCopies, bookId;
    private DLinkedList<String> authors;
    private QueueLinkedList borrowers, requesters;
    private int totalCopies; // Max copies

    public Book(int bookId, DLinkedList<String> authors, String title, String description, String publicationDate, int noOfCopies,
            QueueLinkedList borrowers, QueueLinkedList requesters) {
        this.bookId = bookId;        
        this.authors = authors;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.noOfCopies = noOfCopies;
        this.totalCopies = noOfCopies; // Set the Maximun copies
        this.borrowers = borrowers;
        this.requesters = requesters;
    }

    public Book(DLinkedList<String> authors, String title, String description, String publicationDate) {
        this(0, authors, title, description, publicationDate, 0, null, null);
    }

    public Book() {
        this(0, null, null, null, null, 0, null, null);
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

    public void setTotalCopies(int totalCopies){
        this.totalCopies = totalCopies;
    }

    public void setBorrowersList(QueueLinkedList borrowers){
        this.borrowers = borrowers;
    }

    // getters
    public int getBookId() {
        return bookId;
    }

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

    public QueueLinkedList getBorrowers() { // returns the queue of (User) borrowers
        Login login = new Login();
        QueueLinkedList borrowersList = new QueueLinkedList();
        if (borrowers.isEmpty()) {
            return null;
        } else {
            Node currNode = borrowers.head;
            while (currNode != null) {
                User user = login.accounts.getUser((int) currNode.getItem());
                borrowersList.enqueue(user);
                currNode = currNode.getLink();
            }
            return borrowersList;
        }
    }
    public QueueLinkedList getRequesters() { // returns the queue of (User) requesters
        Login login = new Login();
        QueueLinkedList requestersList = new QueueLinkedList();
        if (requesters.isEmpty()) {
            return null;
        } else {
            Node currNode = requesters.head;
            while (currNode != null) {
                User user = login.accounts.getUser((int) currNode.getItem());
                requestersList.enqueue(user);
                currNode = currNode.getLink();
            }
            return requestersList;
        }
    }
    public QueueLinkedList getRequestersQueue() {
        return requesters;
    }
    public QueueLinkedList getBorrowersQueue() {
        return borrowers;
    }
    public String getBorrowersKeys() {
        if (borrowers == null || borrowers.isEmpty()) {
            return "No Borrower/s";
        } else {
            return borrowers.toString();
        }
    }

    public String getRequestersKeys() {
        if (requesters == null || requesters.isEmpty()) {
            return "No Requester/s";
        } else {
            return requesters.toString();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // Check if both references are the same
        if (obj == null || getClass() != obj.getClass())
            return false; // Check if the object is of the same type

        Book book = (Book) obj;

        // Compare only the relevant fields (exclude number of borrowers or other
        // fields)

        // System.out.println("Start of compare: " + authors + " " + book.authors);
        // System.out.println(Objects.equals(authors, book.authors));
        // System.out.println(title + " " + book.title);
        // System.out.println(Objects.equals(title, book.title));
        // System.out.println(description + " " + book.description);
        // System.out.println(Objects.equals(description, book.description));
        // System.out.println(publicationDate + " " + book.publicationDate);
        // System.out.println(Objects.equals(publicationDate, book.publicationDate));

        return Objects.equals(getAuthors(), book.getAuthors()) &&
                Objects.equals(title, book.title) &&
                Objects.equals(description, book.description) &&
                Objects.equals(publicationDate, book.publicationDate);
    }

}
