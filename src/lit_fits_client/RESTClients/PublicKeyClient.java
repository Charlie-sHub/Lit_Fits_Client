package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:PublicKeyFacadeREST [litfitsserver.encryption.publicKey]<br>
 * USAGE:
 * <pre>
 *        PublicKeyClient client = new PublicKeyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Carlos Mendez
 */
public class PublicKeyClient implements PublicKeyClientInterface {
    private WebTarget webTarget;
    private Client client;

    /**
     *
     * @param baseUri
     */
    public PublicKeyClient(String baseUri) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(baseUri).path("litfitsserver.encryption.publicKey");
    }

    @Override
    public <T> T getPublicKey(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    @Override
    public void close() {
        client.close();
    }
    
}
