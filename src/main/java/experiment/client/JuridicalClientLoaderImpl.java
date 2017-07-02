package experiment.client;

import groovy.lang.Singleton;

@Singleton
public class JuridicalClientLoaderImpl extends AbstractClient implements ClientLoader  {

    private String inn;

    public JuridicalClientLoaderImpl() {
        super.clientType = ClientType.juridical;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public String load() {
        return "JuridicalClient{" +
                "clientId='" + super.getClientId() + '\'' +
                "clientType='" + super.getClientType() + '\'' +
                "inn='" + inn + '\'' +
                '}';
    }

    @Override
    public String getClientDescription() {
        return "It's juridical client.";
    }
}
