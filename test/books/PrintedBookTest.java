package books;

import org.junit.Assert;
import org.junit.Test;

public class PrintedBookTest {

    @Test
    public void newBookIsNotLentTest() {
        // arrange
        PrintedBook printedBook = new PrintedBook(
                "Title 1", "Author 1", "1234", 100, "hardcover"
        );

        // act
        String bookInfo = printedBook.toString();

        // assert
        Assert.assertTrue(bookInfo.contains("Is lent: no"));
    }

    @Test
    public void borrowedBookIsLentTest() {
        // arrange
        PrintedBook printedBook = new PrintedBook(
                "Title 2", "Author 2", "5678", 200, "papercover"
        );

        // act
        printedBook.setLent(true);
        String bookInfo = printedBook.toString();

        // assert
        Assert.assertTrue(bookInfo.contains("Is lent: yes"));
    }
}
