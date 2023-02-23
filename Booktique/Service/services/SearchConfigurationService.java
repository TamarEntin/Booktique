package services;

import entities.Configuration;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import exceptions.UserNotFoundException;
import interfaces.business.IConfigurationManager;
import interfaces.repository.IConfigurationRepository;
import managers.ConfigurationManager;
import services.requests.SearchConfigurationRequest;
import services.responses.SearchConfigurationResponse;

import java.util.List;

public class SearchConfigurationService implements  IService<SearchConfigurationRequest, SearchConfigurationResponse> {
    private IConfigurationManager configurationManager;

    public SearchConfigurationService(IConfigurationRepository configurationRepository)
    {
        this.configurationManager = new ConfigurationManager(configurationRepository);
    }

    @Override
    public void validate(SearchConfigurationRequest searchConfigurationRequest) throws InvalidRequestException, UserNotFoundException {

    }

    @Override
    public SearchConfigurationResponse execute(SearchConfigurationRequest searchConfigurationRequest) throws BusinessException {
        List<Configuration> configurations = configurationManager.getConfigurations();
        return new SearchConfigurationResponse(configurations);
    }

    @Override
    public SearchConfigurationResponse rejectResponseBuilder(BusinessException businessException) {
        return new SearchConfigurationResponse(businessException);
    }
}
