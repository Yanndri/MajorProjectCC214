package DataStructures;

import LibGUI.Login;
import Objects.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BookLibrary {
<<<<<<< Updated upstream
    public DLinkedList<Book> bookshelf = new DLinkedList<>();
    Login accounts = new Login();

    // Adding Method/s
    public void addBook(DLinkedList<String> authors, String title, String description, String publicationDate,
            int noOfCopies, DLinkedList<User> borrowers) {
        bookshelf.addLast(new Book(authors, title, description, publicationDate, noOfCopies, borrowers));
=======
    private Set<Integer> usedBookIds = new HashSet<>();
    public DLinkedList<Book> bookshelf = new DLinkedList<>();

    public BookLibrary() {
        getBooks();
    }

    // Adding Method/s
    public void addBook(DLinkedList<String> authors, String title, String description, String publicationDate,
            int noOfCopies, QueueLinkedList borrowers) {
        int bookId = generateUniqueBookId();
        Book newBook = new Book(bookId, authors, title, description, publicationDate, noOfCopies, borrowers);
        bookshelf.addLast(newBook);
>>>>>>> Stashed changes
    }

    public void addBook(Book book) {
        bookshelf.addLast(book);
    }

    // Deleting Method/s

    // Searching Methods

    public Book searchBookwithKey(int key){
        DNode<Book> currBook = bookshelf.head;
        while(currBook != null){
            if (currBook.getItem().getBookId() == key) {
                return currBook.getItem();
            }
            currBook = currBook.getNext();
        }
        return null;
    }

    public DLinkedList<Book> searchTitle(String title) { // returns a lists of books that contains the keyword
        System.out.println("Library Test > searchTitle()");
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

<<<<<<< Updated upstream
    // TEXT FILE METHODS
=======
    private int generateUniqueBookId() { // this is inefficient in larger data sets
        int uniqueId = 0;
        DNode<Book> currNode = bookshelf.head;
        while(currNode != null){
            usedBookIds.add(currNode.getItem().getBookId());
            currNode = currNode.getNext();
        }
        while (usedBookIds.contains(uniqueId)) {
            uniqueId++;
        }
        usedBookIds.add(uniqueId); 
        return uniqueId;
    } 

>>>>>>> Stashed changes
    public void getBooks() {
        BufferedReader reader = null;
        System.out.println("Library Test > getBooks()");
        try {
            reader = new BufferedReader(
                    new FileReader(
                            "LandingPagesGUI\\AdminAcess\\Books.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] separator = line.split(":", 3);
                if (separator.length == 3) {
                    String authorsPart = separator[0].trim(); // authors part
                    String bookPart = separator[1].trim(); // book detail
<<<<<<< Updated upstream
                    String borrowersPart = separator[2].trim(); // borrower
=======
                    String borrowerPart = separator[2].trim(); // borrowers
>>>>>>> Stashed changes

                    String[] authorsArray = authorsPart.split("[,&]");
                    String[] bookDetails = bookPart.split("//");
                    String[] borrowersArray = borrowersPart.split("[,&]");

                    DLinkedList<String> authors = new DLinkedList<>();
                    for (String author : authorsArray) {
                        if(!author.equals("No Author/s") && !author.isBlank())
                        authors.addLast(author.trim()); // trim to delete the leading and trailing white spaces
                    }

<<<<<<< Updated upstream
                    DLinkedList<User> borrowers = new DLinkedList<>();
                    for (String borrower : borrowersArray){
                        if(!borrower.equals("No Borrower/s") && !borrower.isBlank()){
                        borrowers.addLast(accounts.accounts.getUser(Integer.parseInt(borrower.trim())));
                    }
                    }

                    addBook(authors, bookDetails[0], bookDetails[1], bookDetails[2],
                            Integer.parseInt(bookDetails[3]), borrowers);
=======
                    QueueLinkedList borrowers = new QueueLinkedList();
                    for (String borrower : borrowersArray) {
                        if (!borrower.equals("No Borrower/s") && !borrower.isBlank()) {
                            borrowers.enqueue(Integer.parseInt(borrower.trim()));
                        }
                    }

                    Book newBook = new Book(Integer.parseInt(bookDetails[0]), authors, bookDetails[1], bookDetails[2],
                            bookDetails[3],
                            Integer.parseInt(bookDetails[4]), borrowers);
                    if (!bookshelf.isFound(newBook)) { // anti duplication
                        addBook(newBook);
                    }
>>>>>>> Stashed changes
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

    public void updateFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(
                    "LandingPagesGUI\\AdminAcess\\Books.txt"));

            DNode<Book> currNode = bookshelf.head;
            while (currNode != null) {
                Book currBook = currNode.getItem();
                // String authors = currBook.getAuthors().trim();
                // String borrowers = currBook.getBorrowers().trim();

                String bookDetails = String.format("%s : %s//%s//%s//%d : %s", // there was a more convenient way to do it?
                        currBook.getAuthors().trim(),
                        currBook.getTitle(),
                        currBook.getDescription(),
                        currBook.getPublicationDate(),
                        currBook.getTotalCopies(),
                        currBook.getBorrowersString().trim());


                writer.write(bookDetails);
                writer.newLine();
<<<<<<< Updated upstream
=======
            } else {
                System.out.println("Overwriting file with current bookshelf contents...");
                // overwrite the entire file with the current state of the bookshelf
                DNode<Book> currNode = bookshelf.head;
                int bookId = 1;
                while (currNode != null) {
                    Book currBook = currNode.getItem();
                    String bookDetails = String.format("%s : %s//%s//%s//%d : %s",
                            currBook.getAuthors().trim(),
                            currBook.getTitle(),
                            currBook.getDescription(),
                            currBook.getPublicationDate(),
                            currBook.getTotalCopies(),
                            currBook.getBorrowersKeys());
>>>>>>> Stashed changes

                currNode = currNode.getNext();
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

<<<<<<< Updated upstream
    public static void main(String[] args) {
    // DLinkedList author1 = new DLinkedList();
    // author1.addLast("Peter");
    // author1.addLast("JK Rowling");
    // Book book1 = new Book(author1, "Harry Potter", "Desc1", "12/23/24", 1, null);

    // DLinkedList author2 = new DLinkedList();
    // author2.addLast("Pete");
    // author2.addLast("John Rowling");
    // Book book2 = new Book(author2, "Potter Harry", "Desc1", "12/23/24", 1, null);

    User user1 = new User("Asheerah", "Stop", "Bautro", 75, "Kangkong, Cordova", "Bayot", "09123456789","asheerahbokiboki", "jedgo123", 1675, null);

    BookLibrary lib = new BookLibrary();
    lib.getBooks();
    Book currBook = (Book) lib.bookshelf.head.getItem(); // take note of this my guy
    System.out.println(currBook.getAuthors());
    //currBook.addAuthor("TESTAUTHOR");
    //currBook.addBorrower(user1);
    User borrowerUser = (User) currBook.getBorrowerDLinkedList().head.getItem();
    System.out.println("Borrower: " + borrowerUser.getFirstName());

    // lib.addBook(book1);
    // lib.addBook(book2);
    lib.updateFile();
    //System.out.println(lib.bookshelf);
=======
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

    public static void main(String[] args) {
        // DLinkedList<String> author1 = new DLinkedList<>();
        // author1.addLast("Peter");
        // author1.addLast("JK Rowling");
        // Book book1 = new Book(0, author1, "Harry Potter", "Desc1", "12/23/24", 1,
        // null);
        // System.out.println("Book1 ID: "+book1.getBookId());
        // Book book2 = new Book(0, author1, "Harry Potter", "Desc1", "12/23/24", 1,
        // null);

        // // DLinkedList author2 = new DLinkedList();
        // // author2.addLast("Pete");
        // // author2.addLast("John Rowling");
        // // Book book2 = new Book(author2, "Porter Harry", "Desc1", "12/23/24", 1);

        // BookLibrary lib = new BookLibrary();
        // // lib.getBooks();
        // Book currBook = (Book) lib.bookshelf.head.getItem(); // take note of this my
        // guy

        // System.out.println("Head Author: " +
        // currBook.getAuthorsList().head.getItem());

        // System.out.println(currBook.getAuthors());
        // // currBook.addAuthor("Test");
        // System.out.println(currBook.removeAuthor("Test"));
        // System.out.println("Borrowers: " + currBook.getBorrowersKeys());
        // DLinkedList<User> headBorrowers = currBook.getBorrowers();
        // User user = headBorrowers.head.getItem();
        // System.out.println("Borrower User: " + user.getFirstName());

        // System.out.println(currBook.getAuthors());

        // lib.updateFile(null, false);
>>>>>>> Stashed changes

    }

}
