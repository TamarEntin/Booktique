package services.requests;

public class ApproveBookReturnRequest extends RequestBase {
    private String userId;
    private String borrowId;

    public ApproveBookReturnRequest(String userId, String borrowId)
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
