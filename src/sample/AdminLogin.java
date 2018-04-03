package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AdminLogin {
    public Button logoutbtn;
    public TextField phoneTextfield;
    public TextField usernametextfield;
    public TextField emailtextfield;
    public PasswordField passwordtextfield;
    public PasswordField secondpasswordfield;
    public Separator separator1;
    public Separator separator4;
    public Separator separator3;
    public Separator separator2;
    public Button btbCheck;
    public Separator separator5;
    public Label lblemployee;
    public Label lblusernamebusy;
    public Label lblusernamefree;
    public Label lbltitlecustomers;
    public Label lblpassworderorr;
    public Label readylbl;
    public TextField firstnametext;
    public TextField lastnametext;
    public TextField phoncecustumerstext;
    public TextField emailcustomerstext;
    public MenuItem menuClickLoginOut;
    public ComboBox combobox1;
    public CheckMenuItem removecustomersmenu;
    public Button removebtn;
    public Separator separatorcustomersEmail;
    public Separator separatorCustomerslastname;
    public Separator separatorCustomerspnone;
    public Separator separatorcustommerfirstname;
    public ImageView photoAdminImageView;
    public ImageView photoEnterCustomers;
    public TextArea TextAreaViewStatiscks;
    public Button btnSave;
    public TextField nameTexetfiled;
    public Separator separtorname;
    public ComboBox ComboBoxViewStatiscks;
    public Button viewbtn;
    private Stage stage;
    private int flag11 = 0;
    private int flag13 = 0;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Logout(ActionEvent actionEvent) {
        try {
            openLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void openLogin() throws IOException {


        stage = (Stage) logoutbtn.getScene().getWindow();
        AnchorPane root;
        //stage.setTitle("Login as Admin");
        root = (AnchorPane) FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        System.out.println("Login.fxml opened");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void entercustomers(ActionEvent actionEvent) throws IOException {
        photoAdminImageView.setVisible(false);

        TextAreaViewStatiscks.setVisible(false);
        ComboBoxViewStatiscks.setVisible(false);
        nameTexetfiled.setVisible(false);
        separtorname.setVisible(false);
        photoEnterCustomers.setVisible(true);
        lblpassworderorr.setVisible(false);
        lblusernamebusy.setVisible(false);
        lblusernamefree.setVisible(false);
        readylbl.setVisible(false);
        usernametextfield.setVisible(false);
        passwordtextfield.setVisible(false);
        secondpasswordfield.setVisible(false);
        emailtextfield.setVisible(false);
        phoneTextfield.setVisible(false);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        btbCheck.setVisible(false);
        lblemployee.setVisible(false);
        lbltitlecustomers.setVisible(true);
        firstnametext.setVisible(true);
        lastnametext.setVisible(true);
        emailcustomerstext.setVisible(true);
        phoncecustumerstext.setVisible(true);
        separatorcustomersEmail.setVisible(true);
        separatorCustomerslastname.setVisible(true);
        separatorCustomerspnone.setVisible(true);
        separatorcustommerfirstname.setVisible(true);
        viewbtn.setVisible(false);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean checkUsername(ActionEvent actionEvent) {
        int counter = 0;
        int flag = 0;
        String usernameText = usernametextfield.getText();
        File file = new File("empoloyeelist");

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
                    lblusernamebusy.setVisible(true);
                    lblusernamefree.setVisible(false);
                    usernametextfield.setText("");
                    flag = 1;
                } else {

                    lblusernamebusy.setVisible(false);
                    lblusernamefree.setVisible(true);
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

        String password = passwordtextfield.getText();
        String secondPassword = secondpasswordfield.getText();
        int flag = 0;


        if (password.equals(secondPassword) && secondPassword.equals(password)) {
            lblpassworderorr.setVisible(false);
            flag = 1;
        } else {
            lblpassworderorr.setVisible(true);
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
        if (emailtextfield.getText().equals("") || phoneTextfield.getText().equals("")
                || passwordtextfield.getText().equals("") || secondpasswordfield.getText().equals("") ||
                nameTexetfiled.getText().equals("")) {
            flag = 1;

        }
        if (checkUsername(actionEvent) == true && checkPassword() == true && flag == 0 && checPhoneNumberEmployee() == true &&
                checkEmailAdress() == true) {

            lblpassworderorr.setVisible(false);
            lblusernamebusy.setVisible(false);
            lblusernamefree.setVisible(false);
            readylbl.setVisible(false);
            File file = new File("empoloyeelist");
            FileWriter writer;
            try {
                boolean isCreated = file.createNewFile();
                if (isCreated) {
                    System.out.println("File has been created successfully");
                } else {
                    writer = new FileWriter(file, true);
                    PrintWriter printer = new PrintWriter(writer);
                    printer.append(usernametextfield.getText());
                    printer.println();
                    printer.append(passwordtextfield.getText());
                    printer.println();
                    printer.close();
                    readylbl.setVisible(true);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            saveInformationForEmployee();
            nameSomeEmployeerList();
        }
        saveCustomers();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void nameSomeEmployeerList() {
        File file = new File("FileNameEmloyee");
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
                printer.append("Name: " + nameTexetfiled.getText());
                printer.println();
                printer.close();
                readylbl.setVisible(true);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveInformationForEmployee() {
        String saveFileName = usernametextfield.getText() + ".txt";

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
                printer.append("Name: " + nameTexetfiled.getText());
                printer.println();
                printer.append("Username: " + usernametextfield.getText() + "           password: " + passwordtextfield.getText());
                printer.println();
                printer.append("Email: " + emailtextfield.getText() + "\nPhone Number: " + phoneTextfield.getText());
                printer.println();
                printer.close();
                readylbl.setVisible(true);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveCustomers() {
        if (firstnametext.getText().equals("") || lastnametext.getText().equals("") || phoncecustumerstext.getText().equals("")
                || phoncecustumerstext.getText().equals("")) {
            System.out.println("Error There are empty fields");
        } else {
            File file = new File("Customers");
            boolean isCreated = false;
            try {
                isCreated = file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (isCreated) {
                System.out.println("File has been created successfully");
            } else {
                file = new File("Customers");
                FileWriter writer;
                try {
                    writer = new FileWriter(file, true);
                    PrintWriter printer = new PrintWriter(writer);
                    printer.append(firstnametext.getText() + " " + lastnametext.getText());
                    printer.println();
                    printer.close();
                    readylbl.setVisible(true);
                    combobox1.getItems().add(firstnametext.getText() + " " + lastnametext.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void newClear(ActionEvent actionEvent) {

        lblpassworderorr.setVisible(false);
        lblusernamebusy.setVisible(false);
        lblusernamefree.setVisible(false);
        readylbl.setVisible(false);
        usernametextfield.setText("");
        passwordtextfield.setText("");
        secondpasswordfield.setText("");
        emailtextfield.setText("");
        phoneTextfield.setText("");
        // lbltitlecustomers.setText("");
        firstnametext.setText("");
        lastnametext.setText("");
        emailcustomerstext.setText("");
        phoncecustumerstext.setText("");
        nameTexetfiled.setText("");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void registNewEmployee(ActionEvent actionEvent) throws IOException {
        photoAdminImageView.setVisible(true);
        photoEnterCustomers.setVisible(false);

        newClear(actionEvent);
        lbltitlecustomers.setVisible(false);
        firstnametext.setVisible(false);
        lastnametext.setVisible(false);
        separatorcustomersEmail.setVisible(false);
        separatorCustomerslastname.setVisible(false);
        separatorCustomerspnone.setVisible(false);
        separatorcustommerfirstname.setVisible(false);
        emailcustomerstext.setVisible(false);
        phoncecustumerstext.setVisible(false);
        usernametextfield.setVisible(true);
        passwordtextfield.setVisible(true);
        secondpasswordfield.setVisible(true);
        emailtextfield.setVisible(true);
        phoneTextfield.setVisible(true);
        viewbtn.setVisible(false);
        separator1.setVisible(true);
        separator2.setVisible(true);
        separator3.setVisible(true);
        separator4.setVisible(true);
        separator5.setVisible(true);
        btbCheck.setVisible(true);
        lblemployee.setVisible(true);
        TextAreaViewStatiscks.setVisible(false);
        ComboBoxViewStatiscks.setVisible(false);
        nameTexetfiled.setVisible(true);
        separtorname.setVisible(true);
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

    public void removeCustomers(ActionEvent actionEvent) {

        if (removecustomersmenu.isSelected()) {
            combobox1.setVisible(true);
            removebtn.setVisible(true);
            combobox1.setPromptText("Select customers");
            try {
                File file = new File("Customers");
                boolean isCreated = file.createNewFile();
                if (isCreated) {
                    System.out.println("File has been created successfully");
                } else {
                    System.out.println("File already present at the specified location");
                    Scanner fileReader = new Scanner(file);
                    // int lineNumber = 0;
                    int counter = 0;

                    removeEmptyline();
                    if (flag11 == 0) {
                        while (fileReader.hasNextLine()) {

                            //combobox.addItem(fileReader.nextLine());

                            combobox1.getItems().addAll(fileReader.nextLine());

                        }
                        flag11 = 1;
                    }
                    fileReader.close();
                }

            } catch (IOException e) {
                System.out.println("Exception Occurred:");
                e.printStackTrace();
            }
        } else {
            combobox1.setVisible(false);
            removebtn.setVisible(false);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void removeEmptyline() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("Customers"));
        String line = "";
        String out = "";
        while ((line = reader.readLine()) != null) {
            if (line.length() > 0) {
                out += line + "\n";
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Customers"));
        writer.append(out);
        writer.close();
        System.out.println(out);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //this is btn //
    public void remove(ActionEvent actionEvent) {
        String removeCustomer = (String) combobox1.getValue();

        try {
            Path path = Paths.get("Customers");
            Stream<String> lines = Files.lines(path);
            List<String> replaced = lines.map(line -> line.replaceAll(removeCustomer, "")).collect(Collectors.toList());
            Files.write(path, replaced);
            combobox1.getItems().remove(removeCustomer);
            lines.close();
            System.out.println("Find and Replace done!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean checPhoneNumberEmployee() {
        String phoneNumberIs = phoneTextfield.getText();
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

    private boolean checkEmailAdress() {
        String emaiiText = emailtextfield.getText();

        if (emaiiText.contains("@") || emaiiText.contains(".")) {
            return true;
        } else return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void checkViewEmployeeStatics(ActionEvent actionEvent) throws IOException {
        newClear(actionEvent);
        nameTexetfiled.setVisible(false);
        separtorname.setVisible(false);
        photoAdminImageView.setVisible(false);
        photoEnterCustomers.setVisible(true);
        lbltitlecustomers.setVisible(false);
        firstnametext.setVisible(false);
        lastnametext.setVisible(false);
        separatorcustomersEmail.setVisible(false);
        separatorCustomerslastname.setVisible(false);
        separatorCustomerspnone.setVisible(false);
        separatorcustommerfirstname.setVisible(false);
        emailcustomerstext.setVisible(false);
        phoncecustumerstext.setVisible(false);
        usernametextfield.setVisible(false);
        passwordtextfield.setVisible(false);
        secondpasswordfield.setVisible(false);
        emailtextfield.setVisible(false);
        phoneTextfield.setVisible(false);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        btbCheck.setVisible(false);
        lblemployee.setVisible(false);
        ////////
        ///.....//
        ////


        //btnSave.setVisible(false);
        ComboBoxViewStatiscks.setVisible(true);
        TextAreaViewStatiscks.setVisible(true);

        viewbtn.setVisible(true);
        try {
            File file = new File("FileNameEmloyee");
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
                Scanner fileReader = new Scanner(file);
                if (flag13 == 0) {
                    while (fileReader.hasNextLine()) {
                        ComboBoxViewStatiscks.getItems().addAll(fileReader.nextLine());

                    }
                    flag13=1;
                }
                fileReader.close();
            }

        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }

    }

    public void ViewStatiskClicked(ActionEvent actionEvent) throws FileNotFoundException {
        TextAreaViewStatiscks.clear();
        Scanner fileReader = null;
        File folder = new File("EmployeeInformation\\");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileReader = new Scanner(file, "UTF-8");
                while (fileReader.hasNextLine()) {

                    if (fileReader.nextLine().equals((String) ComboBoxViewStatiscks.getValue())) {
                        System.out.println(file.getName());
                        String filename= file.getName();

                        try {
                            file = new File("EmployeeInformation\\"+filename);
                            boolean isCreated = file.createNewFile();
                            if (isCreated) {
                                System.out.println("File has been created successfully");
                            } else {
                                System.out.println("File already present at the specified location");
                                Scanner fileReader1 = new Scanner(file);
                                while (fileReader1.hasNextLine()) {
                                    TextAreaViewStatiscks.appendText(fileReader1.nextLine()+" \n");
                                }
                                fileReader1.close();
                            }

                        } catch (IOException e) {
                            System.out.println("Exception Occurred:");
                            e.printStackTrace();
                        }

                    }
                }



            }
            fileReader.close();
        }
    }
}





