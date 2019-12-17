/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Ander Rodriguez
 */
public abstract class FXMLDocumentControllerInput extends FXMLDocumentController {
    @FXML
    protected Label lblLength;
    @FXML
    protected Button btnUndo;
    @FXML
    protected Button btnRedo;
    protected ArrayList<String> undoneStrings;
    protected ArrayList<TextField> textFields;

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
        Boolean disable = null;
        for (TextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                disable = true;
                break;
            } else {
                disable = false;
            }
        }
        if (disable) {
            btn.setDisable(disable);
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
        Boolean disable = null;
        for (TextField textField : textFields) {
            if (textField.getText().trim().length() > 30) {
                disable = true;
                break;
            } else {
                disable = false;
            }
        }
        lblLength.setVisible(disable);
        btn.setDisable(disable);
    }

    /**
     * A worthy but ultimately failed attempt at a Undo
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param event
     */
    public void onUndoPress(ActionEvent event) {
        for (TextField textField : textFields) {
            undoneStrings.add(textField.getText());
            textField.setText("");
        }
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
        for (TextField textField : textFields) {
            textField.setText(undoneStrings.get(aux));
            aux++;
        }
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
