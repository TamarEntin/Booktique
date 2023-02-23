package services.responses;

import enums.ResponseStatus;
import exceptions.BusinessException;

public abstract class ResponseBase {

    protected java.lang.String errorMessage;
    protected int status;

    public void rejectResponse(BusinessException exception)
    {
        errorMessage = exception.getErrorMessage();
        status = ResponseStatus.Error.errorCode();
    }

    public void rejectResponse(java.lang.String errorMessage)
    {
        this.errorMessage = errorMessage;
        status = ResponseStatus.Error.errorCode();
    }

    public void buildResponse()
    {
        errorMessage = "";
        status = ResponseStatus.OK.errorCode();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}