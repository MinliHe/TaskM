package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class dbConnect {

	private static final String url = "jdbc:sqlite:src/application/task.db";
    private static final String INSERTschool_QUERY = "INSERT INTO taskM_school(name, date) VALUES (?, ?)";
    private static final String INSERTwork_QUERY = "INSERT INTO taskM_work(name, date) VALUES (?, ?)";
    private static final String INSERTpersonal_QUERY = "INSERT INTO taskM_personal(name, date) VALUES (?, ?)";
    //private static final String UPDATE_QUERY = "UPDATE taskM SET name=? WHERE newName=?";
    private static final String DELETEschool_QUERY = "DELETE FROM taskM_school WHERE name=?";
    private static final String DELETEwork_QUERY = "DELETE FROM taskM_work WHERE name=?";
    private static final String DELETEpersonal_QUERY = "DELETE FROM taskM_personal WHERE name=?";
    private static final String SELECTschool_QUERY = "SELECT name FROM taskM_school";
    private static final String SELECTwork_QUERY = "SELECT name FROM taskM_work";
    private static final String SELECTpersonal_QUERY = "SELECT name FROM taskM_personal";
    
    
    // insert the school record into the database
    public void insertSchoolRecord(String name, LocalDate date) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
            .getConnection(url, name, date.toString());

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTschool_QUERY)) {
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
    
    // insert the work record into the database
    public void insertWorkRecord(String name, LocalDate date) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
            .getConnection(url, name, date.toString());

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTwork_QUERY)) {
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
    
    
    // insert the personal record into the database
    public void insertPersonalRecord(String name, LocalDate date) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
            .getConnection(url, name, date.toString());

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTpersonal_QUERY)) {
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
    
//    public void editRecord(String name) throws SQLException {
//
//        // Step 1: Establishing a Connection and 
//        // try-with-resource statement will auto close the connection.
//        try (Connection connection = DriverManager
//            .getConnection(url);
//
//            // Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
//            preparedStatement.setString(1, name);
//
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            // print SQL exception information
//            printSQLException(e);
//        }
//    }
  
    
    // delete the school record from the database
    public void deleteSchoolRecord(String name) throws SQLException {

      // Step 1: Establishing a Connection and 
      // try-with-resource statement will auto close the connection.
      try (Connection connection = DriverManager
          .getConnection(url);

          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(DELETEschool_QUERY)) {
          preparedStatement.setString(1, name);

          System.out.println(preparedStatement);
          // Step 3: Execute the query or update query
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          // print SQL exception information
          printSQLException(e);
      }
  }
  
    // delete the work record from the database
    public void deleteWorkRecord(String name) throws SQLException {

      // Step 1: Establishing a Connection and 
      // try-with-resource statement will auto close the connection.
      try (Connection connection = DriverManager
          .getConnection(url);

          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(DELETEwork_QUERY)) {
          preparedStatement.setString(1, name);

          System.out.println(preparedStatement);
          // Step 3: Execute the query or update query
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          // print SQL exception information
          printSQLException(e);
      }
  }
    
    // delete the personal record from the database
    public void deletePersonalRecord(String name) throws SQLException {

      // Step 1: Establishing a Connection and 
      // try-with-resource statement will auto close the connection.
      try (Connection connection = DriverManager
          .getConnection(url);

          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(DELETEpersonal_QUERY)) {
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
  
    // select the school record from the database
    public void selectSchoolRecord(String name) throws SQLException {

      // Step 1: Establishing a Connection and 
      // try-with-resource statement will auto close the connection.
      try (Connection connection = DriverManager
          .getConnection(url);

          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(SELECTschool_QUERY)) {
          preparedStatement.setString(1, name);

          System.out.println(preparedStatement);
          // Step 3: Execute the query or update query
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          // print SQL exception information
          printSQLException(e);
      }
  }
  
    // select the work record from the database
    public void selectWorkRecord(String name) throws SQLException {

      // Step 1: Establishing a Connection and 
      // try-with-resource statement will auto close the connection.
      try (Connection connection = DriverManager
          .getConnection(url);

          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(SELECTwork_QUERY)) {
          preparedStatement.setString(1, name);

          System.out.println(preparedStatement);
          // Step 3: Execute the query or update query
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          // print SQL exception information
          printSQLException(e);
      }
  }
  
    // select the personal record from the database
    public void selectPersonalRecord(String name) throws SQLException {

      // Step 1: Establishing a Connection and 
      // try-with-resource statement will auto close the connection.
      try (Connection connection = DriverManager
          .getConnection(url);

          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(SELECTpersonal_QUERY)) {
          preparedStatement.setString(1, name);

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
