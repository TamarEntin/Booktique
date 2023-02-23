package services;

import entities.UserLending;
import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IUserBooksManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBooksInOrdersRepository;
import interfaces.repository.IBorrowedBookRepository;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.UserBooksManager;
import services.requests.AllBooksLendingsInformationRequest;
import services.responses.AllBooksLendingsInformationResponse;

import java.util.Vector;

public class AllBooksLendingsInformationService implements IService<AllBooksLendingsInformationRequest, AllBooksLendingsInformationResponse> {
    private IAuthenticationValidator authenticationValidator;
    private IUserBooksManager userBooksManager;

    public AllBooksLendingsInformationService(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository, IBookStockRepository bookStockRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.userBooksManager = new UserBooksManager(bookStockRepository, borrowedBookRepository,userRepository);
    }

    @Override
    public void validate(AllBooksLendingsInformationRequest allBooksLendingsInformationRequest) throws BusinessException {
        this.authenticationValidator.validateUserId(allBooksLendingsInformationRequest.getUserId());
    }

    @Override
    public AllBooksLendingsInformationResponse execute(AllBooksLendingsInformationRequest allBooksLendingsInformationRequest) throws BusinessException {
        Vector<UserLending> userLending = this.userBooksManager.getAllUserActiveBorrowing(allBooksLendingsInformationRequest.getUserId());
        return new AllBooksLendingsInformationResponse(userLending);
    }

    @Override
    public AllBooksLendingsInformationResponse rejectResponseBuilder(BusinessException businessException) {
        return new AllBooksLendingsInformationResponse(businessException);
    }
}
