package services.responses;

import entities.UserLending;
import exceptions.BusinessException;

import java.util.Vector;

public class AllBooksLendingsInformationResponse extends ResponseBase {
    private Vector<UserLending> userLendings;

    public AllBooksLendingsInformationResponse(Vector<UserLending> borrowedBooks)
    {
        this.buildResponse();
        this.userLendings = borrowedBooks;
    }

    public AllBooksLendingsInformationResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.userLendings = null;
    }

    public Vector<UserLending> getBorrowedBook() {
        return userLendings;
    }}
