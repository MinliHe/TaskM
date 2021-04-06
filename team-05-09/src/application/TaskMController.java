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
	

	
    //methods for School section
    @FXML
    void addS(ActionEvent event) {
    	String task = addTaskS.getText();
    	if(addTaskS.getText() != null && addTaskS.getText().length() > 0) //check if a task has been inputed
    		{
    			schoolTask.add(task);
    			listViewS.setItems(schoolTask);//add task to listView
    			addTaskS.clear(); //clear input for new task
    		}

    }
    @FXML
    void addDateS(ActionEvent event) {
    	LocalDate date = datePickerS.getValue();
    	dateS.add(date);
    	dateListS.setItems(dateS);
    }
    
    // This method deletes the selected task from ListView in school section.
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
    
    
    //methods for Work Section
    @FXML
    void addW(ActionEvent event) {
    	String task = addTaskW.getText();
    	if(addTaskW.getText() != null && addTaskW.getText().length() > 0) //check if a task has been inputed
    		{	
    			workTask.add(task);
    			ListViewW.setItems(workTask); //add task to listView 
    			addTaskW.clear(); //clear input for new task
    		}

    }
    @FXML
    void addDateW(ActionEvent event) {
    	LocalDate date = datePickerW.getValue();
    	dateW.add(date);
    	dateListW.setItems(dateW);

    }
    
    // This method deletes the selected task from ListView in work section.
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
    
    
    //Methods for Personal Section
    @FXML
    void addP(ActionEvent event) {
    	String task = addTaskP.getText();
    	if(addTaskP.getText() != null && addTaskP.getText().length() > 0) //check if a task has been inputed
    		{
    			personalTask.add(task);
    			ListViewP.setItems(personalTask); //add task to listView 
    			addTaskP.clear(); //clear input for new task
    		}

    }
    @FXML
    void addDateP(ActionEvent event) {
    	LocalDate date = datePickerP.getValue();
    	dateP.add(date);
    	dateListP.setItems(dateP);
    }
    
    // This method deletes the selected task from ListView in personal section.
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
