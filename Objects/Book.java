package Objects;

<<<<<<< Updated upstream
=======
import DataStructures.BookLibrary;
>>>>>>> Stashed changes
import DataStructures.DLinkedList;
import DataStructures.DNode;
import DataStructures.Node;
import DataStructures.QueueLinkedList;
<<<<<<< Updated upstream

public class Book {
    private String title, description, publicationDate;
    private int totalCopies;
    private QueueLinkedList<User> requesters;
    private DLinkedList<String> authors;
    private DLinkedList<User> borrowers;

    public Book(DLinkedList<String> authors, String title, String description, String publicationDate, int noOfCopies,
            DLinkedList<User> borrowers) {
=======
import LibGUI.Login;
import java.util.Objects;

public class Book {
    private String title, description, publicationDate;
    private final int bookId;
    private int noOfCopies;
    private QueueLinkedList requesters, borrowers;
    private DLinkedList<String> authors;
    private int totalCopies; // Max copies

    public Book(int bookId, DLinkedList<String> authors, String title, String description, String publicationDate,
            int noOfCopies, QueueLinkedList borrowers) {
        this.bookId = bookId;
>>>>>>> Stashed changes
        this.authors = authors;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
<<<<<<< Updated upstream
=======
        this.noOfCopies = noOfCopies;
>>>>>>> Stashed changes
        this.totalCopies = noOfCopies;
        this.borrowers = borrowers;
    }

<<<<<<< Updated upstream
=======
    // public Book(DLinkedList<String> authors, String title, String description,
    // String publicationDate) {
    // this(authors, title, description, publicationDate, 0, null);
    // }

>>>>>>> Stashed changes
    public Book() {
        this(0, null, null, null, null, 0, null);
    }

    // Setter to generate a unique book ID
    public int bookId() {
        BookLibrary bookFetcher = new BookLibrary();

        int uniqueId = 0; // Start generating IDs from 0 or any other base value

        while (true) {
            boolean isUnique = true;
            DNode<Book> currNode = bookFetcher.bookshelf.head;
            while (currNode != null) {
                if (currNode.getItem().getBookId() == uniqueId) { // Assuming Book has a getBookId method
                    isUnique = false;
                    break;
                }
                currNode = currNode.getNext();
            }
            if (isUnique) {
                return uniqueId;
            }
            uniqueId++;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

<<<<<<< Updated upstream
=======
    public void setAuthors(DLinkedList<String> authors) {
        this.authors = authors;
    }

>>>>>>> Stashed changes
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublicatonDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setNoOfCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public String addAuthor(String author) {
        if (authors == null) {
            authors = new DLinkedList<>(); // ensure authors list is initialized
        }
        authors.addLast(author);

        return authors.toString();
    }

    public Object removeAuthor(String author) {
        Object removedAuthor;
        if (!authors.isFound(author)) {
            return null;
        } else {
            int pos = authors.getItemPosition(author);
            removedAuthor = authors.getItemAt(pos);
            authors.deleteItemAt(pos);
        }
        return removedAuthor;
    }

<<<<<<< Updated upstream
    public String addBorrower(User user) {
        if (borrowers == null)
            borrowers = new DLinkedList<>();
        borrowers.addLast(user);
        return borrowers.toString();
=======
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setBorrowersList(QueueLinkedList borrowers) {
        this.borrowers = borrowers;
>>>>>>> Stashed changes
    }

    // getters
    public int getBookId() {
        return bookId;
    }

    public String getAuthors() {
<<<<<<< Updated upstream
        if (authors == null) {
            return "No Author/s";
        } else
            return authors.toString();
=======
        if (authors == null || authors.isEmpty()) {
            return "\tNo authors";
        } else
            return authors.toString();
    }

    public DLinkedList<String> getAuthorsList() {
        return authors;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
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
        if (borrowers.isEmpty()) {
            return null;
        } else {
            Node currNode = borrowers.head;
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

>>>>>>> Stashed changes
    public int getTotalCopies() {
        return totalCopies;
    }

    public DLinkedList<User> getBorrowerDLinkedList() {
        return borrowers;
    }

    public String getBorrowersString() { // this will be primarily used in the books txt file
        StringBuilder sb = new StringBuilder(); // the purpose of this method is that so that we can list the borrowers
        DNode<User> p; // of this.book; otherwise, i think we will be listing all the details
        if (borrowers == null) { // of the users in the text file
            return "No Borrower/s";
        } else {
            p = borrowers.head;
            while (p != null) {
                sb.append(p.getItem().getKey());
                if (p.getNext() != null) {
                    if (p.getNext().getNext() == null) { // if next node is tial
                        sb.append(" & ");
                    } else {
                        sb.append(", ");
                    }
                }
                p = p.getNext();
            }
            return sb.toString();
        }
    }

    // queue
    public void bookRequest(User borrower) { // if book is not available put the users in requesters
        if (!isAvailable())
            requesters.enqueue(borrower);
        // else
        // borrowers.addFront(requesters.dequeue()); // will need to add an admin
        // section where they manage these
    }

    public boolean isAvailable() {
        return borrowers.count < totalCopies;
    }

    public String bookReturned(User borrower) {
        if (borrowers.count <= totalCopies && borrowers != null) {
            if (!borrowers.isFound(borrower)) {
                return "Borrower Not Found.";
            } else {
                borrowers.deleteItemAt(borrowers.getItemPosition(borrower));
                return borrower + " Returned Book.";
            }
        }
        return "No Borrowed Book/s.";
    }

    // toString
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getAuthors() + ":" + getTitle() + "//" + getDescription() + "//" + getPublicationDate() + "//"
                + getTotalCopies());

        return sb.toString();
    }

}
