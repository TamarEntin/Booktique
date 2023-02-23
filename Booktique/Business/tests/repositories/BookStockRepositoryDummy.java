package tests.repositories;

import entities.BookStock;
import enums.TestState;
import exceptions.BusinessException;
import interfaces.repository.IBookStockRepository;

import java.util.Vector;

public class BookStockRepositoryDummy implements IBookStockRepository {
    private TestState testState;
    private BookStock bookStock;
    private Vector<BookStock> bookStocks;

    public void setup(TestState state, BookStock book, Vector<BookStock> books)
    {
        this.testState = state;
        this.bookStock = book;
        this.bookStocks = books;
    }

    @Override
    public BookStock update(BookStock bookStock) {
        if (testState == TestState.ReturnObject)
            return this.bookStock;
        return null;
    }

    @Override
    public BookStock fetch(String bookID) {
        if (testState == TestState.ReturnObject)
            return this.bookStock;
        return null;
    }

    @Override
    public BookStock fetchByCode(String bookCode) {
        return null;
    }

    @Override
    public Vector<BookStock> searchByName(String bookName) {
        if (testState == TestState.ReturnObject)
            return this.bookStocks;
        return null;
    }

    @Override
    public BookStock fetch(String bookName, String authorName) {
        if (testState == TestState.ReturnObject)
            return this.bookStock;
        return null;
    }

    @Override
    public BookStock delete(BookStock bookStock, int quantity) throws BusinessException {
        if (testState == TestState.ReturnObject)
            return this.bookStock;
        return null;
    }

    @Override
    public BookStock insert(BookStock bookStock) throws BusinessException {
        if (testState == TestState.ReturnObject)
            return this.bookStock;
        return null;
    }
}
