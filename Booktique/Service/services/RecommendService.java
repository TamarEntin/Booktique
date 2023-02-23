package services;

import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IRecommendationManager;
import interfaces.repository.IRecommendationRepository;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.RecommendationManager;
import services.requests.RecommendRequest;
import services.responses.RecommendResponse;

public class RecommendService implements IService<RecommendRequest, RecommendResponse> {

    private IRecommendationManager recommendationManager;
    private IAuthenticationValidator authenticationValidator;

    public RecommendService(IUserRepository userRepository, IRecommendationRepository recommendationRepository)
    {
        this.recommendationManager = new RecommendationManager(recommendationRepository);
        this.authenticationValidator = new AuthenticationValidator(userRepository);
    }

    @Override
    public void validate(RecommendRequest recommendRequest) throws BusinessException {
        if (recommendRequest.getRecommendation() == null)
            throw new InvalidRequestException("RecommendRequest");

        this.authenticationValidator.validateUserId(recommendRequest.getRecommendation().getUserID());
    }

    @Override
    public RecommendResponse execute(RecommendRequest recommendRequest) throws BusinessException {
        boolean result = recommendationManager.addRecommendation(recommendRequest.getRecommendation());
        return new RecommendResponse(result);
    }

    @Override
    public RecommendResponse rejectResponseBuilder(BusinessException businessException) {
        return new RecommendResponse(businessException);
    }

}
