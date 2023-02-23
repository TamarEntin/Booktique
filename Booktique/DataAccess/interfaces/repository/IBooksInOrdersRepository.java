package interfaces.repository;

import entities.BooksInOrders;

public interface IBooksInOrdersRepository extends IRepository<BooksInOrders>{

    //BooksInOrders insert(BooksInOrders bookInOrder);

    BooksInOrders delete(String orderID);

    BooksInOrders fetch(String bookID);

    BooksInOrders fetchByOrderID(String orderID);
}
