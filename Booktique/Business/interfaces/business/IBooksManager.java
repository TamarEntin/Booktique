package interfaces.business;

import entities.BookStock;
import enums.BooksFilter;
import exceptions.BusinessException;

import java.util.List;
import java.util.Vector;

public interface IBooksManager {
    
    BookStock removeBookQuantity(String bookId, int quantity) throws BusinessException;
    Vector<BookStock> getBooksByFilter(BooksFilter filter, boolean updateQuantity);

}
