package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControllerLoginAsEmployee extends  AdminLogin {


    public ComboBox listNameCustomersComboBox;
    public ComboBox setTimeInComboBox;
    public int flag = 0;
    public int flag1 = 0;
    public int timeWorking = 0;
    public Button savebtn;
    public TextArea textArea;
    public Button donebtn;
    public String userWhoNameInSystem = getNameWhoLogin() + ".txt";
    public int flagStop = 0;
    public Label titleServiceProtocol;
    public Label welcomelbl;


    public String getNameWhoLogin() {
        String nameLoginClient = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("nameLoginEmployee"));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                nameLoginClient = strLine;
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return nameLoginClient;
    }
public void visiableTrueAllElements(){
        titleServiceProtocol.setVisible(true);
        setTimeInComboBox.setVisible(true);
        savebtn.setVisible(true);
    listNameCustomersComboBox.setVisible(true);
    donebtn.setVisible(true);
    textArea.setVisible(true);
    textArea.setDisable(true);
    welcomelbl.setVisible(false);

}

    public void addTextInComboBoxChooseName(ActionEvent actionEvent) {
        visiableTrueAllElements();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Customers"));
            String strLine;
            if (flag == 0) {
                while ((strLine = br.readLine()) != null) {
                    listNameCustomersComboBox.getItems().addAll(strLine);
                }
                flag = 1;
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        addTextInComboBoxMinutes();
    }


    public void addTextInComboBoxMinutes() {
        for (int i = 0; i <= 12; i++) {
            setTimeInComboBox.getItems().addAll(i);

        }
    }

    public int calculateTimeWorking() {
        timeWorking = timeWorking + (int) setTimeInComboBox.getValue();
        return timeWorking;
    }

    public void saveInformationInFileAndVisiableInformationInTextArea(ActionEvent actionEvent) throws IOException {
        LocalDate localDate = LocalDate.now();
        textArea.appendText(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
        textArea.appendText(" Work for " + listNameCustomersComboBox.getValue().toString());
        textArea.appendText("  " + setTimeInComboBox.getValue().toString() + " hours");
        textArea.appendText("\n");
        calculateTimeWorking();

    }

    public void saveInformationOfWorkInFile(ActionEvent actionEvent) {
        File file = new File("EmployeeInformation\\" + userWhoNameInSystem);
        FileWriter writer;

        try {

            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");

            } else {
                if (flagStop == 0) {
                    flagStop = 1;
                    writer = new FileWriter(file, true);
                    PrintWriter printer = new PrintWriter(writer);
                    printer.append(textArea.getText());
                    System.out.println(calculateTimeWorking());
                    printer.close();
                } else {
                    System.out.println("vie veche napravihte protocol zaa  denq");

                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void closeProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void loginOut(ActionEvent actionEvent) throws IOException {

    AdminLogin openLogin = new AdminLogin();
      openLogin.openLogin();

    }
}


