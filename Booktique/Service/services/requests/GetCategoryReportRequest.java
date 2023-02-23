package services.requests;

public class GetCategoryReportRequest extends RequestBase {
    private String userId;

    public GetCategoryReportRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId(){return this.userId;}
}
