/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerical_linear_algebra;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projectXController.PreloaderController;
import projectXController.projectXController;
import projectXController.projectXGuideController;

/**
 *
 * @author vicksemmanuel
 */
public class Numerical_Linear_Algebra extends Application {
    
   private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        preloader();
    }
    public void preloader() throws IOException{
        FXMLLoader load=new FXMLLoader(Numerical_Linear_Algebra.class.getResource("/ProjectXView/preloader.fxml"));
        AnchorPane mainPane=load.load();
        PreloaderController controller=load.getController();
        Scene scene=new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Quad 1.0");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/quad.JPG")));
        primaryStage.show();
    }
   
    public static void main(String[] args) { 
        Console console=new Console();
        launch(args);
    }
}
