package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;

/**
 * Interface for the public key client
 *
 * @author Carlos Mendez
 */
public interface PublicKeyClientInterface {
    void close();

    <T> T getPublicKey(Class<T> responseType) throws ClientErrorException;
}
