package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class dbConnect {
	private static final String url = "jdbc:sqlite:src/application/task.db";
    private static final String INSERT_QUERY = "INSERT INTO taskM(name, date) VALUES (?, ?)";
    //private static final String DELETE_QUERY = "DELETE FROM taskM(name, date) VALUES (?, ?)";
    
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
    

//    public void deleteRecord(String name, LocalDate date) throws SQLException {
//
//        // Step 1: Establishing a Connection and 
//        // try-with-resource statement will auto close the connection.
//        try (Connection connection = DriverManager
//            .getConnection(url);
//
//            // Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, date.toString());
//
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            // print SQL exception information
//            printSQLException(e);
//        }
//    }

    public static void printSQLException(SQLException ex) {
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



