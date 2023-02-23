package exceptions;

public class InvalidBookException extends BusinessException {
    public InvalidBookException()
    {
        super("InvalidBook");
    }
}
