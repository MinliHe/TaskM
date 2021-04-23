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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

	@FXML Button editS;

	

	
	
    
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
    			String selected = "";
    			if (selectedIndex != -1)
    			{
    				selected = listViewS.getSelectionModel().getSelectedItem();
    				listViewS.getItems().remove(selected);
    			}	
    			
    			// delete the record from  the database
    	    	dbConnect connect = new dbConnect();
    	    	try {
					connect.deleteRecord(selected);
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
    				connect.deleteRecord(selected);
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
    	
    	// insert the record into the database
    	dbConnect connect = new dbConnect();
    	connect.insertRecord(task,date);
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
					connect.deleteRecord(selected);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		} 		
    	});
    }
    
    private class dbConnect {
    	
    	private static final String url = "jdbc:sqlite:src/application/task.db";
        private static final String INSERT_QUERY = "INSERT INTO taskM(name, date) VALUES (?, ?)";
        //private static final String UPDATE_QUERY = "UPDATE taskM SET name=? WHERE newName=?";
        private static final String DELETE_QUERY = "DELETE FROM taskM WHERE name=?";
        
        public void insertRecord(String name, LocalDate date) throws SQLException {

            // Step 1: Establishing a Connection and 
            // try-with-resource statement will auto close the connection.
            try (Connection connection = DriverManager
                .getConnection(url, name, date.toString());

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, date.toString());

                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                // print SQL exception information
                printSQLException(e);
            }
        }
        
//        public void editRecord(String name) throws SQLException {
//    
//            // Step 1: Establishing a Connection and 
//            // try-with-resource statement will auto close the connection.
//            try (Connection connection = DriverManager
//                .getConnection(url);
//    
//                // Step 2:Create a statement using connection object
//                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
//                preparedStatement.setString(1, name);
//    
//                System.out.println(preparedStatement);
//                // Step 3: Execute the query or update query
//                preparedStatement.executeUpdate();
//            } catch (SQLException e) {
//                // print SQL exception information
//                printSQLException(e);
//            }
//        }
        
      public void deleteRecord(String name) throws SQLException {

          // Step 1: Establishing a Connection and 
          // try-with-resource statement will auto close the connection.
          try (Connection connection = DriverManager
              .getConnection(url);

              // Step 2:Create a statement using connection object
              PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
              preparedStatement.setString(1, name);
              //preparedStatement.setString(2, date.toString());

              System.out.println(preparedStatement);
              // Step 3: Execute the query or update query
              preparedStatement.executeUpdate();
          } catch (SQLException e) {
              // print SQL exception information
              printSQLException(e);
          }
      }
        
        

        public void printSQLException(SQLException ex) {
            for (Throwable e: ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }

}
