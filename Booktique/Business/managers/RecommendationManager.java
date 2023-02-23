package managers;

import entities.Recommendation;
import exceptions.BusinessException;
import interfaces.business.IRecommendationManager;
import interfaces.repository.IRecommendationRepository;

import java.util.Vector;

public class RecommendationManager implements IRecommendationManager {

    private IRecommendationRepository recommendationRepository;

    public RecommendationManager(IRecommendationRepository recommendationRepository)
    {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public boolean addRecommendation(Recommendation recommendation) throws BusinessException {

        Recommendation recommend = recommendationRepository.insert(recommendation);

        return recommend == null ? false : true;
    }

    public Vector<Recommendation> getAllRecommendationByBookId(String bookId)
    {
        return this.recommendationRepository.searchRecommendationByBookID(bookId);
    }
}
