package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SampleController {
	
	private ArrayList<String> list = new ArrayList<>();
	@FXML Button add;
	@FXML TextField task;
	@FXML ListView taskView;
	
//	public void add(String message) {
//		list.add(message);
//		System.out.println("Task: " + message);
//	}
	
//	public void enterTest(KeyEvent event) {
//		if(event.getCode().equals(KeyCode.ENTER))
//		{
//			System.out.println("User typed enter");
//		}
//		
//	}
	
	public void onAdd(ActionEvent event) {
		
		if(task.getText() != null && task.getText().length() > 0)
		{
			list.add(task.getText());
			taskView.getItems().add((String)task.getText());
			task.clear();
		}
	

		

	}
	
	public void display() {
		System.out.println("Displaying List of Tasks");
		for(String item: list)
		{
			System.out.println(item);
		}
	}
	
	
	
}
