package services.responses;

import entities.Order;
import exceptions.BusinessException;

public class CancelOrderResponse extends ResponseBase {
    private boolean isSuccess;
    private Order canceledOrder;

    public CancelOrderResponse(Order canceledOrder)
    {
        this.buildResponse();
        this.canceledOrder = canceledOrder;
        this.isSuccess = (canceledOrder == null) ? false : true;
    }

    public CancelOrderResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.isSuccess = false;
        this.canceledOrder = null;
    }
    public boolean isSuccess() {
        return isSuccess;
    }

    public Order getCanceledOrder() {
        return canceledOrder;
    }
}
