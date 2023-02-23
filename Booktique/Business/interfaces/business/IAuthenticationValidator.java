package interfaces.business;

import exceptions.BusinessException;
import exceptions.UserNotFoundException;

public interface IAuthenticationValidator {

    void validateUserId(String userId) throws UserNotFoundException;
    void validateUserManager(String userId) throws BusinessException;

    void validateUserNotReader(String userId) throws BusinessException;
}
