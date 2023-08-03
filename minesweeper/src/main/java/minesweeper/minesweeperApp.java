package minesweeper;
 
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

 
 
public class minesweeperApp extends Application {
    public void start(Stage primaryStage) throws IOException {
        URL fxmlLocation = minesweeperApp.class.getResource("/minesweeperApp.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Pane root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(minesweeperApp.class, args);
    }
}