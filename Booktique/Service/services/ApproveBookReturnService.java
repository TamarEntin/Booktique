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
import services.requests.ApproveBookReturnRequest;
import services.responses.ApproveBookReturnResponse;

public class ApproveBookReturnService implements IService<ApproveBookReturnRequest, ApproveBookReturnResponse> {
    private IBookBorrowManager bookBorrowManager;
    private IAuthenticationValidator authenticationValidator;

    public ApproveBookReturnService(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository,
                                     IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.bookBorrowManager = new BookBorrowManager(userRepository, borrowedBookRepository,bookStockRepository,configurationRepository);
    }

    @Override
    public void validate(ApproveBookReturnRequest approveBookReturnRequest) throws BusinessException{
        this.authenticationValidator.validateUserId(approveBookReturnRequest.getUserId());

        if (approveBookReturnRequest.getBorrowId().equals(""))
            throw new InvalidRequestException("ApproveBookReturnRequest");
    }

    @Override
    public ApproveBookReturnResponse execute(ApproveBookReturnRequest approveBookReturnRequest) throws BusinessException {
        BorrowedBook borrowedBook = this.bookBorrowManager.approveBookReturn(approveBookReturnRequest.getUserId(), approveBookReturnRequest.getBorrowId());
        return new ApproveBookReturnResponse(borrowedBook);
    }

    @Override
    public ApproveBookReturnResponse rejectResponseBuilder(BusinessException businessException) {
        return new ApproveBookReturnResponse(businessException);
    }
}
