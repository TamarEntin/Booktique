package services.responses;

import entities.BookStock;
import exceptions.BusinessException;

import java.util.List;
import java.util.Vector;

public class GetBooksResponse extends ResponseBase {
    private Vector<BookStock> books;

    public GetBooksResponse(Vector<BookStock> books) {
        this.buildResponse();
        this.books = books;
    }

    public GetBooksResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.books = null;
    }

    public Vector<BookStock> getBooks(){ return  this.books;}
}
