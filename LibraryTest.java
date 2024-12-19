
import java.util.Scanner;

public class LibraryTest extends DLinkedList<Book> {

    // Searching Methods
    public static String searchTitle(DLinkedList<Book> list, String title) {
        DLinkedList<Book> results = new DLinkedList<>();
        DNode<Book> temp = list.head;

        while (temp != null) {
            Book currBook = temp.getItem();
            if (currBook.getTitle().contains(title)) {
                results.addLast(currBook);
            }
            temp = temp.getNext();
        }
        return results.toString();
    }

    public String searchAuthor(String author) {
        DLinkedList<Book> results = new DLinkedList<>();
        DNode<Book> temp = head;

        while (temp != null) {
            Book currBook = temp.getItem();
            if (currBook.getAuthors().contains(author)) {
                results.addLast(currBook);
            }
            temp = temp.getNext();
        }
        return results.toString();
    }

    public static void main(String[] args) {

        Scanner scanf = new Scanner(System.in);
        DLinkedList<Book> list = new DLinkedList<>();
        byte choice = 0;

        System.out.println("Bookshelf");
        while (choice != 4) {
            System.out.print("\nManage Books: \n\t1.) ADD Book \n\t2.) REMOVE Book\n\t3.) SEARCH\n\t4.) EXIT\n\t>> ");
            choice = scanf.nextByte();

            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Add Book: ");
                    Book newBook = new Book();

                    System.out.print("Title: ");
                    newBook.setTitle(scanf.nextLine());

                    // Read author name
                    System.out.print("Author: ");
                    newBook.addAuthor(scanf.nextLine());

                    // Read description
                    System.out.print("Description: ");
                    newBook.setDescription(scanf.nextLine());

                    // Read publication date
                    System.out.print("Publication Date: ");
                    newBook.setPublicatonDate(scanf.nextLine());

                    // Consume the leftover newline after reading the number of copies
                    System.out.print("\nNo. of Copies: ");
                    newBook.setNoOfCopies(scanf.nextInt());
                    scanf.nextLine();

                    list.addLast(newBook);
                    break;
                case 3:
                    System.out.println("Search by Title: ");
                    String result = searchTitle(list, scanf.nextLine());

            }
        }
    }

}
