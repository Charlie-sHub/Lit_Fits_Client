package lit_fits_client.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Garment entity
 *
 * @author Charlie
 */
@XmlRootElement
public class Garment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Unique id of the Garment
     */
    private long id;
    /**
     * Unique barcode identifier of the garment
     */
    private SimpleStringProperty barcode;
    /**
     * The person that designed the garment
     */
    private SimpleStringProperty designer;
    /**
     * How much is worth
     */
    private SimpleDoubleProperty price;
    /**
     * The kind of situation is suited for
     */
    private SimpleObjectProperty mood;
    /**
     * Where it is worn
     */
    private SimpleObjectProperty bodyPart;
    /**
     * What kind of garment it is
     */
    private SimpleObjectProperty garmentType;
    /**
     * Indicates if it can be bought
     */
    private SimpleBooleanProperty available;
    /**
     * Indicates if the company requested a promotion for this garment
     */
    private SimpleBooleanProperty promotionRequest;
    /**
     * Indicates if the promotion request is accepted
     */
    private SimpleBooleanProperty promoted;
    /**
     * Path in the database to the picture of the garment
     */
    private String imagePath; // Do i even need this in the client?
    /**
     * Company that sells the garment
     */
    private Company company;
    /**
     * What colors are in the garment
     */
    private SimpleObjectProperty colors;
    /**
     * What materials is the garment made out of
     */
    private SimpleObjectProperty materials;
    /**
     * The picture of the garment
     */
    private SimpleObjectProperty picture;

    /**
     * Empty constructor
     */
    public Garment() {
    }

    /**
     * Full constructor, except for the id as it is auto-generated by the server
     *
     * @param barcode
     * @param designer
     * @param price
     * @param mood
     * @param bodyPart
     * @param garmentType
     * @param available
     * @param promotionRequest
     * @param promoted
     * @param imagePath
     * @param company
     * @param colors
     * @param materials
     * @param picture
     */
    public Garment(String barcode, String designer, Double price, Mood mood, BodyPart bodyPart, GarmentType garmentType, boolean available, boolean promotionRequest, boolean promoted, String imagePath, Company company, Set<Color> colors, Set<Material> materials, File picture) {
        this.barcode = new SimpleStringProperty(barcode);
        this.designer = new SimpleStringProperty(designer);
        this.price = new SimpleDoubleProperty(price);
        this.mood = new SimpleObjectProperty<Mood>(mood);
        this.bodyPart = new SimpleObjectProperty<BodyPart>(bodyPart);
        this.garmentType = new SimpleObjectProperty<GarmentType>(garmentType);
        this.available = new SimpleBooleanProperty(available);
        this.promotionRequest = new SimpleBooleanProperty(promotionRequest);
        this.promoted = new SimpleBooleanProperty(promoted);
        this.imagePath = imagePath;
        this.company = company;
        this.colors = new SimpleObjectProperty<Set<Color>>(colors);
        this.materials = new SimpleObjectProperty<Set<Material>>(materials);
        this.picture = new SimpleObjectProperty<File>(picture);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBarcode() {
        return this.barcode.get();
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    public String getDesigner() {
        return this.designer.get();
    }

    public void setDesigner(String designer) {
        this.designer.set(designer);
    }

    public Double getPrice() {
        return this.price.get();
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }

    public Mood getMood() {
        return (Mood) this.mood.get();
    }

    public void setMood(Mood mood) {
        this.mood.set(mood);
    }

    public BodyPart getBodyPart() {
        return (BodyPart) this.bodyPart.get();
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart.set(bodyPart);
    }

    public GarmentType getGarmentType() {
        return (GarmentType) this.garmentType.get();
    }

    public void setGarmentType(GarmentType garmentType) {
        this.garmentType.set(garmentType);
    }

    public boolean isAvailable() {
        return this.available.get();
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    public boolean isPromotionRequest() {
        return this.promotionRequest.get();
    }

    public void setPromotionRequest(boolean promotionRequest) {
        this.promotionRequest.set(promotionRequest);
    }

    public boolean isPromoted() {
        return this.promoted.get();
    }

    public void setPromoted(boolean promoted) {
        this.promoted.set(promoted);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Color> getColors() {
        return (Set<Color>) this.colors.get();
    }

    public void setColors(Set<Color> colors) {
        this.colors.set(colors);
    }

    public Set<Material> getMaterials() {
        return (Set<Material>) this.materials.get();
    }

    public void setMaterials(Set<Material> materials) {
        this.materials.set(materials);
    }

    public File getPicture() {
        return (File) this.picture.get();
    }

    public void setPicture(File picture) {
        this.picture.set(picture);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.barcode);
        hash = 59 * hash + Objects.hashCode(this.designer);
        hash = 59 * hash + Objects.hashCode(this.price);
        hash = 59 * hash + Objects.hashCode(this.mood);
        hash = 59 * hash + Objects.hashCode(this.bodyPart);
        hash = 59 * hash + Objects.hashCode(this.garmentType);
        hash = 59 * hash + Objects.hashCode(this.available);
        hash = 59 * hash + Objects.hashCode(this.promotionRequest);
        hash = 59 * hash + Objects.hashCode(this.promoted);
        hash = 59 * hash + Objects.hashCode(this.imagePath);
        hash = 59 * hash + Objects.hashCode(this.company);
        hash = 59 * hash + Objects.hashCode(this.colors);
        hash = 59 * hash + Objects.hashCode(this.materials);
        return hash;
    }

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
        final Garment other = (Garment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.available != other.available) {
            return false;
        }
        if (this.promotionRequest != other.promotionRequest) {
            return false;
        }
        if (this.promoted != other.promoted) {
            return false;
        }
        if (!Objects.equals(this.barcode, other.barcode)) {
            return false;
        }
        if (!Objects.equals(this.designer, other.designer)) {
            return false;
        }
        if (!Objects.equals(this.imagePath, other.imagePath)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (this.mood != other.mood) {
            return false;
        }
        if (this.bodyPart != other.bodyPart) {
            return false;
        }
        if (this.garmentType != other.garmentType) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.colors, other.colors)) {
            return false;
        }
        return Objects.equals(this.materials, other.materials);
    }

    @Override
    public String toString() {
        return "Garment{" + "id=" + id + ", barcode=" + barcode + ", designer=" + designer + ", price=" + price + ", mood=" + mood + ", bodyPart=" + bodyPart + ", garmentType=" + garmentType + ", available=" + available + ", promotionRequest=" + promotionRequest + ", promoted=" + promoted + ", imagePath=" + imagePath + ", company=" + company + ", colors=" + colors + ", materials=" + materials + '}';
    }
}
