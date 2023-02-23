package services.requests;

public abstract class RequestBase {
    private String serviceName = this.getClass().getSimpleName().replace("Request", "Service");

    public String getServiceName() {
        return serviceName;
    }
}
