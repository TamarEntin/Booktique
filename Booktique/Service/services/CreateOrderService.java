package services;

import entities.Order;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IOrderManager;
import interfaces.repository.*;
import managers.AuthenticationValidator;
import managers.OrderManager;
import services.requests.CreateOrderRequest;
import services.responses.CreateOrderResponse;

public class CreateOrderService implements IService<CreateOrderRequest, CreateOrderResponse> {

    private IOrderManager orderManager;
    private IAuthenticationValidator authenticationValidator;

    public CreateOrderService(IOrderRepository orderRepository, IUserRepository userRepository, IBookStockRepository bookStockRepository,
                              IConfigurationRepository configurationRepository, IBooksInOrdersRepository booksInOrdersRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.orderManager = new OrderManager(orderRepository, userRepository, bookStockRepository, booksInOrdersRepository);
    }


    @Override
    public void validate(CreateOrderRequest createOrderRequest) throws BusinessException{
        if (createOrderRequest.getBooksInOrder() == null)
            throw new InvalidRequestException("CreateOrderRequest");

        if (createOrderRequest.getNewOrder() == null)
            throw new InvalidRequestException("CreateOrderRequest");

        authenticationValidator.validateUserId(createOrderRequest.getUserId());
    }

    @Override
    public CreateOrderResponse execute(CreateOrderRequest request) throws BusinessException {
        Order order = orderManager.insertOrder(request.getUserId(), request.getNewOrder(), request.getBooksInOrder());
        return new CreateOrderResponse(order);
    }

    @Override
    public CreateOrderResponse rejectResponseBuilder(BusinessException businessException) {
        return new CreateOrderResponse(businessException);
    }
}
