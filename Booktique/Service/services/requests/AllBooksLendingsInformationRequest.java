package services.requests;

public class AllBooksLendingsInformationRequest extends RequestBase {
    private String userId;

    public AllBooksLendingsInformationRequest(String userId)
    {
        this.userId = userId;
    }

    public String getUserId(){return this.userId;}
}
