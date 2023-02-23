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
import services.requests.ExtendLendingRequest;
import services.responses.ExtendLendingResponse;

public class ExtendLendingService implements IService<ExtendLendingRequest, ExtendLendingResponse> {
    private IBookBorrowManager bookBorrowManager;
    private IAuthenticationValidator authenticationValidator;

    public ExtendLendingService(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository,
                                IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.bookBorrowManager = new BookBorrowManager(userRepository, borrowedBookRepository, bookStockRepository, configurationRepository);
    }

    @Override
    public void validate(ExtendLendingRequest extendLendingRequest) throws BusinessException{
        if (extendLendingRequest.getBorrowId().equals(""))
            throw new InvalidRequestException("extendLendingRequest");

        authenticationValidator.validateUserId(extendLendingRequest.getUserId());
    }

    @Override
    public ExtendLendingResponse execute(ExtendLendingRequest extendLendingRequest) throws BusinessException {
        BorrowedBook borrowedBook = bookBorrowManager.extendBookBorrowing(extendLendingRequest.getUserId(), extendLendingRequest.getBorrowId());
        boolean isSuccess = borrowedBook == null ? false : true;
        return new ExtendLendingResponse(isSuccess);

    }

    @Override
    public ExtendLendingResponse rejectResponseBuilder(BusinessException businessException) {
        return new ExtendLendingResponse(businessException);
    }
}
