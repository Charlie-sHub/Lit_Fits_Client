package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:MaterialFacadeREST [litfitsserver.entities.material]<br>
 * USAGE:
 * <pre>
 *        MaterialClient client = new MaterialClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Carlos Mendez
 */
public class MaterialClient implements MaterialClientInterface {
    private WebTarget webTarget;
    private Client client;
    // Gotta change the URL, probably read it once at the start of the program and then pass it the REST client
    private static final String BASE_URI = "http://localhost:8080/Lit_Fits_Server/webresources";

    /**
     * Constructor of the material client
     */
    public MaterialClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("litfitsserver.entities.material");
    }

    /**
     * Counts the amount of materials
     *
     * @return
     * @throws ClientErrorHException
     */
    @Override
    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Edits a given material
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    @Override
    public void edit(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Finds all the materials
     *
     * @param <T>
     * @param responseType
     * @param name
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T find(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Creates a new material
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    @Override
    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Finds all the materials
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Deletes a material given its name
     *
     * @param name
     * @throws ClientErrorException
     */
    @Override
    public void remove(String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{name})).request().delete();
    }

    /**
     * Closes the client
     */
    @Override
    public void close() {
        client.close();
    }
}
