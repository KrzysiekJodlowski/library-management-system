package books;
public abstract class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isLent;

    // Constructor
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isLent = false; // By default, a new book is not lent out
    }

    public abstract String getBookType();


    // Methods
    public void lendBook() {
        this.isLent = true;
    }

    public void returnBook() {
        this.isLent = false;
    }

    // Getter and Setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isLent() {
        return isLent;
    }

    public void setLent(boolean isLent) {
        this.isLent = isLent;
    }

    @Override
    public String toString() {
        String isAvailable = isLent() ? "yes" : "no";
        return "Is lent: " + isAvailable + ", Title: " + title + ", Author: " + author + ", ISBN: " + ISBN;
    }
}
