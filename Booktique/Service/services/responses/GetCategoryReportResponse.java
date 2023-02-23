package services.responses;

import entities.CategoryReport;
import exceptions.BusinessException;

import java.util.Vector;

public class GetCategoryReportResponse extends ResponseBase {
    private Vector<CategoryReport> categoryReports;


    public GetCategoryReportResponse(Vector<CategoryReport> categoryReports) {
        this.categoryReports = categoryReports;
        this.buildResponse();
    }

    public GetCategoryReportResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
    }
}
