package services;

import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.UserFunctionalityManager;
import services.requests.LogoutRequest;
import services.responses.LogoutResponse;

public class LogoutService implements  IService<services.requests.LogoutRequest, services.responses.LogoutResponse> {

        private IUserFunctionalityManager userFunctionalityManager;
        private IAuthenticationValidator authenticationValidator;

        public LogoutService(IUserRepository userRepository) {
                this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
                this.authenticationValidator = new AuthenticationValidator(userRepository);
        }

        @Override
        public void validate(LogoutRequest logoutRequest) throws BusinessException{
               authenticationValidator.validateUserId(logoutRequest.getUserId());
        }

        @Override
        public LogoutResponse execute(LogoutRequest logoutRequest) throws BusinessException {
                boolean isSuccess = userFunctionalityManager.logout(logoutRequest.getUserId());
                return new LogoutResponse();
        }

        @Override
        public LogoutResponse rejectResponseBuilder(BusinessException businessException) {
                return new LogoutResponse();
        }
}