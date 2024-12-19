import java.util.Scanner;

public class LibraryTest {

    // Searching Methods
    public String searchTitle(DLinkedList<Book> list, String title) {
        DLinkedList<Book> results = new DLinkedList<>();
        DNode<Book> temp = list.head; // Use DNode<Book> for type safety

        while (temp != null) {
            Book currBook = temp.getItem(); // Item is now a Book, no need for casting
            if (currBook.getTitle().contains(title)) {
                results.addLast(currBook); // add Book to results
            }
            temp = temp.getNext();
        }
        return results.toString();
    }

    public String searchAuthor(DLinkedList<Book> list, String author) {
        DLinkedList<Book> results = new DLinkedList<>();
        DNode<Book> temp = list.head;

        while (temp != null) {
            Book currBook = temp.getItem(); // Item is now a Book, no need for casting
            if (currBook.getAuthors().contains(author)) {
                results.addLast(currBook); // add Book to results
            }
            temp = temp.getNext();
        }
        return results.toString();
    }

    public static void main(String[] args) {

        Scanner scanf = new Scanner(System.in);
        DLinkedList<Book> list = new DLinkedList<>();
        LibraryTest lib = new LibraryTest();
        byte choice = 0;

        System.out.println("Bookshelf");
        while (choice != 4) {
            System.out.print("\nManage Books: \n\t1.) ADD Book \n\t2.) REMOVE Book\n\t3.) SEARCH\n\t4.) EXIT\n\t>> ");
            choice = scanf.nextByte();
            scanf.nextLine(); // "ENTER" (\n) consumer

            switch (choice) {
                case 1:
                    System.out.print("Add Book: ");
                    Book newBook = new Book();

                    System.out.print("\nTitle: ");
                    newBook.setTitle(scanf.nextLine());

                    System.out.print("\nAuthor: ");
                    newBook.addAuthor(scanf.nextLine());

                    System.out.print("\nDescription: ");
                    newBook.setDescription(scanf.nextLine());

                    System.out.print("\nPublication Date: ");
                    newBook.setPublicatonDate(scanf.nextLine());

                    System.out.print("\nNo. of Copies: ");
                    newBook.setNoOfCopies(scanf.nextInt());
                    scanf.nextLine(); // "ENTER" (\n) consumer

                    list.addLast(newBook);
                    break;
                case 3:
                    System.out.println("Search by Title: ");
                    String result = lib.searchTitle(list, scanf.nextLine());
                    System.out.println(result);

            }
        }
    }

}
