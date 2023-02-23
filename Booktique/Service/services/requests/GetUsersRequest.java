package services.requests;

public class GetUsersRequest extends RequestBase {
    private String userId;

    public GetUsersRequest(String userId)
    {
        this.userId = userId;
    }

    public String getUserId(){return this.userId;}
}
