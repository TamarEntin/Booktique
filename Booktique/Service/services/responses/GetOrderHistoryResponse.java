package services.responses;

import entities.EmployeeOrder;
import exceptions.BusinessException;

import java.util.Vector;

public class GetOrderHistoryResponse extends ResponseBase {
    private Vector<EmployeeOrder> ordersHistory;

    public GetOrderHistoryResponse(Vector<EmployeeOrder> ordersHistory) {
        this.buildResponse();
        this.ordersHistory = ordersHistory;
    }

    public GetOrderHistoryResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
    }

    public Vector<EmployeeOrder> getOrdersHistory(){return this.ordersHistory;}
}
