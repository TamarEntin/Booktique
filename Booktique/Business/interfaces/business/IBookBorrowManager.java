package interfaces.business;

import entities.BookStock;
import entities.BorrowedBook;
import entities.CategoryReport;
import exceptions.BusinessException;

import java.util.HashMap;
import java.util.Vector;

public interface IBookBorrowManager {

    BorrowedBook extendBookBorrowing(String userId, String borrowId) throws BusinessException;

    BorrowedBook borrowBook(String userId, String bookId) throws BusinessException;

    BorrowedBook getBookBorrowInformation(String userId, String bookID) throws BusinessException;

    BorrowedBook returnBook (String userId, String borrowID) throws BusinessException;

    BorrowedBook approveBookReturn(String userId, String borrowID) throws BusinessException;

    Vector<CategoryReport> getTheMostBorrowedCategory();
}
