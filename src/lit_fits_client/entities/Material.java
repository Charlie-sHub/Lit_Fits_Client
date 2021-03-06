package lit_fits_client.entities;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Material entity class
 *
 * @author Carlos Mendez
 */
@XmlRootElement
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Unique name for the material
     */
    private SimpleStringProperty name;

    public Material() {
        this.name = new SimpleStringProperty();
    }

    public Material(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return this.name.get();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.name);
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
        final Material other = (Material) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
