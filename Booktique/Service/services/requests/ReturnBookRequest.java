package services.requests;

public class ReturnBookRequest extends RequestBase {
    private String userId;
    private String borrowId;

    public ReturnBookRequest(String userId, String borrowId)
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
