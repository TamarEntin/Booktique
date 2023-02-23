package services.responses;

import entities.Configuration;
import exceptions.BusinessException;

import java.util.Vector;

public class UpdateConfigurationResponse extends ResponseBase{
    private Vector<Configuration> configuration;

    public UpdateConfigurationResponse(Vector<Configuration> configuration) {
        if (configuration == null)
            this.rejectResponse("General Error");
        else
            this.buildResponse();

        this.configuration = configuration;
    }

    public UpdateConfigurationResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.configuration = null;
    }


}
