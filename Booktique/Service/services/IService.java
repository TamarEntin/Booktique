package services;

import exceptions.BusinessException;
import services.requests.RequestBase;
import services.responses.ResponseBase;

public interface IService<TRequest extends RequestBase, TResponse extends ResponseBase> {

    void validate(TRequest request) throws BusinessException;

    TResponse execute(TRequest request) throws BusinessException;

    TResponse rejectResponseBuilder(BusinessException businessException);
}
