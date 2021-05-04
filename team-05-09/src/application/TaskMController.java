package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskMController implements Initializable{
	
	//variables for School Section
    @FXML
    private TextField taskLabel;
    
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

	@FXML Button editS;

	
	
	//variables for Search Function
	
	@FXML TextField searchField;

	@FXML
	private Button searchButton;
	
	@FXML
	ObservableList<String> taskMatch = FXCollections.observableArrayList();
	
	@FXML
	private ListView<String> searchResults = new ListView<String>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

//	       Connection conn = null;
//	       
//	       // Establishing a Connection
//	       try {
//	           conn = DriverManager.getConnection("jdbc:sqlite:src/application/task.db");
//	           System.out.println("Connected");
//	       } catch (SQLException e) {
//	             System.out.println(e.getMessage());
//	       }
//	       
//	       // Connect to the database and select name from the database and display it
//		   listViewS.setItems(schoolTask);
//	       Statement stmt = null;
//	       ResultSet rs_school = null;
//	       
//	       try {
//	    	   stmt = conn.createStatement();
//	    	   rs_school = stmt.executeQuery("SELECT name FROM taskM_school");
//
//	           while (rs_school.next()) {
//	               schoolTask.add(rs_school.getString(1));
//	               System.out.println("School: " + rs_school.getString(1));
//	           }
//	           
//	       } catch (SQLException e) {
//	    	   e.printStackTrace();
//	       }
//	       
//	       // Connect to the database and select name from the database and display it
//	       ListViewW.setItems(workTask);
//	       Statement stmt_work = null;
//	       ResultSet rs_work = null;
//	       try {
//	    	   stmt_work = conn.createStatement();
//	    	   rs_work = stmt_work.executeQuery("SELECT name FROM taskM_work");
//
//	           while (rs_work.next()) {
//	               workTask.add(rs_work.getString(1));
//	               System.out.println("Work: " + rs_work.getString(1));
//	           }
//	       } catch (SQLException e) {
//	    	   e.printStackTrace();
//	       }
//		
//	       // Connect to the database and select name from the database and display it
//	       ListViewP.setItems(personalTask);
//	       Statement stmt_personal = null;
//	       ResultSet rs_personal = null;
//	       try {
//	    	   stmt_personal = conn.createStatement();
//	    	   rs_personal = stmt_personal.executeQuery("SELECT name FROM taskM_personal");
//
//	           while (rs_personal.next()) {
//	               personalTask.add(rs_personal.getString(1));
//	               System.out.println("Personal: " + rs_personal.getString(1));
//	           }
//	       } catch (SQLException e) {
//	    	   e.printStackTrace();
//	       }
	}

	public class TaskFieldListCell extends TextFieldListCell<String> {
		

	    @Override
		public void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);
	        setText(item);
	        
	        if(item != null && item.contains("IMPORTANT")) {
	        	//setTextFill(Color.RED);
	        	setText("IMPORTANT TASK DUE TODAY");
	        }
	        else if (item != null && item.contains("TOMORROW")) {
	        	//setTextFill(Color.YELLOW);
	        	setText("IMPORTANT TASK DUE TOMORROW");
	        }
	        
	    }
	}
	
    /**
     * This method gets the current date to compare with the date the user added to see if it is a priority task
     */
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
    void addS(ActionEvent event) throws SQLException {
    	String task = addTaskS.getText();
    	LocalDate date = datePickerS.getValue();
    		
    	if(date != null) {
    		task = task + ": due "+ (date.toString());
        	if(date.equals(getCurrentDate())) {
        		task= task + ("\u2605") + ("\u2605") + ("\u2605")+ "  Hello";
        		
        	}
        	int compareUserDate = date.getDayOfMonth();
        	int compareCurrentDate = getCurrentDate().getDayOfMonth();
        	
        	if(compareCurrentDate-compareUserDate == 1) // this means the task will be due tomorrow
        	{
        		task= task + ("\u2605") + ("\u2605")+ " Pass Due Date";
        	}
        	
   

      
    	}
     	
    	if(addTaskS.getText() != null && addTaskS.getText().length() > 0) //check if a task has been inputed
    		{
    			schoolTask.add(task);
    			listViewS.setItems(schoolTask);//add task to listView
    	        listViewS.setCellFactory(TextFieldListCell.forListView());
    			addTaskS.clear(); //clear input for new task
    			

    	    	// insert the record into the database
//    	    	dbConnect connect = new dbConnect();
//    	    	connect.insertSchoolRecord(task,date);
    		}   	

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
    			String selected = "";
    			if (selectedIndex != -1)
    			{
    				selected = listViewS.getSelectionModel().getSelectedItem();
    				listViewS.getItems().remove(selected);
    			}	
    			
    			// delete the record from  the database
    	    	dbConnect connect = new dbConnect();
    	    	try {
					connect.deleteSchoolRecord(selected);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
     * @throws SQLException 
     */
    @FXML
    void addW(ActionEvent event) throws SQLException {
    	LocalDate date = datePickerW.getValue();
    	String task = addTaskW.getText();
    	
    	
    	if(date != null) {
    		task = task + ": due "+ (date.toString());
        	if(date.equals(getCurrentDate())) {
        		task= task + ("\u2605") + ("\u2605") + ("\u2605")+ " Due Today";
        	}
        	int compareUserDate = date.getDayOfMonth();
        	int compareCurrentDate = getCurrentDate().getDayOfMonth();
        	
        	if(compareCurrentDate-compareUserDate == 1) // this means the task will be due tomorrow
        	{
        		task= task + ("\u2605") + ("\u2605")+ " Pass Due Date";
        	}
        	
    	}
    	
    	if(addTaskW.getText() != null && addTaskW.getText().length() > 0) //check if a task has been inputed
    		{	
    			workTask.add(task);
    			ListViewW.setItems(workTask); //add task to listView
    			ListViewW.setCellFactory(TextFieldListCell.forListView());
    			addTaskW.clear(); //clear input for new task
    			
    	    	// insert the record into the database
//    	    	dbConnect connect = new dbConnect();
//    	    	connect.insertWorkRecord(task,date);
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
    			String selected = "";
    			if (selectedIndex != -1)
    			{
    				selected = ListViewW.getSelectionModel().getSelectedItem();
    				ListViewW.getItems().remove(selected);
    			}
    			
        		// delete the record from  the database
    	    	dbConnect connect = new dbConnect();
    	    	try {
    				connect.deleteWorkRecord(selected);
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
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
        		task= task + ("\u2605") + ("\u2605") + ("\u2605")+ " Due Today";
        	}
        	int compareUserDate = date.getDayOfMonth();
        	int compareCurrentDate = getCurrentDate().getDayOfMonth();
        	
        	if(compareCurrentDate-compareUserDate == 1) // this means the task will be due tomorrow
        	{
        		task= task + ("\u2605") + ("\u2605")+ " Pass Due Date";
        	}
        	
    	}
    	

    	if(addTaskP.getText() != null && addTaskP.getText().length() > 0) //check if a task has been inputed
    		{
    			personalTask.add(task);
    			ListViewP.setItems(personalTask); //add task to listView 
    			ListViewP.setCellFactory(TextFieldListCell.forListView());
    			addTaskP.clear(); //clear input for new task
    			
    	    	// insert the record into the database
//    	    	dbConnect connect = new dbConnect();
//    	    	connect.insertPersonalRecord(task,date);
    		}
    	

    }
    
    @FXML
    void addDateP(ActionEvent event) {
    	LocalDate date = datePickerP.getValue();
    	dateP.add(date);
    	dateListP.setItems(dateP);
    	dateP.clear();
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
    			String selected = "";
    			if (selectedIndex != -1)
    			{
    				selected = ListViewP.getSelectionModel().getSelectedItem();
    				ListViewP.getItems().remove(selected);
    			}
    			
    			// delete the record from  the database
    	    	dbConnect connect = new dbConnect();
    	    	try {
					connect.deletePersonalRecord(selected);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		} 		
    	});
    }
    
    /** 
     * 	This method implements the search bar. 
     * 	User search for the task and the task name and date will be shown in the list.
     */
    @FXML
    public void searchTask()
    {
    	
    	String keyword = searchField.getText();
    	
    	for(String task: schoolTask) {
    		//System.out.println("School : " + task);
    		if(task.contains(keyword)) {
    			taskMatch.add(task);
    		}
    	}
    	
    	for(String task: workTask) {
    		//System.out.println("Work : " + task);
    		if(task.contains(keyword)) {
    			taskMatch.add(task);
    		}
    	}
    	
    	for(String task: personalTask) {
    		//System.out.println("Personal : " + task);
    		if(task.contains(keyword)) {
    			taskMatch.add(task);
    		}

    	}
    	

    	

   

    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchResults.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            PopupSearchController controller = fxmlLoader.getController();
            controller.setSearchResults(taskMatch);
            controller.setKeyWord(searchField.getText());
//            for(String test: taskMatch) {
//            	System.out.println("task matched: " + test);
//            }
           
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
    		
            
    	} catch (Exception e) {
    		e.printStackTrace();
    	}


    	

//    	//Wrap the ObservableList in a FilteredList (initially display all data).
//      FilteredList<task> filteredData = new FilteredList<>(dataList, e -> true);
//
//        //Set the filter Predicate whenever the filter changes.
//        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//        filteredData.setPredicate(Task ->{
//        // If filter text is empty, display all persons.
//        	if(newValue == null || newValue.isEmpty()){
//        		return true;
//        		}
//
//             // Compare first name and last name of every client with filter text.
//             String lowerCaseFilter = newValue.toLowerCase();
//			 
//			if(Task.getName().toLowerCase().contains(lowerCaseFilter)){
//                 return true; //filter matches the name
//             }
//                 return false; //Does not match
//             });
//        });
//        
//        	// 3. Wrap the FilteredList in a SortedList. 
//        	SortedList<task> sortedData = new SortedList<>(filteredData);
//     		
//            //4. put the sorted list into the listview
//     		taskList.setItems(sortedData);
       
    }
    

}