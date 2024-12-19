public class Book {
    private String title, description, publicationDate;
    private int totalCopies, borrowedCopies;
    private MyLinkedList borrowers;
    private QueueLinkedList requesters;
    private MyLinkedList authors;

    public Book(MyLinkedList authors, String title, String description, String publicationDate, int noOfCopies) {
        this.authors = authors;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.totalCopies = noOfCopies;
    }

    public Book() {
        this(null, null, null, null, 0);
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
            authors = new MyLinkedList();  //ensure authors list is initialized
        }
        authors.addFront(author);
        return authors.getFirstElement().toString();
    }

    public Object removeAuthor(String author) {
        Object removedAuthor;
        if (!authors.isFound(author)) {
            return null;
        } else {
            int pos = authors.getPosition(author);
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

    public String getBorrowers() {
        return borrowers.toString();
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    // queue
    public void bookRequest(Borrower borrower){
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

    public String bookReturned(Borrower borrower) {
        if(borrowedCopies <= totalCopies && borrowers!=null){
        if(!borrowers.isFound(borrower)){
            return "Borrower Not Found.";
        }
        else{
            borrowers.deleteItemAt(borrowers.getPosition(borrower));
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
