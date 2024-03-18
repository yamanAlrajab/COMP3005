//Mohammed Yaman Alrajab 101222347
package org.example;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        //getAllStudents();
        //addStudent("Yaman", "Alrajab", "new@example.com", new Date(2020, 3, 1));
        //deleteStudent(1);
        //updateStudentEmail(3, "update@gmail.com");


    }
    public class jdbc {
        private static final String url = "jdbc:postgresql://localhost:5432/Assignment3q1";
        private static final String user = "postgres";
        static final String password = "Yaman2003!";

        /**
         * This method is used to establish a connection to the PostgreSQL database.
         * It uses the DriverManager's getConnection method to establish a connection using the database URL, username, and password.
         * and it returns a Connection object which is used by the calling funciton to execute SQL queries.
         */
        public static Connection connect() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }
    }

    // Function to fetch all students' information from the database
    public static void getAllStudents() {
        System.out.println("Printing all students:");
        try (Connection connection = jdbc.connect();) {
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM students");
            ResultSet resultSet = s.getResultSet();
            for (; resultSet.next();) {
                System.out.printf("%-10s%-15s%-15s%-30s%-15s%n", resultSet.getString("student_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getDate("enrollment_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("getAllStudents function completed");
    }

    // Method to add a new student to the database
    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date) {
        System.out.println("Adding Function called");
        try (Connection connection = jdbc.connect();) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, email);
            ps.setDate(4, enrollment_date);
            ps.executeUpdate();
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM students");
            ResultSet resultSet = s.getResultSet();
            for (; resultSet.next();) {
                System.out.printf("%-10s%-15s%-15s%-30s%-15s%n", resultSet.getString("student_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getDate("enrollment_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("addStudent function completed");
    }

    // Method to update a student's email in the database
    public static void updateStudentEmail(int student_id, String email) {
        System.out.println("Update Function called");
        try (Connection connection = jdbc.connect();) {
            PreparedStatement ps = connection.prepareStatement("UPDATE students SET email = ? WHERE student_id = ?;");
            ps.setString(1, email);
            ps.setInt(2, student_id);
            ps.executeUpdate();
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM students");
            ResultSet resultSet = s.getResultSet();
            for (; resultSet.next();) {
                System.out.printf("%-10s%-15s%-15s%-30s%-15s%n", resultSet.getString("student_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getDate("enrollment_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("updateStudentEmail function completed");
    }

    // Method to delete a student from the database
    public static void deleteStudent(int student_id) {
        System.out.println("Delete Function called");
        try (Connection connection = jdbc.connect();) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM students WHERE student_id = ?;");
            ps.setInt(1, student_id);
            ps.executeUpdate();
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM students");
            ResultSet resultSet = s.getResultSet();
            for (; resultSet.next();) {
                System.out.printf("%-10s%-15s%-15s%-30s%-15s%n", resultSet.getString("student_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getDate("enrollment_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("deleteStudent function completed");
    }
}
