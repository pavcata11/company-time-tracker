package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminLogin {
    public Button btnLogout;
    public TextField txtPhone;
    public TextField txtUsername;
    public TextField txtEmail;
    public PasswordField txtPassword;
    public PasswordField txtPasswordSecond;
    public Separator separator1;
    public Separator separator4;
    public Separator separator3;
    public Separator separator2;
    public Button btnCheck;
    public Separator separator5;
    public Label lblEmployee;
    public Label lblUsernameUsedAlready;
    public Label lblUsernameFree;
    public Label lblTitleCustomers;
    public Label lblPasswordError;
    public Label lblReady;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtCustomerPhone;
    public TextField txtCustomerEmail;
    public MenuItem miLogout;
    public ComboBox cmbSelectCustomer;
    public MenuItem miRemoveCustomer;
    public Button btnRemove;
    public Separator separatorCustomerEmail;
    public Separator separatorCustomerLastName;
    public Separator separatorCustomerPhone;
    public Separator separatorCustomerFirstName;
    public ImageView imgAdmin;
    public ImageView imgCustomers;
    public TextArea txtViewStatistics;
    public Button btnSave;
    public TextField txtName;
    public Separator separatorName;
    public ComboBox cmbViewStatistics;
    public Button btnViewData;
    public Button btnWorkingTime;
    public TextField txtWorkingTime;
    private Stage stage;
    private int flag13 = 0;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Logout(ActionEvent actionEvent) throws IOException {
        openWelcomePane();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void openWelcomePane() throws IOException {
        stage = (Stage) btnLogout.getScene().getWindow();
        AnchorPane root;
        root = (AnchorPane) FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("Login.fxml opened");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void clickCustomersVisibleFields(ActionEvent actionEvent) throws IOException {
        imgAdmin.setVisible(false);
        txtWorkingTime.setVisible(false);
        btnWorkingTime.setVisible(false);
        txtViewStatistics.setVisible(false);
        cmbViewStatistics.setVisible(false);
        txtName.setVisible(false);
        separatorName.setVisible(false);
        imgCustomers.setVisible(true);
        lblPasswordError.setVisible(false);
        lblUsernameUsedAlready.setVisible(false);
        lblUsernameFree.setVisible(false);
        lblReady.setVisible(false);
        txtUsername.setVisible(false);
        txtPassword.setVisible(false);
        txtPasswordSecond.setVisible(false);
        txtEmail.setVisible(false);
        txtPhone.setVisible(false);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        btnCheck.setVisible(false);
        lblEmployee.setVisible(false);
        lblTitleCustomers.setVisible(true);
        txtFirstName.setVisible(true);
        txtLastName.setVisible(true);
        txtCustomerEmail.setVisible(true);
        txtCustomerPhone.setVisible(true);
        separatorCustomerEmail.setVisible(true);
        separatorCustomerLastName.setVisible(true);
        separatorCustomerPhone.setVisible(true);
        separatorCustomerFirstName.setVisible(true);
        btnViewData.setVisible(false);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean checkUsername(ActionEvent actionEvent) {
        int counter = 0;
        int flag = 0;
        String usernameText = txtUsername.getText();
        File file = new File("employeeList");
        try {
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");
            } else {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    if (usernameText.equals(fileReader.nextLine())) {
                        counter++;
                    }
                }
                if (counter > 0) {
                    lblUsernameUsedAlready.setVisible(true);
                    lblUsernameFree.setVisible(false);
                    txtUsername.setText("");
                    flag = 1;
                } else {

                    lblUsernameUsedAlready.setVisible(false);
                    lblUsernameFree.setVisible(true);
                    flag = 0;
                }
                fileReader.close();
            }
        } catch (IOException e1) {
            System.out.println("Exception Occurred:");
            e1.printStackTrace();
        }
        if (flag == 1) {
            return false;
        } else
            return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean checkPassword() {
        String password = txtPassword.getText();
        String secondPassword = txtPasswordSecond.getText();
        int flag = 0;
        if (password.equals(secondPassword) && secondPassword.equals(password)) {
            lblPasswordError.setVisible(false);
            flag = 1;
        } else {
            lblPasswordError.setVisible(true);
            flag = 0;
        }
        if (flag == 1) {
            return true;
        } else
            return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void ClickSave(ActionEvent actionEvent) {
        int flag = 0;
        if (txtEmail.getText().equals("") || txtPhone.getText().equals("")
                || txtPassword.getText().equals("") || txtPasswordSecond.getText().equals("") ||
                txtName.getText().equals("")) {
            flag = 1;
        }
        if (checkUsername(actionEvent) == true && checkPassword() == true && flag == 0 && checkPhoneNumberEmployee() == true &&
                checkEmailAddress() == true) {
            lblPasswordError.setVisible(false);
            lblUsernameUsedAlready.setVisible(false);
            lblUsernameFree.setVisible(false);
            lblReady.setVisible(false);
            File file = new File("employeeList");
            FileWriter writer;
            try {
                boolean isCreated = file.createNewFile();
                if (isCreated) {
                    System.out.println("File has been created successfully");
                } else {
                    writer = new FileWriter(file, true);
                    PrintWriter printer = new PrintWriter(writer);
                    printer.append(txtUsername.getText());
                    printer.println();
                    printer.append(txtPassword.getText());
                    printer.println();
                    printer.close();
                    lblReady.setVisible(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveInformationForEmployee();
            saveOnlyNamesOnSuccessfullyRegistrationInFile();
        }
        saveCustomers();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveOnlyNamesOnSuccessfullyRegistrationInFile() {
        File file = new File("fileNameEmployee");
        FileWriter writer;
        int flag = 0;
        try {
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");
                flag = 1;
            }
            if (flag == 1 || isCreated == false) {
                writer = new FileWriter(file, true);
                PrintWriter printer = new PrintWriter(writer);
                printer.append(txtName.getText());
                printer.println();
                printer.close();
                lblReady.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveInformationForEmployee() {
        String saveFileName = txtUsername.getText() + ".txt";
        File file = new File("EmployeeInformation\\" + saveFileName);
        FileWriter writer;
        int flag = 0;
        try {
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");
                flag = 1;
            }
            if (flag == 1 || isCreated == false) {
                writer = new FileWriter(file, true);
                PrintWriter printer = new PrintWriter(writer);
                printer.append(txtName.getText());
                printer.println();
                printer.append("Username: " + txtUsername.getText() + "           password: " + txtPassword.getText());
                printer.println();
                printer.append("Email: " + txtEmail.getText() + "\nPhone Number: " + txtPhone.getText());
                printer.println();
                printer.close();
                lblReady.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveCustomers() {
        if (txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtCustomerPhone.getText().equals("")
                || txtCustomerPhone.getText().equals("")) {
            System.out.println("Error There are empty fields");
        } else {
            File file;
            file = new File("Customers");
            FileWriter writer;
            try {
                writer = new FileWriter(file, true);
                PrintWriter printer = new PrintWriter(writer);
                printer.append(txtFirstName.getText() + " " + txtLastName.getText());
                printer.println();
                printer.close();
                lblReady.setVisible(true);
                AddCustomersNameInComboBox();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void AddCustomersNameInComboBox() {
        cmbSelectCustomer.getItems().add(txtFirstName.getText() + " " + txtLastName.getText());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void clearAllFields(ActionEvent actionEvent) {
        lblPasswordError.setVisible(false);
        lblUsernameUsedAlready.setVisible(false);
        lblUsernameFree.setVisible(false);
        lblReady.setVisible(false);
        txtUsername.setText("");
        txtPassword.setText("");
        txtPasswordSecond.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtCustomerEmail.setText("");
        txtCustomerPhone.setText("");
        txtName.setText("");
        txtViewStatistics.clear();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setVisibleNewEmployeeRegistration(ActionEvent actionEvent) throws IOException {
        imgAdmin.setVisible(true);
        imgCustomers.setVisible(false);
        txtWorkingTime.setVisible(false);
        btnWorkingTime.setVisible(false);
        clearAllFields(actionEvent);
        lblTitleCustomers.setVisible(false);
        txtFirstName.setVisible(false);
        txtLastName.setVisible(false);
        separatorCustomerEmail.setVisible(false);
        separatorCustomerLastName.setVisible(false);
        separatorCustomerPhone.setVisible(false);
        separatorCustomerFirstName.setVisible(false);
        txtCustomerEmail.setVisible(false);
        txtCustomerPhone.setVisible(false);
        txtUsername.setVisible(true);
        txtPassword.setVisible(true);
        txtPasswordSecond.setVisible(true);
        txtEmail.setVisible(true);
        txtPhone.setVisible(true);
        btnViewData.setVisible(false);
        separator1.setVisible(true);
        separator2.setVisible(true);
        separator3.setVisible(true);
        separator4.setVisible(true);
        separator5.setVisible(true);
        btnCheck.setVisible(true);
        lblEmployee.setVisible(true);
        txtViewStatistics.setVisible(false);
        cmbViewStatistics.setVisible(false);
        txtName.setVisible(true);
        separatorName.setVisible(true);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void removeCustomers(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RemoveUser.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Remove customer");
        primaryStage.setScene(new Scene(root, 300, 170));
        primaryStage.show();
    }

    public void FindAndReplaceRemoveCustomers(ActionEvent actionEvent) {
        String removeCustomer = (String) cmbSelectCustomer.getValue();
        try {
            Path path = Paths.get("Customers");
            Stream<String> lines = Files.lines(path);
            List<String> replaced = lines.map(line -> line.replaceAll(removeCustomer, "")).collect(Collectors.toList());
            Files.write(path, replaced);
            cmbSelectCustomer.getItems().remove(removeCustomer);
            lines.close();
            System.out.println("Find and Replace done!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean checkPhoneNumberEmployee() {
        String phoneNumberIs = txtPhone.getText();
        char textPhone;
        int counter = 0;
        for (int i = 0; i < phoneNumberIs.length(); i++) {
            textPhone = phoneNumberIs.charAt(i);
            System.out.println(phoneNumberIs.charAt(i));
            if (textPhone == '1' || textPhone == '2' || textPhone == '3' || textPhone == '4' || textPhone == '5'
                    || textPhone == '6' || textPhone == '7' || textPhone == '8' || textPhone == '9' || textPhone == '0') {
                counter++;
            }
        }
        if (counter == phoneNumberIs.length()) {
            return true;
        } else return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean checkEmailAddress() {
        String emaiiText = txtEmail.getText();
        if (emaiiText.contains("@") || emaiiText.contains(".")) {
            return true;
        } else return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void visibleFieldsEmployeeStatistics(ActionEvent actionEvent) throws IOException {
        clearAllFields(actionEvent);
        txtWorkingTime.setVisible(false);
        btnWorkingTime.setVisible(true);
        txtName.setVisible(false);
        separatorName.setVisible(false);
        imgAdmin.setVisible(false);
        imgCustomers.setVisible(true);
        lblTitleCustomers.setVisible(false);
        txtFirstName.setVisible(false);
        txtLastName.setVisible(false);
        separatorCustomerEmail.setVisible(false);
        separatorCustomerLastName.setVisible(false);
        separatorCustomerPhone.setVisible(false);
        separatorCustomerFirstName.setVisible(false);
        txtCustomerEmail.setVisible(false);
        txtCustomerPhone.setVisible(false);
        txtUsername.setVisible(false);
        txtPassword.setVisible(false);
        txtPasswordSecond.setVisible(false);
        txtEmail.setVisible(false);
        txtPhone.setVisible(false);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        btnCheck.setVisible(false);
        lblEmployee.setVisible(false);
        cmbViewStatistics.setVisible(true);
        txtViewStatistics.setVisible(true);
        btnViewData.setVisible(true);
        createFileWhoContainsOnlyNameOfEmployee();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void createFileWhoContainsOnlyNameOfEmployee() {
        try {
            File file = new File("fileNameEmployee");
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
                Scanner fileReader = new Scanner(file);
                if (flag13 == 0) {
                    while (fileReader.hasNextLine()) {
                        cmbViewStatistics.getItems().addAll(fileReader.nextLine());
                    }
                    flag13 = 1;
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

    public void ViewStatisticClicked(ActionEvent actionEvent) throws IOException {
        txtViewStatistics.clear();
        try {
            File file = new File("EmployeeInformation\\" + openFileWhoBySelectName());
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("\n" + "File already present at the specified location");
                Scanner fileReader1 = new Scanner(file);
                while (fileReader1.hasNextLine()) {
                    txtViewStatistics.appendText(fileReader1.nextLine() + " \n");
                }
                fileReader1.close();
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String howTimeWorkingSetTextInTextField() throws FileNotFoundException {
        String howSratsWorkindHowWorkingHowFinishedWorking = null;
        String firstWordHowStartsWorking = "";
        LocalDate localDate = LocalDate.now();
        System.out.println(firstWordHowStartsWorking);
        howSratsWorkindHowWorkingHowFinishedWorking = "Do: " + DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
        howSratsWorkindHowWorkingHowFinishedWorking = howSratsWorkindHowWorkingHowFinishedWorking +
                " The employee has worked for " + readOnlyIntegerHowTimeWorking() + " minutes";
        System.out.println(howSratsWorkindHowWorkingHowFinishedWorking);
        System.out.println(readOnlyIntegerHowTimeWorking());
        return howSratsWorkindHowWorkingHowFinishedWorking;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double readOnlyIntegerHowTimeWorking() throws FileNotFoundException {
        double sum = 0;
        int lines = 0, count = 0, i = 0;
        File file = new File("EmployeeInformation\\" + openFileWhoBySelectName());
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            count++;
            scanner.nextLine();
            if (count >= 4) {
                List<Integer> integers = new ArrayList<>();
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        integers.add(scanner.nextInt());
                    } else {
                        scanner.next();
                    }
                }
                for (i = 0; i < integers.size(); i++) {
                    sum += integers.get(i);
                }
            }
        }
        return sum;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String openFileWhoBySelectName() throws FileNotFoundException {
        Scanner fileReader = null;
        String filename = null;
        File folder = new File("EmployeeInformation\\");
        File[] listOfFiles = folder.listFiles();
        double sum = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileReader = new Scanner(file, "UTF-8");
                while (fileReader.hasNextLine()) {
                    if (fileReader.nextLine().equals((String) cmbViewStatistics.getValue())) {
                        System.out.println(file.getName());
                        filename = file.getName();
                    }
                }
            }
        }
        fileReader.close();
        return filename;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void viewHowTimeWorking(ActionEvent actionEvent) throws IOException {
        txtWorkingTime.setVisible(true);
        txtWorkingTime.setText(howTimeWorkingSetTextInTextField());
    }
}
