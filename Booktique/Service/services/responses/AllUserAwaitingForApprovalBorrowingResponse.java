package services.responses;

import entities.UserLending;
import exceptions.BusinessException;

import java.util.Vector;

public class AllUserAwaitingForApprovalBorrowingResponse extends ResponseBase {
    private Vector<UserLending> userLending;

    public AllUserAwaitingForApprovalBorrowingResponse(Vector<UserLending> userLending) {

            this.buildResponse();
        this.userLending = userLending;
    }

    public AllUserAwaitingForApprovalBorrowingResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.userLending = null;
    }

    public Vector<UserLending> getUserLending(){return this.userLending;}
}
