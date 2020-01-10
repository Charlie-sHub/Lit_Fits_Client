/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.RESTClients;

import javax.ws.rs.ClientErrorException;

/**
 *
 * @author 2dam
 */
public interface ExpertClientInterface {
    /**
     * Closes the instance of the client
     */
    void close();
    
        /**
     * Sends a request to save a new fashion expert in the database
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void create(Object requestEntity) throws ClientErrorException;
    
    /**
     * Updates the data of a Fashion Expert
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    void edit(Object requestEntity) throws ClientErrorException;
    
    
    /**
     * Finds and returns an expert by a given id
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return Company
     * @throws ClientErrorException
     */
    <T> T find(Class<T> responseType, String id) throws ClientErrorException;
}
