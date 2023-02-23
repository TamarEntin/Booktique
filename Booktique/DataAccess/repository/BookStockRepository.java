package repository;

import exceptions.BusinessException;
import exceptions.InvalidBookQuantityException;
import interfaces.repository.IBookStockRepository;
import entities.BookStock;

import java.util.Vector;
import java.util.stream.Collectors;

public class BookStockRepository extends RepositoryBase<BookStock> implements IBookStockRepository {
    private Vector<BookStock> books;

    public BookStockRepository()
    {
        this.books = this.loadData();
    }

    public BookStock insert(BookStock bookStock) {
        if (books == null)
            this.books = new Vector<>();

        BookStock bookResult = books.stream().filter(book -> book.getId().equals(bookStock.getId())
                || (book.getAuthorName().equalsIgnoreCase(bookStock.getAuthorName()) && book.getBookName().equalsIgnoreCase(bookStock.getBookName())))
                .findFirst().orElse(null);

        boolean result = false;
        if (bookResult != null) {
            //update quantity
            bookResult.setQuantity(bookResult.getQuantity() + bookStock.getQuantity());
            books.remove(bookResult);
            result = books.add(bookResult);
        } else {
            result = books.add(bookStock);
        }

        if (result) {
            this.saveData(books);
            return bookResult == null ? bookStock : bookResult;
        } else {
            return null;
        }
    }

    public BookStock update (BookStock bookStock) {
        if (books == null || books.isEmpty())
            return null;

        BookStock bookResult = books.stream().filter(book ->
                book.getId().equals(bookStock.getId())).findFirst().orElse(null);

        if (bookResult == null)
            return null;

        books.remove(bookResult);

        boolean result = books.add(bookStock);

        if (result) {
            this.saveData(books);
            return bookStock;
        } else {
            return null;
        }
    }

    public BookStock fetch(String bookID)
    {
        if (books == null || books.isEmpty())
            return null;

        return books.stream().filter(book->
                book.getId().equals(bookID)).findFirst().orElse(null);
    }

    public BookStock fetchByCode(String bookCode)
    {
        if (books == null || books.isEmpty())
            return null;

        return books.stream().filter(book->
                book.getBookCode().equals(bookCode)).findFirst().orElse(null);
    }

    public Vector<BookStock> searchByName(String bookName)
    {
        if (books == null || books.isEmpty())
            return null;

        if (bookName == null || bookName.isEmpty())
            return this.books;

        return books.stream().filter(book->book.getBookName().equals(bookName))
                .collect(Collectors.toCollection(() -> new Vector<BookStock>()));
    }

    public BookStock fetch(String bookName, String authorName)
    {
        if (bookName.isEmpty() || authorName.isEmpty())
            return null;

        if (books == null || books.size() == 0)
            return null;

        return books.stream().filter(book -> book.getBookName().equals(bookName) && book.getAuthorName().equals(authorName))
                .findFirst().orElse(null);
    }

    public BookStock delete(BookStock bookStock, int quantity) throws BusinessException
    {
        if (books == null || books.isEmpty())
            return null;

        BookStock bookResult = books.stream().filter(book ->
                book.getId().equals(bookStock.getId())).findFirst().orElse(null);

        if (bookResult == null)
            return null;

        if (bookResult.getQuantity() < quantity)
            throw new InvalidBookQuantityException();

        if (bookResult.getQuantity() == quantity)
        {
            books.remove(bookResult);
            this.saveData(books);
            return bookResult;
        }
        else{
            bookResult.setQuantity(bookResult.getQuantity() - quantity);
            books.remove(bookResult);
            boolean result = books.add(bookStock);

            if (result) {
                this.saveData(books);
                return bookStock;
            } else {
                return null;
            }
        }
    }
}
