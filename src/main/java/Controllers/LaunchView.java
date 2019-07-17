package Controllers;

import Model.InitData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Контроллер для LaunchView.fxml
 * @author xez
 */
public class LaunchView {

    /**
     * Открытие формы выбора файла
     * @param event
     */
    @FXML
    void openFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Открыть файл");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File dataFile = chooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
        if(dataFile != null) {
            System.out.println("File opened");
            System.out.println(dataFile.getName());


            Thread thread = new Thread( () -> {
                InitData.parseCsv(dataFile);
                InitData.printCsv();
                InitData.getInitData();
                InitData.printInit();
            });

            thread.start();

        }
        else
            System.out.println("File didn't open");
    }

    /**
     * Открытие файла в директории
     * @param event
     */
    @FXML
    void openFileInDirectory(ActionEvent event) {

    }

}
