package exceptions;

public class UserAlreadyExistException extends BusinessException {

    public UserAlreadyExistException()
    {
        super("UserAlreadyExist");
    }
}
