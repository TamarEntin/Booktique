package services.responses;

import entities.BookStock;
import exceptions.BusinessException;

public class RemoveBookResponse extends ResponseBase {
    private BookStock book;

    public RemoveBookResponse(BookStock book)
    {
        if (book == null)
            this.rejectResponse("General Error");
        else
            this.buildResponse();
        this.book = book;
    }

    public RemoveBookResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
    }
}
