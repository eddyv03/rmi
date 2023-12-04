import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class Abstract extends UnicastRemoteObject implements Student {
    protected Abstract() throws RemoteException {
        super();
    }

    @Override
    public void extract() throws RemoteException {
    }

    @Override
    public void sort() throws RemoteException {
    }

    @Override
    public String display() throws RemoteException {
        return null;
    }

    @Override
    public void delete() throws RemoteException {
    }

    @Override
    public String update() throws RemoteException {
        return null;
    }
}
