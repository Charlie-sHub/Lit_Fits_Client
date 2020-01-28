package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 * Interface for the CompanyClient
 *
 * @author Carlos Mendez
 */
public interface CompanyClientInterface {
    /**
     * Closes the instance of the client
     */
    void close();

    /**
     * Counts the amount of companies in the database
     *
     * @return String
     * @throws ClientErrorException
     */
    String countREST() throws ClientErrorException;

    /**
     * Sends a request to save a new company in the database
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void create(Object requestEntity) throws ClientErrorException;

    /**
     * Updates the data of a given company
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void edit(Object requestEntity) throws ClientErrorException;

    /**
     * Finds and returns a company by a given id
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return Company
     * @throws ClientErrorException
     */
    <T> T find(Class<T> responseType, String id) throws ClientErrorException;

    /**
     * Returns all the companies in the database
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    <T> T findAll(GenericType<T> responseType) throws ClientErrorException;

    /**
     * Finds and returns a company by a given nif
     *
     * @param <T>
     * @param responseType
     * @param nif
     * @return
     * @throws ClientErrorException
     */
    <T> T findCompanyByNif(Class<T> responseType, String nif) throws ClientErrorException;

    /**
     * Takes a nif and password (encapsulated in a Company object) and returns the full company
     *
     * @param <T>
     * @param requestEntity
     * @param responseType
     * @return
     * @throws ClientErrorException
     */
    <T> T login(Object requestEntity, Class<T> responseType) throws ClientErrorException;

    /**
     * Reestablishes the password of associated with the given nif, sending an email with the new password
     *
     * @param nif
     * @throws ClientErrorException
     */
    String reestablishPassword(String nif) throws ClientErrorException;

    /**
     * Deletes the company with the given id
     *
     * @param id
     * @throws ClientErrorException
     */
    void remove(String id) throws ClientErrorException;
}
