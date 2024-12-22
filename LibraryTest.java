
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
        MyLinkedList author1 = new MyLinkedList();
        author1.addLast("Peter");
        author1.addLast("JK Rowling");
        Book book1 = new Book(author1, "Harry Potter", "Desc1", "12/23/24", 1);

        MyLinkedList author2 = new MyLinkedList();
        author2.addLast("Pete");
        author2.addLast("John Rowling");
        Book book2 = new Book(author2, "Porter Harry", "Desc1", "12/23/24", 1);

        DLinkedList<Book> dlist = new DLinkedList<>();

        dlist.addLast(book2);
        dlist.addLast(book1);

        LibraryTest lib = new LibraryTest();
        System.out.println(lib.searchAuthor(dlist, "Pe"));

        DLinkedList<User> lib2 = new DLinkedList<>();
        User blnk = new User();
        lib2.addFront(blnk);
    }

}
