package tests.repositories;

import entities.Configuration;
import enums.TestState;
import exceptions.BusinessException;
import exceptions.GeneralErrorException;
import interfaces.repository.IConfigurationRepository;

import java.util.List;
import java.util.Vector;

public class ConfigurationRepositoryDummy implements IConfigurationRepository {
    private Configuration configuration;
    private TestState testState;
    private Vector<Configuration> configurations;

    public void setup(TestState state,Configuration borrowedBook, Vector<Configuration> borrowedBooks)
    {
        this.configurations = borrowedBooks;
        this.configuration = borrowedBook;
        this.testState = state;
    }


    @Override
    public Vector<Configuration> update(Vector<Configuration> configurations) throws GeneralErrorException {
        if (this.testState == TestState.ReturnObject)
            return configurations;
        return null;
    }

    @Override
    public Configuration fetchConfigurationByName(String configkey) throws BusinessException {
        if (this.testState == TestState.ReturnObject)
            return configuration;
        return null;
    }

    @Override
    public List<Configuration> getConfigurations() {
        if (this.testState == TestState.ReturnObject)
            return configurations;
        return null;
    }

    @Override
    public Configuration insert(Configuration configuration) throws BusinessException {
        if (this.testState == TestState.ReturnObject)
            return configuration;
        return null;
    }
}
