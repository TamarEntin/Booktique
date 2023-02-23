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
import services.requests.BookLendingInformationRequest;
import services.responses.BookLendingInformationResponse;

public class BookLendingInformationService implements IService<BookLendingInformationRequest, BookLendingInformationResponse> {
    private IBookBorrowManager bookBorrowManager;
    private IAuthenticationValidator authenticationValidator;

    public BookLendingInformationService(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository,
                                    IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.bookBorrowManager = new BookBorrowManager(userRepository, borrowedBookRepository,bookStockRepository,configurationRepository);
    }

    @Override
    public void validate(BookLendingInformationRequest bookLendingRequest) throws BusinessException{
        this.authenticationValidator.validateUserId(bookLendingRequest.getUserId());

        if (bookLendingRequest.getBookId().equals(""))
            throw new InvalidRequestException("BookLendingInformationRequest");
    }

    @Override
    public BookLendingInformationResponse execute(BookLendingInformationRequest bookLendingRequest) throws BusinessException {
        BorrowedBook borrowedBook = this.bookBorrowManager.getBookBorrowInformation(bookLendingRequest.getUserId(), bookLendingRequest.getBookId());
        return new BookLendingInformationResponse(borrowedBook);

    }

    @Override
    public BookLendingInformationResponse rejectResponseBuilder(BusinessException businessException) {
        return new BookLendingInformationResponse(businessException);
    }
}
