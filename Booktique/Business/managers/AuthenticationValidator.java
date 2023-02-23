package managers;

import entities.User;
import entities.extension.UserExtension;
import enums.UserStatus;
import exceptions.BusinessException;
import exceptions.UserNotAuthorizeException;
import exceptions.UserNotFoundException;
import interfaces.business.IAuthenticationValidator;
import interfaces.repository.IUserRepository;

public class AuthenticationValidator implements IAuthenticationValidator {

    private IUserRepository userRepository;

    public AuthenticationValidator(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void validateUserId(String userId) throws UserNotFoundException {
        if (userRepository.fetch(userId) == null)
            throw new UserNotFoundException();
    }

    public void validateUserManager(String userId) throws BusinessException
    {
        validateUserId(userId);
        User user = userRepository.fetch(userId);
        if (UserExtension.isUserFitRole(user, UserStatus.Reader))
            throw new UserNotAuthorizeException();

    }

    @Override
    public void validateUserNotReader(String userId) throws BusinessException{
        validateUserId(userId);
        User user = userRepository.fetch(userId);
        if (!UserExtension.isUserFitRole(user, UserStatus.Manager))
            throw new UserNotAuthorizeException();
    }
}
