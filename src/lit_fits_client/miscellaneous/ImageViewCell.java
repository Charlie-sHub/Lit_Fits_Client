package lit_fits_client.miscellaneous;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewCell<T> extends TableCell<T, Image> {
    private final ImageView imageView;

    public ImageViewCell() {
        imageView = new ImageView();
        imageView.setFitWidth(64);
        imageView.setFitHeight(64);
        imageView.setPreserveRatio(true);
        setGraphic(imageView);
        setMinHeight(70);
    }

    @Override
    protected void updateItem(Image image, boolean empty) {
        super.updateItem(image, empty);
        if (empty || image == null) {
            imageView.setImage(null);
        } else {
            // set image and text for non-empty cell
            imageView.setImage(image);
        }
    }
}
