package services;

import entities.UserLending;
import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IUserBooksManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.UserBooksManager;
import services.requests.AllUserAwaitingForApprovalBorrowingRequest;
import services.responses.AllUserAwaitingForApprovalBorrowingResponse;

import java.util.Vector;

public class AllUserAwaitingForApprovalBorrowingService implements IService<AllUserAwaitingForApprovalBorrowingRequest, AllUserAwaitingForApprovalBorrowingResponse> {
    private IUserBooksManager userBooksManager;
    private IAuthenticationValidator authenticationValidator;

    public AllUserAwaitingForApprovalBorrowingService(IUserRepository userRepository,
                                                      IBorrowedBookRepository borrowedBookRepository,
                                                      IBookStockRepository bookStockRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.userBooksManager = new UserBooksManager(bookStockRepository, borrowedBookRepository, userRepository);
    }

    @Override
    public void validate(AllUserAwaitingForApprovalBorrowingRequest allUserAwaitingForApprovalBorrowingRequest) throws BusinessException {
        this.authenticationValidator.validateUserNotReader(allUserAwaitingForApprovalBorrowingRequest.getUserId());
    }

    @Override
    public AllUserAwaitingForApprovalBorrowingResponse execute(AllUserAwaitingForApprovalBorrowingRequest allUserAwaitingForApprovalBorrowingRequest) throws BusinessException {
        Vector<UserLending> usrLending = this.userBooksManager.getAllAwaitingForApprovalBorrowing();
        return new AllUserAwaitingForApprovalBorrowingResponse(usrLending);
    }

    @Override
    public AllUserAwaitingForApprovalBorrowingResponse rejectResponseBuilder(BusinessException businessException) {
        return new AllUserAwaitingForApprovalBorrowingResponse(businessException);
    }
}
