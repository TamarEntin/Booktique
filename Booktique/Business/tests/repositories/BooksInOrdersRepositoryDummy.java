package tests.repositories;

import entities.BooksInOrders;
import enums.TestState;
import exceptions.BusinessException;
import interfaces.repository.IBooksInOrdersRepository;

import java.util.Vector;

public class BooksInOrdersRepositoryDummy implements IBooksInOrdersRepository {
    private TestState testState;
    private BooksInOrders booksInOrder;
    private Vector<BooksInOrders> booksInOrders;

    public void setup(TestState testState, BooksInOrders booksInOrder, Vector<BooksInOrders> booksInOrders)
    {
        this.testState = testState;
        this.booksInOrder = booksInOrder;
        this.booksInOrders = booksInOrders;
    }

    @Override
    public BooksInOrders delete(String orderID) {
        if (testState == TestState.ReturnObject)
            return this.booksInOrder;
        return null;
    }

    @Override
    public BooksInOrders fetch(String bookID) {
        if (testState == TestState.ReturnObject)
            return this.booksInOrder;
        return null;
    }

    @Override
    public BooksInOrders fetchByOrderID(String orderID) {
        if (testState == TestState.ReturnObject)
            return this.booksInOrder;
        return null;
    }

    @Override
    public BooksInOrders insert(BooksInOrders booksInOrders) throws BusinessException {
        if (testState == TestState.ReturnObject)
            return this.booksInOrder;
        return null;
    }
}
