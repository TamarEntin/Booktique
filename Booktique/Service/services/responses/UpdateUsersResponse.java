package services.responses;

import entities.User;
import exceptions.BusinessException;

import java.util.Vector;

public class UpdateUsersResponse extends ResponseBase {
    private Vector<User> updatedUsers;

    public UpdateUsersResponse(Vector<User> updatedUsers) {
        if (updatedUsers == null)
            this.rejectResponse("General Error");
        else
            this.buildResponse();
        this.updatedUsers = updatedUsers;
    }

    public UpdateUsersResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
    }
}
