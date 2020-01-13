package lit_fits_client.RESTClients;

/**
 * Factory for the RESTful clients
 *
 * @author Carlos Mendez
 */
public class ClientFactory {
    /**
     * Returns a new ColorClient object
     *
     * @param uri address of the server to be used by the client
     * @return ColorClient
     */
    public static ColorClient getColorClient(String uri) {
        return new ColorClient(uri);
    }

    /**
     * Returns a new MaterialClient object
     *
     * @param uri address of the server to be used by the client
     * @return MaterialClient
     */
    public static MaterialClient getMaterialClient(String uri) {
        return new MaterialClient(uri);
    }

    /**
     * Returns a new CompanyClient object
     *
     * @param uri address of the server to be used by the client
     * @return CompanyClient
     */
    public static CompanyClient getCompanyClient(String uri) {
        return new CompanyClient(uri);
    }

    /**
     * Returns a new GarmentClient object
     *
     * @param uri address of the server to be used by the client
     * @return GarmentClient
     */
    public static GarmentClient getGarmentClient(String uri) {
        return new GarmentClient(uri);
    }

    /**
     * Returns a new PublicKeyClient object
     *
     * @param uri address of the server to be used by the client
     * @return PublicKeyClient
     */
    public static PublicKeyClient getPublicKeyClient(String uri) {
        return new PublicKeyClient(uri);
    }

    /**
     * Returns a new UserClient object
     *
     * @param uri address of the server to be used by the client
     * @return UserClient
     */
    public static UserClient getUserClient(String uri) {
        return new UserClient(uri);
    }
    /**
     * Returns a new ExpertClient object
     *
     * @return ExpertClient
     */
    public static ExpertClient getExpertClient(String uri) {
        return new ExpertClient(uri);
    }
}
