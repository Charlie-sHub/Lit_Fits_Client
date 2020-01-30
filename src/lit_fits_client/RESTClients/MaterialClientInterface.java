package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 * Interface for the material client
 *
 * @author Carlos Mendez
 */
public interface MaterialClientInterface {
    /**
     * Closes the client
     */
    void close();

    /**
     * Counts the amount of materials
     *
     * @return String amount of garments
     * @throws ClientErrorException
     */
    String countREST() throws ClientErrorException;

    /**
     * Creates a new material
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void create(Object requestEntity) throws ClientErrorException;

    /**
     * Edits a given material
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void edit(Object requestEntity) throws ClientErrorException;

    /**
     * Finds all the materials
     *
     * @param <T>
     * @param responseType
     * @param name
     * @return responseType should be a Material
     * @throws ClientErrorException
     */
    <T> T find(Class<T> responseType, String name) throws ClientErrorException;

    /**
     * Finds all the materials
     *
     * @param <T>
     * @param responseType
     * @return responseType should be a List of Materials
     * @throws ClientErrorException
     */
    <T> T findAll(GenericType<T> responseType) throws ClientErrorException;

    /**
     * Deletes a material given its name
     *
     * @param name
     * @throws ClientErrorException
     */
    void remove(String name) throws ClientErrorException;
}
