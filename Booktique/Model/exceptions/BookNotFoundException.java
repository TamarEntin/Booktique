package exceptions;

public class BookNotFoundException extends BusinessException {
    public BookNotFoundException() {
        super("Book not Found");
    }
}
