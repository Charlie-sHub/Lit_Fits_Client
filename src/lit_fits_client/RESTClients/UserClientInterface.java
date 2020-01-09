package lit_fits_client.RESTClients;

import lit_fits_client.entities.User;

/**
 *
 * @author Asier
 */
public interface UserClientInterface {
    
    /**
     * 
     */
    public void close();
    
    /**
     * 
     * @return 
     */
    public String countREST();
    
    /**
     * 
     * @param requestEntity 
     */
    public void create(Object requestEntity);
    
    /**
     * 
     * @param user 
     */
    public void edit(User user);
    
    /**
     * 
     * @param <T>
     * @param responseType
     * @param id
     * @return 
     */
    public <T> T find(Class<T> responseType, String id);
    
    /**
     * 
     * @param <T>
     * @param responseType
     * @return 
     */
    public <T> T findAll(Class<T> responseType);
    
    /**
     * 
     * @param <T>
     * @param requestEntity
     * @param responseType
     * @return 
     */
    public <T> T login(Object requestEntity, Class<T> responseType);
    
    /**
     * 
     * @param username 
     */
    public void reestablishPassword(String username);
    
    /**
     * 
     * @param username 
     */
    public void remove(String username);
    
}
