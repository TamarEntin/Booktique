package services.responses;

import exceptions.BusinessException;

public class ExtendLendingResponse extends ResponseBase{
    private boolean isSuccess;

    public ExtendLendingResponse(boolean isSuccess)
    {
        if (isSuccess)
        {
            this.buildResponse();
            this.isSuccess = true;
        }
        else
        {
            this.rejectResponse("General Error");
            this.isSuccess = false;
        }
    }

    public ExtendLendingResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.isSuccess = false;
    }
}
