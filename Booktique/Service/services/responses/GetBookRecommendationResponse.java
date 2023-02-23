package services.responses;

import entities.Recommendation;
import exceptions.BusinessException;

import java.util.Vector;

public class GetBookRecommendationResponse extends ResponseBase {
    private Vector<Recommendation> booksRecommendation;

    public GetBookRecommendationResponse(Vector<Recommendation> booksRecommendation) {
        this.booksRecommendation = booksRecommendation;
        if (booksRecommendation == null || booksRecommendation.isEmpty())
            this.rejectResponse("General Error");
        else
            this.buildResponse();
    }

    public GetBookRecommendationResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.booksRecommendation = null;
    }
    public Vector<Recommendation> getBooksRecommendation(){return this.booksRecommendation;}
}
