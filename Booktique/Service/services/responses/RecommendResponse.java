package services.responses;

import exceptions.BusinessException;

public class RecommendResponse extends ResponseBase{

    private boolean isSuccess;

    public RecommendResponse(boolean isSuccess)
    {
        if (isSuccess) {
            this.buildResponse();
            this.isSuccess = true;
        }
        else{
            this.rejectResponse("General Error");
            this.isSuccess = false;
        }
    }

    public RecommendResponse(BusinessException businessException)
    {
        this.rejectResponse(businessException);
        this.isSuccess = false;
    }

    public boolean getIsSuccess() {return isSuccess;}
}
