package services.requests;

public class LogoutRequest extends RequestBase {
    private String userId;

    public LogoutRequest(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return this.userId;
    }

}
