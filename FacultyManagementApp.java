package com.example;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



class Faculty {
    private int id;
    private String name;
    private String department;
    private String email;
    private String phone;

    public Faculty() {}

    public Faculty(int id, String name, String department, String email, String phone) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Faculty [ID=" + id + ", Name=" + name + ", Department=" + department + ", Email=" + email + ", Phone=" + phone + "]";
    }
}

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/faculty_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Varshini@30";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
            throw e;
        }
        return connection;
    }
}

class FacultyDAO {
    public void addFaculty(Faculty faculty) {
        String query = "INSERT INTO faculty (name, department, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, faculty.getName());
            stmt.setString(2, faculty.getDepartment());
            stmt.setString(3, faculty.getEmail());
            stmt.setString(4, faculty.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Faculty> getAllFaculty() {
        List<Faculty> facultyList = new ArrayList<>();
        String query = "SELECT * FROM faculty";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Faculty faculty = new Faculty(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                facultyList.add(faculty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultyList;
    }

    public void updateFaculty(Faculty faculty) {
        String query = "UPDATE faculty SET name = ?, department = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, faculty.getName());
            stmt.setString(2, faculty.getDepartment());
            stmt.setString(3, faculty.getEmail());
            stmt.setString(4, faculty.getPhone());
            stmt.setInt(5, faculty.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFaculty(int id) {
        String query = "DELETE FROM faculty WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




public class FacultyManagementApp {
    public static void main(String[] args) {
        FacultyDAO facultyDAO = new FacultyDAO();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nFaculty Management System");
            System.out.println("1. Add Faculty");
            System.out.println("2. View All Faculty");
            System.out.println("3. Update Faculty");
            System.out.println("4. Delete Faculty");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter department: ");
                    String department = scanner.next();
                    System.out.print("Enter email: ");
                    String email = scanner.next();
                    System.out.print("Enter phone: ");
                    String phone = scanner.next();

                    Faculty faculty = new Faculty();
                    faculty.setName(name);
                    faculty.setDepartment(department);
                    faculty.setEmail(email);
                    faculty.setPhone(phone);

                    facultyDAO.addFaculty(faculty);
                    System.out.println("Faculty added successfully!");
                }
                case 2 -> {
                    List<Faculty> facultyList = facultyDAO.getAllFaculty();
                    facultyList.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter faculty ID to update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter new name: ");
                    String name = scanner.next();
                    System.out.print("Enter new department: ");
                    String department = scanner.next();
                    System.out.print("Enter new email: ");
                    String email = scanner.next();
                    System.out.print("Enter new phone: ");
                    String phone = scanner.next();

                    Faculty faculty = new Faculty(id, name, department, email, phone);
                    facultyDAO.updateFaculty(faculty);
                    System.out.println("Faculty updated successfully!");
                }
                case 4 -> {
                    System.out.print("Enter faculty ID to delete: ");
                    int id = scanner.nextInt();
                    facultyDAO.deleteFaculty(id);
                    System.out.println("Faculty deleted successfully!");
                }
                case 5 -> System.out.println("Exiting the system...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}

