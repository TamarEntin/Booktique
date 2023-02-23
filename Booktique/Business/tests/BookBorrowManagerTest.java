package tests;

import constants.ConfigurationsKeys;
import entities.*;
import entities.extension.DateExtension;
import enums.BorrowStatus;
import enums.TestState;
import exceptions.ExtendBorrowingNotAllowException;
import exceptions.UserNotAuthorizeException;
import exceptions.UserPassMaxBooksBorrowException;
import managers.BookBorrowManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.repositories.*;

import java.util.Date;
import java.util.Vector;

public class BookBorrowManagerTest {

    BooksInOrdersRepositoryDummy booksInOrdersRepositoryDummy;
    BookStockRepositoryDummy bookStockRepositoryDummy;
    BorrowedBookRepositoryDummy borrowedBookRepositoryDummy;
    OrderRepositoryDummy orderRepositoryDummy;
    UserRepositoryDummy userRepositoryDummy;
    ConfigurationRepositoryDummy configurationRepositoryDummy;
    BookBorrowManager bookBorrowManager;

    @Before
    public void setup()
    {
        booksInOrdersRepositoryDummy = new BooksInOrdersRepositoryDummy();
        bookStockRepositoryDummy = new BookStockRepositoryDummy();
        borrowedBookRepositoryDummy = new BorrowedBookRepositoryDummy();
        orderRepositoryDummy = new OrderRepositoryDummy();
        userRepositoryDummy = new UserRepositoryDummy();
        configurationRepositoryDummy = new ConfigurationRepositoryDummy();
        bookBorrowManager = new BookBorrowManager(userRepositoryDummy, borrowedBookRepositoryDummy,
                bookStockRepositoryDummy, configurationRepositoryDummy);


    }
    @Test
    public void extendBookBorrowingTest_HappyPath() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "10000", "Max Days to Borrow");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        borrowedBook2.setFinalBorrowDate(DateExtension.AddDaysToDate(now, 7));
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);
        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        configurationRepositoryDummy.setup(TestState.ReturnObject,configMaxDaysBorrow, null);


        try {
            BorrowedBook borrowedBook = bookBorrowManager.extendBookBorrowing(newUser.getId(), borrowedBook2.getBorrowID());
            Assert.assertTrue(borrowedBook != null);
            Assert.assertTrue(borrowedBook.isExtended());
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void extendBookBorrowingTest_extendedNotAllow() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "10000", "Max Days to Borrow");
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        borrowedBook2.setFinalBorrowDate(now);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);
        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        configurationRepositoryDummy.setup(TestState.ReturnObject,configMaxDaysBorrow, null);

        try {
            BorrowedBook borrowedBook = bookBorrowManager.extendBookBorrowing(newUser.getId(), borrowedBook2.getBorrowID());
            Assert.fail("Should Return Exception");
        }
        catch (Exception e)
        {
            Assert.assertTrue(e instanceof ExtendBorrowingNotAllowException);
        }
    }


    @Test
    public void borrowBookTest_HappyPath() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "10000", "Max Days to Borrow");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        borrowedBook2.setFinalBorrowDate(DateExtension.AddDaysToDate(now, 7));
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);
        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        configurationRepositoryDummy.setup(TestState.ReturnObject,configMaxDaysBorrow, null);

        try {
            BorrowedBook borrowedBook = bookBorrowManager.borrowBook(newUser.getId(), book.getId());
            Assert.assertTrue(borrowedBook != null);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void borrowBookTest_userPassMaxBooksBorrow() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "1", "Max Days to Borrow");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        borrowedBook2.setFinalBorrowDate(DateExtension.AddDaysToDate(now, 7));
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        configurationRepositoryDummy.setup(TestState.ReturnObject,configMaxDaysBorrow, null);

        try {
            BorrowedBook borrowedBook = bookBorrowManager.borrowBook(newUser.getId(), book.getId());
            Assert.fail("Should thrown exception");
        }
        catch (Exception e)
        {
            Assert.assertTrue(e instanceof UserPassMaxBooksBorrowException);
        }
    }


    @Test
    public void returnBookTest_HappyPath() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "10000", "Max Days to Borrow");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        borrowedBook2.setFinalBorrowDate(DateExtension.AddDaysToDate(now, 7));
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        configurationRepositoryDummy.setup(TestState.ReturnObject,configMaxDaysBorrow, null);

        try {
            BorrowedBook borrowedBook = bookBorrowManager.returnBook(newUser.getId(), book.getId());
            Assert.assertTrue(borrowedBook != null);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void returnBookTest_HappyPath_readerStatus() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 1, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "10000", "Max Days to Borrow");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        borrowedBook2.setFinalBorrowDate(DateExtension.AddDaysToDate(now, 7));
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        configurationRepositoryDummy.setup(TestState.ReturnObject,configMaxDaysBorrow, null);

        try {
            BorrowedBook borrowedBook = bookBorrowManager.returnBook(newUser.getId(), book.getId());
            Assert.assertTrue(borrowedBook != null);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }


    @Test
    public void approveBookReturn_userNotAuthorized() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 1, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "10000", "Max Days to Borrow");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        borrowedBook2.setFinalBorrowDate(DateExtension.AddDaysToDate(now, 7));
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        configurationRepositoryDummy.setup(TestState.ReturnObject,configMaxDaysBorrow, null);

        try {
            BorrowedBook borrowedBook = bookBorrowManager.approveBookReturn(newUser.getId(), book.getId());
            Assert.fail();
        }
        catch (Exception e)
        {
            Assert.assertTrue(e instanceof UserNotAuthorizeException);
        }
    }


    @Test
    public void approveBookReturn_HappyPath() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "10000", "Max Days to Borrow");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        borrowedBook2.setFinalBorrowDate(DateExtension.AddDaysToDate(now, 7));
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        configurationRepositoryDummy.setup(TestState.ReturnObject,configMaxDaysBorrow, null);

        try {
            BorrowedBook borrowedBook = bookBorrowManager.approveBookReturn(newUser.getId(), book.getId());
            Assert.assertTrue(borrowedBook != null);
            Assert.assertTrue(borrowedBook.getStatus() == BorrowStatus.Approved.StatusValue());
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }


}