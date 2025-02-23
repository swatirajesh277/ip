package cherrybot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private CherryBot cherryBot = new CherryBot("./data/cherrybot.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("CherryBot");
            Image logo = new Image(getClass().getResourceAsStream("/images/cherrybot_logo.jpeg"));
            stage.getIcons().add(logo);
            stage.setMinHeight(220);
            stage.setMinWidth(450);
            fxmlLoader.<MainWindow>getController().setCherryBot(cherryBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
