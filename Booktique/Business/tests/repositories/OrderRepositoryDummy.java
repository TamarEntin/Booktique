package tests.repositories;

import entities.Order;
import enums.TestState;
import exceptions.BusinessException;
import interfaces.repository.IOrderRepository;

import java.util.Date;
import java.util.Vector;

public class OrderRepositoryDummy implements IOrderRepository {
    private TestState testState;
    private Order order;
    private Vector<Order> orders;

    public void setup(TestState testState, Order order, Vector<Order> orders)
    {
        this.testState = testState;
        this.order = order;
        this.orders = orders;
    }


    @Override
    public Order delete(Order order) {
        if (testState == TestState.ReturnObject)
            return this.order;
        return null;
    }

    @Override
    public Order fetch(String orderID) {
        if (testState == TestState.ReturnObject)
            return this.order;
        return null;
    }

    @Override
    public Order update(Order order) {
        if (testState == TestState.ReturnObject)
            return this.order;
        return null;
    }

    @Override
    public Vector<Order> searchOrders(Date startRange, Date endRage) {
        if (testState == TestState.ReturnObject)
            return this.orders;
        return null;
    }

    @Override
    public Vector<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order insert(Order order) throws BusinessException {
        if (testState == TestState.ReturnObject)
            return this.order;
        return null;
    }
}
