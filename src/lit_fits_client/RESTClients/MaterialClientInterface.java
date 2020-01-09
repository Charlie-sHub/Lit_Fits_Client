package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;

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
     * @return
     * @throws ClientErrorHException
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
     * @return
     * @throws ClientErrorException
     */
    <T> T find(Class<T> responseType, String name) throws ClientErrorException;

    /**
     * Finds all the materials
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    <T> T findAll(Class<T> responseType) throws ClientErrorException;

    /**
     * Deletes a material given its name
     *
     * @param name
     * @throws ClientErrorException
     */
    void remove(String name) throws ClientErrorException;
}