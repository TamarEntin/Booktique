package interfaces.business;

import entities.BooksInOrders;
import entities.EmployeeOrder;
import entities.Order;
import entities.User;
import exceptions.BusinessException;

import java.util.Vector;

public interface IOrderManager {

    Order insertOrder(String userId, Order order, BooksInOrders bookInOrder) throws BusinessException;

    Vector<EmployeeOrder> getAllOrders();
}
