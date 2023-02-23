package services;

import entities.User;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.UserFunctionalityManager;
import services.requests.RegisterUserRequest;
import services.responses.RegisterUserResponse;

public class RegisterUserService implements  IService<RegisterUserRequest, RegisterUserResponse>  {

    private IUserFunctionalityManager userFunctionalityManager;

    public RegisterUserService(IUserRepository userRepository) {
        this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
    }


    @Override
    public void validate(RegisterUserRequest loginRequest) throws BusinessException{
        if (loginRequest.getNewUser()== null)
            throw new InvalidRequestException("RegisterUserRequest");
    }

    @Override
    public RegisterUserResponse execute(RegisterUserRequest loginRequest) throws BusinessException {
        User user = userFunctionalityManager.register(loginRequest.getNewUser());
        return new RegisterUserResponse(user);
    }

    @Override
    public RegisterUserResponse rejectResponseBuilder(BusinessException businessException) {
        return new RegisterUserResponse(businessException);
    }
}
