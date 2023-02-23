package interfaces.repository;

import entities.BorrowedBook;

import java.util.Vector;

public interface IBorrowedBookRepository extends IRepository<BorrowedBook>{

    BorrowedBook update (BorrowedBook borrowedBook);

    BorrowedBook fetch(String userID, String bookID);

    BorrowedBook fetch(String borrowID);

    Vector<BorrowedBook> searchBorrowedBooksByID (String bookID);

    Vector<BorrowedBook> searchBorrowedBooksByUserID(String userID, int bookStatus);

    Vector<BorrowedBook> getAllBorrowed();

}
