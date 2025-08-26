package users;

import java.util.ArrayList;
import java.util.List;

import books.Book;
public class User {
    private String name;
    private List<Book> borrowedBooks;

    // Constructor
    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Methods
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void listBorrowedBooks() {
        System.out.println(name + " has borrowed the following books:");
        for (Book book : borrowedBooks) {
            System.out.println(book);
        }
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
