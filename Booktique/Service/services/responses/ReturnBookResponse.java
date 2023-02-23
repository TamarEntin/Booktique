package services.responses;

import entities.BorrowedBook;
import exceptions.BusinessException;
import services.requests.ReturnBookRequest;

public class ReturnBookResponse extends  ResponseBase {
    private BorrowedBook borrowedBook;

    public ReturnBookResponse(BorrowedBook borrowedBook)
    {
        if (borrowedBook == null) {
            this.rejectResponse("GeneralError");
            this.borrowedBook = null;
        }
        else{
            this.buildResponse();
            this.borrowedBook = borrowedBook;
        }
    }

    public ReturnBookResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.borrowedBook = null;
    }

    public BorrowedBook getBorrowedBook() {
        return borrowedBook;
    }
}
