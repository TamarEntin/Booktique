package services.responses;

import entities.User;
import exceptions.BusinessException;

public class LoginResponse extends ResponseBase {

    private User user;
    public LoginResponse(User user)
    {
        this.buildResponse();
        this.user = user;
    }

    public LoginResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.user = null;
    }

    public User getUser()
    {
        return user;
    }
}
