package tests;

import entities.BookStock;
import entities.BorrowedBook;
import enums.BooksFilter;
import enums.TestState;
import managers.BooksManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.repositories.BookStockRepositoryDummy;
import tests.repositories.BorrowedBookRepositoryDummy;

import java.util.Date;
import java.util.Vector;

public class BooksManagerTest {

    BookStockRepositoryDummy bookStockRepositoryDummy;
    BorrowedBookRepositoryDummy borrowedBookRepositoryDummy;
    BooksManager booksManager;

    @Before
    public void setup()
    {
        bookStockRepositoryDummy = new BookStockRepositoryDummy();
        borrowedBookRepositoryDummy = new BorrowedBookRepositoryDummy();
        booksManager = new BooksManager(bookStockRepositoryDummy, borrowedBookRepositoryDummy);

    }
    @Test
    public void getBooksByFilter_All_HappyPath() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);
        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);

        try {
            Vector<BookStock> allBooks = booksManager.getBooksByFilter(BooksFilter.All, false);
            Assert.assertTrue(allBooks.size() == 2);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getBooksByFilter_Available_HappyPath() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);
        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);

        try {
            Vector<BookStock> allBooks = booksManager.getBooksByFilter(BooksFilter.AvailableOnly, false);
            Assert.assertTrue(allBooks.size() == 2);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getBooksByFilter_Available_NoElements() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 1, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 1, "SiFi", "", "");
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "2", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);
        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);

        try {
            Vector<BookStock> allBooks = booksManager.getBooksByFilter(BooksFilter.AvailableOnly, false);
            Assert.assertTrue(allBooks == null || allBooks.size() ==0);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }



}