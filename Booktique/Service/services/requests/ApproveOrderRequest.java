package services.requests;

import entities.Order;

public class ApproveOrderRequest extends RequestBase{
    private String userId;
    private Order order;

    public ApproveOrderRequest(String userId, Order order)
    {
        this.userId = userId;
        this.order = order;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
