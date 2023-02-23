package interfaces;

import entities.BookStock;
import entities.Order;

public interface IBookStockBuilder {

    BookStock build(Order order);
}
