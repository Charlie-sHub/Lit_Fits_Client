package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

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
public class GarmentClient {
    private WebTarget webTarget;
    private Client client;
    // Gotta change the URL, probably read it once at the start of the program and then pass it the REST client
    private static final String BASE_URI = "http://localhost:8080/Lit_Fits_Server/webresources";

    public GarmentClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("litfitsserver.entities.garment");
    }

    public <T> T findGarmentGarmentByBarcode(Class<T> responseType, String barcode) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("barcode/{0}", new Object[]{barcode}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findGarmentGarmentsByRequest(Class<T> responseType, String requested) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("request/{0}", new Object[]{requested}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void createGarment(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void editGarment(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T findGarment(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findGarmentAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findGarmentGarmentsPromoted(Class<T> responseType, String promoted) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("promotion/{0}", new Object[]{promoted}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findGarmentGarmentsByCompany(Class<T> responseType, String nif) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("company/{0}", new Object[]{nif}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getImage(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("picture/{0}", new Object[]{id}));
        return resource.get(responseType);
    }

    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
