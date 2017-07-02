package experiment.client;


public abstract class AbstractClient {

    protected Long clientId;

    protected ClientType clientType;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public abstract String getClientDescription();

}
