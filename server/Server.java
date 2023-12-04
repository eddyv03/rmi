import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Server extends Abstract {

    protected Server() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            
            Server dimple = new Server();
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            Registry registry = LocateRegistry.createRegistry(9100);
            registry.bind("server", dimple);

            System.out.println("Server is listening to server 9100...");
            System.out.println("Server started press Enter to exit");
            try (Scanner s = new Scanner(System.in)) {
                s.nextLine();
            }
            registry.unbind("server");
            UnicastRemoteObject.unexportObject(dimple, true);
            System.out.println("Server has stop");

        } catch (Exception e) {
            System.out.println("Some server error..." + e);
        }
    }

    @Override
    public void extract() throws RemoteException {
        XMLParser parser = new XMLParser();
        parser.parse();
    }
    
    @Override
    public void sort() throws RemoteException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RMI", "root", "edvincent123*");

            String sort = "SELECT * FROM students ORDER BY name";
            PreparedStatement prep = con.prepareStatement(sort);
            ResultSet resultSet = prep.executeQuery(sort);

            StringBuilder sortedData = new StringBuilder();
            while (resultSet.next()) {
                String rowData = resultSet.getString("name");
                sortedData.append(rowData).append("\n");
            }

            resultSet.close();
            prep.close();
            System.out.println("Sorted data:\n" + sortedData.toString());
        } catch (SQLException e) {
            System.out.println("Error sorting data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public String display() throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RMI", "root", "edvincent123*");
            String display = "select * from students";
            PreparedStatement prep = con.prepareStatement(display);
            ResultSet rs = prep.executeQuery();
            
            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String contact = rs.getString("contact");
                
                result.append("ID: ").append(id)
                        .append(", Name: ").append(name)
                        .append(", Age: ").append(age)
                        .append(", Address: ").append(address)
                        .append(", Contact: ").append(contact)
                        .append("\n");
            }
            
            return result.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }


    @Override
    public void delete() throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RMI", "root", "edvincent123*");
            
            Scanner s = new Scanner(System.in);
            System.out.print("Enter the ID to delete: ");
            int id = s.nextInt();
            
            String delete = "DELETE FROM students WHERE id = ?";
            PreparedStatement prep = con.prepareStatement(delete);
            prep.setInt(1, id);
            prep.executeUpdate();
            prep.close();
            
            System.out.println("Data deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String update() throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter to update student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter to update student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter to update student address: ");
        String address = scanner.nextLine();

        System.out.print("Enter to update student contact: ");
        String contact = scanner.nextLine();

        scanner.close();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RMI", "root", "edvincent123*");
            String update = "UPDATE students SET name=?, age=?, address=?, contact=? WHERE id=?";
            PreparedStatement prep = con.prepareStatement(update);
            prep.setString(1, name);
            prep.setInt(2, age);
            prep.setString(3, address);
            prep.setString(4, contact);
            prep.setInt(5, id);
            prep.executeUpdate();
            prep.close();
            return "Record Updated Successfully";
        } catch (SQLException e) {
            return "Error updating record: " + e.getMessage();
        } catch (ClassNotFoundException e) {
            return e.toString();
        }
    }

}




