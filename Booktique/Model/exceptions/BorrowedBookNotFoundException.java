package exceptions;

public class BorrowedBookNotFoundException extends BusinessException {
    public BorrowedBookNotFoundException()
    {
        super("BorrowedBookNotFound");
    }

}
