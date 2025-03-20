import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static String url = "jdbc:mysql://localhost:3306/your_database";
    static String user = "root";
    static String password = "password";
    static Connection con;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n1. Insert\n2. Read\n3. Update\n4. Delete\n5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        insertProduct();
                        break;
                    case 2:
                        readProducts();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 5);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ProductID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter ProductName: ");
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        String query = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setDouble(3, price);
        pstmt.setInt(4, quantity);

        int rows = pstmt.executeUpdate();
        System.out.println(rows + " product inserted.");
    }

    public static void readProducts() throws SQLException {
        String query = "SELECT * FROM Product";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println("ProductID: " + rs.getInt("ProductID") +
                               ", Name: " + rs.getString("ProductName") +
                               ", Price: " + rs.getDouble("Price") +
                               ", Quantity: " + rs.getInt("Quantity"));
        }
    }

    public static void updateProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ProductID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();

        String query = "UPDATE Product SET Price = ? WHERE ProductID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDouble(1, price);
        pstmt.setInt(2, id);

        int rows = pstmt.executeUpdate();
        System.out.println(rows + " product updated.");
    }

    public static void deleteProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ProductID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM Product WHERE ProductID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, id);

        int rows = pstmt.executeUpdate();
        System.out.println(rows + " product deleted.");
    }
}
