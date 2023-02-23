package tests;

import entities.*;
import enums.TestState;
import exceptions.UserNotFoundException;
import managers.OrderManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.repositories.BookStockRepositoryDummy;
import tests.repositories.BooksInOrdersRepositoryDummy;
import tests.repositories.OrderRepositoryDummy;
import tests.repositories.UserRepositoryDummy;

import java.util.Date;
import java.util.Vector;

public class OrderManagerTest {
    UserRepositoryDummy userRepositoryDummy;
    BookStockRepositoryDummy bookStockRepositoryDummy;
    OrderRepositoryDummy orderRepositoryDummy;
    BooksInOrdersRepositoryDummy booksInOrdersRepositoryDummy;
    OrderManager orderManager;
    @Before
    public void setup()
    {
        bookStockRepositoryDummy = new BookStockRepositoryDummy();
        orderRepositoryDummy = new OrderRepositoryDummy();
        booksInOrdersRepositoryDummy = new BooksInOrdersRepositoryDummy();
        userRepositoryDummy = new UserRepositoryDummy();
        orderManager = new OrderManager(orderRepositoryDummy, userRepositoryDummy, bookStockRepositoryDummy,
                booksInOrdersRepositoryDummy);

    }
    @Test
    public void insertOrder_HappyPath_newBook() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);

        try {
            Order order = orderManager.insertOrder(newUser.getId(), newOrder, booksInOrders);Assert.assertTrue(order != null);
        }
        catch (Exception e)
        {
            Assert.assertTrue(e instanceof UserNotFoundException);
        }
    }

    @Test
    public void insertOrder_HappyPath_bookExist() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "SiFi", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "SiFi", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "1", "Tamar", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "1", "SiFi");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);
        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnObject);

        try {
            Order order = orderManager.insertOrder(newUser.getId(), newOrder, booksInOrders);Assert.assertTrue(order != null);
        }
        catch (Exception e)
        {
            Assert.assertTrue(e instanceof UserNotFoundException);
        }
    }

    @Test
    public void insertOrder_userNotFound() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Tamar", 3, "SiFi", "", "");
        Order newOrder = new Order("1", 1, now, now, false, "1", "book1", "author1", 2);
        BooksInOrders booksInOrders = new BooksInOrders("1", "book1", "cool");
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 2, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);
        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);

        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);
        orderRepositoryDummy.setup(TestState.ReturnObject, newOrder, null);
        booksInOrdersRepositoryDummy.setup(TestState.ReturnObject, booksInOrders, null);
        userRepositoryDummy.setup(newUser, TestState.ReturnException);

        try {
            Order order = orderManager.insertOrder("2", newOrder, booksInOrders);
            Assert.fail("Should have thrown exeption");
        }
        catch (Exception e)
        {
            Assert.assertTrue(e instanceof UserNotFoundException);
        }
    }

}