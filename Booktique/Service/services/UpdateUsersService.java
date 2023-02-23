package services;

import entities.User;
import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.UserFunctionalityManager;
import services.requests.UpdateUsersRequest;
import services.responses.UpdateUsersResponse;

import java.util.Vector;

public class UpdateUsersService implements IService<UpdateUsersRequest, UpdateUsersResponse> {
    private IAuthenticationValidator authenticationValidator;
    private IUserFunctionalityManager userFunctionalityManager;

    public UpdateUsersService(IUserRepository userRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
    }

    @Override
    public void validate(UpdateUsersRequest updateUsersRequest) throws BusinessException {
        this.authenticationValidator.validateUserManager(updateUsersRequest.getUserId());
    }

    @Override
    public UpdateUsersResponse execute(UpdateUsersRequest updateUsersRequest) throws BusinessException {
        Vector<User> updatedUsers = this.userFunctionalityManager.updateUsers(updateUsersRequest.getUsersToUpdate());
        return new UpdateUsersResponse(updatedUsers);
    }

    @Override
    public UpdateUsersResponse rejectResponseBuilder(BusinessException businessException) {
        return new UpdateUsersResponse(businessException);
    }
}
