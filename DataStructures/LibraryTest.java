package DataStructures;

import Objects.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LibraryTest {
    public DLinkedList<Book> bookshelf = new DLinkedList<>();

    // Adding Method/s
    public void addBook(MyLinkedList authors, String title, String description, String publicationDate,
            int noOfCopies) {
        bookshelf.addLast(new Book(authors, title, description, publicationDate, noOfCopies));
    }

    public void addBook(Book book) {
        bookshelf.addLast(book);
    }

    // Deleting Method/s

    // Searching Methods
    public DLinkedList<Book> searchTitle(String title) { // returns a lists of books that contains the keyword
        DLinkedList<Book> results = new DLinkedList<>();
        DNode<Book> temp = bookshelf.head; // Use DNode<Book> for type safety

        while (temp != null) {
            Book currBook = temp.getItem(); // Item is now a Book, no need for casting
            if (currBook.getTitle().contains(title)) {
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
            if (currBook.getAuthors().contains(author)) {
                results.addLast(currBook); // add Book to results
            }
            temp = temp.getNext();
        }
        return results;
    }

    public void getBooks() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new FileReader(
                            "C:\\Users\\Ashee\\Documents\\GitHub\\MajorProjectCC214\\LandingPagesGUI\\AdminAcess\\Books.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] separator = line.split(":", 2);
                if (separator.length == 2) {
                    String authorsPart = separator[0].trim(); // authors part
                    String bookPart = separator[1].trim(); // book detail
    
                    String[] authorsArray = authorsPart.split("-->");
                    String[] bookDetails = bookPart.split("//");
    
                    MyLinkedList authors = new MyLinkedList();
                    for (String author : authorsArray) {
                        authors.addLast(author.trim()); // trim to delete the leading and trailing white spaces
                    }
    
                    addBook(authors, bookDetails[0], bookDetails[1], bookDetails[2],
                            Integer.parseInt(bookDetails[3]));
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
                    "C:\\Users\\Ashee\\Documents\\GitHub\\MajorProjectCC214\\LandingPagesGUI\\AdminAcess\\Books.txt"));

            DNode<Book> currNode = bookshelf.head;
            while (currNode != null) {
                Book currBook = currNode.getItem();
                String authors = currBook.getAuthors().trim();

                String bookDetails = String.format("%s : %s//%s//%s//%d", // there was a more convenient way to do it?
                        authors,
                        currBook.getTitle(),
                        currBook.getDescription(),
                        currBook.getPublicationDate(),
                        currBook.getTotalCopies());

                writer.write(bookDetails);
                writer.newLine();

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

    public static void main(String[] args) {
        MyLinkedList author1 = new MyLinkedList();
        author1.addLast("Peter");
        author1.addLast("JK Rowling");
        Book book1 = new Book(author1, "Harry Potter", "Desc1", "12/23/24", 1);

        MyLinkedList author2 = new MyLinkedList();
        author2.addLast("Pete");
        author2.addLast("John Rowling");
        Book book2 = new Book(author2, "Porter Harry", "Desc1", "12/23/24", 1);

        LibraryTest lib = new LibraryTest();
        lib.getBooks();
        // Book currBook = (Book) lib.bookshelf.head.getItem(); // take note of this my guy
        // System.out.println(currBook.getAuthors());

        lib.addBook(book1);
        lib.addBook(book2);
        //lib.updateFile();
        System.out.println(lib.bookshelf);

    }

}
