package repository;

import entities.BooksInOrders;
import interfaces.repository.IBooksInOrdersRepository;

import java.util.Vector;

public class BooksInOrdersRepository extends RepositoryBase<BooksInOrders> implements IBooksInOrdersRepository {

    private Vector<BooksInOrders> booksInOrder;

    public BooksInOrdersRepository() {
        this.booksInOrder = this.loadData();
    }

    public BooksInOrders insert(BooksInOrders bookInOrder) {
        if (booksInOrder == null)
            this.booksInOrder = new Vector<>();

        BooksInOrders bookResult = booksInOrder.stream().filter(book -> book.getOrderID().equals(bookInOrder.getOrderID()) &&
                                                                book.getBookInOrderID().equals(bookInOrder.getBookInOrderID()))
                                                        .findFirst().orElse(null);

        if (bookResult != null)
            return null;

        boolean result = booksInOrder.add(bookInOrder);
        if (result)
        {
            this.saveData(booksInOrder);
            return bookInOrder;
        }
        else{
            return null;
        }
    }

    public BooksInOrders delete(String orderID)
    {
        if (booksInOrder == null || booksInOrder.isEmpty())
            return null;

        BooksInOrders bookInOrder = booksInOrder.stream().filter(book->
                book.getOrderID().equals(orderID)).findFirst().orElse(null);

        if (bookInOrder != null) {
            boolean result = booksInOrder.remove(bookInOrder);
            if (result)
                return bookInOrder;
        }

        return  null;
    }

    public BooksInOrders fetch(String bookID)
    {
        if (booksInOrder == null || booksInOrder.isEmpty())
            return null;

        return booksInOrder.stream().filter(book->
                book.getBookInOrderID().equals(bookID)).findFirst().orElse(null);
    }

    public BooksInOrders fetchByOrderID(String orderID)
    {
        if (booksInOrder == null || booksInOrder.isEmpty())
            return null;

        return booksInOrder.stream().filter(book->
                book.getOrderID().equals(orderID)).findFirst().orElse(null);
    }


}
