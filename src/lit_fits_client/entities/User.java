package lit_fits_client.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableSet;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The class that will be used to manage all the users data.
 * 
 * @author Asier 
 */

@XmlRootElement
public class User implements Serializable {
    
    protected SimpleStringProperty username;
    protected SimpleStringProperty fullName;
    protected SimpleStringProperty password;
    protected SimpleStringProperty phoneNumber;
    protected SimpleStringProperty email;
    protected Date lastAccess;
    protected Date lastPasswordChange;
    protected UserType type;
    private SimpleSetProperty<Color> likedColors;
    private SimpleSetProperty<Material> likedMaterials;
    private Set<Garment> garments;
    
    /**
     * Empty constructor.
     */
    public User () {
        username = new SimpleStringProperty();
        fullName = new SimpleStringProperty();
        password = new SimpleStringProperty();
        phoneNumber = new SimpleStringProperty();
        email = new SimpleStringProperty();
        likedColors = new SimpleSetProperty<Color>();
        likedMaterials = new SimpleSetProperty<Material>();
    }
    
    /**
     * Basic user constructor, used for classes that extend user.
     * 
     * @param username The username that will be set.
     * @param fullName The full name that will be set.
     * @param password The password that that will be set.
     * @param phoneNumber The phone number that will be set.
     * @param email The email that will be set.
     * @param lastAccess The last access date that will be set.
     * @param lastPasswordChange The last password change date that will be set.
     * @param type The type of the user that will be set.
     */
    public User (String username, String fullName, String password, String phoneNumber,
            String email, Date lastAccess, Date lastPasswordChange, UserType type) {
        this.username = new SimpleStringProperty(username);
        this.fullName = new SimpleStringProperty(fullName);
        this.password = new SimpleStringProperty(password);
        this.phoneNumber= new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.lastAccess = lastAccess;
        this.lastPasswordChange = lastPasswordChange;
        this.type = type;
    }
    
    /**
     * Full user constructor.
     * 
     * @param username The username that will be set.
     * @param fullName The full name that will be set.
     * @param password The password that that will be set.
     * @param phoneNumber The phone number that will be set.
     * @param email The email that will be set.
     * @param lastAccess The last access date that will be set.
     * @param lastPasswordChange The last password change date that will be set.
     * @param type The type of the user that will be set.
     * @param likedColors The user liked colors that will be set.
     * @param likedMaterials The user liked materials that will be set.
     * @param garments All the garments of the user that will be set.
     */
    public User (String username, String fullName, String password, String phoneNumber,
            String email, Date lastAccess, Date lastPasswordChange, UserType type,
            Set<Color> likedColors, Set<Material> likedMaterials, Set<Garment> garments) {
        this.username = new SimpleStringProperty(username);
        this.fullName = new SimpleStringProperty(fullName);
        this.password = new SimpleStringProperty(password);
        this.phoneNumber= new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.lastAccess = lastAccess;
        this.lastPasswordChange = lastPasswordChange;
        this.type = type;
        this.likedColors = new SimpleSetProperty<Color>((ObservableSet<Color>) likedColors);
        this.likedMaterials = new SimpleSetProperty<Material>((ObservableSet<Material>) likedMaterials);
        this.garments = garments;
    }
    
    /**
     * Gets the users username.
     * 
     * @return The username of the user.
     */
    public String getUsername () {
        return username.get();
    }

    /**
     * Sets the users username.
     * 
     * @param username The username to set.
     */
    public void setUsername (String username) {
        this.username.set(username);
    }
    
    /**
     * Gets the users fullName.
     * 
     * @return The users fullName.
     */
    public String getFullName () {
        return fullName.get();
    }

    /**
     * Changes the users fullName to the received one
     * 
     * @param fullName The fullName to set.
     */
    public void setFullName (String fullName) {
        this.fullName.set(fullName);
    }

    /**
     * Gets the password of the current user.
     * 
     * @return The password of the user.
     */
    public String getPassword () {
        return password.get();
    }

    /**
     * Sets the users password to the received one.
     * 
     * @param password The password to set.
     */
    public void setPassword (String password) {
        this.password.set(password);
    }
    
    /**
     * Gets the phoneNumber of the user.
     * 
     * @return The users phoneNumber.
     */
    public String getPhoneNumber () {
        return phoneNumber.get();
    }

    /**
     * Sets the users phoneNumber to the received one.
     * 
     * @param phoneNumber The phoneNumber to set.
     */
    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    /**
     * Gets the email of the user.
     * 
     * @return The email of the user.
     */
    public String getEmail () {
        return email.get();
    }

    /**
     * Changes the users email for the received one.
     * 
     * @param email The email that will be saved.
     */
    public void setEmail (String email) {
        this.email.set(email);
    }

    /**
     * Gets the lastAccess date of the user.
     * 
     * @return The date for the lastAccess of the user.
     */
    public Date getLastAccess () {
        return lastAccess;
    }

    /**
     * Changes the lastAccess date for the current user with the received one.
     * 
     * @param lastAccess The lastAccess date that will be set
     */
    public void setLastAccess (Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Gets the users lastPasswordChange date.
     * 
     * @return The lastPasswordChange date of the user.
     */
    public Date getLastPasswordChange () {
        return lastPasswordChange;
    }

    /**
     * Changes the lastPasswordChange date for the user with the received one.
     * 
     * @param lastPasswordChange The new date that will be set.
     */
    public void setLastPasswordChange (Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }
    
    /**
     * Gets the type of the user.
     * 
     * @return The UserType of the user.
     */
    public UserType getType () {
        return type;
    }

    /**
     * Receives and sets the type of the user.
     * It can be <i>admin</i> or <i>user</i>
     * 
     * @param type The type of the user
     */
    public void setType (UserType type) {
        this.type = type;
    }

    /**
     * Gets the Set of colors that the user likes.
     * 
     * @return The Set with all the liked colors.
     */
    @XmlTransient
    public Set<Color> getLikedColors () {
        return likedColors;
    }

    /**
     * Replaces all the colors that the user likes with the received ones.
     * 
     * @param likedColors The colors Set that will be saved for the user.
     */
    public void setLikedColors (Set<Color> likedColors) {
        this.likedColors.addAll(likedColors);
    }

    /**
     * This method is created <u>to avoid duplicates</u> on likedColors.
     * Instead of <i>user.getLikedColors().add(color)</i>, use this 
     * method. This will avoid errors on the server.
     * 
     * @param color 
     */
    public void addLikedColor (Color color) {
        
        if (!this.likedColors.contains(color)) {
            this.likedColors.add(color);
        }
    }
    
    /**
     * Checks if the user likes the specified color. 
     * If it does, the color is removed from its liked colors.
     * 
     * @param color The color that should be removed.
     */
    public void removeLikedColor (Color color) {
        
        if (this.likedColors.contains(color)) {
            this.likedColors.remove(color);
        }
    }
    
    /**
     * Gets the Set of materials that the user likes.
     * 
     * @return The Set with all the liked materials.
     */
    @XmlTransient
    public Set<Material> getLikedMaterials () {
        return likedMaterials;
    }

    /**
     * Replaces all the materials that the user likes with the received ones.
     * 
     * @param likedMaterials The materials Set that will be saved for the user.
     */
    public void setLikedMaterials (Set<Material> likedMaterials) {
        this.likedMaterials.addAll(likedMaterials);
    }
    
    /**
     * This method is created <u>to avoid duplicates</u> on likedMaterials.
     * Instead of <i>user.getLikedMaterials().add(material)</i>, use this 
     * method. This will avoid errors on the server.
     * 
     * @param material The material that sould be added to the Set.
     */
    public void addLikedMaterial (Material material) {
        
        if (!this.likedMaterials.contains(material)) {
            this.likedMaterials.add(material);
        }
    }
    
    /**
     * Checks if the user likes the specified material. 
     * If it does, the material is removed from its liked materials.
     * 
     * @param material The material that should be removed.
     */
    public void removeLikedMaterial (Material material) {
        
        if (this.likedMaterials.contains(material)) {
            this.likedMaterials.remove(material);
        }
    }

    /**
     * Gets the garments that the user has saved.
     * 
     * @return A Set with all the garments.
     */
    @XmlTransient
    public Set<Garment> getGarments () {
        return garments;
    }

    /**
     * Replaces the users garments with the received set.
     * 
     * @param garments The garments that will be saved for this user.
     */
    public void setGarments (Set<Garment> garments) {
        this.garments = garments;
    }
    
    /**
     * Creates the hash for the User.
     * 
     * @return An int with all the attributes as hash
     */
    @Override
    public int hashCode () {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + Objects.hashCode(this.likedColors);
        hash = 29 * hash + Objects.hashCode(this.likedMaterials);
        hash = 29 * hash + Objects.hashCode(this.garments);
        return hash;
    }

    /**
     * Compares the received object to the current User.
     * 
     * @param obj The object to compare.
     * @return True if they are equal. False if not.
     */
    @Override
    public boolean equals (Object obj) {
        
        if (obj == null) {
            return false;
        }
        
        User casted = (User) obj;
        
        if (!casted.getUsername().equals(this.username.get())) {
            return false;
        }
        
        if (!casted.getFullName().equals(this.fullName.get())) {
            return false;
        }
        
        if (!casted.getEmail().equals(this.email.get())) {
            return false;
        }
        
        if (!casted.getPhoneNumber().equals(this.phoneNumber.get())) {
            return false;
        }
        
        if (!casted.getType().equals(this.type)) {
            return false;
        }
        
        return true;
    }

    /**
     * Converts the data of the current User into a String.
     * 
     * @return The user as a String. Shows the <u>username</u> and <u>full name</u>
     */
    @Override
    public String toString () {
        
        return this.username + ", " + this.fullName;
    }
}