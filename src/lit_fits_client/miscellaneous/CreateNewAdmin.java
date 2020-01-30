package lit_fits_client.miscellaneous;

import java.io.InputStream;
import java.util.Date;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.PublicKeyClientInterface;
import lit_fits_client.RESTClients.UserClientInterface;
import lit_fits_client.entities.User;
import lit_fits_client.entities.UserType;
import org.apache.commons.io.IOUtils;

/**
 * This class is used to create a new admin 
 * with an encrypted password into the database.
 * It will not be used into the application. It is only used to set admins.
 * 
 * @author Asier Vila
 */
public class CreateNewAdmin {
    
    private User admin;
    
    private String uri;
    
    
    public CreateNewAdmin(String uri) {
        this.uri = uri;
    }
    
    public void createNewAdmin() {
        
        UserClientInterface userClient = ClientFactory.getUserClient(uri);
        PublicKeyClientInterface publicKeyClient = ClientFactory.getPublicKeyClient(uri);
        try {
            byte[] publicKeyBytes = IOUtils.toByteArray(publicKeyClient.getPublicKey(InputStream.class));
            
            admin = this.setUserData(publicKeyBytes);
            
            userClient.create(admin);

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            userClient.close();
            publicKeyClient.close();
        }
    }
    
    private User setUserData(byte[] publicKey) throws Exception {
        
        User user = new User();
        
        user.setUsername("adminAsier");
        user.setFullName("Asier as admin");
        user.setPassword(Encryptor.encryptText("abcd*1234", publicKey));
        user.setPhoneNumber("123456789");
        user.setEmail("asier@asier.com");
        user.setLastAccess(new Date());
        user.setLastPasswordChange(new Date());
        user.setType(UserType.ADMIN);

        return user;
    }
    
}
