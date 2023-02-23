package services.responses;

import entities.User;
import exceptions.BusinessException;

public class UpdateUserInfoResponse extends ResponseBase {
    private User user;
    public UpdateUserInfoResponse(User user)
    {
        this.buildResponse();
        this.user = user;
    }

    public UpdateUserInfoResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.user = null;
    }

    public User getUser()
    {
        return user;
    }
}
