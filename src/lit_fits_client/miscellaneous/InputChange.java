package lit_fits_client.miscellaneous;

import java.util.Objects;
import java.util.Optional;

/**
 * Abstract class for the input changes
 *
 * Following: https://github.com/FXMisc/UndoFX
 *
 * @author Carlos Mendez
 */
public abstract class InputChange<T> {
    protected final T oldValue;
    protected final T newValue;

    protected InputChange(T oldValue, T newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public abstract void redo();

    public abstract InputChange<T> invert();

    public Optional<InputChange<?>> mergeWith(InputChange<?> other) {
        // don't merge changes by default
        return Optional.empty();
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldValue, newValue);
    }
}
