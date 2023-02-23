package exceptions;

public class OrderPriceHigherThanBudgetException extends BusinessException {
    public OrderPriceHigherThanBudgetException()
    {
        super("OrderPriceHigherThanBudget");
    }
}
