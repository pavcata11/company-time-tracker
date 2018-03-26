package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLoginAsEmployee implements Initializable {
    public TextField txtClientName;
    public TextField txtTimeSpent;
    public Button btnWriteData;
    public DatePicker dtpDate;
    public Label lblIfActionSuccesfull;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void WriteData(ActionEvent event) throws FileNotFoundException {
        try (PrintStream fileWriter = new PrintStream("employeeData")) {
            fileWriter.printf(txtClientName.getText() + " " + txtTimeSpent.getText() + " " + dtpDate.getValue());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        txtClientName.clear();
        txtTimeSpent.clear();
        lblIfActionSuccesfull.setText("Data succesfully stored");

    }
}
