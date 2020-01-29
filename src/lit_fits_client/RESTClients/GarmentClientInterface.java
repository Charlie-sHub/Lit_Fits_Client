package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 * Interface for the Garment client
 *
 * @author Carlos Mendez
 */
public interface GarmentClientInterface {
    /**
     * Closes the client
     */
    void close();

    /**
     * Counts the total amount of garments
     *
     * @return String the amount of Garments
     * @throws ClientErrorException
     */
    String countREST() throws ClientErrorException;

    /**
     * Creates a new garment
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void createGarment(Object requestEntity) throws ClientErrorException;

    /**
     * Updates a garment with the data send
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void editGarment(Object requestEntity) throws ClientErrorException;

    /**
     * Finds a garment by id
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return responseType should be a Garment
     * @throws ClientErrorException
     */
    <T> T findGarment(Class<T> responseType, String id) throws ClientErrorException;

    /**
     * Finds all garments
     *
     * @param <T>
     * @param responseType
     * @return responseType should be a List of Garments
     * @throws ClientErrorException
     */
    <T> T findGarmentAll(GenericType<T> responseType) throws ClientErrorException;

    /**
     * Finds the garment with the given barcode
     *
     * @param <T>
     * @param responseType
     * @param barcode
     * @return responseType should be a Garment
     * @throws ClientErrorException
     */
    <T> T findGarmentByBarcode(Class<T> responseType, String barcode) throws ClientErrorException;

    /**
     * Finds all the garments of a given company
     *
     * @param <T>
     * @param responseType
     * @param nif
     * @return responseType should be a List of Garments
     * @throws ClientErrorException
     */
    <T> T findGarmentsByCompany(GenericType<T> responseType, String nif) throws ClientErrorException;

    /**
     * Finds all the garments with or without requested promotions
     *
     * @param <T>
     * @param responseType
     * @param requested
     * @return responseType should be a List of Garments
     * @throws ClientErrorException
     */
    <T> T findGarmentsByRequest(GenericType<T> responseType, String requested) throws ClientErrorException;

    /**
     * Finds all promoted garments
     *
     * @param <T>
     * @param responseType
     * @param promoted
     * @return
     * @throws ClientErrorException
     */
    <T> T findGarmentsPromoted(GenericType<T> responseType, String promoted) throws ClientErrorException;

    /**
     * Gets the picture of the garment
     *
     * Deprecated since images are now embedded in the Garment
     *
     * @param <T>
     * @param id
     * @return responseType should be a InputStream
     * @throws ClientErrorException
     */
    @Deprecated
    <T> T getImage(Class<T> responseType, String id) throws ClientErrorException;

    /**
     * Deletes the garment associated with the given id
     *
     * @param id
     * @throws ClientErrorException
     */
    void remove(String id) throws ClientErrorException;
}
