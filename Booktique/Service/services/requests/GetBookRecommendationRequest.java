package services.requests;

public class GetBookRecommendationRequest extends RequestBase {
    private String bookId;

    public GetBookRecommendationRequest(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId(){return this.bookId;}
}
