package services.requests;

import entities.Recommendation;

public class RecommendRequest extends RequestBase{

    private Recommendation recommendation;

    public RecommendRequest (Recommendation recommendation)
    {
        this.recommendation = recommendation;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }
}
