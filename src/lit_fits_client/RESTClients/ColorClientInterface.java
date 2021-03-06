package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 * Interface for the Color client
 *
 * @author Carlos Mendez
 */
public interface ColorClientInterface {
    /**
     * Closes the client
     */
    void close();

    /**
     * Counts the amount of colors
     *
     * @return String the amount of Colors
     * @throws ClientErrorException
     */
    String count() throws ClientErrorException;

    /**
     * Creates a new color
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void create(Object requestEntity) throws ClientErrorException;

    /**
     * Edits a given color
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void edit(Object requestEntity) throws ClientErrorException;

    /**
     * Finds all the colors
     *
     * @param <T>
     * @param responseType
     * @param name
     * @return responseType should be a Color
     * @throws ClientErrorException
     */
    <T> T find(Class<T> responseType, String name) throws ClientErrorException;

    /**
     * Finds all the colors
     *
     * @param <T>
     * @param responseType
     * @return responseType should be a List of Colors
     * @throws ClientErrorException
     */
    <T> T findAll(GenericType<T> responseType) throws ClientErrorException;

    /**
     * Deletes a color given its name
     *
     * @param name
     * @throws ClientErrorException
     */
    void remove(String name) throws ClientErrorException;
}
