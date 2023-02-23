package services.responses;

import entities.BorrowedBook;
import exceptions.BusinessException;
import services.requests.BookLendingInformationRequest;

public class BookLendingInformationResponse extends ResponseBase{
    private BorrowedBook borrowedBook;

    public BookLendingInformationResponse(BorrowedBook borrowedBook)
    {
        this.buildResponse();
        this.borrowedBook = borrowedBook;
    }

    public BookLendingInformationResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.borrowedBook = null;
    }

    public BorrowedBook getBorrowedBook() {
        return borrowedBook;
    }
}
