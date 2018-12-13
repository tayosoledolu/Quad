    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectXController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vicksemmanuel
 */
public class PreloaderController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }  
    public void mainWindow() throws IOException{
        FXMLLoader load=new FXMLLoader(PreloaderController.class.getResource("/ProjectXView/projectX.fxml"));
        AnchorPane mainPane=load.load();
        projectXController controller=load.getController();
        controller.setMain(this);
        Scene scene=new Scene(mainPane);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quad 1.0");
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/quad.JPG")));
        primaryStage.show();
    }
     public void webWindow() throws IOException{
        FXMLLoader load=new FXMLLoader(PreloaderController.class.getResource("/ProjectXView/quickGuide.fxml"));
        AnchorPane mainPane=load.load();
        projectXGuideController controller=load.getController();
        controller.setView(this);
        Stage primaryStage=new Stage();
        Scene scene=new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quick Guide");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/quad.JPG")));
        primaryStage.show();
    }
     
    class SplashScreen extends Thread{
        public void run(){
            try {
                Thread.sleep(5000);
                Platform.runLater(()->{
                    try {
                        mainWindow();
                        anchorPane.getScene().getWindow().hide();
                    } catch (IOException ex) {}
                });
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
