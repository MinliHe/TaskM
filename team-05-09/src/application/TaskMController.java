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
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class TaskMController {
	
	//variables for School Section
    @FXML
    private TextField searchTask;
    
	@FXML Button deleteS;

	@FXML TextField addTaskS;

	@FXML Button addS;

	@FXML DatePicker datePickerS;
	@FXML
    private ObservableList<String> schoolTask = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listViewS = new ListView<String>(schoolTask);
    @FXML
    private ObservableList<Object> dateS = FXCollections.observableArrayList();
    @FXML
    private ListView<Object> dateListS = new javafx.scene.control.ListView<>();
    @FXML
    private StringConverter<String> converter = new DefaultStringConverter();
    
    

    //variables for Work Section
	@FXML TextField addTaskW;

	@FXML Button addW;

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
    @FXML
	private int selctedIndex;
    @FXML
	private String oldValue;
    @FXML
    private String newTask;

	
	
    
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
     */
    @FXML
    void addS(ActionEvent event) 
    {
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
    			listViewS.setCellFactory(TextFieldListCell.forListView());
    			addTaskS.clear(); //clear input for new task
    		}   	
    }  
    
    @FXML
    void addDateS(ActionEvent event) 
    {
    	
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
     * THis method allow in-line update of task name and date.
     * User double click on item, and use keyboard to make a change. 
     * When done, press return button.
     */
    @FXML
    public void editEventS()
    {	
    	listViewS.setOnEditStart(start -> {
    		System.out.println("Edit Start");
    	});
    	listViewS.setOnEditCommit(commit -> {
    		listViewS.getItems().set(commit.getIndex(), commit.getNewValue());
    	});
    	listViewS.setOnEditCancel(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> event) {
				// TODO Auto-generated method stub				
			} 		
    	});   	
    }
    
    /**
     * This method implements the "Add" button under work section.
     * User input task name in the text field, then pick a due day.
     * Then click on "Add" button. The task and due date are shown to the list.
     * @param event
     */
    @FXML
    void addW(ActionEvent event) {
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
    			ListViewW.setCellFactory(TextFieldListCell.forListView());
    			addTaskW.clear(); //clear input for new task
    		}

    }
    
    
    @FXML
    void addDateW(ActionEvent event) {
    	LocalDate date = datePickerW.getValue();
    	dateW.add(date);
    	dateListW.setItems(dateW);

    }
    
    /**
     * THis method allow in-line update of task name and date.
     * User double click on item, and use keyboard to make a change. When done, press
     * return button.
     */
    @FXML
    public void editEventW()
    {	
    	ListViewW.setOnEditStart(start -> {
    		System.out.println("Edit Start");
    	});
    	ListViewW.setOnEditCommit(commit -> {
    		listViewS.getItems().set(commit.getIndex(), commit.getNewValue());
    	});
    	ListViewW.setOnEditCancel(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> event) {
				// TODO Auto-generated method stub				
			} 		
    	});   	
    }
    
    /** 
     * 	This method implements the "delete" button under work section. 
     * 	User select the task and click delete button. Task name and date will be removed from list.
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
    			}
    		} 		
    	});
    }
    
    
    /**
     * This method implements the "Add" button under personal section.
     * User input task name in the text field, then pick a due day.
     * Then click on "Add" button. The task and due date are shown to the list.
     * @param event
     */
    @FXML
    void addP(ActionEvent event) {
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
    			ListViewP.setCellFactory(TextFieldListCell.forListView());
    			addTaskP.clear(); //clear input for new task
    		}
    }
    
    @FXML
    void addDateP(ActionEvent event) {
    	LocalDate date = datePickerP.getValue();
    	dateP.add(date);
    	dateListP.setItems(dateP);
    }
    
    /**
     * THis method allow in-line update of task name and date.
     * User double click on item, and use keyboard to make a change. When done, press
     * return button.
     */
    @FXML
    public void editEventP()
    {	
    	ListViewP.setOnEditStart(start -> {
    		System.out.println("Edit Start");
    	});
    	ListViewP.setOnEditCommit(commit -> {
    		ListViewP.getItems().set(commit.getIndex(), commit.getNewValue());
    	});
    	ListViewP.setOnEditCancel(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> event) {
				// TODO Auto-generated method stub				
			} 		
    	});   	
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
