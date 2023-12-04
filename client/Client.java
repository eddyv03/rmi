import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private static Student server;

    public static void main(String[] args) {
        try {
            // Connect to the server
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
            server = (Student) registry.lookup("server");

            // Launch the GUI
            java.awt.EventQueue.invokeLater(() -> {
                new ClientGUI(server).setVisible(true);
            });

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
