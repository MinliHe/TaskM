package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
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
	@FXML
	private Button deleteS;
	@FXML 
	private TextField addTaskS;
	@FXML 
	private Button addS;	
	@FXML 
	private Button editS;
	@FXML 	
	private Button saveS;	
	@FXML
	private DatePicker datePickerS;
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
	@FXML
	private TextField addTaskW;
	@FXML
	private Button addW;
	@FXML
	private Button deleteW;	
	@FXML
	private Button editW;
	@FXML
	private Button saveW;
	@FXML
	private DatePicker datePickerW;
	@FXML
    private ObservableList<String> workTask = FXCollections.observableArrayList();
	@FXML
	private ListView<String> ListViewW = new ListView<String>();
	@FXML
	private ObservableList<Object> dateW = FXCollections.observableArrayList();
    @FXML
    private ListView<Object> dateListW = new javafx.scene.control.ListView<>();
	
    
	
    //variables for Personal Section
    @FXML 
    private TextField addTaskP;    
	@FXML 
	private Button addP;	
	@FXML 
	private Button deleteP;	
	@FXML 
	private Button editP;
	@FXML 
	private Button saveP;
	@FXML
	private DatePicker datePickerP;
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


	
	//variables for Search Function
	
	@FXML 
	private TextField searchField;
	@FXML
	private Button searchButton;	
	@FXML
	private ObservableList<String> taskMatch = FXCollections.observableArrayList();	
	@FXML
	private ListView<String> searchResults = new ListView<String>();
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	       Connection conn = null;
	       
	       // Establishing a Connection
	       try {
	           conn = DriverManager.getConnection("jdbc:sqlite:src/application/task.db");
	           System.out.println("Connected");
	       } catch (SQLException e) {
	             System.out.println(e.getMessage());
	       }
	       
	       // Connect to the database and select name from the database and display it
		   listViewS.setItems(schoolTask);
	       Statement stmt = null;
	       ResultSet rs_school = null;
	       
	       try {
	    	   stmt = conn.createStatement();
	    	   rs_school = stmt.executeQuery("SELECT name FROM taskM_school");

	           while (rs_school.next()) {
	               schoolTask.add(rs_school.getString(1));
	               System.out.println("School: " + rs_school.getString(1));
	           }
	           
	       } catch (SQLException e) {
	    	   e.printStackTrace();
	       }
	       
	       // Connect to the database and select name from the database and display it
	       ListViewW.setItems(workTask);
	       Statement stmt_work = null;
	       ResultSet rs_work = null;
	       try {
	    	   stmt_work = conn.createStatement();
	    	   rs_work = stmt_work.executeQuery("SELECT name FROM taskM_work");

	           while (rs_work.next()) {
	               workTask.add(rs_work.getString(1));
	               System.out.println("Work: " + rs_work.getString(1));
	           }
	       } catch (SQLException e) {
	    	   e.printStackTrace();
	       }
		
	       // Connect to the database and select name from the database and display it
	       ListViewP.setItems(personalTask);
	       Statement stmt_personal = null;
	       ResultSet rs_personal = null;
	       try {
	    	   stmt_personal = conn.createStatement();
	    	   rs_personal = stmt_personal.executeQuery("SELECT name FROM taskM_personal");

	           while (rs_personal.next()) {
	               personalTask.add(rs_personal.getString(1));
	               System.out.println("Personal: " + rs_personal.getString(1));
	           }
	       } catch (SQLException e) {
	    	   e.printStackTrace();
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
     * The "Add" button under School section.
     * User input task name in the text field, then pick a due day.
     * Then click on "Add" button. 
     * @param event
     */
    
    @FXML
    void addS(ActionEvent event) throws SQLException {    	
    	 String task = addTaskS.getText();
    	 LocalDate date = datePickerS.getValue();
    	if(date != null && addTaskS.getText() != null && addTaskS.getText().length() > 0) {
    		task = task + ": due "+ (date.toString());
        	if(date.equals(getCurrentDate())) {
        		task= task + ("\u2605") + ("\u2605") + ("\u2605");
        		
        	}
        	int compareUserDate = date.getDayOfMonth();
        	int compareCurrentDate = getCurrentDate().getDayOfMonth();
        	
        	if(compareCurrentDate - compareUserDate == -1) // this means the task will be due tomorrow
        	{
        		task= task + ("\u2605") + ("\u2605");
        	}
        	
        	schoolTask.add(task);
			listViewS.setItems(schoolTask);//add task to listView
			addTaskS.clear(); //clear input for new task
			
			// insert the record into the database
	    	dbConnect connect = new dbConnect();
	    	connect.insertSchoolRecord(task,date);
    	}
    	else
    	{
    		Alert dateError = new Alert(AlertType.ERROR);
    		dateError.setContentText("Please Pick A Due Date.");
    		dateError.show();
    	}

    }  

    /** 
     * 	The "delete" button under School section. 
     * 	User select the task and click delete button. 
     */
    @FXML
     void deleteEventS(ActionEvent event) throws SQLException {
    
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
			e.printStackTrace();
		}
    	
    }
    
    /**
     * THis method allow in-line update of task name and date.
     * User select a task,press edit to enter edit mode and use keyboard to make a change. 
     * press enter to confirm finishing change, and click save button to save to database
     */
    
    @FXML
    void editEventS(ActionEvent event) throws SQLException { 

   	String selected = listViewS.getSelectionModel().getSelectedItem() ;
		listViewS.setEditable(true);
		listViewS.setCellFactory(TextFieldListCell.forListView());	
			dbConnect connect = new dbConnect();
			try {
				connect.deleteSchoolRecord(selected);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		listViewS.setOnEditCommit(commit ->{ 
		listViewS.getItems().set(commit.getIndex(), commit.getNewValue());
	    	  });

   }
   
    @FXML
     void saveEventS(ActionEvent event) throws SQLException { 				
    	String sele = listViewS.getSelectionModel().getSelectedItem();
			// insert the record into the database
        	dbConnect connect = new dbConnect();
        	try {
        		connect.editSchoolRecord(sele,sele);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	listViewS.setEditable(false);

	  }
	 	
    
    /**
     * The "Add" button under work section.
     * User input task name in the text field, then pick a due day.
     * Then click on "Add" button. 
     * @param event
     * @throws SQLException 
     */
    @FXML
    void addW(ActionEvent event) throws SQLException {
    	LocalDate date = datePickerW.getValue();
    	String task = addTaskW.getText();

    	if(date != null && addTaskW.getText() != null && addTaskW.getText().length() > 0) //check if a task has been inputed)
    	{
    		task = task + ": due "+ (date.toString());
        	if(date.equals(getCurrentDate())) {
        		task= task + ("\u2605") + ("\u2605") + ("\u2605");
        	}
        	int compareUserDate = date.getDayOfMonth();
        	int compareCurrentDate = getCurrentDate().getDayOfMonth();
        	
        	if(compareCurrentDate-compareUserDate == -1) // this means the task will be due tomorrow
        	{
        		task= task + ("\u2605") + ("\u2605");
        	}
        	
        	workTask.add(task);
			ListViewW.setItems(workTask); //add task to listView
			addTaskW.clear(); //clear input for new task
			
			// insert the record into the database
	    	dbConnect connect = new dbConnect();
	    	connect.insertWorkRecord(task,date);
        	
    	}
    	else
    	{
    		Alert dateError = new Alert(AlertType.ERROR);
    		dateError.setContentText("Please Pick A Due Date.");
    		dateError.show();
    	}

    }

    /**
     * THis method allow in-line update of task name and date.
     * User select a task,press edit to enter edit mode and use keyboard to make a change. 
     * press enter to confirm finishing change, and click save button to save to database
     */
    
    @FXML
    void editEventW(ActionEvent event) throws SQLException { 

   	String selected = ListViewW.getSelectionModel().getSelectedItem() ;
		ListViewW.setEditable(true);
		ListViewW.setCellFactory(TextFieldListCell.forListView());	
			dbConnect connect = new dbConnect();
			try {
				connect.deleteWorkRecord(selected);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		ListViewW.setOnEditCommit(commit ->{ 
		ListViewW.getItems().set(commit.getIndex(), commit.getNewValue());
	    	  });

   }
   
    @FXML
     void saveEventW(ActionEvent event) throws SQLException { 				
    	String sele = ListViewW.getSelectionModel().getSelectedItem();
			// insert the record into the database
        	dbConnect connect = new dbConnect();
        	try {
        		connect.editWorkRecord(sele,sele);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	ListViewW.setEditable(false);

	  }
	 	
    /** The "delete" button under work section. 
     * 	User select the task and click delete button. 
     * @throws SQLException 
     */
    @FXML
     void deleteEventW(ActionEvent event) throws SQLException{
  
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
			e.printStackTrace();
		}	 	
    }
    
    
    /**
     * The "Add" button under personal section.
     * User input task name in the text field, then pick a due day.
     * Then click on "Add" button. 
     * @param event
     * @throws SQLException 
     */
    @FXML
    void addP(ActionEvent event) throws SQLException {
    	LocalDate date = datePickerP.getValue();
    	String task = addTaskP.getText();
    	
    	if(date != null && addTaskP.getText() != null && addTaskP.getText().length() > 0) 
    	{
    		task = task + ": due "+ (date.toString());
        	if(date.equals(getCurrentDate())) {
        		task= task + ("\u2605") + ("\u2605") + ("\u2605");
        	}
        	int compareUserDate = date.getDayOfMonth();
        	int compareCurrentDate = getCurrentDate().getDayOfMonth();
        	
        	if(compareCurrentDate-compareUserDate == -1) // this means the task will be due tomorrow
        	{
        		task= task + ("\u2605") + ("\u2605");
        	}
        	
        	personalTask.add(task);
			ListViewP.setItems(personalTask); //add task to listView 
			addTaskP.clear(); //clear input for new task
			
			// insert the record into the database
	    	dbConnect connect = new dbConnect();
	    	connect.insertPersonalRecord(task,date);       	
    	}
    	
    	else
    	{
    		Alert dateError = new Alert(AlertType.ERROR);
    		dateError.setContentText("Please Pick A Due Date.");
    		dateError.show();
    	}

    }

    
    /**
     * This method allow in-line update of task name and date.
     * User select a task,press edit to enter edit mode and use keyboard to make a change. 
     * press enter to confirm finishing change, and click save button to save to database
     */
    
    @FXML
    void editEventP(ActionEvent event) throws SQLException { 

   	String selected = ListViewP.getSelectionModel().getSelectedItem() ;
		ListViewP.setEditable(true);
		ListViewP.setCellFactory(TextFieldListCell.forListView());	
			dbConnect connect = new dbConnect();
			try {
				connect.deletePersonalRecord(selected);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		ListViewP.setOnEditCommit(commit ->{ 
		ListViewP.getItems().set(commit.getIndex(), commit.getNewValue());
	    	  });

   }
   
    @FXML
     void saveEventP(ActionEvent event) throws SQLException { 				
    	String sele = ListViewP.getSelectionModel().getSelectedItem();
			// insert the record into the database
        	dbConnect connect = new dbConnect();
        	try {
        		connect.editPersonalRecord(sele,sele);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	ListViewP.setEditable(false);
	  }
	 	
    
    /** 
     * 	The "delete" button under personal section. 
     * 	User select the task and click delete button. 
     */
    @FXML
   void deleteEventP(ActionEvent event) throws SQLException {

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
			e.printStackTrace();
		}
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
    		if(task.length() < keyword.length()) continue;
    		if(task.substring(0,keyword.length()).toLowerCase().equals(keyword.toLowerCase())) {
    			taskMatch.add(task);
    		}
    	}
    	
    	for(String task: workTask) {
    		//System.out.println("Work : " + task);
    		if(task.length() < keyword.length()) continue;
    		if(task.toLowerCase().equals(keyword.toLowerCase())) {
    			taskMatch.add(task);
    		}
    	}
    	
    	for(String task: personalTask) {
    		//System.out.println("Personal : " + task);
    		if(task.substring(0,keyword.length()).length() < keyword.length()) continue;
    		if(task.substring(0,keyword.length()).toLowerCase().equals(keyword.toLowerCase())) {
    			taskMatch.add(task);
    		}

    	}
    	
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchResults.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            PopupSearchController controller = fxmlLoader.getController();
            controller.setSearchResults(taskMatch);
            controller.setKeyWord(searchField.getText());
       
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnCloseRequest( event -> {taskMatch.clear();} );
    		
            
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
       
    }
    

}