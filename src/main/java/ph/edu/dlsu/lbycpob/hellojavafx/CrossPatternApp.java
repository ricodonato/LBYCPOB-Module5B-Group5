package ph.edu.dlsu.lbycpob.hellojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// 1.16 launcher for cross_pattern.fxml
public class CrossPatternApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cross_pattern.fxml"));

        Scene scene = new Scene(loader.load());

        stage.setTitle("JavaFX Flag Cross");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}