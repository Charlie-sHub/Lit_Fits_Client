package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:CompanyFacadeREST [litfitsserver.entities.company]<br>
 * USAGE:
 * <pre>
 *        CompanyClient client = new CompanyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Carlos Mendez
 */
public class CompanyClient implements CompanyClientInterface {
    private WebTarget webTarget;
    private Client client;

    /**
     * Constructor of the Company client
     *
     * @param baseUri
     */
    public CompanyClient(String baseUri) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(baseUri).path("litfitsserver.entities.company");
    }

    /**
     * Counts the amount of companies in the database
     *
     * @return String
     * @throws ClientErrorException
     */
    @Override
    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Reestablishes the password of associated with the given nif, sending an email with the new password
     *
     * @param nif
     * @throws ClientErrorException
     */
    @Override
    public String reestablishPassword(String nif) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("passwordReestablishment/{0}", new Object[]{nif}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Updates the data of a given company
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    @Override
    public void edit(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Finds and returns a company by a given id
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return Company
     * @throws ClientErrorException
     */
    @Override
    public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Sends a request to save a new company in the database
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    @Override
    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Finds and returns a company by a given nif
     *
     * @param <T>
     * @param responseType
     * @param nif
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findCompanyByNif(Class<T> responseType, String nif) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("company/{0}", new Object[]{nif}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Takes a nif and password (encapsulated in a Company object) and returns the full company
     *
     * @param <T>
     * @param requestEntity
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T login(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("login").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    /**
     * Returns all the companies in the database
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findAll(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Deletes the company with the given id
     *
     * @param id
     * @throws ClientErrorException
     */
    @Override
    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    /**
     * Closes the instance of the client
     */
    @Override
    public void close() {
        client.close();
    }
}
