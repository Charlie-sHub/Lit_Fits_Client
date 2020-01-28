package lit_fits_client.miscellaneous;

import java.util.Objects;
import javafx.scene.control.TextField;
import org.reactfx.Change;

/**
 * Class that extends the InputChange class to accommodate changes in text fields
 *
 * Following and adapting: https://github.com/FXMisc/UndoFX
 *
 * @author Carlos Mendez
 */
public class TextChange extends InputChange<String> {
    private final TextField field;

    public TextChange(String oldValue, String newValue, TextField field) {
        super(oldValue, newValue);
        this.field = field;
    }

    public TextChange(Change<String> text, TextField field) {
        this((String) text.getOldValue(), (String) text.getNewValue(), field);
    }

    @Override
    public void redo() {
        field.setText(newValue);
    }

    @Override
    public TextChange invert() {
        return new TextChange(newValue, oldValue, field);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TextChange) {
            TextChange that = (TextChange) other;
            return Objects.equals(this.oldValue, that.oldValue) && Objects.equals(this.newValue, that.newValue);
        } else {
            return false;
        }
    }
}
