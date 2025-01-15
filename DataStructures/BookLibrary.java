package DataStructures;

import LibGUI.Login;
import Objects.Book;
import Objects.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookLibrary {
    public BookLibrary() {
        getBooks();
    }

    private Set<Integer> usedBookIds = new HashSet<>();
    public DLinkedList<Book> bookshelf = new DLinkedList<>();

    // Adding Method/s
    public void addBook(DLinkedList<String> authors, String title, String description, String publicationDate,
            int noOfCopies, QueueLinkedList borrowers, QueueLinkedList requesters) {
        int bookId = generateUniqueBookId();
        Book newBook = new Book(bookId, authors, title, description, publicationDate, noOfCopies, borrowers,
                requesters);
        bookshelf.addLast(newBook);
    }

    public void addBook(Book book) {
        bookshelf.addLast(book);
    }

    private int generateUniqueBookId() { // this is inefficient in larger data sets
        int uniqueId = 0;
        DNode<Book> currNode = bookshelf.head;
        while (currNode != null) {
            usedBookIds.add(currNode.getItem().getBookId());
            currNode = currNode.getNext();
        }
        while (usedBookIds.contains(uniqueId)) {
            uniqueId++;
        }
        usedBookIds.add(uniqueId);
        return uniqueId;
    }

    // Searching Methods

    public Book searchBookwithKey(int key) {
        DNode<Book> currBook = bookshelf.head;
        while (currBook != null) {
            if (currBook.getItem().getBookId() == key) {
                return currBook.getItem();
            }
            currBook = currBook.getNext();
        }
        return null;
    }

    public DLinkedList<Book> searchTitle(String title) { // returns a lists of books that contains the keyword
        // System.out.println("Library Test > searchTitle()");
        DLinkedList<Book> results = new DLinkedList<>();
        DNode<Book> temp = bookshelf.head; // Use DNode<Book> for type safety

        while (temp != null) {
            Book currBook = temp.getItem(); // Item is now a Book, no need for casting
            if (currBook.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.addLast(currBook); // add Book to results
            }
            temp = temp.getNext();
        }

        return results;
    }

    public DLinkedList<Book> searchAuthor(String author) { // returns a lists of books that contains the keyword
        DLinkedList<Book> results = new DLinkedList<>();
        DNode<Book> temp = bookshelf.head;

        while (temp != null) {
            Book currBook = temp.getItem(); // Item is now a Book, no need for casting
            if (currBook.getAuthors().toLowerCase().contains(author.toLowerCase())) {
                results.addLast(currBook); // add Book to results
            }
            temp = temp.getNext();
        }
        return results;
    }

    public void getBooks() {
        BufferedReader reader = null;
        // System.out.println("Library Test > getBooks()");
        try {
            reader = new BufferedReader(
                    new FileReader(
                            "LandingPagesGUI\\AdminAcess\\Books.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] separator = line.split(":", 4);
                if (separator.length == 4) {
                    String authorsPart = separator[0].trim(); // authors part
                    String bookPart = separator[1].trim(); // book detail
                    String borrowerPart = separator[2].trim(); // borrowers
                    String requesterPart = separator[3].trim(); // requesters

                    String[] authorsArray = authorsPart.split("[,&]");
                    String[] bookDetails = bookPart.split("//");
                    String[] borrowersArray = borrowerPart.split("[,&]");
                    String[] requestersArray = requesterPart.split("[,&]");

                    DLinkedList<String> authors = new DLinkedList<>();
                    for (String author : authorsArray) {
                        if (!author.equals("No Author/s") && !author.isBlank()) {
                            authors.addLast(author.trim()); // trim to delete the leading and trailing white spaces
                        }
                    }

                    QueueLinkedList borrowers = new QueueLinkedList();
                    for (String borrower : borrowersArray) {
                        if (!borrower.equals("No Borrower/s") && !borrower.isBlank()) {
                            borrowers.enqueue(Integer.parseInt(borrower.trim()));
                        }
                    }

                    QueueLinkedList requesters = new QueueLinkedList();
                    for (String requester : requestersArray) {
                        if (!requester.equals("No Requester/s") && !requester.isBlank()) {
                            requesters.enqueue(Integer.parseInt(requester.trim()));
                        }
                    }

                    Book newBook = new Book(Integer.parseInt(bookDetails[0]), authors, bookDetails[1], bookDetails[2],
                            bookDetails[3],
                            Integer.parseInt(bookDetails[4]), borrowers, requesters);
                    if (!bookshelf.isFound(newBook)) { // anti duplication
                        addBook(newBook);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateFile(Book newBook, boolean append) {
        // System.out.println("Bookshelf: " + bookshelf);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("LandingPagesGUI\\AdminAcess\\Books.txt", append));
            if (append) {
                // Append only the new book entry
                String bookDetails = String.format("%s : %d//%s//%s//%s//%d : %s : %s",
                        newBook.getAuthors().trim(),
                        newBook.getBookId(),
                        newBook.getTitle(),
                        newBook.getDescription(),
                        newBook.getPublicationDate(),
                        newBook.getTotalCopies(),
                        newBook.getBorrowersKeys().trim(),
                        newBook.getRequestersKeys());
                writer.write(bookDetails);
                writer.newLine();
            } else {
                System.out.println("Overwriting file with current bookshelf contents...");
                // overwrite the entire file with the current state of the bookshelf
                DNode<Book> currNode = bookshelf.head;
                while (currNode != null) {
                    Book currBook = currNode.getItem();
                    String bookDetails = String.format("%s : %d//%s//%s//%s//%d : %s",
                            currBook.getAuthors().trim(),
                            currBook.getBookId(),
                            currBook.getTitle(),
                            currBook.getDescription(),
                            currBook.getPublicationDate(),
                            currBook.getTotalCopies(),
                            currBook.getBorrowersKeys().trim());

                    // System.out.println("Writing book: " + bookDetails); // Debugging
                    writer.write(bookDetails);
                    writer.newLine();

                    currNode = currNode.getNext();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean removeBook(Book bookToRemove) {

        if (bookToRemove == null)
            return false;

        if (isBookFound(bookToRemove)) {

            int pos = getBookPosition(bookToRemove);

            System.out.println("Book Position: " + pos);
            System.out.println("Book title found: " + bookshelf.getItemAt(pos));

            bookshelf.deleteItemAt(pos);
            updateFile(null, false);
            // System.out.print(bookshelf.toString());
            return true;
        }

        return false;
    }

    public boolean updateBookDetails(Book newBook, Book oldBook) {

        if (newBook == null || oldBook == null) {
            return false;
        }

        if (isBookFound(oldBook)) {

            int pos = getBookPosition(oldBook);

            removeBook(oldBook);
            bookshelf.insertAt(newBook, pos);
            updateFile(null, false);
            return true;
        }

        return false;
    }

    public boolean isBookFound(Book book) {

        DNode<Book> p = bookshelf.head;
        System.out.println(book.getTitle());
        System.out.println("Bookshelf" + bookshelf);
        while (p != null) {
            if (book.equals(p.getItem()))
                return true;
            p = p.getNext();
        }

        return false;
    }

    public int getBookPosition(Book book) {
        if (isBookFound(book)) {

            DNode<Book> p = bookshelf.head;

            while (p != null) {
                if (book.equals(p.getItem()))
                    return bookshelf.getItemPosition((Book) p.getItem());
                p = p.getNext();
            }
        }
        return 0;
    }

    // public void updateFile() {
    // BufferedWriter writer = null;
    // try {
    // writer = new BufferedWriter(new FileWriter(
    // "LandingPagesGUI\\AdminAcess\\Books.txt"));

    // DNode<Book> currNode = bookshelf.head;
    // while (currNode != null) {
    // Book currBook = currNode.getItem();
    // String authors = currBook.getAuthors().trim();

    // String bookDetails = String.format("%s : %s//%s//%s//%d : %s", // there was a
    // more convenient way to do it?
    // authors,
    // currBook.getTitle(),
    // currBook.getDescription(),
    // currBook.getPublicationDate(),
    // currBook.getTotalCopies(),
    // currBook.getBorrowersKeys());

    // writer.write(bookDetails);
    // writer.newLine();

    // currNode = currNode.getNext();
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    // if (writer != null) {
    // try {
    // writer.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    // }

    public void approveBorrowRequest(Book book) {
        if (book.isAvailable() && !book.getRequestersQueue().isEmpty()) {
            int userKey = (int) book.getRequestersQueue().dequeue();
            Login login = new Login();
            User approvedUser = login.accounts.getUser(userKey);
            book.addBorrower(approvedUser);
            approvedUser.addBorrowedBook(book);
            book.setNoOfCopies(-1); // Decrease the number of available copies
            System.out.println("Approved User: " + approvedUser.getFirstName());
        } else {
            System.out.println("No available copies or no requesters.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookLibrary bookFetcher = new BookLibrary();
        Book currBook = bookFetcher.bookshelf.head.getItem();

        while (true) {
            System.out.println("Current Book: " + currBook.getTitle());
            System.out.println("Borrowers: " + currBook.getBorrowersKeys());
            System.out.println("Requesters: " + currBook.getRequestersKeys());
            System.out.println("Do you want to approve the next requester? (yes/no)");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("yes")) {
                bookFetcher.approveBorrowRequest(currBook);
            } else {
                System.out.println("Approval process terminated.");
                break;
            }
        }

        scanner.close();
    }

    // public static void main(String[] args) { // borrow
    // BookLibrary bookFetcher = new BookLibrary();
    // Book currBook = bookFetcher.bookshelf.head.getItem();
    // QueueLinkedList queue = currBook.getBorrowers();
    // // a much easier way to do it
    // // User currUser = (User)
    // // bookFetcher.bookshelf.head.getItem().getBorrowers().qFront();
    // User frontUser = (User) queue.qFront();
    // System.out.println("Front User: " + frontUser.getFirstName()); // borrower
    // System.out.println("Requester: " + currBook.getRequestersKeys());

    // }

}
