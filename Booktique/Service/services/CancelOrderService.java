/*
package services;

import entities.Order;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IOrderManager;
import interfaces.repository.*;
import managers.AuthenticationValidator;
import managers.OrderManager;
import services.requests.CancelOrderRequest;
import services.responses.CancelOrderResponse;

public class CancelOrderService implements IService<CancelOrderRequest, CancelOrderResponse>{

    private IOrderManager orderManager;
    private IAuthenticationValidator authenticationValidator;

    public CancelOrderService(IOrderRepository orderRepository, IUserRepository userRepository, IBookStockRepository bookStockRepository,
                               IConfigurationRepository configurationRepository, IBooksInOrdersRepository booksInOrdersRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.orderManager = new OrderManager(orderRepository, userRepository, bookStockRepository, configurationRepository, booksInOrdersRepository);
    }

    @Override
    public void validate(CancelOrderRequest cancelOrderRequest) throws BusinessException{
        if (cancelOrderRequest.getOrderId().equals(""))
            throw new InvalidRequestException("CancelOrderRequest");

        authenticationValidator.validateUserId(cancelOrderRequest.getUserId());
    }

    @Override
    public CancelOrderResponse execute(CancelOrderRequest cancelOrderRequest) throws BusinessException {
        Order canceledOrder = orderManager.cancelOrder(cancelOrderRequest.getOrderId(), cancelOrderRequest.getUserId());
        return new CancelOrderResponse(canceledOrder);
    }

    @Override
    public CancelOrderResponse rejectResponseBuilder(BusinessException businessException) {
        return new CancelOrderResponse(businessException);
    }
}


/*
 */