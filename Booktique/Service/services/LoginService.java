package services;

import entities.User;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.UserFunctionalityManager;
import services.requests.LoginRequest;
import services.responses.LoginResponse;

public class LoginService implements  IService<services.requests.LoginRequest, services.responses.LoginResponse> {

    private IUserFunctionalityManager userFunctionalityManager;

    public LoginService(IUserFunctionalityManager userFunctionalityManager)
    {
        this.userFunctionalityManager = userFunctionalityManager;
    }

    public LoginService(IUserRepository userRepository)
    {
        this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
    }


    @Override
    public void validate(LoginRequest loginRequest) throws BusinessException{
        if (loginRequest.getUsername().isEmpty())
            throw new InvalidRequestException("LoginRequest");

        if (loginRequest.getPassword().isEmpty())
            throw new InvalidRequestException("LoginRequest");
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest) throws BusinessException{
        User logedUser = userFunctionalityManager.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new LoginResponse(logedUser);
    }

    @Override
    public LoginResponse rejectResponseBuilder(BusinessException businessException) {
        return new LoginResponse(businessException);
    }


}
