package lab10_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class register extends Application 
{
	@Override
	   public void start(Stage stage) throws Exception 
	   {
	      Parent root = 
	         FXMLLoader.load(getClass().getResource("register.fxml"));

	      Scene scene = new Scene(root); // attach scene graph to scene
	      stage.setTitle("Register"); // displayed in window's title bar
	      stage.setScene(scene); // attach scene to stage
	      stage.show(); // display the stage
	   }

	   public static void main(String[] args) {
	      launch(args); 
	   }
}