package exceptions;

public class InvalidBookQuantityException extends BusinessException  {
    public InvalidBookQuantityException() {
        super("Invalid Book Quantity");
    }
}
