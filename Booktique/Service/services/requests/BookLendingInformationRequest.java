package services.requests;

public class BookLendingInformationRequest extends RequestBase{
    private String userId;
    private String bookId;

    public BookLendingInformationRequest(String userId, String bookId)
    {
        this.userId = userId;
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }
}
