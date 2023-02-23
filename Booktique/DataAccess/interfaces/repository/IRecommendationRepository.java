package interfaces.repository;

import entities.Recommendation;

import java.util.Vector;

public interface IRecommendationRepository extends IRepository<Recommendation>{

    Recommendation update (Recommendation recommendation);

    Recommendation fetchRecommendationByUserIDBookID(String userID, String bookID);

    Vector<Recommendation> fetchRecommendationByUserID(String userID);

    Vector<Recommendation> searchRecommendationByBookID(String bookID);
}
