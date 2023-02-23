package repository;

import entities.Order;
import exceptions.BusinessException;
import exceptions.UserAlreadyRecommendBookException;
import interfaces.repository.IRecommendationRepository;
import entities.Recommendation;

import java.util.Vector;
import java.util.stream.Collectors;

public class RecommendationRepository extends RepositoryBase<Recommendation> implements IRecommendationRepository {

    private Vector<Recommendation> recommendations;

    public RecommendationRepository(){ this.recommendations = this.loadData();}

    public Recommendation insert(Recommendation recommendation) throws BusinessException {
        if (recommendations == null)
            this.recommendations = new Vector<>();

        Recommendation recommendResult = recommendations.stream().filter(tempRecommend ->
                tempRecommend.getUserID().equalsIgnoreCase(recommendation.getUserID()) &&
                        tempRecommend.getBookID().equalsIgnoreCase(recommendation.getBookID()))
                .findFirst().orElse(null);

        if (recommendResult == null){
            recommendations.add(recommendation);
            this.saveData(recommendations);
            return recommendation;
        }

        throw new UserAlreadyRecommendBookException();
    }

    public Recommendation update (Recommendation recommendation)
    {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        Recommendation recommendationResult = recommendations.stream().filter(rcmd ->
                rcmd.getUserID().equalsIgnoreCase(recommendation.getUserID()) &&
                        rcmd.getBookID().equalsIgnoreCase(recommendation.getBookID()))
                .findFirst().orElse(null);

        if(recommendationResult == null)
            return null;

        recommendations.remove(recommendationResult);

        boolean result = recommendations.add(recommendation);

        if (result)
        {
            this.saveData(recommendations);
            return recommendation;
        }
        else{
            return null;
        }
    }


    public Recommendation fetchRecommendationByUserIDBookID(String userID, String bookID)
    {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        Recommendation rcmd = recommendations.stream().filter(recommendation ->
                recommendation.getUserID().equals(userID) && recommendation.getBookID().equals(bookID))
                .findFirst().orElse(null);

        return rcmd;
    }

    public Vector<Recommendation> fetchRecommendationByUserID(String userID)
    {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        return recommendations.stream().filter(recommendation ->
                recommendation.getUserID().equals(userID))
                .collect(Collectors.toCollection(() -> new Vector<Recommendation>()));
    }

    public Vector<Recommendation> searchRecommendationByBookID(String bookID)
    {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        return recommendations.stream().filter(recommendation ->
                recommendation.getBookID().equals(bookID))
                .collect(Collectors.toCollection(() -> new Vector<Recommendation>()));
    }
}
