package services;

import entities.Recommendation;
import exceptions.BusinessException;
import interfaces.business.IRecommendationManager;
import interfaces.repository.IRecommendationRepository;
import managers.RecommendationManager;
import services.requests.GetBookRecommendationRequest;
import services.responses.GetBookRecommendationResponse;

import java.util.Vector;

public class GetBookRecommendationService implements IService<GetBookRecommendationRequest, GetBookRecommendationResponse> {
    private IRecommendationManager recommendationManager;

    public GetBookRecommendationService(IRecommendationRepository recommendationRepository) {
        this.recommendationManager = new RecommendationManager(recommendationRepository);
    }


    @Override
    public void validate(GetBookRecommendationRequest getBookRecommendationRequest) throws BusinessException {

    }

    @Override
    public GetBookRecommendationResponse execute(GetBookRecommendationRequest getBookRecommendationRequest) throws BusinessException {
        Vector<Recommendation> result = this.recommendationManager.getAllRecommendationByBookId(getBookRecommendationRequest.getBookId());
        return new GetBookRecommendationResponse(result);
    }

    @Override
    public GetBookRecommendationResponse rejectResponseBuilder(BusinessException businessException) {
        return new GetBookRecommendationResponse(businessException);
    }
}
