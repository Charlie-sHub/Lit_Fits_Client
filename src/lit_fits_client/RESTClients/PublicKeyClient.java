package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Class that implements the PublicKeyInterface
 *
 * @author Carlos Mendez
 */
public class PublicKeyClient implements PublicKeyClientInterface {
    private WebTarget webTarget;
    private Client client;

    /**
     * Constructor
     *
     * @param baseUri address of the service
     */
    public PublicKeyClient(String baseUri) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(baseUri).path("litfitsserver.encryption.publicKey");
    }

    @Override
    public <T> T getPublicKey(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return (T) resource.request(javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM).get(responseType);
    }

    @Override
    public void close() {
        client.close();
    }
}
