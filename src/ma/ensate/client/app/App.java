
/*
package ma.ensate.client.app;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class App extends Application {
   
 
    private double xOffset = 0;
    private double yOffset = 0;
	
    @Override
    public void start(Stage primaryStage) throws Exception{
            
	Parent root = FXMLLoader.load(getClass().getResource("/ma/ensate/server/vues/Login.fxml")); // On 		
	primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setMaximized(true); 
  
	root.setOnMousePressed(event->{
		xOffset = event.getSceneX();
		yOffset = event.getSceneY();  
	});
        		
	root.setOnMouseDragged(event->{
            primaryStage.setX(event.getScreenX()-xOffset);
            primaryStage.setY(event.getScreenY()-yOffset);
	});
				
        //primaryStage.getIcons().add(new Image("/images/logo ENSA.png")); //C'esl le logo????????????????????????
        primaryStage.setTitle("SendMe");
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.show();
        
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

    
}
*/