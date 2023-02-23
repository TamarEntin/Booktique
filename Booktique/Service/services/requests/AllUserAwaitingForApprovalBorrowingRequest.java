package services.requests;

public class AllUserAwaitingForApprovalBorrowingRequest extends RequestBase{
    private String userId;


    public AllUserAwaitingForApprovalBorrowingRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId (){return this.userId;}
}