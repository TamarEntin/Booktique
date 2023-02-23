package interfaces.repository;

import entities.Order;

import java.util.Date;
import java.util.Vector;

public interface IOrderRepository extends IRepository<Order>{

    Order delete(Order order);

    Order fetch(String orderID);

    Order update(Order order);

    Vector<Order> searchOrders(Date startRange, Date endRage);

    Vector<Order> getAllOrders();
}
