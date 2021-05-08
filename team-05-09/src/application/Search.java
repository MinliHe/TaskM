//package application;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.util.StringConverter;
//import javafx.util.converter.DefaultStringConverter;
//
//public class Search {
//	//variables for School Section
//    @FXML
//    private TextField taskLabel;
//    
//	@FXML Button deleteS;
//
//	@FXML TextField addTaskS;
//
//	@FXML Button addS;
//	
//
//	@FXML DatePicker datePickerS;
//	@FXML
//    private ObservableList<String> schoolTask = FXCollections.observableArrayList();
//    @FXML
//    private ListView<String> listViewS = new ListView<String>(schoolTask);
//    @FXML
//    private ObservableList<Object> dateS = FXCollections.observableArrayList();
//    @FXML
//    private ListView<Object> dateListS = new javafx.scene.control.ListView<>();
//    @FXML
//    private StringConverter<String> converter = new DefaultStringConverter();
//    
//    
//
//    //variables for Work Section
//	@FXML TextField addTaskW;
//
//	@FXML Button addW;
//
//	@FXML Button deleteW;
//
//	@FXML DatePicker datePickerW;
//	@FXML
//    private ObservableList<String> workTask = FXCollections.observableArrayList();
//	@FXML
//	private ListView<String> ListViewW = new ListView<String>();
//	@FXML
//	private ObservableList<Object> dateW = FXCollections.observableArrayList();
//    @FXML
//    private ListView<Object> dateListW = new javafx.scene.control.ListView<>();
//	
//    
//	
//    //variables for Personal Section
//    @FXML TextField addTaskP;
//    
//	@FXML Button addP;
//	
//	@FXML Button deleteP;
//	
//	@FXML DatePicker datePickerP;
//	@FXML
//    private ObservableList<String> personalTask = FXCollections.observableArrayList();
//	@FXML
//    private ListView<String> ListViewP = new ListView<String>();
//	@FXML
//	private ObservableList<Object> dateP = FXCollections.observableArrayList();
//    @FXML
//    private ListView<Object> dateListP = new javafx.scene.control.ListView<>();
//    @FXML
//	private int selctedIndex;
//    @FXML
//	private String oldValue;
//    @FXML
//    private String newTask;
//
//	@FXML Button editS;
//
//	@FXML TextField searchField;
//	
//	
//    /** 
//     * 	This method implements the search bar. 
//     * 	User search for the task and the task name and date will be shown in the list.
//     */
//    @FXML
//    public void searchTask()
//    {
////    	System.out.println("Search Clicked");
////    	System.out.println(taskLabel.getText());
//  	
//    	
//    	String keyword = searchField.getText();
//  
//        ObservableList<String> taskMatch = FXCollections.observableArrayList();
//      	ListView<String> searchResult = new ListView<String>(taskMatch);
//    	
//    	for(String task: schoolTask) {
//    		//System.out.println("School : " + task);
//    		if(task.contains(keyword)) {
//    			taskMatch.add(task);
//    		}
//    	}
//    	
//    	for(String task: workTask) {
//    		//System.out.println("Work : " + task);
//    		if(task.contains(keyword)) {
//    			taskMatch.add(task);
//    		}
//    	}
//    	
//    	for(String task: personalTask) {
//    		//System.out.println("Personal : " + task);
//    		if(task.contains(keyword)) {
//    			taskMatch.add(task);
//    		}
//
//    	}
//    	
//    	for(String task: taskMatch) {
//    		System.out.println("Search Results Task: " + task);
//    	}
////    	//Wrap the ObservableList in a FilteredList (initially display all data).
////      FilteredList<task> filteredData = new FilteredList<>(dataList, e -> true);
////
////        //Set the filter Predicate whenever the filter changes.
////        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
////        filteredData.setPredicate(Task ->{
////        // If filter text is empty, display all persons.
////        	if(newValue == null || newValue.isEmpty()){
////        		return true;
////        		}
////
////             // Compare first name and last name of every client with filter text.
////             String lowerCaseFilter = newValue.toLowerCase();
////			 
////			if(Task.getName().toLowerCase().contains(lowerCaseFilter)){
////                 return true; //filter matches the name
////             }
////                 return false; //Does not match
////             });
////        });
////        
////        	// 3. Wrap the FilteredList in a SortedList. 
////        	SortedList<task> sortedData = new SortedList<>(filteredData);
////     		
////            //4. put the sorted list into the listview
////     		taskList.setItems(sortedData);
//       
//    }
//
//}
