package services.responses;

import entities.User;
import exceptions.BusinessException;
import services.requests.GetUsersRequest;

import java.util.Vector;

public class GetUsersResponse extends ResponseBase {
    private Vector<User> users;

    public GetUsersResponse(Vector<User> users) {
        if (users == null)
            this.rejectResponse("General Error");
        else
            this.buildResponse();
        this.users = users;
    }

    public GetUsersResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.users = null;
    }

    public Vector<User> getUsers(){
        return users;
    }
}
