package Objects;

import DataStructures.DLinkedList;
import DataStructures.DNode;
import DataStructures.QueueLinkedList;

public class Book {
    private String title, description, publicationDate;
    private int totalCopies;
    private QueueLinkedList<User> requesters;
    private DLinkedList<String> authors;
    private DLinkedList<User> borrowers;

    public Book(DLinkedList<String> authors, String title, String description, String publicationDate, int noOfCopies,
            DLinkedList<User> borrowers) {
        this.authors = authors;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.totalCopies = noOfCopies;
        this.borrowers = borrowers;
    }

    public Book() {
        this(null, null, null, null, 0, null);
    }

    // setters
    public void setTitle(String title) {
        this.title = title;
    }

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

    public String addBorrower(User user) {
        if (borrowers == null)
            borrowers = new DLinkedList<>();
        borrowers.addLast(user);
        return borrowers.toString();
    }

    // getters
    public String getAuthors() {
        if (authors == null) {
            return "No Author/s";
        } else
            return authors.toString();
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
