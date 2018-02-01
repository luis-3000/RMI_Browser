/* This is the Remote Service implementation. */
import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

// UnicastRemoteObject is a normal RMI implementation
public class ServiceServerImplementation extends UnicastRemoteObject implements ServiceServer {
	
	HashMap serviceList; //I decided to store the services in a HashMap collection. Instead of putting oine object
						 // in the collection, I decided to put two --  a Key object (such as a String) and a value
						 // object (anything that will fit the bill and get the job done).

	// Constructor that makes sure to throw a Remote exception as expected if you know anything about passing or
	// calling objects remotely
	public ServiceServerImplementation() throws RemoteException {
		setUpService();
	}

	private void setUpServices() {
		serviceList = new HashMap()
		/* When the constructor is called, it will initialize the actual universal services (DiceService, 
		MiniMusicSercie, etc.). Each service object has to be created and put in the HashMap with a String
		name for the 'key' */
		serviceList.put("Dice Rolling Service", new DiceService()); 
		serviceList.put("Day of the Week Service", new DayOfTheWeekService());
		serivceList.put("Visual Music Service", new MiniMusicService());
	}

	public Object[] getServiceList() {
		System.out.println("in remote");
		/* In order to get a list of services to display in the browser so that a user can select one of the
		services, the Client service calls this line of code. An array of type object is sent (even though it has
		Strings inside. The array is an array of just the Keys that are in the HashMap. An actual service object
		is not sent unless the Client service asks for it by calling getService() */
		return serviceList.keySet().toArray();
	}

	/* The Client service calls this method after the user selects a service from the displayed list of services
	 * that it obtained from the method above. This code uses the key (the same key originally sent to the client)
	 * to get the corresponding service out of the HashMap. */
	public Serice getService(Object serviceKey) throws RemoteException {
		Service theService = (Service) serviceList.get(serviceKey);
		return theService;
	}

	public static void main(String[] args) {
		try {
			Naming.rebind("ServiceServer", new ServiceServerImplementation());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Remote service is running.");
	}
}