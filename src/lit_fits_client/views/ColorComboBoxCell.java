package lit_fits_client.views;

import java.util.Set;
import javafx.scene.control.cell.ComboBoxTableCell;
import lit_fits_client.entities.Color;
import lit_fits_client.entities.Garment;

/**
 * Custom ComboBox cell to show the colors of the garment as colors
 *
 * @author Carlos Mendez
 */
public class ColorComboBoxCell extends ComboBoxTableCell<Garment, Set<Color>> {
    public ColorComboBoxCell() {
    }

    @Override
    public void updateItem(Set<Color> item, boolean empty) {
        super.updateItem(item, empty);
        item.stream().forEach(color -> {
            javafx.scene.paint.Color newColor = javafx.scene.paint.Color.web(color.getName());
            setTextFill(newColor);
        }
        );
    }
}
