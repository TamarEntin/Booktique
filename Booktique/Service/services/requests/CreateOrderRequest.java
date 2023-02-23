package services.requests;

import entities.BooksInOrders;
import entities.Order;

public class CreateOrderRequest extends RequestBase {

    private String userId;
    private Order newOrder;
    private BooksInOrders booksInOrder;

    public CreateOrderRequest(String userId, Order newOrder, BooksInOrders booksInOrder) {
        this.userId = userId;
        this.newOrder = newOrder;
        this.booksInOrder = booksInOrder;
    }

    public String getUserId() {
        return userId;
    }

    public Order getNewOrder() {
        return newOrder;
    }

    public BooksInOrders getBooksInOrder() {
        return booksInOrder;
    }

}