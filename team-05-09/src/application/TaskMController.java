package application;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	

	
    /**
     * This method implements the "Add" button under School section,
     * User input task name in the text field, then click on "Add" button. The task is added to the list.
     * @param event
     */
    @FXML
    void addS(ActionEvent event) {
    	
    	LocalDate date = datePickerS.getValue();
    	
    	
    	String task = addTaskS.getText();
    	
    	task = task + ": due "+ (date.toString());
    	if(addTaskS.getText() != null && addTaskS.getText().length() > 0) //check if a task has been inputed
    		{
    			schoolTask.add(task);
    			listViewS.setItems(schoolTask);//add task to listView
    			addTaskS.clear(); //clear input for new task
    		}

    }
    
    /**
     * This method implements "Pick Date" function under School, user click on the small calendar icon on the right of the pick date text field,
     * then select the desire date from the calendar. Due date will be added to the list.
     * @param event
     */
    @FXML
    void addDateS(ActionEvent event) {
    	LocalDate date = datePickerS.getValue();
    	dateS.add(date);
    	dateListS.setItems(dateS);
    }
    
    /** 
     * 	This method implements the "delete" button under School section. 
     * 	User first selects the task which need to be deleted, then select the corresponding due date. When both items are selected,
     *  click the "delete" button, the selected task and due date are removed from list.
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
    				dateListS.getItems().remove(selectedIndex);
    			}
    		} 		
    	});
    }
    
    
    /**
     * This method implements the "Add" button under work section,
     * User input task name in the text field, then click on "Add" button. The task is added to the list.
     * @param event
     */
    @FXML
    void addW(ActionEvent event) {
    	LocalDate date = datePickerW.getValue();
    	
    	
    	String task = addTaskW.getText();
    	
    	task = task + ": due "+ (date.toString());
    	if(addTaskW.getText() != null && addTaskW.getText().length() > 0) //check if a task has been inputed
    		{	
    			workTask.add(task);
    			ListViewW.setItems(workTask); //add task to listView 
    			addTaskW.clear(); //clear input for new task
    		}

    }
    
    /**
     * This method implements "Pick Date" function under Work section, user click on the small calendar icon on the right of the pick date text field,
     * then select the desire date from the calendar. Due date will be added to the list.
     * @param event
     */
    @FXML
    void addDateW(ActionEvent event) {
    	LocalDate date = datePickerW.getValue();
    	dateW.add(date);
    	dateListW.setItems(dateW);

    }
    
    /** 
     * 	This method implements the "delete" button under Work section. 
     * 	User first selects the task which need to be deleted, then select the corresponding due date. When both items are selected,
     *  click the "delete" button, the selected task and due date are removed from list.
     */
    @FXML
    public void deleteEventW()
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
    				dateListW.getItems().remove(selectedIndex);
    			}
    		} 		
    	});
    }
    
    
    /**
     * This method implements the "Add" button under Personal section,
     * User input task name in the text field, then click on "Add" button. The task is added to the list.
     * @param event
     */
    @FXML
    void addP(ActionEvent event) {
    	LocalDate date = datePickerP.getValue();
    	
    	
    	String task = addTaskP.getText();
    	
    	task = task + ": due "+ (date.toString());
    	if(addTaskP.getText() != null && addTaskP.getText().length() > 0) //check if a task has been inputed
    		{
    			personalTask.add(task);
    			ListViewP.setItems(personalTask); //add task to listView 
    			addTaskP.clear(); //clear input for new task
    		}

    }
    
    /**
     * This method implements "Pick Date" function under Personal section, user click on the small calendar icon on the right of the pick date text field,
     * then select the desire date from the calendar. Due date will be added to the list.
     * @param event
     */
    @FXML
    void addDateP(ActionEvent event) {
    	LocalDate date = datePickerP.getValue();
    	dateP.add(date);
    	dateListP.setItems(dateP);
    }
    
    /** 
     * 	This method implements the "delete" button under Personal section. 
     * 	User first selects the task which need to be deleted, then select the corresponding due date. When both items are selected,
     *  click the "delete" button, the selected task and due date are removed from list.
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
    				dateListP.getItems().remove(selectedIndex);
    			}
    		} 		
    	});
    }

}
