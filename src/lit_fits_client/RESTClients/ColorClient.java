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
    /**
     * Constructor of the color client
     */
    public ColorClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("litfitsserver.entities.color");
    }
    /**
     * Edits a given color
     * @param requestEntity
     * @throws ClientErrorException 
     */
    public void edit(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }
    /**
     * Finds all the colors
     * @param <T>
     * @param responseType
     * @param name
     * @return
     * @throws ClientErrorException 
     */
    public <T> T find(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
    /**
     * Counts the amount of colors
     * @return
     * @throws ClientErrorException 
     */
    public String count() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }
    /**
     * Creates a new color
     * @param requestEntity
     * @throws ClientErrorException 
     */
    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }
    /**
     * Finds all the colors
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException 
     */
    public <T> T findAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
    /**
     * Deletes a color given its name
     * @param name
     * @throws ClientErrorException 
     */
    public void remove(String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{name})).request().delete();
    }
    /**
     * Closes the client
     */
    public void close() {
        client.close();
    }
    
}
