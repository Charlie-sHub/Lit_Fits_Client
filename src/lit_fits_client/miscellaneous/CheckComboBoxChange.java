package lit_fits_client.miscellaneous;

import java.util.Objects;
import org.controlsfx.control.CheckComboBox;
import org.reactfx.Change;

/**
 * Class that extends the InputChange class to accommodate changes in Check Combo Boxes
 *
 * I shouldn't waste my time on this honestly
 *
 * Following and adapting: https://github.com/FXMisc/UndoFX
 *
 * @author Carlos Mendez
 */
public class CheckComboBoxChange extends InputChange<Object> {
    private final CheckComboBox comboBox;

    public CheckComboBoxChange(Object oldValue, Object newValue, CheckComboBox comboBox) {
        super(oldValue, newValue);
        this.comboBox = comboBox;
    }

    public CheckComboBoxChange(Change<Object> value, CheckComboBox comboBox) {
        this((Object) value.getOldValue(), (Object) value.getNewValue(), comboBox);
    }

    @Override
    public void redo() {
        comboBox.getCheckModel().check(newValue);
    }

    @Override
    public CheckComboBoxChange invert() {
        return new CheckComboBoxChange(newValue, oldValue, comboBox);
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
