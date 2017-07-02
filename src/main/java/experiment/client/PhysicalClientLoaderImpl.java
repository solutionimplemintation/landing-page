package experiment.client;

import groovy.lang.Singleton;

@Singleton
public class PhysicalClientLoaderImpl extends AbstractClient implements ClientLoader {

    private String firstName;

    private String lastName;

    public PhysicalClientLoaderImpl() {
        super.clientType = ClientType.physical;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String load() {
        return "PhysicalClient{" +
                "clientId='" + super.getClientId() + '\'' +
                "clientType='" + super.getClientType() + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public String getClientDescription() {
        return "It's physical client.";
    }
}
