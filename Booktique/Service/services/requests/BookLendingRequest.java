package services.requests;

import entities.BookStock;
import entities.BorrowedBook;

public class BookLendingRequest extends RequestBase{
    private String userId;
    private String bookId;

    public BookLendingRequest(String userId, String bookStock)
    {
        this.userId = userId;
        this.bookId = bookStock;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookStock() {
        return bookId;
    }
}
