package interfaces.repository;

import entities.BookStock;
import exceptions.BusinessException;

import java.util.Vector;

public interface IBookStockRepository extends IRepository<BookStock>{

    BookStock update (BookStock bookStock);

    BookStock fetch(String bookID);

    BookStock fetchByCode(String bookCode);

    Vector<BookStock> searchByName(String bookName);

    BookStock fetch(String bookName, String authorName);

    BookStock delete(BookStock bookStock, int quantity) throws BusinessException;
}
