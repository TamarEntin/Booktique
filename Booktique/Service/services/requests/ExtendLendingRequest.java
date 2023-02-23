package services.requests;

public class ExtendLendingRequest extends RequestBase{
    private String userId;
    private String borrowId;

    public ExtendLendingRequest(String userId, String borrowId)
    {
        this.userId = userId;
        this.borrowId = borrowId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBorrowId() {
        return borrowId;
    }
}
