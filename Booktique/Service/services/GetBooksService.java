package services;

import entities.BookStock;
import exceptions.BusinessException;
import interfaces.business.IBooksManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;
import managers.BooksManager;
import services.requests.GetBooksRequest;
import services.responses.GetBooksResponse;

import java.util.List;
import java.util.Vector;

public class GetBooksService implements IService<GetBooksRequest, GetBooksResponse> {
    private IBooksManager booksManager;

    public GetBooksService(IBookStockRepository bookStockRepository, IBorrowedBookRepository borrowedBookRepository)
    {
        this.booksManager = new BooksManager(bookStockRepository, borrowedBookRepository);
    }

    @Override
    public void validate(GetBooksRequest getBooksRequest) throws BusinessException {

    }

    @Override
    public GetBooksResponse execute(GetBooksRequest getBooksRequest) throws BusinessException {
        Vector<BookStock> books = this.booksManager.getBooksByFilter(getBooksRequest.getFilter(), getBooksRequest.getUpdateQuantity());
        return new GetBooksResponse(books);
    }

    @Override
    public GetBooksResponse rejectResponseBuilder(BusinessException businessException) {
        return new GetBooksResponse(businessException);
    }
}
