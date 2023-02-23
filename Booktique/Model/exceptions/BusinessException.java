package exceptions;

public class BusinessException extends Exception{
    private String ErrorMessage;

    public BusinessException(String errorMessage)
    {
        ErrorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return this.ErrorMessage;
    }
}
