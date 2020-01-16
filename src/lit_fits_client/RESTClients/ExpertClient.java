/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author 2dam
 */
public class ExpertClient implements ExpertClientInterface {
    private final Client client;
    private final WebTarget webTarget;

    /**
     * Constructor the expert client
     * @param URL
     */
    public ExpertClient(String URL) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(URL).path("litfitserver.entities.fashionexpert");
        
    }
    
     /**
     * Closes the instance of the client
     */ 
    @Override
    public void close() {
        client.close();
    }
    /**
     * Sends a request to save a new FashionExpert in the database
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    @Override
    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }
    /**
     * Updates the data of a given expert
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    @Override
    public void edit(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_XML_TYPE));
    }
    /**
     * Finds and returns an expert by a given id
     *
     * @param <T>
     * @param responseType
     * @param username
     * @return FashionExpert
     * @throws ClientErrorException
     */
    @Override
    public <T> T findExpertByUsername(Class<T> responseType, String username) throws ClientErrorException {
       WebTarget resource = webTarget;
       resource = resource.path(java.text.MessageFormat.format("expert/{0}", new Object[]{username}));
       return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
    /**
     * Reestablishes the password of associated with the given username, sending an email with the new password
     *
     * @param username
     * @throws ClientErrorException
     */
    @Override
    public void reestablishPassword(String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("passwordReestablishment/{0}", new Object[]{username}));
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
     * Takes an username and password ,encapsulated in a FashionExpert object,
     * and returns the full expert
     *
     * @param <T>
     * @param requestEntity
     * @param responseType
     * @return FashionExpert
     * @throws ClientErrorException
     */
    @Override
    public <T> T login(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("login").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    } 
    
    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T getRecommendedColors(Class<T> responseType) throws ClientErrorException{
        WebTarget resource = webTarget;        
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
    
    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    @Override
    public <T> T getRecommendedMaterials(Class<T> responseType) throws ClientErrorException{
        WebTarget resource = webTarget;        
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
}
