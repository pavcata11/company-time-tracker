package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLoginAsEmployee implements Initializable {
    public TextField txtClientName;
    public TextField txtTimeSpent;
    public Button btnWriteData;
    public DatePicker dtpDate;
    public Label lblMessages;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void WriteData(ActionEvent event) throws IOException {

        /*
        if(!txtClientName.getText().isEmpty() || !txtTimeSpent.getText().isEmpty() || dtpDate.getValue() == null) {
            Parent root = FXMLLoader.load(getClass().getResource("successfullyStoredPopup.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Data stored");
            primaryStage.setScene(new Scene(root, 200, 100));
            primaryStage.show();
            primaryStage.setResizable(true);
        }
        */
        if(txtClientName.getText().isEmpty() || txtTimeSpent.getText().isEmpty() || dtpDate.getValue() == null) {
            lblMessages.setText("Please enter correct information!");

        }
        else {
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
            lblMessages.setText("Data for " + txtClientName.getText() + " successfully stored!");
            txtClientName.clear();
            txtTimeSpent.clear();
        }
    }

}