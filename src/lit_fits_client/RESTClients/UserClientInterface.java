package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 * The auto-generated interface for the UserClient.
 * 
 * @author Asier
 */
public interface UserClientInterface {

    void close ();

    <T> T countRESTUser (Class<T> responseType) throws ClientErrorException;

    void createUser (Object requestEntity) throws ClientErrorException;

    void editUser (Object requestEntity, String username) throws ClientErrorException;

    <T> T findAllUser (GenericType<T> responseType) throws ClientErrorException;

    <T> T findUser (Class<T> responseType, String username) throws ClientErrorException;

    <T> T findUserByEmail (Class<T> responseType, String email) throws ClientErrorException;

    <T> T login (Object requestEntity, Class<T> responseType) throws ClientErrorException;

    void reestablishPassword (String username) throws ClientErrorException;

    void removeUser (String username) throws ClientErrorException;
    
}
