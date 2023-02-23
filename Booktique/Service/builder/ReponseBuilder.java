package builder;

import exceptions.BusinessException;
import services.responses.ResponseBase;

public class ReponseBuilder {

    public <TResponse extends ResponseBase> ResponseBase buildResponse(Exception exception, TResponse response)
    {
        if (exception instanceof BusinessException)
        {
            response.rejectResponse((BusinessException)exception);
        }
        else
        {
            response.rejectResponse(exception.getMessage());
        }
        return (ResponseBase)response;
    }
}
