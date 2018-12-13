/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectXController;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author vicksemmanuel
 */
public class projectXGuideController{

    @FXML
    private WebView web;

    private PreloaderController num;
    public void setView(PreloaderController num){
        this.num=num;
        URL url = getClass().getResource("/quickGuide/quickGuide.html");
        web.getEngine().load(url.toExternalForm());
    }
    
}
