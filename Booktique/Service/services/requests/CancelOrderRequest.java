package services.requests;

public class CancelOrderRequest extends RequestBase {
    private String userId;
    private String orderId;

    public CancelOrderRequest(String userId, String orderId)
    {
        this.userId = userId;
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    }