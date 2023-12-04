import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Student extends Remote {
    public void extract() throws RemoteException;
    public void sort() throws RemoteException;
    public String display() throws RemoteException;
    public void delete() throws RemoteException;
    public String update() throws RemoteException;
}
