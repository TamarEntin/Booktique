package repository;

import interfaces.repository.IOrderRepository;
import entities.Order;

import java.util.Date;
import java.util.Vector;
import java.util.stream.Collectors;

public class OrderRepository extends RepositoryBase<Order> implements IOrderRepository {
    Vector<Order> orders;

    public OrderRepository()
    {
        orders = this.loadData();
    }

    public Order delete(Order order) {
        if (orders == null || orders.isEmpty())
            return order;

        Order orderResult = orders.stream().filter(ordr -> ordr.getOrderID() == order.getOrderID())
                .findFirst().orElse(null);

        if (orderResult == null) {
            return order;
        }

        orders.remove(orderResult);
        this.saveData(orders);
        return order;

    }

    public Order insert(Order order)
    {
        if (orders == null)
            this.orders = new Vector<>();

        Order orderResult = orders.stream().filter(ordr -> ordr.getOrderID().equals(order.getOrderID()))
                .findFirst().orElse(null);

        if (orderResult == null){
            orders.add(order);
            this.saveData(orders);
            return order;
        }

        return null;
    }

    public Order fetch(String orderID){
        if (orders == null || orders.isEmpty())
            return null;

        return orders.stream().filter(ordr->
                ordr.getOrderID().equals(orderID)).findFirst().orElse(null);
    }

    public Order update(Order order){
        if (orders == null || orders.isEmpty())
            return null;

        Order orderResult = orders.stream().filter(ordr -> ordr.getOrderID() == order.getOrderID())
                .findFirst().orElse(null);

        if(orderResult == null)
            return null;

        orders.remove(orderResult);

        boolean result = orders.add(order);

        if (result)
        {
            this.saveData(orders);
            return order;
        }
        else{
            return null;
        }
    }

    public Vector<Order> searchOrders(Date startRange, Date endRage)
    {
        if (orders == null || orders.isEmpty())
            return null;

        return orders.stream().filter(ordr ->
                ordr.getOrderCreateDate().after(startRange) && ordr.getOrderCreateDate().before(endRage))
                .collect(Collectors.toCollection(() -> new Vector<Order>()));
    }
    public Vector<Order> getAllOrders()
    {
        return this.orders;

    }
}
