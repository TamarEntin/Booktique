package repository;

import entities.BookStock;
import interfaces.repository.IBorrowedBookRepository;
import entities.BorrowedBook;

import java.util.Vector;
import java.util.stream.Collectors;

public class BorrowedBookRepository extends RepositoryBase<BorrowedBook> implements IBorrowedBookRepository {

    private Vector<BorrowedBook> books;

    public BorrowedBookRepository()
    {
        books = this.loadData();
    }


    public BorrowedBook insert(BorrowedBook borrowedBook) {
        if (books == null)
           this.books = new Vector<>();

        BorrowedBook bookResult = books.stream().filter(book ->
                book.getBorrowID().equals(borrowedBook.getBorrowID())).findFirst().orElse(null);

        if (bookResult != null)
            return null;

        boolean result = books.add(borrowedBook);
        if (result)
        {
            this.saveData(books);
            return borrowedBook;
        }
        else{
            return null;
        }
    }

    public BorrowedBook update (BorrowedBook borrowedBook)
    {
        if (books == null || books.isEmpty())
            return null;

        BorrowedBook bookResult = books.stream().filter(bookTemp -> bookTemp.getBorrowID().equals(borrowedBook.getBorrowID()))
                .findFirst().orElse(null);

        if (bookResult == null)
            return null;

        books.remove(bookResult);

        boolean result = books.add(borrowedBook);

        if (result)
        {
            this.saveData(books);
            return borrowedBook;
        }
        else{
            return null;
        }
    }

    public BorrowedBook fetch(String userID, String bookID)
    {
        if (books == null || books.isEmpty())
            return null;

        BorrowedBook bookResult = books.stream().filter(bookTemp -> bookTemp.getBookID().equals(bookID) &&
                                                        bookTemp.getUserID().equals(userID))
                                                .findFirst().orElse(null);

        return bookResult;
    }

    public BorrowedBook fetch(String borrowID)
    {
        if (books == null || books.isEmpty())
            return null;

        BorrowedBook bookResult = books.stream().filter(bookTemp -> bookTemp.getBorrowID().equals(borrowID))
                .findFirst().orElse(null);

        return bookResult;
    }

    public Vector<BorrowedBook> searchBorrowedBooksByID (String bookID)
    {
        if (books == null || books.isEmpty())
            return null;

        Vector<BorrowedBook> bookResult = books.stream().filter(bookTemp -> bookTemp.getBookID().equals(bookID))
                .collect(Collectors.toCollection(() -> new Vector<BorrowedBook>()));

        return bookResult;

    }

    public Vector<BorrowedBook> searchBorrowedBooksByUserID(String userID,  int bookStatus)
    {
        if (books == null)
             return null;

        if (userID.isEmpty() || userID == null)
            return books.stream().filter(bookTemp -> bookTemp.getStatus() == bookStatus)
                    .collect(Collectors.toCollection(() -> new Vector<BorrowedBook>()));

        return books.stream().filter(bookTemp -> bookTemp.getUserID().equals(userID) &&
                bookTemp.getStatus() == bookStatus)
                .collect(Collectors.toCollection(() -> new Vector<BorrowedBook>()));

    }

    public Vector<BorrowedBook> getAllBorrowed()
    {
        return this.books;
    }

}
