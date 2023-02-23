package services.responses;

import entities.BorrowedBook;
import exceptions.BusinessException;

public class ApproveBookReturnResponse extends ResponseBase {
    private BorrowedBook borrowedBook;

    public ApproveBookReturnResponse(BorrowedBook borrowedBook) {
        this.buildResponse();
        this.borrowedBook = borrowedBook;
    }

    public ApproveBookReturnResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.borrowedBook = null;
    }
}
