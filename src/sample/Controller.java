package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Controller {
    public TextField usernameField;
    public PasswordField passwordField;
    public Label lblError;
    public Button loginbtn;
    public ImageView imageView;
    private Stage stage;



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void checkLoginAsEmpoleeOrLoginAsAdmin(ActionEvent actionEvent) throws IOException {
        lblError.setVisible(false);

        if (passwordField.getText().equals("admin") && usernameField.getText().equals("pavel")) {
            System.out.println("YOU login as admin");
            usernameField.setText("");
            passwordField.setText("");

                LoginAsAdmin();

        }
        else {
            try {
                checkWhoEmployeeLoginAndCreateFileWithHisUserName();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public void checkWhoEmployeeLoginAndCreateFileWithHisUserName() throws IOException {

        int counter = 0;
        try {
            File file = new File("empoloyeelist");
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");

            } else {
                Scanner fileReader = new Scanner(file);
                String password = passwordField.getText();
                String username = usernameField.getText();
                while (fileReader.hasNextLine()) {
                    if (username.equals(fileReader.nextLine()) && password.equals(fileReader.nextLine())) {
                        System.out.println("You LOGIN AS A Employee");
                        counter++;
                        File nameLogin = new File("nameLoginEmployee");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(nameLogin));
                        writer.write(username);
                        writer.close();LoginAsEmployee();
                    }
                    usernameField.setText("");
                    passwordField.setText("");
                }
                if (counter == 0) {
                    lblError.setVisible(true);
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void LoginAsAdmin() throws IOException {
        stage = (Stage) loginbtn.getScene().getWindow();
        AnchorPane root;
        stage.setTitle("ADMIN SIGN UP");
        root = (AnchorPane) FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("AdminLogin.fxml opened");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 private  void   LoginAsEmployee()throws IOException{
     stage = (Stage) loginbtn.getScene().getWindow();
     AnchorPane root;
     stage.setTitle("EMPLOYEE SIGN UP");
     root = (AnchorPane) FXMLLoader.load(getClass().getResource("LoginAsEmployee.fxml"));
     Scene scene = new Scene(root);
     stage.setScene(scene);
     System.out.println("LoginAsEmployee.fxml opened");
 }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}


