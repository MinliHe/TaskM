package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;



public class SearchData {
	@FXML
	private ObservableList<String> searchList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> searchResults = new ListView<String>();
}
