package managers;

import entities.Configuration;
import exceptions.GeneralErrorException;
import interfaces.business.IConfigurationManager;
import interfaces.repository.IConfigurationRepository;

import java.util.List;
import java.util.Vector;

public class ConfigurationManager implements IConfigurationManager {

    private IConfigurationRepository _configurationRepository;

    public ConfigurationManager(IConfigurationRepository configurationRepository)
    {
        _configurationRepository = configurationRepository;
    }

    @Override
    public List<Configuration> getConfigurations() {
        return _configurationRepository.getConfigurations();
    }


    @Override
    public Vector<Configuration> updateConfiguration(Vector<Configuration> configurations) throws GeneralErrorException {
        return _configurationRepository.update(configurations);
    }
}
