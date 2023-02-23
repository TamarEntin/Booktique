package services;

import entities.BorrowedBook;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IBookBorrowManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;
import interfaces.repository.IConfigurationRepository;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.BookBorrowManager;
import services.requests.BookLendingRequest;
import services.responses.BookLendingResponse;

public class BookLendingService implements IService<BookLendingRequest, BookLendingResponse> {
    private IBookBorrowManager bookBorrowManager;
    private IAuthenticationValidator authenticationValidator;

    public BookLendingService(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository,
                                    IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.bookBorrowManager = new BookBorrowManager(userRepository, borrowedBookRepository,bookStockRepository,configurationRepository);
    }

    @Override
    public void validate(BookLendingRequest bookLendingRequest) throws BusinessException{
        if (bookLendingRequest.getBookStock() == null)
            throw new InvalidRequestException("BookLendingRequest");

        this.authenticationValidator.validateUserId(bookLendingRequest.getUserId());
    }

    @Override
    public BookLendingResponse execute(BookLendingRequest bookLendingRequest) throws BusinessException {
        BorrowedBook borrowedBook = this.bookBorrowManager.borrowBook(bookLendingRequest.getUserId(), bookLendingRequest.getBookStock());
        return new BookLendingResponse(borrowedBook);

    }

    @Override
    public BookLendingResponse rejectResponseBuilder(BusinessException businessException) {
        return new BookLendingResponse(businessException);
    }
}
