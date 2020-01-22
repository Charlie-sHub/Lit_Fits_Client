package lit_fits_client.miscellaneous;

import java.util.Objects;
import javafx.scene.control.ComboBox;
import org.reactfx.Change;

/**
 * Class that extends the InputChange class to accommodate changes in Combo Boxes
 *
 * Following and adapting: https://github.com/FXMisc/UndoFX
 *
 * @author Carlos Mendez
 */
public class ComboBoxChange extends InputChange<Object> {
    private final ComboBox comboBox;

    public ComboBoxChange(Object oldValue, Object newValue, ComboBox comboBox) {
        super(oldValue, newValue);
        this.comboBox = comboBox;
    }

    public ComboBoxChange(Change<Object> value, ComboBox comboBox) {
        this((Object) value.getOldValue(), (Object) value.getNewValue(), comboBox);
    }

    @Override
    public void redo() {
        comboBox.setValue(newValue);
    }

    @Override
    public ComboBoxChange invert() {
        return new ComboBoxChange(newValue, oldValue, comboBox);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TextChange) {
            ComboBoxChange that = (ComboBoxChange) other;
            return Objects.equals(this.oldValue, that.oldValue) && Objects.equals(this.newValue, that.newValue);
        } else {
            return false;
        }
    }
}
