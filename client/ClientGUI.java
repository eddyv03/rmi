import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientGUI extends JFrame {
    private Student server;

    public ClientGUI(Student server) {
        this.server = server;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RMI");
        setSize(300, 300);

        JPanel panel = new JPanel();

        JButton extractButton = new JButton("Extract");
        JButton sortButton = new JButton("Sort");
        JButton displayButton = new JButton("Display");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        // Action listeners for each button
        extractButton.addActionListener((ActionEvent e) -> {
            extractButtonActionPerformed(e);
        });

        sortButton.addActionListener((ActionEvent e) -> {
            sortButtonActionPerformed(e);
        });

        displayButton.addActionListener((ActionEvent e) -> {
            displayButtonActionPerformed(e);
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            deleteButtonActionPerformed(e);
        });

        updateButton.addActionListener((ActionEvent e) -> {
            updateButtonActionPerformed(e);
        });

        panel.add(extractButton);
        panel.add(sortButton);
        panel.add(deleteButton);
        panel.add(displayButton);
        panel.add(updateButton);

        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
    }

    private void extractButtonActionPerformed(ActionEvent e) {
        try {
            if (server != null) {
                server.extract();
                System.out.println("Extract button clicked");
            } else {
                System.out.println("Server is null. Cannot invoke 'extract()'.");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sortButtonActionPerformed(ActionEvent e) {
        try {
            if (server != null) {
                server.sort();
                System.out.println("Sort button clicked");
            } else {
                System.out.println("Server is null. Cannot invoke 'sort()'.");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayButtonActionPerformed(ActionEvent e) {
        try {
            if (server != null) {
                String result = server.display();
                System.out.println("Display button clicked");
                System.out.println("Result:\n" + result);
            } else {
                System.out.println("Server is null. Cannot invoke 'display()'.");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteButtonActionPerformed(ActionEvent e) {
        try {
            if (server != null) {
                server.delete();
                System.out.println("Delete button clicked");
            } else {
                System.out.println("Server is null. Cannot invoke 'delete()'.");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateButtonActionPerformed(ActionEvent e) {
        try {
            if (server != null) {
                String result = server.update();
                System.out.println("Update button clicked");
                System.out.println("Result: " + result);
            } else {
                System.out.println("Server is null. Cannot invoke 'update()'.");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> {
        try {
            Student server = new Server();
            new ClientGUI(server).setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    });
}
}
