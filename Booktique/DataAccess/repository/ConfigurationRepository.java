package repository;

import entities.User;
import exceptions.*;
import interfaces.repository.IConfigurationRepository;
import entities.Configuration;
// import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Vector;

public class ConfigurationRepository extends RepositoryBase<Configuration> implements IConfigurationRepository {

    private Vector<Configuration> configurations;

    public ConfigurationRepository()
    {
        configurations = this.loadData();
    }


    public Vector<Configuration> update(Vector<Configuration> configurations) throws GeneralErrorException {
        if (configurations == null || configurations.isEmpty())
            return null;

        try {
            configurations.stream().forEach(config -> update(config));
            this.saveData(configurations);
            return configurations;
        }
        catch (Exception e)
        {
            throw new GeneralErrorException();
        }
    }

    private void update(Configuration configuration)
    {
        if (configurations == null || configurations.isEmpty())
            return;

        Configuration configurationResult = configurations.stream().filter(cnfg ->
                cnfg.getConfigKey().equals(configuration.getConfigKey())).findFirst().orElse(null);

        if (configurationResult == null)
            return;

        configurations.remove(configurationResult);
        configurations.add(configuration);
    }


    public Configuration fetchConfigurationByName(String configkey) throws BusinessException
    {
        if (configurations == null || configurations.isEmpty())
            throw new ConfigurationMissingException(configkey);

        Configuration configurationResult = configurations.stream().filter(cnfg ->
                cnfg.getConfigKey().equals(configkey)).findFirst().orElse(null);

        if (configurationResult == null)
            return null;

        return configurationResult;
    }

    public List<Configuration> getConfigurations()
    {
        return configurations;
    }

    @Override
    public Configuration insert(Configuration configuration) throws BusinessException {
        if (configurations == null)
            this.configurations = new Vector<>();

        Configuration userResult = configurations.stream().filter(usr -> usr.getConfigKey().equals(configuration.getConfigKey())).findFirst().orElse(null);

        if (userResult != null)
            throw new ConfigurationAlreadyExistException();

        boolean result = configurations.add(configuration);
        if (result)
        {
            this.saveData(configurations);
            return configuration;
        }
        else{
            return null;
        }
    }
}
