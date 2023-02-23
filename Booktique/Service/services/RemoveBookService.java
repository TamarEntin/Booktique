package services;

import entities.BookStock;
import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.BooksManager;
import repository.BorrowedBookRepository;
import services.requests.RemoveBookRequest;
import services.responses.RemoveBookResponse;

public class RemoveBookService implements IService<RemoveBookRequest, RemoveBookResponse> {
    private IAuthenticationValidator authenticationValidator;
    private BooksManager booksManager;

    public RemoveBookService(IUserRepository userRepository, IBookStockRepository bookStockRepository,
                             IBorrowedBookRepository borrowedBookRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.booksManager = new BooksManager(bookStockRepository, borrowedBookRepository);
    }

    @Override
    public void validate(RemoveBookRequest removeBookRequest) throws BusinessException {
        this.authenticationValidator.validateUserNotReader(removeBookRequest.getUserId());
    }

    @Override
    public RemoveBookResponse execute(RemoveBookRequest removeBookRequest) throws BusinessException {
        BookStock book = booksManager.removeBookQuantity(removeBookRequest.getBookId(), removeBookRequest.getQuantity());
        return new RemoveBookResponse(book);
    }

    @Override
    public RemoveBookResponse rejectResponseBuilder(BusinessException businessException) {
        return new RemoveBookResponse(businessException);
    }
}
