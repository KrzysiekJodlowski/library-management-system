import java.util.Scanner;

import books.Book;
import books.EBook;
import books.PrintedBook;
import libraries.Library;
import users.User;

public class LibraryApp {
    private static Library library = new Library();
    private static User currentUser = null;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");

            if (currentUser == null) {
                System.out.println("1. Register as a user");
            } else {
                System.out.println("2. Borrow a book");
                System.out.println("3. Return a book");
                System.out.println("4. View borrowed books");
            }

            System.out.println("51. Add printed book to the library");
            System.out.println("52. Add e-book book to the library");
            System.out.println("6. Remove a book from the library");
            System.out.println("7. Lend a book to a user");
            System.out.println("8. List available books in the library");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    if (currentUser == null) {
                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                        currentUser = new User(name);
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("User already registered.");
                    }
                    break;
                case 2:
                    borrowBookForUser();
                    break;
                case 3:
                    returnBookForUser();
                    break;
                case 4:
                    currentUser.listBorrowedBooks();
                    break;
                case 51:
                    addPrintedBookToLibrary();
                    break;
                case 52:
                    addEBookToLibrary();
                    break;
                case 6:
                    removeBookFromLibrary();
                    break;
                case 7:
                    lendBookToUser();
                    break;
                case 8:
                    library.listAvailableBooks();
                    break;
                case 9:
                    System.out.println("Thank you for using the Library Management System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void borrowBookForUser() {
        if (currentUser == null) {
            System.out.println("Please register as a user first.");
            return;
        }
        System.out.print("Enter book ISBN to borrow: ");
        String ISBN = scanner.nextLine();
        Book bookToBorrow = library.findBook(ISBN);
        if (bookToBorrow != null && !bookToBorrow.isLent()) {
            library.lendBook(ISBN);
            currentUser.borrowBook(bookToBorrow);
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Book not available.");
        }
    }

    private static void returnBookForUser() {
        if (currentUser == null) {
            System.out.println("Please register as a user first.");
            return;
        }
        System.out.print("Enter book ISBN to return: ");
        String ISBN = scanner.nextLine();
        Book bookToReturn = library.findBook(ISBN);
        if (bookToReturn != null && bookToReturn.isLent()) {
            library.returnBook(ISBN);
            currentUser.returnBook(bookToReturn);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found or not borrowed by you.");
        }
    }

    private static void addPrintedBookToLibrary() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter number of pages: ");
        int numberOfPages = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter cover type (e.g., hardcover, paperback): ");
        String coverType = scanner.nextLine();

        library.addBook(new PrintedBook(title, author, ISBN, numberOfPages, coverType));
        System.out.println("Printed book added successfully!");
    }

    private static void addEBookToLibrary() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter file size (in MB): ");
        double fileSize = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter file format (e.g., PDF, EPUB): ");
        String fileFormat = scanner.nextLine();

        library.addBook(new EBook(title, author, ISBN, fileSize, fileFormat));
        System.out.println("E-Book added successfully!");
    }


    private static void removeBookFromLibrary() {
        System.out.print("Enter book ISBN to remove: ");
        String removeISBN = scanner.nextLine();
        Book bookToRemove = library.findBook(removeISBN);
        if (bookToRemove != null) {
            library.removeBook(bookToRemove);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void lendBookToUser() {
        if (currentUser == null) {
            System.out.println("Please register as a user first.");
            return;
        }
        System.out.print("Enter book ISBN to lend: ");
        String lendISBN = scanner.nextLine();
        Book bookToLend = library.findBook(lendISBN);
        if (bookToLend != null && !bookToLend.isLent()) {
            library.lendBook(lendISBN);
            currentUser.borrowBook(bookToLend);
            System.out.println("Book lent successfully!");
        } else {
            System.out.println("Book not available.");
        }
    }

}
