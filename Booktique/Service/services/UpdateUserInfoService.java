package services;

import entities.User;
import exceptions.BusinessException;
import exceptions.UserNotFoundException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.UserFunctionalityManager;
import services.requests.UpdateUserInfoRequest;
import services.responses.UpdateUserInfoResponse;

public class UpdateUserInfoService implements IService<UpdateUserInfoRequest, UpdateUserInfoResponse> {

    private IUserFunctionalityManager userFunctionalityManager;
    private IAuthenticationValidator authenticationValidator;

    public UpdateUserInfoService(IUserRepository userRepository)
    {
        this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
        this.authenticationValidator = new AuthenticationValidator(userRepository);
    }

    @Override
    public void validate(UpdateUserInfoRequest updateUserInfoRequest) throws UserNotFoundException {
       this.authenticationValidator.validateUserId(updateUserInfoRequest.getUserToUpdate().getId());
    }

    @Override
    public UpdateUserInfoResponse execute(UpdateUserInfoRequest updateUserInfoRequest) throws BusinessException {
        User updatedUser = this.userFunctionalityManager.update(updateUserInfoRequest.getUserToUpdate());
        return new UpdateUserInfoResponse(updatedUser);
    }

    @Override
    public UpdateUserInfoResponse rejectResponseBuilder(BusinessException businessException) {
        return new UpdateUserInfoResponse(businessException);
    }
}
