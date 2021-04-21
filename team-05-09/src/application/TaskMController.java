package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

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
    private ObservableList<String> schoolTask = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listViewS = new ListView<String>();
    @FXML
    private ObservableList<Object> dateS = FXCollections.observableArrayList();
    @FXML
    private ListView<Object> dateListS = new javafx.scene.control.ListView<>();
    
    

    //variables for Work Section
	@FXML TextField addTaskW;

	@FXML Button addW;

	@FXML Button editW;

	@FXML Button deleteW;

	@FXML DatePicker datePickerW;
	@FXML
    private ObservableList<String> workTask = FXCollections.observableArrayList();
	@FXML
	private ListView<String> ListViewW = new ListView<String>();
	@FXML
	private ObservableList<Object> dateW = FXCollections.observableArrayList();
    @FXML
    private ListView<Object> dateListW = new javafx.scene.control.ListView<>();
	
    
	
    //variables for Personal Section
    @FXML TextField addTaskP;
    
	@FXML Button addP;
	
	@FXML Button editP;
	
	@FXML Button deleteP;
	
	@FXML DatePicker datePickerP;
	@FXML
    private ObservableList<String> personalTask = FXCollections.observableArrayList();
	@FXML
    private ListView<String> ListViewP = new ListView<String>();
	@FXML
	private ObservableList<Object> dateP = FXCollections.observableArrayList();
    @FXML
    private ListView<Object> dateListP = new javafx.scene.control.ListView<>();
	

    
    
    LocalDate getCurrentDate() {
    	Date currentDate = new Date();
    	Instant instant = currentDate.toInstant();
    	LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
    	
    	return localDate;
    }
	
    /**
     * This method implements the "Add" button under School section.
     * User input task name in the text field, then pick a due day.
     * Then click on "Add" button. The task and due date are shown to the list.
     * @param event
     * @throws SQLException 
     */
    @FXML
    void addS(ActionEvent event) throws SQLException {
    	String task = addTaskS.getText();
    	LocalDate date = datePickerS.getValue();
    		
    	if(date != null) {
    		task = task + ": due "+ (date.toString());
        	if(date.equals(getCurrentDate())) {
        		task= task + ("\u2605") + ("\u2605") + ("\u2605")+ " IMPORTANT DUE TODAY";
        	}
        	
    	}
     	
    	if(addTaskS.getText() != null && addTaskS.getText().length() > 0) //check if a task has been inputed
    		{
    			schoolTask.add(task);
    			listViewS.setItems(schoolTask);//add task to listView
    			addTaskS.clear(); //clear input for new task
    		}
    	
    	// insert the record into the database
    	dbConnect connect = new dbConnect();
    	connect.insertRecord(task,date);
    }  
    
    @FXML
    void addDateS(ActionEvent event) throws SQLException {
    	LocalDate date = datePickerS.getValue();
    	dateS.add(date);
    	dateListS.setItems(dateS);
    }
    
    /** 
     * 	This method implements the "delete" button under School section. 
     * 	User select the task and click delete button. Task name and date will be removed from list.
     */
    @FXML
    public void deleteEventS()
    {
    	deleteS.setOnAction(new EventHandler<ActionEvent>()
    	{
    		@Override
    		public void handle(ActionEvent event)
    		{
    			int selectedIndex = listViewS.getSelectionModel().getSelectedIndex();
    			if (selectedIndex != -1)
    			{
    				listViewS.getItems().remove(selectedIndex);
    			}	
    		}
    	});
    }
    
    
    /**
     * This method implements the "Add" button under work section.
     * User input task name in the text field, then pick a due day.
     * Then click on "Add" button. The task and due date are shown to the list.
     * @param event
     * @throws SQLException 
     */
    @FXML
    void addW(ActionEvent event) throws SQLException {
    	LocalDate date = datePickerW.getValue();
    	String task = addTaskW.getText();
    	
    	
    	if(date != null) {
    		task = task + ": due "+ (date.toString());
        	if(date.equals(getCurrentDate())) {
        		task= task + ("\u2605") + ("\u2605") + ("\u2605")+ " IMPORTANT DUE TODAY";
        	}
        	
    	}
    	
    	if(addTaskW.getText() != null && addTaskW.getText().length() > 0) //check if a task has been inputed
    		{	
    			workTask.add(task);
    			ListViewW.setItems(workTask); //add task to listView 
    			addTaskW.clear(); //clear input for new task
    		}

    	// insert the record into the database
    	dbConnect connect = new dbConnect();
    	connect.insertRecord(task,date);
    }
    
    
    @FXML
    void addDateW(ActionEvent event) {
    	LocalDate date = datePickerW.getValue();
    	dateW.add(date);
    	dateListW.setItems(dateW);

    }
    
    /** 
     * 	This method implements the "delete" button under work section. 
     * 	User select the task and click delete button. Task name and date will be removed from list.
     * @throws SQLException 
     */
    @FXML
    public void deleteEventW() throws SQLException
    {
    	deleteW.setOnAction(new EventHandler<ActionEvent>()
    	{
    		@Override
    		public void handle(ActionEvent event)
    		{
    			int selectedIndex = ListViewW.getSelectionModel().getSelectedIndex();
    			if (selectedIndex != -1)
    			{
    				ListViewW.getItems().remove(selectedIndex);
    			}
    		} 		
    	});
    	
    }
    
    
    /**
     * This method implements the "Add" button under personal section.
     * User input task name in the text field, then pick a due day.
     * Then click on "Add" button. The task and due date are shown to the list.
     * @param event
     * @throws SQLException 
     */
    @FXML
    void addP(ActionEvent event) throws SQLException {
    	LocalDate date = datePickerP.getValue();
    	String task = addTaskP.getText();
    	
    	
    	if(date != null) {
    		task = task + ": due "+ (date.toString());
        	if(date.equals(getCurrentDate())) {
        		task= task + ("\u2605") + ("\u2605") + ("\u2605")+ " IMPORTANT DUE TODAY";
        	}
        	
    	}
    	

    	if(addTaskP.getText() != null && addTaskP.getText().length() > 0) //check if a task has been inputed
    		{
    			personalTask.add(task);
    			ListViewP.setItems(personalTask); //add task to listView 
    			addTaskP.clear(); //clear input for new task
    		}
    	
    	// insert the record into the database
    	dbConnect connect = new dbConnect();
    	connect.insertRecord(task,date);
    }
    
    @FXML
    void addDateP(ActionEvent event) {
    	LocalDate date = datePickerP.getValue();
    	dateP.add(date);
    	dateListP.setItems(dateP);
    }
    
    /** 
     * 	This method implements the "delete" button under personal section. 
     * 	User select the task and click delete button. Task name and date will be removed from list.
     */
    @FXML
    public void deleteEventP()
    {
    	deleteP.setOnAction(new EventHandler<ActionEvent>()
    	{
    		@Override
    		public void handle(ActionEvent event)
    		{
    			int selectedIndex = ListViewP.getSelectionModel().getSelectedIndex();
    			if (selectedIndex != -1)
    			{
    				ListViewP.getItems().remove(selectedIndex);
    			}
    		} 		
    	});
    }

}
