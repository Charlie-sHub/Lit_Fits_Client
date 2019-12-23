package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

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
    // Gotta change the URL, probably read it once at the start of the program and then pass it the REST client
    private static final String BASE_URI = "http://localhost:8080/Lit_Fits_Server/webresources";

    public PublicKeyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("litfitsserver.encryption.publicKey");
    }

    @Override
    public <T> T getPublicKey(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    @Override
    public void close() {
        client.close();
    }
    
}
