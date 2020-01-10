package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;

/**
 * The interface for the user in the client.
 * 
 * @author Asier
 */
public interface UserClientInterface {
 
    /**
     * Gets the user with the received username.
     * 
     * @param <T>
     * @param responseType
     * @param id The username for the user that will be selected.
     * @return The user with all the data.
     * @throws ClientErrorException 
     */
    public <T> T findUser (Class<T> responseType, String id) throws ClientErrorException;
    
    /**
     * Removes a user from the database.
     * 
     * @param id The username for the user that will be removed.
     * @throws ClientErrorException 
     */
    public void removeUser (String id) throws ClientErrorException;
    
    /**
     * Returns the user that contains the received email.
     * 
     * @param <T>
     * @param responseType
     * @param email The email that will be used to filter.
     * @return The user with all the data.
     * @throws ClientErrorException 
     */
    public <T> T findUserByEmail (Class<T> responseType, String email) throws ClientErrorException;
    
    /**
     * Returns all the users on the database.
     * 
     * @param <T>
     * @param responseType
     * @return All the users with their data.
     * @throws ClientErrorException 
     */
    public <T> T findAllUser (Class<T> responseType) throws ClientErrorException;
    
    /**
     * Updates the data for the received user.
     * 
     * @param requestEntity
     * @param id The user that will be modified, with the new data.
     * @throws ClientErrorException 
     */
    public void editUser (Object requestEntity, String id) throws ClientErrorException;
    
    /**
     * Inserts a new user on the database.
     * 
     * @param requestEntity
     * @throws ClientErrorException 
     */
    public void createUser (Object requestEntity) throws ClientErrorException;
    
    /**
     * Counts the number of users in the database.
     * 
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException 
     */
    public <T> T countRESTUser (Class<T> responseType) throws ClientErrorException;
    
    /**
     * This method closes the client.
     */
    public void close ();
}
