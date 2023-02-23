package services.responses;

import exceptions.BusinessException;

public class LogoutResponse extends ResponseBase {

    public LogoutResponse()
    {
        this.buildResponse();
    }

    public LogoutResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
    }
}
