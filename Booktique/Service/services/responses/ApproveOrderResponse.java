package services.responses;

import entities.Order;
import exceptions.BusinessException;
import services.requests.ApproveOrderRequest;

public class ApproveOrderResponse extends ResponseBase{

    private Order order;


    public ApproveOrderResponse(Order order) {
        this.buildResponse();
        this.order = order;
    }

    public ApproveOrderResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        order = null;
    }

    public Order getOrder() {
        return order;
    }
}
