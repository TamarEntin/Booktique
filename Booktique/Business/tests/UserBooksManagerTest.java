package tests;

import entities.BookStock;
import entities.BorrowedBook;
import entities.User;
import entities.UserLending;
import enums.TestState;
import managers.UserBooksManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.repositories.BookStockRepositoryDummy;
import tests.repositories.BorrowedBookRepositoryDummy;
import tests.repositories.UserRepositoryDummy;

import java.util.Date;
import java.util.Vector;

public class UserBooksManagerTest {

    UserRepositoryDummy userRepositoryDummy;
    BookStockRepositoryDummy bookStockRepositoryDummy;
    BorrowedBookRepositoryDummy borrowedBookRepositoryDummy;
    UserBooksManager userBooksManager;

    @Before
    public void setup()
    {
        userRepositoryDummy = new UserRepositoryDummy();
        bookStockRepositoryDummy = new BookStockRepositoryDummy();
        borrowedBookRepositoryDummy = new BorrowedBookRepositoryDummy();
        userBooksManager = new UserBooksManager(bookStockRepositoryDummy, borrowedBookRepositoryDummy, userRepositoryDummy);

    }

    @Test
    public void getAllUserActiveBorrowing_HappyPath() {
        Date now = new Date(System.currentTimeMillis());
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);

        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, null);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);

        try {
            Vector<UserLending> userLendingVector = userBooksManager.getAllUserActiveBorrowing(newUser.getId());
            Assert.assertTrue(!userLendingVector.isEmpty());
            Assert.assertTrue(userLendingVector.size() == 1);
            Assert.assertTrue(userLendingVector.firstElement().getUserId().equals(newUser.getId()));
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getAllUserActiveBorrowing_NoElements() {
        Date now = new Date(System.currentTimeMillis());
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 3);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 3);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, null);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);

        try {
            Vector<UserLending> userLendingVector = userBooksManager.getAllUserActiveBorrowing(newUser.getId());
            Assert.assertTrue(userLendingVector.isEmpty() || userLendingVector == null);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getAllAwaitingForApprovalBorrowing_HappyPath() {
        Date now = new Date(System.currentTimeMillis());
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 2);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 2);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, null);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);

        try {
            Vector<UserLending> userLendingVector = userBooksManager.getAllAwaitingForApprovalBorrowing();
            Assert.assertTrue(!userLendingVector.isEmpty());
            Assert.assertTrue(userLendingVector.size() == 2);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void getAllAwaitingForApprovalBorrowing_NoElements() {
        Date now = new Date(System.currentTimeMillis());
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242",true);
        BookStock book = new BookStock("1","1", "Tamar", 3, "SiFi", "", "");
        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 3);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        userRepositoryDummy.setup(newUser, TestState.ReturnObject);
        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, null);
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);

        try {
            Vector<UserLending> userLendingVector = userBooksManager.getAllAwaitingForApprovalBorrowing();
            Assert.assertTrue(userLendingVector.isEmpty() || userLendingVector == null);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }

    }

}