package services.requests;

import entities.User;

public class SearchConfigurationRequest extends RequestBase{

    private User askingUser;

    public SearchConfigurationRequest(User user)
    {
        this.askingUser = user;
    }

    public User getAskingUser()
    {
        return this.askingUser;
    }

}
