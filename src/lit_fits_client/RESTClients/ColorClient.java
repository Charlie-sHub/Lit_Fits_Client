package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ColorFacadeREST [litfitsserver.entities.color]<br>
 * USAGE:
 * <pre>
 *        ColorClient client = new ColorClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Carlos Mendez
 */
public class ColorClient {
    private WebTarget webTarget;
    private Client client;
    // Gotta change the URL, probably read it once at the start of the program and then pass it the REST client
    private static final String BASE_URI = "http://localhost:8080/Lit_Fits_Server/webresources";

    public ColorClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("litfitsserver.entities.color");
    }

    public void edit(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public <T> T find(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String count() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public <T> T findAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void remove(String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{name})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
