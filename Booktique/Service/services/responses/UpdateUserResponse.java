package services.responses;

import entities.User;
import exceptions.BusinessException;

public class UpdateUserResponse extends ResponseBase{

    private User user;
    public UpdateUserResponse(User user)
    {
        this.buildResponse();
        this.user = user;
    }

    public UpdateUserResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.user = null;
    }

    public User getUser()
    {
        return user;
    }
}
