package services.requests;

import entities.User;

import java.util.Vector;

public class UpdateUsersRequest extends RequestBase{
    private String userId;
    private Vector<User> usersToUpdate;

    public UpdateUsersRequest(String userId, Vector<User> usersToUpdate)
    {
        this.userId = userId;
        this.usersToUpdate = usersToUpdate;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public Vector<User> getUsersToUpdate()
    {
        return this.usersToUpdate;
    }
}
