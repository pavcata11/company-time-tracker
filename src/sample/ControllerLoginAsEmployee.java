package sample;

import java.awt.*;

public class ControllerLoginAsEmployee {
    TextField clientNameField = new TextField();

    public TextField getClientNameField() {
        clientNameField.setText("-fx-text-inner-color: red;");
       return clientNameField;
    }
}
