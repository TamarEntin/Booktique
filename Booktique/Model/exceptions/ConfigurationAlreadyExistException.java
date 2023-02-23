package exceptions;

public class ConfigurationAlreadyExistException extends BusinessException {

    public ConfigurationAlreadyExistException()
    {
        super("ConfigurationAlreadyExist");
    }
}
