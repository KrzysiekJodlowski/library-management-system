package libraries;

import books.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {

    @Mock
    Book mockedBook;

    @Test
    public void findBookWhenAddedToBooksTest() {
        // arrange
        String ISBN = "1234";
        when(mockedBook.getISBN()).thenReturn(ISBN);
        Library library = new Library();

        // act
        library.addBook(mockedBook);
        Book actualBook = library.findBook(ISBN);

        // assert
        Assert.assertEquals(mockedBook, actualBook);
    }
}
