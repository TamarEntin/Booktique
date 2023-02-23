package services;

import entities.Configuration;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IConfigurationManager;
import interfaces.repository.IConfigurationRepository;
import managers.ConfigurationManager;
import services.requests.UpdateConfigurationRequest;
import services.responses.UpdateConfigurationResponse;

import java.util.Vector;

public class UpdateConfigurationService implements IService<UpdateConfigurationRequest, UpdateConfigurationResponse> {

    private IConfigurationManager configurationManager;

    public UpdateConfigurationService(IConfigurationRepository configurationRepository)
    {
        this.configurationManager = new ConfigurationManager(configurationRepository);
    }

    @Override
    public void validate(UpdateConfigurationRequest updateConfigurationRequest) throws InvalidRequestException {
        if (updateConfigurationRequest.getConfiguration() == null)
            throw new InvalidRequestException("UpdateConfigurationRequest");
    }

    @Override
    public UpdateConfigurationResponse execute(UpdateConfigurationRequest updateConfigurationRequest) throws BusinessException {
        Vector<Configuration> configuration = configurationManager.updateConfiguration(updateConfigurationRequest.getConfiguration());
        return new UpdateConfigurationResponse(configuration);
    }

    @Override
    public UpdateConfigurationResponse rejectResponseBuilder(BusinessException businessException) {
        return new UpdateConfigurationResponse(businessException);
    }
}
