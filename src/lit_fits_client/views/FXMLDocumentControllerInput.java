package lit_fits_client.views;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lit_fits_client.miscellaneous.InputChange;
import org.fxmisc.undo.UndoManager;
import org.reactfx.EventStream;

/**
 * Document Controller with methods to validate input fields common to all views with inputs
 *
 * @author Ander Rodriguez & Carlos Mendez
 */
public abstract class FXMLDocumentControllerInput extends FXMLDocumentController {
    /**
     * Label that warns the user about the length of a text
     */
    @FXML
    protected Label lblLength;
    /**
     * Button to "undo"
     */
    @FXML
    protected Button btnUndo;
    /**
     * Button to "redo"
     */
    @FXML
    protected Button btnRedo;
    /**
     * The Undo Manager of the application
     */
    protected UndoManager<InputChange<?>> undoManager;
    /**
     * The stream of events to be undone or redone
     */
    protected EventStream<InputChange<?>> inputChanges;
    /**
     * ArrayList that saves the text that was undone
     *
     * Recommended to use the UndoManager instead
     */
    @Deprecated
    protected ArrayList<String> undoneStrings;
    /**
     * ArrayList of all the TextFields of the view
     *
     * Recommended to use the UndoManager instead
     */
    protected ArrayList<TextField> textFields;

    public UndoManager<InputChange<?>> getUndoManager() {
        return undoManager;
    }

    public void setUndoManager(UndoManager<InputChange<?>> undoManager) {
        this.undoManager = undoManager;
    }

    public EventStream<InputChange<?>> getInputChanges() {
        return inputChanges;
    }

    public void setInputChanges(EventStream<InputChange<?>> inputChanges) {
        this.inputChanges = inputChanges;
    }

    @Deprecated
    public ArrayList<String> getUndoneStrings() {
        return undoneStrings;
    }

    @Deprecated
    public void setUndoneStrings(ArrayList<String> undoneStrings) {
        this.undoneStrings = undoneStrings;
    }

    public ArrayList<TextField> getTextFields() {
        return textFields;
    }

    public void setTextFields(ArrayList<TextField> textFields) {
        this.textFields = textFields;
    }

    /**
     * This function will verify if all the fields are filled
     *
     * @author Carlos Mendez
     * @param button The login or register button to be disabled or enabled
     */
    public void onFieldFilled(Button button) {
        if (textFields.stream().filter(textField -> textField.getText().trim().isEmpty()).count() > 0) {
            button.setDisable(true);
        } else {
            lengthCheck(button);
        }
    }

    /**
     * Checks the length of the strings entered on the text fields, if they're longer than what the database will accept
     * the button to send them will be disabled
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param button The login or register button to be disabled or enabled
     */
    public void lengthCheck(Button button) {
        if (textFields.stream().filter(textField -> textField.getText().trim().length() > 30).count() > 0) {
            lblLength.setVisible(true);
            button.setDisable(true);
        } else {
            button.setDisable(false);
        }
    }

    /**
     * Un-does the last change
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param event
     */
    @Deprecated
    public void onUndoPress(ActionEvent event) {
        undoManager.undo();
    }

    /**
     * Re-does the last change
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param event
     */
    @Deprecated
    public void onRedoPress(ActionEvent event) {
        undoManager.redo();
    }

    /**
     * The actions depends on the implementing class
     *
     * @param event
     */
    public abstract void onRegisterPress(ActionEvent event);
}
