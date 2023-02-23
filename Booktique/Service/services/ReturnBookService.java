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
import services.requests.ReturnBookRequest;
import services.responses.ReturnBookResponse;

public class ReturnBookService implements IService<ReturnBookRequest, ReturnBookResponse> {
    private IBookBorrowManager bookBorrowManager;
    private IAuthenticationValidator authenticationValidator;

    public ReturnBookService(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository,
                              IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.bookBorrowManager = new BookBorrowManager(userRepository, borrowedBookRepository,bookStockRepository,configurationRepository);
    }

    @Override
    public void validate(ReturnBookRequest returnBookRequest) throws BusinessException{
        authenticationValidator.validateUserId(returnBookRequest.getUserId());

        if (returnBookRequest.getBorrowId().equals(""))
            throw new InvalidRequestException("ReturnBookRequest");
    }

    @Override
    public ReturnBookResponse execute(ReturnBookRequest returnBookRequest) throws BusinessException {
        BorrowedBook borrowedBook = bookBorrowManager.returnBook(returnBookRequest.getUserId(), returnBookRequest.getBorrowId());
        return new ReturnBookResponse(borrowedBook);
    }

    @Override
    public ReturnBookResponse rejectResponseBuilder(BusinessException businessException) {
        return new ReturnBookResponse(businessException);
    }
}
