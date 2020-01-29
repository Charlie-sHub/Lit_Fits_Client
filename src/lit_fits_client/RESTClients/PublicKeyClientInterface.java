package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;

/**
 * Interface for the public key client
 *
 * @author Carlos Mendez
 */
public interface PublicKeyClientInterface {
    /**
     * Closes the client
     */
    void close();

    /**
     * Gets the public key from the server
     *
     * @param <T>
     * @param responseType
     * @return responseType should be a InputStream
     * @throws ClientErrorException
     */
    <T> T getPublicKey(Class<T> responseType) throws ClientErrorException;
}
