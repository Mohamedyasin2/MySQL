package jdbc_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbc_task1 {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/jdbc_schema";
        String username = "root";
        String password = "yasin1986";
        
        // SQL query to insert data
        String insertQuery = "INSERT INTO employee (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            
            // Insert multiple records
            insertEmployee(preparedStatement, 101, "Jenny", 25, 10000);
            insertEmployee(preparedStatement, 102, "Jacky", 30, 20000);
            insertEmployee(preparedStatement, 103, "Joe", 20, 40000);
            insertEmployee(preparedStatement, 104, "Jhon", 40, 80000);
            insertEmployee(preparedStatement, 105, "Shammer", 25, 90000);

            // Close resources
            preparedStatement.close();
            connection.close();

            System.out.println("All data inserted successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error!");
            e.printStackTrace();
        }
    }

    // Helper method to insert employee data
    private static void insertEmployee(PreparedStatement preparedStatement, int empcode, String empname, int empage, int esalary) throws SQLException {
        preparedStatement.setInt(1, empcode);
        preparedStatement.setString(2, empname);
        preparedStatement.setInt(3, empage);
        preparedStatement.setInt(4, esalary);
        preparedStatement.executeUpdate();  // Execute the insert query after setting values
    }
}
