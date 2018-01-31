import java.rmi.*;

/* This is a normal RMI interface, where the two methods the remote service will have are defined. */
public interface ServiceServer extends Remote {
	Object[] getServiceList() throws RemoteException;
	Service getService(Object serviceKey) throws RemoteException;
}