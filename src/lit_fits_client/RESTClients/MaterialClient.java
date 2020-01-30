package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Class that implements the MaterialClientInterface
 *
 * @author Carlos Mendez
 */
public class MaterialClient implements MaterialClientInterface {
    private WebTarget webTarget;
    private Client client;

    /**
     * Constructor of the material client
     *
     * @param baseUri
     */
    public MaterialClient(String baseUri) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(baseUri).path("litfitsserver.entities.material");
    }

    @Override
    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    @Override
    public void edit(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

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

    @Override
    public <T> T findAll(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    @Override
    public void remove(String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{name})).request().delete();
    }

    @Override
    public void close() {
        client.close();
    }
}
