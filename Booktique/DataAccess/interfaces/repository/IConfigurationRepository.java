package interfaces.repository;

import entities.Configuration;
import exceptions.BusinessException;
import exceptions.GeneralErrorException;

import java.util.List;
import java.util.Vector;

public interface IConfigurationRepository extends IRepository<Configuration>{

    Vector<Configuration> update(Vector<Configuration> configurations) throws GeneralErrorException;

    Configuration fetchConfigurationByName(String configkey) throws BusinessException;

    List<Configuration> getConfigurations();
}
