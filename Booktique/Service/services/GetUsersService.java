package services;

import entities.User;
import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.UserFunctionalityManager;
import services.requests.GetUsersRequest;
import services.responses.GetUsersResponse;

import java.util.Vector;

public class GetUsersService implements IService<GetUsersRequest, GetUsersResponse> {
    private IUserFunctionalityManager userFunctionalityManager;
    private IAuthenticationValidator authenticationValidator;

    public GetUsersService(IUserRepository userRepository)
    {
        this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
        this.authenticationValidator = new AuthenticationValidator(userRepository);
    }

    @Override
    public void validate(GetUsersRequest getUsersRequest) throws BusinessException {
        this.authenticationValidator.validateUserManager(getUsersRequest.getUserId());
    }

    @Override
    public GetUsersResponse execute(GetUsersRequest getUsersRequest) throws BusinessException {
        Vector<User> users = this.userFunctionalityManager.getAllUsers();
        return new GetUsersResponse(users);
    }

    @Override
    public GetUsersResponse rejectResponseBuilder(BusinessException businessException) {
        return new GetUsersResponse(businessException);
    }
}
