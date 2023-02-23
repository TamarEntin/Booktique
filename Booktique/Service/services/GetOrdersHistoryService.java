package services;

import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IOrderManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBooksInOrdersRepository;
import interfaces.repository.IOrderRepository;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.OrderManager;
import services.requests.GetOrdersHistoryRequest;
import services.responses.GetOrderHistoryResponse;

public class GetOrdersHistoryService implements IService<GetOrdersHistoryRequest, GetOrderHistoryResponse> {
    private IAuthenticationValidator authenticationValidator;
    private IOrderManager orderManager;

    public GetOrdersHistoryService(IUserRepository userRepository, IOrderRepository orderRepository,
                                   IBookStockRepository bookStockRepository, IBooksInOrdersRepository booksInOrdersRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.orderManager = new OrderManager(orderRepository, userRepository, bookStockRepository, booksInOrdersRepository);
    }

    @Override
    public void validate(GetOrdersHistoryRequest getOrdersHistoryRequest) throws BusinessException {
        this.authenticationValidator.validateUserNotReader(getOrdersHistoryRequest.getUserId());

    }

    @Override
    public GetOrderHistoryResponse execute(GetOrdersHistoryRequest getOrdersHistoryRequest) throws BusinessException {
        return new GetOrderHistoryResponse(this.orderManager.getAllOrders());
    }

    @Override
    public GetOrderHistoryResponse rejectResponseBuilder(BusinessException businessException) {
        return new GetOrderHistoryResponse(businessException);
    }
}
