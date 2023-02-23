package interfaces.business;

import entities.Recommendation;
import entities.User;
import exceptions.BusinessException;

import java.util.Vector;

public interface IRecommendationManager {

    boolean addRecommendation(Recommendation recommendation) throws BusinessException;
     Vector<Recommendation> getAllRecommendationByBookId(String bookId);

}
