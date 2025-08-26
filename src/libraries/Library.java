package libraries;

import java.util.ArrayList;
import java.util.List;

import books.Book;

public class Library {
    private List<Book> books;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
    }

    // Methods
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void lendBook(String ISBN) {
        Book book = findBook(ISBN);
        if (book != null) {
            if (!book.isLent()) {
                book.lendBook();
                System.out.println("Book lent successfully!");
            } else {
                System.out.println("Book is already lent.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(String ISBN) {
        Book book = findBook(ISBN);
        if (book != null) {
            if (book.isLent()) {
                book.returnBook();
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Book is not lent.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void listAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isLent()) {
                System.out.println(book);
            }
        }
    }

    public Book findBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }
}
