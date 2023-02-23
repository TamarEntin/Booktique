package interfaces.business;

import entities.Configuration;
import exceptions.GeneralErrorException;

import java.util.List;
import java.util.Vector;

public interface IConfigurationManager {

    List<Configuration> getConfigurations();

    Vector<Configuration> updateConfiguration(Vector<Configuration> configuration) throws GeneralErrorException;
}
