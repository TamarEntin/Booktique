package exceptions;

@SuppressWarnings("serial")
public class BookAlreadyReturnedException extends BusinessException {
    public BookAlreadyReturnedException() {
        super("Book Already Returned");
    }
}
