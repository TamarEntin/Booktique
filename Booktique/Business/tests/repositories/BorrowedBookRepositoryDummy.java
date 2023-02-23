package tests.repositories;

import entities.BorrowedBook;
import enums.TestState;
import exceptions.BusinessException;
import interfaces.repository.IBorrowedBookRepository;

import java.util.Vector;

public class BorrowedBookRepositoryDummy  implements IBorrowedBookRepository 
{
    private BorrowedBook borrowedBook;
    private TestState testState;
    private Vector<BorrowedBook> borrowedBooks;

    public void setup(TestState state,BorrowedBook borrowedBook, Vector<BorrowedBook> borrowedBooks)
    {
        this.borrowedBooks = borrowedBooks;
        this.borrowedBook = borrowedBook;
        this.testState = state;
    }

    @Override
    public BorrowedBook update(BorrowedBook borrowedBook) {return this.borrowedBook;}

    @Override
    public BorrowedBook fetch(String userID, String bookID) {return this.borrowedBook;}

    @Override
    public BorrowedBook fetch(String borrowID) {return borrowedBook; }

    @Override
    public Vector<BorrowedBook> searchBorrowedBooksByID(String bookID) 
    {
        Vector<BorrowedBook> users = new Vector<>();
        users.add(this.borrowedBook);
        return users;    
        }

    @Override
    public Vector<BorrowedBook> searchBorrowedBooksByUserID(String userID, int bookStatus) 
    {
        Vector<BorrowedBook> users = new Vector<>();
        users.add(this.borrowedBook);
        return users;
    }

    @Override
    public Vector<BorrowedBook> getAllBorrowed() 
    {
            if (this.testState == TestState.ReturnObject)
                return borrowedBooks;
            return null;
    }

    @Override
    public BorrowedBook insert(BorrowedBook borrowedBook) throws BusinessException 
    {
        if (this.testState == TestState.ReturnObject)
            return borrowedBook;
        return null;
    }
}
