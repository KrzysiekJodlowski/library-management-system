package books;
public class PrintedBook extends Book {
    private int numberOfPages;
    private String coverType;

    public PrintedBook(String title, String author, String ISBN, int numberOfPages, String coverType) {
        super(title, author, ISBN);
        this.numberOfPages = numberOfPages;
        this.coverType = coverType;
    }

    @Override
    public String getBookType() {
        return "Printed Book";
    }

    @Override
    public String toString() {
        return super.toString() + ", Number of Pages: " + numberOfPages + ", Cover Type: " + coverType;
    }
}
