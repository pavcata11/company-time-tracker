package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RemoveUserController implements Initializable {

    public ComboBox cmbSelectCustomer;
    public Button btnRemoveClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            removeEmptyLine();
            BufferedReader br = new BufferedReader(new FileReader("Customers"));
            String strLine;
            int flag = 0;
            if (flag == 0) {
                while ((strLine = br.readLine()) != null) {
                    cmbSelectCustomer.getItems().addAll(strLine);
                }
                flag = 1;
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void removeEmptyLine() throws IOException {
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

    public void btnRemove(ActionEvent event) {
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
}
