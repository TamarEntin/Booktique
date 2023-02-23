package exceptions;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException()
    {
        super("UserNotFound");
    }
}
