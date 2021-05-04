package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PopupSearchController {

   private Stage stage;
   private Scene scene;
   private Parent root;
    
   
  public void showResults(ActionEvent event) throws IOException{
	  root = FXMLLoader.load(getClass().getResource("SearchResults.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
  }
  
  
  public void goBack(ActionEvent event) throws IOException{
	  root = FXMLLoader.load(getClass().getResource("TaskMController.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();	  
  }
    

   
}
