package exceptions;

public class InvalidRequestException extends BusinessException {
    private String requestName;

    public InvalidRequestException(String requestName) {
        super("InvalidRequest");
        this.requestName = requestName;
    }

    public String getRequestName()
    {
        return this.requestName;
    }
}
