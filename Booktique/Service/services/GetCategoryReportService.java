package services;

import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IBookBorrowManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;
import interfaces.repository.IConfigurationRepository;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.BookBorrowManager;
import services.requests.GetCategoryReportRequest;
import services.responses.GetCategoryReportResponse;

public class GetCategoryReportService implements IService<GetCategoryReportRequest, GetCategoryReportResponse> {
    private IAuthenticationValidator authenticationValidator;
    private IBookBorrowManager bookBorrowManager;

    public GetCategoryReportService(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository,
                                    IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.bookBorrowManager = new BookBorrowManager(userRepository, borrowedBookRepository, bookStockRepository, configurationRepository);
    }

    @Override
    public void validate(GetCategoryReportRequest getCategoryRequest) throws BusinessException {
        this.authenticationValidator.validateUserNotReader(getCategoryRequest.getUserId());
    }

    @Override
    public GetCategoryReportResponse execute(GetCategoryReportRequest getCategoryRequest) throws BusinessException {
        return new GetCategoryReportResponse(this.bookBorrowManager.getTheMostBorrowedCategory());
    }

    @Override
    public GetCategoryReportResponse rejectResponseBuilder(BusinessException businessException) {
        return new GetCategoryReportResponse(businessException);
    }
}
