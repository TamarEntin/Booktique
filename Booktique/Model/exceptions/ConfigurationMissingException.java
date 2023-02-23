package exceptions;

public class ConfigurationMissingException extends BusinessException {
    private String MissingElement;

    public ConfigurationMissingException(String missingElement)
    {
        super("ConfigurationMissing " + missingElement);
        MissingElement = missingElement;
    }
}
