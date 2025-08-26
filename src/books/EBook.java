package books;
public class EBook extends Book {
    private double fileSize;
    private String fileFormat;

    public EBook(String title, String author, String ISBN, double fileSize, String fileFormat) {
        super(title, author, ISBN);
        this.fileSize = fileSize;
        this.fileFormat = fileFormat;
    }

    @Override
    public String getBookType() {
        return "E-Book";
    }

    @Override
    public String toString() {
        return super.toString() + ", File Size: " + fileSize + "MB, File Format: " + fileFormat;
    }
}
