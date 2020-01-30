package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 * Interface for the User client
 *
 * @author Asier Vila Dominguez
 */
public interface UserClientInterface {
    /**
     * Closes the instance of the client
     */
    void close();

    /**
     * Returns the amount of users stored into the database.
     *
     * @return String The number of users.
     * @throws ClientErrorException
     */
    String countREST() throws ClientErrorException;

    /**
     * Inserts a new User into the database.
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void create(Object requestEntity) throws ClientErrorException;

    /**
     * Receives the user with the new data. It will be updated into the database.
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void edit(Object requestEntity) throws ClientErrorException;

    /**
     * Finds a user using the username and returns it with its full data.
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return Company
     * @throws ClientErrorException
     */
    <T> T find(Class<T> responseType, String id) throws ClientErrorException;

    /**
     * Gets the data of all the users stored into the database.
     *
     * @param <T>
     * @param responseType
     * @return responseType A List with all the users.
     * @throws ClientErrorException
     */
    <T> T findAll(GenericType<T> responseType) throws ClientErrorException;

    /**
     * Gets all the data of a user if its password and username are both correct.
     *
     * @param <T>
     * @param requestEntity
     * @param responseType
     * @return responseType A User with all its data.
     * @throws ClientErrorException
     */
    <T> T login(Object requestEntity, Class<T> responseType) throws ClientErrorException;

    /**
     * Reestablishes the password for the user with the received username.
     *
     * @param username
     * @throws ClientErrorException
     */
    String reestablishPassword(String username) throws ClientErrorException;

    /**
     * Deletes the user with the received username.
     *
     * @param username
     * @throws ClientErrorException
     */
    void remove(String username) throws ClientErrorException;
}