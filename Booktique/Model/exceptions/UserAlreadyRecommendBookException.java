package exceptions;

public class UserAlreadyRecommendBookException extends BusinessException {
    public UserAlreadyRecommendBookException() {
        super("User Already Recommended Before");
    }
}
