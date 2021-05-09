package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopupSearchController {

   private Stage stage;
   private Scene scene;
   private Parent root;
   
	@FXML
	private ObservableList<String> searchList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> searchResults = new ListView<String>();
	@FXML
	private Text keyword;
	
public void setKeyWord(String text) {
	this.keyword.setText(text);
}

 public void setSearchResults(ObservableList<String> searchList) {
	 this.searchList= searchList;
 }
   
  public void showResults(ActionEvent event) throws IOException{
	  root = FXMLLoader.load(getClass().getResource("SearchResults.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
  }
  
  public void displayResults() {
//  	
//  	System.out.println("SEARCH BUTTON HAS BEEN CLCIKED");
//  	ObservableList<String> test = FXCollections.observableArrayList();
//  	test.add("Hello");
//  	searchResults.setItems(test);
//  	System.out.println("Printing schhol task results below:");
//  	

	  searchResults.setItems(searchList);
	
	  
		
  }
  
  
  public void goBack(ActionEvent event) throws IOException{
	  root = FXMLLoader.load(getClass().getResource("TaskMController.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();	  
  }
    

   
}
