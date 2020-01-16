package lit_fits_client.views;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
     * ArrayList that saves the text that was undone
     */
    protected ArrayList<String> undoneStrings;
    /**
     * ArrayList of all the TextFields of the view
     */
    protected ArrayList<TextField> textFields;
    
    public Label getLblLength() {
        return lblLength;
    }
    
    public void setLblLength(Label lblLength) {
        this.lblLength = lblLength;
    }
    
    public Button getBtnUndo() {
        return btnUndo;
    }
    
    public void setBtnUndo(Button btnUndo) {
        this.btnUndo = btnUndo;
    }
    
    public Button getBtnRedo() {
        return btnRedo;
    }
    
    public void setBtnRedo(Button btnRedo) {
        this.btnRedo = btnRedo;
    }
    
    public ArrayList<String> getUndoneStrings() {
        return undoneStrings;
    }
    
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
     * @param btn
     */
    public void onFieldFilled(Button btn) {
        if (textFields.stream().filter(textField -> textField.getText().trim().isEmpty()).count() > 0) {
            btn.setDisable(true);
        } else {
            lengthCheck(btn);
        }
    }

    /**
     * Checks the length of the strings entered on the text fields, if they're longer than what the database will accept
     * the button to send them will be disabled
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param btn The login or register button to be disabled or enabled
     */
    public void lengthCheck(Button btn) {
        if (textFields.stream().filter(textField -> textField.getText().trim().length() > 30).count() > 0) {
            lblLength.setVisible(true);
            btn.setDisable(true);
        } else {
            btn.setDisable(false);
        }
    }

    /**
     * A worthy but ultimately failed attempt at a Undo
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param event
     */
    public void onUndoPress(ActionEvent event) {
        textFields.stream().forEach(textField -> {
            undoneStrings.add(textField.getText());
            textField.setText("");
        });
        
        btnRedo.setDisable(false);
    }

    /**
     * A worthy but ultimately failed attempt at a Undo
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param event
     */
    public void onRedoPress(ActionEvent event) {
        int aux = 0;
        //textFields.stream().forEach(textField -> {
        //    textField.setText(undoneStrings.get(aux));
        //    aux++;
        //});
        btnRedo.setDisable(true);
        undoneStrings.removeAll(undoneStrings);
    }

    /**
     * The actions depends on the implementing class
     *
     * @param event
     */
    public abstract void onRegisterPress(ActionEvent event);
}
