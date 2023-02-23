package builder;

import entities.BookStock;
import entities.BooksInOrders;
import entities.Order;
import interfaces.IBookStockBuilder;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBooksInOrdersRepository;

public class BookStockBuilder implements IBookStockBuilder {

    private IBooksInOrdersRepository booksInOrdersRepository;

    public BookStockBuilder(IBooksInOrdersRepository booksInOrdersRepository)
    {
        this.booksInOrdersRepository = booksInOrdersRepository;
    }

    public BookStock build(Order order)
    {
        BooksInOrders bookInOrder = booksInOrdersRepository.fetchByOrderID(order.getOrderID());
        if (bookInOrder == null) return null;

        BookStock newBook = new BookStock(bookInOrder.getBookName(),order.getAuthorName(), order.getQuantity(), bookInOrder.getCategory());
        return newBook;
    }
}
