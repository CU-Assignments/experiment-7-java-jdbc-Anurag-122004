import java.sql.*;

public class EmployeeData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database"; // Replace with your database name
        String user = "root"; // Replace with your MySQL username
        String password = "password"; // Replace with your MySQL password

        try {
            // Load and register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // Create and execute query
            String query = "SELECT * FROM Employee";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Print retrieved data
            while (rs.next()) {
                System.out.println("EmpID: " + rs.getInt("EmpID") + 
                                   ", Name: " + rs.getString("Name") + 
                                   ", Salary: " + rs.getDouble("Salary"));
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
