import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main класс
 * @author xez
 */
public class Main extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource("fxml/LaunchView.fxml")
            );
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("SberMaster");
            stage.setMinWidth(450);
            stage.setMinHeight(350);
            stage.show();
        }catch (IOException ioe){

        }

    }
}
