package initializer;

import interfaces.repository.*;
import services.*;
import services.requests.RequestBase;

import java.util.Hashtable;

public class ServicesIntializer {

    private Hashtable<String, IService> services;
    private RepositoriesInitializer repositoriesInitializer;

    public ServicesIntializer()
    {
        repositoriesInitializer = new RepositoriesInitializer();

        services = new Hashtable<>();

        services.put("GetOrdersHistoryService",
                new GetOrdersHistoryService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IOrderRepository) repositoriesInitializer.getRepository("IOrderRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IBooksInOrdersRepository) repositoriesInitializer.getRepository("IBooksInOrdersRepository")));

        services.put("GetCategoryReportService",
                new GetCategoryReportService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBorrowedBookRepository) repositoriesInitializer.getRepository("IBorrowedBookRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IConfigurationRepository) repositoriesInitializer.getRepository("IConfigurationRepository")));


        services.put("AllBooksLendingsInformationService",
                new AllBooksLendingsInformationService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository")));

        services.put("AllUserAwaitingForApprovalBorrowingService",
                new AllUserAwaitingForApprovalBorrowingService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository")));

        services.put("ApproveBookReturnService",
                new ApproveBookReturnService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IConfigurationRepository)repositoriesInitializer.getRepository("IConfigurationRepository")));

        services.put("BookLendingInformationService",
                new BookLendingInformationService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IConfigurationRepository)repositoriesInitializer.getRepository("IConfigurationRepository")));

        services.put("BookLendingService",
                new BookLendingService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IConfigurationRepository)repositoriesInitializer.getRepository("IConfigurationRepository")));

        services.put("RemoveBookService",
                new RemoveBookService(
                        (IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IBorrowedBookRepository) repositoriesInitializer.getRepository("IBorrowedBookRepository")));

        services.put("CreateOrderService",
                new CreateOrderService((IOrderRepository) repositoriesInitializer.getRepository("IOrderRepository"),
                        (IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IConfigurationRepository)repositoriesInitializer.getRepository("IConfigurationRepository"),
                        (IBooksInOrdersRepository)repositoriesInitializer.getRepository("IBooksInOrdersRepository")));

        services.put("ExtendLendingService",
                new ExtendLendingService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IConfigurationRepository)repositoriesInitializer.getRepository("IConfigurationRepository")));

        services.put("GetBookRecommendationService",
                new GetBookRecommendationService((IRecommendationRepository) repositoriesInitializer.getRepository("IRecommendationRepository")));

        services.put("LoginService",
                new LoginService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository")));

        services.put("GetUsersService",
                new GetUsersService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository")));

        services.put("GetBooksService",
                new GetBooksService((IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository")));

        services.put("LogoutService",
                new LogoutService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository")));

        services.put("RecommendService",
                new RecommendService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IRecommendationRepository)repositoriesInitializer.getRepository("IRecommendationRepository")));

        services.put("RegisterUserService",
                new RegisterUserService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository")));

        services.put("ReturnBookService",
                new ReturnBookService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository"),
                        (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository"),
                        (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository"),
                        (IConfigurationRepository)repositoriesInitializer.getRepository("IConfigurationRepository")));

        services.put("SearchConfigurationService",
                new SearchConfigurationService((IConfigurationRepository) repositoriesInitializer.getRepository("IConfigurationRepository")));

        services.put("UpdateConfigurationService",
                new UpdateConfigurationService((IConfigurationRepository) repositoriesInitializer.getRepository("IConfigurationRepository")));

        services.put("UpdateUserInfoService",
                new UpdateUserInfoService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository")));

        services.put("UpdateUsersService",
                new UpdateUsersService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository")));
    }

    public IService getService(RequestBase request) {
        if (services == null) return null;

        try {
            return services.get(request.getServiceName());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
