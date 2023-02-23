package exceptions;

public class OrderNotFoundException extends BusinessException {

    public OrderNotFoundException()
    {
        super("OrderNotFound");
    }
}
