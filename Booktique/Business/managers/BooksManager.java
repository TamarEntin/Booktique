package managers;

import entities.BookStock;
import enums.BooksFilter;
import exceptions.BookNotFoundException;
import exceptions.BusinessException;
import interfaces.IBookAvailableStrategy;
import interfaces.business.IBooksManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBooksInOrdersRepository;
import interfaces.repository.IBorrowedBookRepository;
import strategy.BookAvailableStrategy;

import java.util.List;
import java.util.Vector;

public class BooksManager implements IBooksManager {
    private IBookStockRepository bookStockRepository;
    private IBorrowedBookRepository borrowedBookRepository;
    private IBookAvailableStrategy bookAvailableStrategy;

    public BooksManager(IBookStockRepository bookStockRepository, IBorrowedBookRepository borrowedBookRepository)
    {
        this.bookStockRepository = bookStockRepository;
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookAvailableStrategy = new BookAvailableStrategy(borrowedBookRepository);
    }

    public Vector<BookStock> getBooksByFilter(BooksFilter filter, boolean updateQuantity)
    {
        if (filter == BooksFilter.All)
        {
            return this.bookStockRepository.searchByName(null);
        }
        else {
            Vector<BookStock> books = this.bookStockRepository.searchByName(null);
            if (books != null) {
                Vector<BookStock> booksAvailable = new Vector<>();
                for (BookStock book : books) {
                    boolean isAvailable = this.bookAvailableStrategy.isBookAvailableToBorrow(book);
                    if (isAvailable) {
                        /*
                        if (updateQuantity) {
                            int updatedQuantity = this.bookAvailableStrategy.getNumberOfBooksAvailableToBorrow(book);
                            book.setQuantity(updatedQuantity);
                        }

                         */
                        booksAvailable.add(book);
                    }
                }
                return booksAvailable;
            }
        }
        return null;
    }

    public BookStock removeBookQuantity(String bookId, int quantity) throws BusinessException
    {
        BookStock book = this.bookStockRepository.fetch(bookId);
        if (book == null)
            throw new BookNotFoundException();

        return this.bookStockRepository.delete(book, quantity);
    }
}
