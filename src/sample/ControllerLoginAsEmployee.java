package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLoginAsEmployee implements Initializable {
    public TextField txtClientName;
    public TextField txtTimeSpent;
    public Button btnWriteData;
    public DatePicker dtpDate;
    public Label lblIfActionSuccessful;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void WriteData(ActionEvent event) throws FileNotFoundException {
        File employeeData = new File("employeeData.txt");
        try {
            if (!employeeData.exists()) {
                employeeData.createNewFile();
            }
            PrintWriter fileWriter = new PrintWriter(new FileWriter(employeeData, true));
            fileWriter.append(txtClientName.getText() + " " + txtTimeSpent.getText() + " " + dtpDate.getValue());
            fileWriter.println();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtClientName.clear();
        txtTimeSpent.clear();
        lblIfActionSuccessful.setText("Data successfully stored");

    }
}

