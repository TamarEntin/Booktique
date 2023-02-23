package services.responses;

import entities.BorrowedBook;
import exceptions.BusinessException;
import services.requests.BookLendingRequest;

public class BookLendingResponse extends ResponseBase{
    private BorrowedBook borrowedBook;

    public BookLendingResponse(BorrowedBook borrowedBook)
    {
        this.buildResponse();
        this.borrowedBook = borrowedBook;
    }

    public BookLendingResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.borrowedBook = null;
    }

    public BorrowedBook getBorrowedBook() {
        return borrowedBook;
    }
}
