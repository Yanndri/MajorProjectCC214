package Objects;

import DataStructures.DLinkedList;
import DataStructures.QueueLinkedList;

public class Book {
    private String title, description, publicationDate;
    private int totalCopies, borrowedCopies;
    private QueueLinkedList requesters;
    private DLinkedList authors, borrowers;

    public Book(DLinkedList authors, String title, String description, String publicationDate, int noOfCopies, DLinkedList borrowers) {
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

    // getters
    public String getAuthors() {
        if (authors.isEmpty()) {
            return "\tNo authors";
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

    // queue
    public void bookRequest(Borrower borrower) {
        if (!isAvailable())
            requesters.enqueue(borrower);
    }

    public void addBorrower(Borrower borrower) {
        if (isAvailable())
            borrowers.addFront(borrower);
    }

    public boolean isAvailable() {
        return borrowers.count < totalCopies;
    }

    public String bookReturned(User borrower) {
        if (borrowedCopies <= totalCopies && borrowers != null) {
            if (!borrowers.isFound(borrower)) {
                return "Borrower Not Found.";
            } else {
                borrowers.deleteItemAt(borrowers.getItemPosition(borrower));
                borrowedCopies--;
                return borrower + " Returned Book.";
            }
        }
        return "No Borrowed Book/s.";
    }

    // toString
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("\tBook Title: " + title + "\n\n\tAuthor/s : " + getAuthors() + "\n\n\tPublication Date: "
                + publicationDate + "\n\n\tDescription: " + description);

        return sb.toString();
    }

}
