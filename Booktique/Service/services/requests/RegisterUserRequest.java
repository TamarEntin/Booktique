package services.requests;

import entities.User;

public class RegisterUserRequest extends RequestBase {
    private User newUser;

    public RegisterUserRequest(User newUser)
    {
        this.newUser = newUser;
    }

    public User getNewUser()
    {
        return this.newUser;
    }
}
