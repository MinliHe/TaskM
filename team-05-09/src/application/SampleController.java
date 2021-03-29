package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SampleController {
	
	public Button signupB;
	
	private ArrayList<String> list = new ArrayList<>();
	@FXML Button add;
	@FXML TextField task;
	@FXML ListView taskView;

	
// TESTING ADD BUTTON
//	public void add(String message) {
//		list.add(message);
//		System.out.println("Task: " + message);
//	}
	
// TESTING ENTER BUTTON
//	public void enterTest(KeyEvent event) {
//		if(event.getCode().equals(KeyCode.ENTER))
//		{
//			System.out.println("User typed enter");
//		}
//		
//	}
	
	//Adds task to taskView and displays it
	public void onAdd(ActionEvent event) {
		
		if(task.getText() != null && task.getText().length() > 0) //check if a task has been inputed
		{
			list.add(task.getText()); //add task object to list for later features
			taskView.getItems().add((String)task.getText()); // add task to task view
			task.clear(); //clear input for new task
		}
	}

	
    
// TESTING DISPLAY OF TASKVIEW
//	public void display() {
//		System.out.println("Displaying List of Tasks");
//		for(String item: list)
//		{
//			System.out.println(item);
//		}
//	}
	
	
	
}
