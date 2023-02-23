package services.requests;

import entities.Configuration;
import entities.User;

import java.util.Vector;

public class UpdateConfigurationRequest extends RequestBase{
    private Vector<Configuration> configuration;
    private User user;

    public User getUser() {
        return user;
    }

    public UpdateConfigurationRequest(User user, Vector<Configuration> configuration) {
        this.configuration = configuration;
    }

    public Vector<Configuration> getConfiguration() {
        return configuration;
    }
}
