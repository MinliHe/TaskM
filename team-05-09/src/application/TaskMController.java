package application;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TaskMController {
	

    @FXML
    private TextField searchTask;
    
    
    //variables for School Section
	@FXML Button deleteS;

	@FXML Button editS;

	@FXML TextField addTaskS;

	@FXML Button addS;

	@FXML DatePicker datePickerS;
    @FXML
    private ListView<String> ListViewS = new ListView<String>();
    @FXML
    private ListView<Object> dateListS = new javafx.scene.control.ListView<>();
    

    //variables for Work Section
	@FXML TextField addTaskW;

	@FXML Button addW;

	@FXML Button editW;

	@FXML Button deleteW;

	@FXML DatePicker datePickerW;
	@FXML
	private ListView<String> ListViewW = new ListView<String>();
    @FXML
    private ListView<Object> dateListW = new javafx.scene.control.ListView<>();
	
    
	
    //variables for Personal Section
    @FXML TextField addTaskP;
    
	@FXML Button addP;
	
	@FXML Button editP;
	
	@FXML Button deleteP;
	
	@FXML DatePicker datePickerP;
	@FXML
    private ListView<String> ListViewP = new ListView<String>();
    @FXML
    private ListView<Object> dateListP = new javafx.scene.control.ListView<>();
	

	
    //methods for School section
    @FXML
    void addS(ActionEvent event) {
    	String task = addTaskS.getText();
    	if(addTaskS.getText() != null && addTaskS.getText().length() > 0) //check if a task has been inputed
    		{
    			ListViewS.getItems().add(task); //add task to listView 
    			addTaskS.clear(); //clear input for new task
    		}

    }
    @FXML
    void addDateS(ActionEvent event) {
    	LocalDate date = datePickerS.getValue();
    	dateListS.getItems().add(date);

    }
    

    //methods for Work Section
    @FXML
    void addW(ActionEvent event) {
    	String task = addTaskW.getText();
    	if(addTaskW.getText() != null && addTaskW.getText().length() > 0) //check if a task has been inputed
    		{
    			ListViewW.getItems().add(task); //add task to listView 
    			addTaskW.clear(); //clear input for new task
    		}

    }
    @FXML
    void addDateW(ActionEvent event) {
    	LocalDate date = datePickerW.getValue();
    	dateListW.getItems().add(date);

    }
    
    
    
    //Methods for Personal Section
    @FXML
    void addP(ActionEvent event) {
    	String task = addTaskP.getText();
    	if(addTaskP.getText() != null && addTaskP.getText().length() > 0) //check if a task has been inputed
    		{
    			ListViewP.getItems().add(task); //add task to listView 
    			addTaskP.clear(); //clear input for new task
    		}

    }
    @FXML
    void addDateP(ActionEvent event) {
    	LocalDate date = datePickerP.getValue();
    	dateListP.getItems().add(date);
    	

    }

}
