package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerLoginAsEmployee implements Initializable {

    public String userWhoNameInSystem = getNameWhoLogin() + ".txt";
    public MenuBar myMenuBar;
    public MenuItem miCloseProgram;
    public MenuItem miLogOut;
    public ComboBox cmbCustomerNames;
    public TextField txtTimeSpent;
    public Button btnWriteData;
    public DatePicker dtpDate;
    public Button btnCloseSavedWindow;
    public Button btnCloseWindow;

    public String getNameWhoLogin()  { String line = "";
        File file = new File("nameLoginEmployee");
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                line = scan.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(userWhoNameInSystem);
        try {
            BufferedReader br = new BufferedReader(new FileReader("Customers"));
            String strLine;
            int flag = 0;
            if (flag == 0) {
                while ((strLine = br.readLine()) != null) {
                    cmbCustomerNames.getItems().addAll(strLine);
                }
                flag = 1;
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void closeProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void WriteData(ActionEvent actionEvent) throws IOException {
        if (cmbCustomerNames.getValue() == null || txtTimeSpent.getText().isEmpty() || dtpDate.getValue() == null) {
            Parent root = FXMLLoader.load(getClass().getResource("incorrectDataPopup.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Incorrect data");
            primaryStage.setScene(new Scene(root, 260, 120));
            primaryStage.show();

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("successfullySavedPopup.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Data saved");
            primaryStage.setScene(new Scene(root, 260, 120));
            primaryStage.show();
            File file = new File("EmployeeInformation\\" + userWhoNameInSystem);
            FileWriter writer;

            try {

                boolean isCreated = file.createNewFile();
                if (isCreated) {
                    System.out.println("File has been created successfully");

                } else {

                    writer = new FileWriter(file, true);
                    PrintWriter printer = new PrintWriter(writer);
                    int ConvertMinutesStringToMinutesInt = Integer.parseInt(txtTimeSpent.getText());
                    printer.append(dtpDate.getValue().toString() + " " + "Work for " + cmbCustomerNames.getValue().toString() + " " + (ConvertMinutesStringToMinutesInt + " minutes" + "\n"));
                    printer.close();
                    dtpDate.setAccessibleText("");

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void closeSavedWindow(ActionEvent event) {
        Stage stage = (Stage) btnCloseSavedWindow.getScene().getWindow();
        stage.close();
    }

   public void closeIncorrectWindow(ActionEvent event) {

       Stage stage = (Stage) btnCloseWindow.getScene().getWindow();
       stage.close();
   }

    public void LogOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("AdminLogin.fxml opened");
    }
}
