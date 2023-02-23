package services.requests;

public class GetOrdersHistoryRequest extends RequestBase {
    private String userId;

    public GetOrdersHistoryRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId(){return this.userId;}
}
