/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ander Rodriguez
 */

@XmlRootElement
public class FashionExpert implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty fullName;
    private SimpleStringProperty email;
    private SimpleStringProperty publication;
    private Date lastPasswordChange;
    private Date lastAccess;
    private List<Material> recommendedMaterials;
    private List<Color> recommendedColors;

    public FashionExpert() {
    }

    public FashionExpert(SimpleStringProperty username, SimpleStringProperty password, SimpleStringProperty phoneNumber, SimpleStringProperty fullName, SimpleStringProperty email, SimpleStringProperty publication, Date lastPasswordChange, Date lastAccess, List<Material> recommendedMaterials, List<Color> recommendedColors) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
        this.publication = publication;
        this.lastPasswordChange = lastPasswordChange;
        this.lastAccess = lastAccess;
        this.recommendedMaterials = recommendedMaterials;
        this.recommendedColors = recommendedColors;
    }

    
    
    
    
    /**
     * Function toString returns all the fields of the expert
     * @return a String with all the fields
     */
    @Override
    public String toString() {
        return "Expert{ " + "Username: " + username + ", Password: " + password + ", Phone Number: " + phoneNumber + 
                ", Full Name: " + fullName + ", Email: " + email + ", Publication: " + publication + ", lastAccess=" + lastAccess + ", lastPasswordChange=" + lastPasswordChange
                + ", Recommended Materials: " + recommendedMaterials + "and Recommended Colors: " + recommendedColors + " }" ;
   }
    /**
     * Function hash code 
     * 
     * @return an integer
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.fullName);
        hash = 29 * hash + Objects.hashCode(this.phoneNumber);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.publication);
        hash = 29 * hash + Objects.hashCode(this.recommendedColors);
        hash = 29 * hash + Objects.hashCode(this.recommendedMaterials);
        return hash;

    }
    /**
     * Functon equals receives an object and compare it
     * @param obj an object is receive for comparing
     * @return a boolean if it is equals true, if not false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FashionExpert other = (FashionExpert) obj;
        if(!Objects.equals(this.username, other.username))
            return false;
        
        if (!Objects.equals(this.password, other.password)) 
            return false;

        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) 
            return false;
        
        if (!Objects.equals(this.lastAccess, other.lastAccess)) 
            return false;
        
        if (!Objects.equals(this.lastPasswordChange, other.lastPasswordChange))
            return false;
        return true;
    }

    public String getUsername() {
        return this.username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return this.password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getPhoneNumber() {
        return this.phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber); 
    }

    public String getFullName() {
        return this.fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPublication() {
        return this.publication.get();
    }

    public void setPublication(String publication) {
        this.publication.set(publication);
    }


    
    
    /**
     * @return the lastPasswordChange
     */
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * @param lastPasswordChange the lastPasswordChange to set
     */
    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    /**
     * @return the lastAccess
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * @return the recommendedMaterials
     */
    @XmlTransient
    public List<Material> getRecommendedMaterials() {
        return recommendedMaterials;
    }

    /**
     * @param recommendedMaterials the recommendedMaterials to set
     */
    public void setRecommendedMaterials(List<Material> recommendedMaterials) {
        this.recommendedMaterials = recommendedMaterials;
    }

    /**
     * @return the recommendedColors
     */
    @XmlTransient
    public List<Color> getRecommendedColors() {
        return recommendedColors;
    }

    /**
     * @param recommendedColors the recommendedColors to set
     */
    public void setRecommendedColors(List<Color> recommendedColors) {
        this.recommendedColors = recommendedColors;
    }
    
}