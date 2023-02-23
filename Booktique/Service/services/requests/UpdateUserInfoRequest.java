package services.requests;

import entities.User;

public class UpdateUserInfoRequest extends RequestBase {
    private User userToUpdate;
    
    public UpdateUserInfoRequest(User userToUpdate)
    {
        this.userToUpdate = userToUpdate;
    }

    public User getUserToUpdate()
    {
        return this.userToUpdate;
    }
}
