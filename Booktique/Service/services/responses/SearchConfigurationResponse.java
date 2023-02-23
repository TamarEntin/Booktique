package services.responses;

import entities.Configuration;
import exceptions.BusinessException;

import java.util.List;

public class SearchConfigurationResponse extends ResponseBase{
    private List<Configuration> configurations;

    public SearchConfigurationResponse(List<Configuration> configurations) {
        if (configurations == null || configurations.size() == 0)
        {
            this.rejectResponse("General Error");
        }
        else
        {
            this.buildResponse();
        }
        this.configurations = configurations;
    }

    public SearchConfigurationResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.configurations = null;
    }

    public List<Configuration> getConfiguration()
    {
        return configurations;
    }
}
