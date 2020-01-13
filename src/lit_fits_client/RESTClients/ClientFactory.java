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
     * @return ColorClient
     */
    public static ColorClient getColorClient() {
        return new ColorClient();
    }

    /**
     * Returns a new MaterialClient object
     *
     * @return MaterialClient
     */
    public static MaterialClient getMaterialClient() {
        return new MaterialClient();
    }

    /**
     * Returns a new CompanyClient object
     *
     * @return CompanyClient
     */
    public static CompanyClient getCompanyClient() {
        return new CompanyClient();
    }

    /**
     * Returns a new GarmentClient object
     *
     * @return GarmentClient
     */
    public static GarmentClient getGarmentClient() {
        return new GarmentClient();
    }

    /**
     * Returns a new PublicKeyClient object
     *
     * @return PublicKeyClient
     */
    public static PublicKeyClient getPublicKeyClient() {
        return new PublicKeyClient();
    }

    /**
     * Returns a new UserClient object
     *
     * @return UserClient
     */

    public static UserClient getUserClient() {
        return new UserClient();
    }
    ////////////////////////////////////////////
    /**
     * Returns a new ExpertClient object
     *
     * @return ExpertClient
     */
    /*
    public static ExpertClient getExpertClient() {
        return new ExpertClient();
    }
     */
}
