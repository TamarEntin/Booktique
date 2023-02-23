package serviceHost;

import exceptions.BusinessException;
import initializer.ServicesIntializer;
import services.IService;
import services.requests.RequestBase;
import services.responses.ResponseBase;

public class ServiceCommand {

    private ServicesIntializer services;
    private static ServiceCommand Instance;

    private ServiceCommand()
    {
        services = new ServicesIntializer();
    }

    public static ServiceCommand getInstance()
    {
        if (Instance == null)
            Instance = new ServiceCommand();
        return Instance;
    }
    public <TRequest extends RequestBase,TResponse extends ResponseBase> TResponse execute(TRequest request)
    {
        try{

            IService service = services.getService(request);

            try {
                return (TResponse) service.execute(request);
            }
            catch (BusinessException businessException)
            {
                return (TResponse) service.rejectResponseBuilder(businessException);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
