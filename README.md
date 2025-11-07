# Library Management System

A Java-based console application demonstrating Object-Oriented Programming (OOP) principles through a practical library management system. This project serves as an educational implementation showcasing encapsulation, abstraction, inheritance, and polymorphism in action.

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Architecture](#architecture)
- [OOP Principles in Practice](#oop-principles-in-practice)
- [Class Breakdown](#class-breakdown)
- [How the System Works](#how-the-system-works)
- [Running the Application](#running-the-application)
- [Testing](#testing)

## Overview

This Library Management System implements a complete book lending workflow, allowing users to:
- Register as library users
- Add books (printed and electronic) to the library inventory
- Borrow and return books
- View available books and personal borrowing history

The system is designed to demonstrate how OOP principles translate from theory into working code, making it an excellent learning resource for understanding object-oriented design.

## Project Structure

```
library-management-system/
├── src/
│   ├── LibraryApp.java              # Main application entry point
│   ├── books/
│   │   ├── Book.java                # Abstract base class for all books
│   │   ├── EBook.java               # Electronic book implementation
│   │   └── PrintedBook.java         # Physical book implementation
│   ├── libraries/
│   │   └── Library.java             # Library inventory management
│   └── users/
│       └── User.java                # User/borrower management
└── test/
    ├── books/
    │   └── PrintedBookTest.java     # JUnit tests for PrintedBook
    ├── libraries/
    │   └── LibraryTest.java         # JUnit + Mockito tests for Library
    └── users/
        └── UserTest.java            # JUnit tests for User
```

## Architecture

### Class Hierarchy

```
        Book (abstract)
         /    \
        /      \
PrintedBook   EBook
```

### Component Relationships

- **LibraryApp**: Main controller orchestrating user interactions
- **Library**: Manages book inventory and lending operations
- **User**: Tracks individual borrowers and their borrowed books
- **Book**: Abstract base class defining common book properties
- **PrintedBook/EBook**: Concrete implementations for different book types

The system uses **composition** (has-a relationships):
- Library HAS books
- User HAS borrowed books

## OOP Principles in Practice

### 1. Encapsulation

**What it is**: Bundling data and methods within a class while restricting direct access to internal state.

**How it's implemented**:

All classes use private fields with public getters/setters:

```java
// Book.java
private String title;
private String author;
private boolean isLent;

public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
```

The `Library` class encapsulates business logic for lending:

```java
// Library.java:15-28
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
```

### 2. Abstraction

**What it is**: Hiding complexity by exposing only essential features and defining contracts for subclasses.

**How it's implemented**:

The `Book` class is abstract and defines a contract through abstract methods:

```java
// Book.java:8
public abstract class Book {
    // Common implementation provided
    public abstract String getBookType(); // Must be implemented by subclasses
}
```

You cannot instantiate `Book` directly - you must create a specific type (PrintedBook or EBook). This represents the concept that a "book" is an abstract idea; only concrete types like "printed book" or "e-book" exist in reality.

### 3. Inheritance

**What it is**: Creating new classes based on existing classes to promote code reuse.

**How it's implemented**:

Both `PrintedBook` and `EBook` extend `Book`, inheriting common functionality:

```java
// PrintedBook.java:8-14
public class PrintedBook extends Book {
    private int numberOfPages;
    private String coverType;

    public PrintedBook(String title, String author, String ISBN,
                       int numberOfPages, String coverType) {
        super(title, author, ISBN);  // Inherits parent's constructor
        this.numberOfPages = numberOfPages;
        this.coverType = coverType;
    }
}
```

Both subclasses inherit:
- Fields: `title`, `author`, `ISBN`, `isLent`
- Methods: `lendBook()`, `returnBook()`, all getters/setters
- Must implement: `getBookType()` (abstract method)

### 4. Polymorphism

**What it is**: Same interface, different implementations; objects taking many forms.

**How it's implemented**:

#### Method Overriding

Each subclass provides its own implementation of abstract methods:

```java
// PrintedBook.java:48-51
@Override
public String getBookType() {
    return "Printed Book";
}

// EBook.java:44-47
@Override
public String getBookType() {
    return "E-Book";
}
```

#### Polymorphic Collections

The Library stores different book types in a single collection:

```java
// Library.java:8
private List<Book> books;  // Can hold PrintedBook AND EBook objects

public void addBook(Book book) {
    books.add(book);  // Accepts any Book subtype
}

public void listAvailableBooks() {
    for (Book book : books) {
        System.out.println(book);  // Calls correct toString() at runtime
    }
}
```

When `toString()` is called on each book, Java automatically invokes the correct version (PrintedBook or EBook) based on the actual object type.

## Class Breakdown

### Book.java (Abstract Base Class)

**Location**: `src/books/Book.java`

**Purpose**: Defines common properties and behaviors for all books.

**Key Components**:
- **Fields**: `title`, `author`, `ISBN`, `isLent` (all private)
- **Constructor**: Initializes book properties, sets `isLent = false`
- **Abstract Method**: `getBookType()` - forces subclasses to define their type
- **State Management**: `lendBook()` and `returnBook()` methods toggle lending status
- **String Representation**: Overrides `toString()` for readable output

### PrintedBook.java

**Location**: `src/books/PrintedBook.java`

**Purpose**: Represents physical books with page count and cover type.

**Additional Fields**:
- `numberOfPages` (int)
- `coverType` (String) - e.g., "hardcover", "paperback"

**Overridden Methods**:
- `getBookType()`: Returns "Printed Book"
- `toString()`: Extends parent's output with print-specific details

### EBook.java

**Location**: `src/books/EBook.java`

**Purpose**: Represents electronic books with file information.

**Additional Fields**:
- `fileSize` (double) - size in megabytes
- `fileFormat` (String) - e.g., "PDF", "EPUB", "MOBI"

**Overridden Methods**:
- `getBookType()`: Returns "E-Book"
- `toString()`: Extends parent's output with digital-specific details

### User.java

**Location**: `src/users/User.java`

**Purpose**: Represents a library patron who can borrow books.

**Key Components**:
- **Fields**: `name`, `borrowedBooks` (List<Book>)
- **Methods**:
  - `borrowBook(Book book)`: Adds book to user's collection
  - `returnBook(Book book)`: Removes book from user's collection
  - `listBorrowedBooks()`: Displays all borrowed books (uses polymorphism)

**Design Note**: The same Book object is referenced by both the Library's inventory and the User's borrowed list, ensuring state consistency.

### Library.java

**Location**: `src/libraries/Library.java`

**Purpose**: Central management system for library inventory and operations.

**Key Methods**:
- `addBook(Book book)`: Adds a book to inventory (polymorphic parameter)
- `removeBook(Book book)`: Removes a book from inventory
- `lendBook(String ISBN)`: Processes book lending with validation
- `returnBook(String ISBN)`: Processes book returns with validation
- `findBook(String ISBN)`: Locates books using linear search
- `listAvailableBooks()`: Displays non-lent books (demonstrates polymorphism)

**Business Logic**:
- Validates book existence before operations
- Checks lending status before lending/returning
- Provides user feedback for all operations

### LibraryApp.java

**Location**: `src/LibraryApp.java`

**Purpose**: Console-based user interface orchestrating all components.

**Architecture**:
- **Single Library instance**: Maintains one library throughout session
- **Current User tracking**: Manages logged-in user state
- **Menu-driven interface**: Infinite loop with switch-case for user choices

**Key Operations**:
1. User Registration (Option 1)
2. Borrow Book (Option 2)
3. Return Book (Option 3)
4. List User's Borrowed Books (Option 4)
5. Add Printed Book (Option 51)
6. Add E-Book (Option 52)
7. Remove Book (Option 6)
8. List Available Books (Option 8)
9. Exit (Option 9)

## How the System Works

### Borrowing Workflow

1. User selects "Borrow Book" from menu
2. `LibraryApp.borrowBookForUser()` prompts for ISBN
3. Calls `library.findBook(ISBN)` to locate the book
4. Validates book exists and is not already lent
5. Calls `library.lendBook(ISBN)` to mark book as lent
6. Calls `currentUser.borrowBook(book)` to track in user's list
7. Both Library and User now reference the same Book object

### Object References

The system uses shared object references:
- The same Book object exists in both `Library.books` and `User.borrowedBooks`
- Changes to `book.isLent()` are immediately visible everywhere
- No data duplication

### Search Algorithm

Book lookup uses linear search:

```java
// Library.java:50-57
public Book findBook(String ISBN) {
    for (Book book : books) {
        if (book.getISBN().equals(ISBN)) {
            return book;
        }
    }
    return null;
}
```

Time complexity: O(n) where n is the number of books.

## Running the Application

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- JUnit 4 (for running tests)
- Mockito (for running library tests)

### Compilation

```bash
# Compile all source files
javac src/LibraryApp.java src/books/*.java src/libraries/*.java src/users/*.java
```

### Execution

```bash
# Run the application
java -cp src LibraryApp
```

### Sample Session

```
--- Library Management System ---
1. Register as a user
2. Borrow a book
3. Return a book
4. List borrowed books
5. Add a printed book to the library
52. Add an e-book to the library
6. Remove a book from the library
8. List available books
9. Exit
Enter your choice: 1
Enter your name: Alice
User registered successfully!

Enter your choice: 51
Enter book title: Clean Code
Enter book author: Robert C. Martin
Enter book ISBN: 978-0132350884
Enter number of pages: 464
Enter cover type: Paperback
Printed book added successfully!

Enter your choice: 2
Enter book ISBN to borrow: 978-0132350884
Book borrowed successfully!
```

## Testing

The project includes unit tests demonstrating different testing approaches:

### PrintedBookTest.java

Tests using JUnit 4 with state verification:

```java
@Test
public void newBookIsNotLentTest() {
    PrintedBook book = new PrintedBook("Title", "Author", "ISBN", 300, "Hardcover");
    Assert.assertTrue(book.toString().contains("Is lent: no"));
}
```

### UserTest.java

Tests basic getter/setter functionality:

```java
@Test
public void getNameTest() {
    User user = new User("Alice");
    Assert.assertEquals("Alice", user.getName());
}
```

### LibraryTest.java

Advanced testing with Mockito for behavior verification:

```java
@Test
public void findBookWhenAddedToBooksTest() {
    String ISBN = "ISBN";
    when(mockedBook.getISBN()).thenReturn(ISBN);
    library.addBook(mockedBook);
    Assert.assertEquals(mockedBook, library.findBook(ISBN));
}
```

### Running Tests

```bash
# Compile tests
javac -cp .:junit-4.13.2.jar:mockito-core-3.12.4.jar test/**/*.java

# Run all tests
java -cp .:junit-4.13.2.jar:mockito-core-3.12.4.jar org.junit.runner.JUnitCore \
  books.PrintedBookTest libraries.LibraryTest users.UserTest
```

## Design Patterns

### Patterns Used

1. **Template Method Pattern**: `Book.toString()` provides a base template that subclasses extend
2. **Singleton-like Pattern**: Single Library and User instance in LibraryApp
3. **Strategy Pattern** (implicit): Different book types encapsulate different data strategies

## Learning Points for Junior Developers

### What This Project Teaches

1. **Encapsulation**: Why private fields with public accessors matter
2. **Abstraction**: How to create contracts with abstract classes
3. **Inheritance**: How to reuse code through class hierarchies
4. **Polymorphism**: How to write flexible code that works with multiple types
5. **Collections**: Using ArrayList and List interfaces
6. **Testing**: Unit testing with JUnit and mocking with Mockito
7. **Package Organization**: Logical grouping of related classes

### Key Takeaways

- Abstract classes represent concepts that don't exist independently
- Polymorphism allows treating different types uniformly through a common interface
- Encapsulation protects data integrity and allows implementation changes
- Shared object references mean changes propagate automatically
- Testing with mocks isolates units from dependencies

## Potential Improvements

For learning exercises, consider enhancing:

1. **Persistence**: Add file I/O or database to save library state
2. **Error Handling**: Implement try-catch blocks for robust error management
3. **Validation**: Add ISBN format validation
4. **Optimization**: Replace linear search with HashMap for O(1) lookup
5. **Multi-user Support**: Allow multiple concurrent users
6. **Borrowing Limits**: Enforce maximum books per user
7. **Due Dates**: Implement lending periods and overdue tracking
8. **Search Features**: Add search by title or author

## Related Resources

This implementation accompanies the blog post: [Object-Oriented Programming in Practice](https://www.seg.dev/posts/object-oriented-in-practice/)

---

**Happy Learning!** This project demonstrates that OOP isn't just theory - it's a practical approach to organizing real-world software systems.
