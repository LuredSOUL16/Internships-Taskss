import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class EmployeeApp {
    // 🔑 Step 1: Connection details (CHANGE these for your DB)
    static final String DB_URL = "jdbc:mariadb://localhost:3306/company?useSSL=false";
    static final String DB_USER = "root";         // change if needed
    static final String DB_PASSWORD = ""; // change if needed

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("✅ MariaDB driver loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MariaDB driver not found.");
            return;
        }


        Scanner scanner = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("✅ Connected to database.");

            createTableIfNotExists(conn);

            int choice;
            do {
                System.out.println("\n=== Employee Menu ===");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addEmployee(conn, scanner);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateEmployee(conn, scanner);
                    case 4 -> deleteEmployee(conn, scanner);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice.");
                }
            } while (choice != 5);

        } 
        catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
        }
        scanner.close();
    }

    // Step 2: Create table if not exists
    static void createTableIfNotExists(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "name VARCHAR(100), " +
                     "department VARCHAR(100), " +
                     "salary DOUBLE)";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    // Step 3: Add employee
    static void addEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String dept = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.executeUpdate();
            System.out.println("✅ Employee added.");
        }
    }

    // Step 4: View employees
    static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Dept: " + rs.getString("department") +
                                   ", Salary: " + rs.getDouble("salary"));
            }
        }
    }

    // Step 5: Update employee
    static void updateEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new department: ");
        String dept = scanner.nextLine();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();

        String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✅ Employee updated.");
            else System.out.println("❌ Employee not found.");
        }
    }

    // Step 6: Delete employee
    static void deleteEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✅ Employee deleted.");
            else System.out.println("❌ Employee not found.");
        }
    }
}
