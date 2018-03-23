package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {






        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 1300, 720));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
//        Group root = new Group();
//        Scene scene = new Scene(root, 500, 500, Color.BLACK);
//
//        Rectangle r = new Rectangle(25,25,250,250);
//        r.setFill(Color.BLUE);
//        root.getChildren().add(r);
//
//        primaryStage.setTitle("JavaFX Scene Graph Demo");
//        primaryStage.setScene(scene);
//        pimaryStage.show();


    public static void main(String[] args) {
        launch(args);
    }
}
