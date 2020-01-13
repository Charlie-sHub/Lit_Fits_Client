package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import lit_fits_client.RESTClients.GarmentClientInterface;

/**
 * Jersey REST client generated for REST resource:GarmentFacadeREST [litfitsserver.entities.garment]<br>
 * USAGE:
 * <pre>
 *        GarmentClient client = new GarmentClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Carlos Mendez
 */
public class GarmentClient implements GarmentClientInterface {
    private WebTarget webTarget;
    private Client client;

    /**
     * Constructor of the Garment client
     * @param baseUri
     */
    public GarmentClient(String baseUri) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(baseUri).path("litfitsserver.entities.garment");
    }

    /**
     * Finds the garment with the given barcode
     *
     * @param <T>
     * @param responseType
     * @param barcode
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findGarmentGarmentByBarcode(Class<T> responseType, String barcode) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("barcode/{0}", new Object[]{barcode}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Finds all the garments with or without requested promotions
     *
     * @param <T>
     * @param responseType
     * @param requested
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findGarmentGarmentsByRequest(Class<T> responseType, String requested) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("request/{0}", new Object[]{requested}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Creates a new garment
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    @Override
    public void createGarment(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Updates a garment with the data send
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    @Override
    public void editGarment(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Counts the total amount of garments
     *
     * @return
     * @throws ClientErrorException
     */
    @Override
    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Finds a garment by id
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findGarment(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Finds all garments
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findGarmentAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Finds all promoted garments
     *
     * @param <T>
     * @param responseType
     * @param promoted
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findGarmentGarmentsPromoted(Class<T> responseType, String promoted) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("promotion/{0}", new Object[]{promoted}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Finds all the garments of a given company
     *
     * @param <T>
     * @param responseType
     * @param nif
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T findGarmentGarmentsByCompany(Class<T> responseType, String nif) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("company/{0}", new Object[]{nif}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Gets the picture of the garment
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return Image
     * @throws ClientErrorException
     */
    @Override
    public <T> T getImage(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("picture/{0}", new Object[]{id}));
        return resource.request(MediaType.APPLICATION_OCTET_STREAM).get(responseType);
    }

    /**
     * Deletes the garment associated with the given id
     *
     * @param id
     * @throws ClientErrorException
     */
    @Override
    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    /**
     * Closes the client
     */
    @Override
    public void close() {
        client.close();
    }
}
