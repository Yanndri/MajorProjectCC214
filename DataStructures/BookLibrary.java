package DataStructures;

import Objects.Book;
import Objects.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BookLibrary {
    public BookLibrary(){
        getBooks();
    }

    public DLinkedList<Book> bookshelf = new DLinkedList<>();
    //public Login userAccounts = new Login();

    // Adding Method/s
    public void addBook(DLinkedList<String> authors, String title, String description, String publicationDate,
            int noOfCopies, DLinkedList<Integer> borrowers) {
        bookshelf.addLast(new Book(authors, title, description, publicationDate, noOfCopies, borrowers));
    }

    public void addBook(Book book) {
        bookshelf.addLast(book);
    }

    // Deleting Method/s

    // Searching Methods
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
                    String borrowerPart = separator[2].trim(); // borrowers

                    System.out.println("borrowers: "+borrowerPart);
                    System.out.println("authors: "+authorsPart);
                    System.out.println("book: "+bookPart);

                    String[] authorsArray = authorsPart.split("[,&]");
                    String[] bookDetails = bookPart.split("//");
                    String[] borrowersArray = borrowerPart.split("[,&]");

                    DLinkedList<String> authors = new DLinkedList<>();
                    for (String author : authorsArray) {
                        if(!author.equals("No Author/s") && !author.isBlank()){
                            authors.addLast(author.trim()); // trim to delete the leading and trailing white spaces
                        }
                    }

                    DLinkedList<Integer> borrowers = new DLinkedList<>();
                    for (String borrower : borrowersArray){
                        if(!borrower.equals("No Borrower/s") && !borrower.isBlank()){
                        borrowers.addLast(Integer.parseInt(borrower.trim()));
                    }
                    }

                    addBook(authors, bookDetails[0], bookDetails[1], bookDetails[2],
                            Integer.parseInt(bookDetails[3]), borrowers);
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
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("LandingPagesGUI\\AdminAcess\\Books.txt", append)); // Open in append or write mode based on the parameter
    
            if (append) {
                // Append only the new book entry
                String bookDetails = String.format("%s : %s//%s//%s//%d : %s",
                        newBook.getAuthors().trim(),
                        newBook.getTitle(),
                        newBook.getDescription(),
                        newBook.getPublicationDate(),
                        newBook.getTotalCopies(),
                        newBook.getBorrowersKeys().trim());
    
                writer.write(bookDetails);
                writer.newLine();
            } else {
                // Overwrite the entire file with the current state of the bookshelf
                DNode<Book> currNode = bookshelf.head;
                while (currNode != null) {
                    Book currBook = currNode.getItem();
                    String bookDetails = String.format("%s : %s//%s//%s//%d : %s",
                            currBook.getAuthors().trim(),
                            currBook.getTitle(),
                            currBook.getDescription(),
                            currBook.getPublicationDate(),
                            currBook.getTotalCopies(),
                            currBook.getBorrowersKeys());
    
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
    



    // public void updateFile() {
    //     BufferedWriter writer = null;
    //     try {
    //         writer = new BufferedWriter(new FileWriter(
    //                 "LandingPagesGUI\\AdminAcess\\Books.txt"));

    //         DNode<Book> currNode = bookshelf.head;
    //         while (currNode != null) {
    //             Book currBook = currNode.getItem();
    //             String authors = currBook.getAuthors().trim();

    //             String bookDetails = String.format("%s : %s//%s//%s//%d : %s", // there was a more convenient way to do it?
    //                     authors,
    //                     currBook.getTitle(),
    //                     currBook.getDescription(),
    //                     currBook.getPublicationDate(),
    //                     currBook.getTotalCopies(),
    //                     currBook.getBorrowersKeys());

    //             writer.write(bookDetails);
    //             writer.newLine();

    //             currNode = currNode.getNext();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } finally {
    //         if (writer != null) {
    //             try {
    //                 writer.close();
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    // }

    public static void main(String[] args) {
    // DLinkedList author1 = new DLinkedList();
    // author1.addLast("Peter");
    // author1.addLast("JK Rowling");
    // Book book1 = new Book(author1, "Harry Potter", "Desc1", "12/23/24", 1);

    // DLinkedList author2 = new DLinkedList();
    // author2.addLast("Pete");
    // author2.addLast("John Rowling");
    // Book book2 = new Book(author2, "Porter Harry", "Desc1", "12/23/24", 1);

    BookLibrary lib = new BookLibrary();
    //lib.getBooks();
    Book currBook = (Book) lib.bookshelf.head.getItem(); // take note of this my guy

    System.out.println("Head Author: "+currBook.getAuthorsList().head.getItem());
    
    System.out.println(currBook.getAuthors());
    //currBook.addAuthor("Test");
    System.out.println(currBook.removeAuthor("Test"));
    System.out.println("Borrowers: "+currBook.getBorrowersKeys()); 
    DLinkedList<User> headBorrowers = currBook.getBorrowers();
    User user = headBorrowers.head.getItem();
    System.out.println("Borrower User: "+user.getFirstName());

    System.out.println(currBook.getAuthors());
    


    lib.updateFile(null,false);

    }

}
