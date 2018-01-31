import java.swing.*;
import java.io.*;

/* This is a plain old, non-remote interface, that contained the one method that any universal
service must have the getGuiPanel() method. This interface extends Serializable, so that any class
implementing the Service interface will automatically be Serializable.

That's necessary because the services get shipped over the wire from the server, as a result of the
client calling getService()  on the remote ServiceServer.  */
public interface Service extends Serializable {
	public JPanel getGuiPanel();
	}
}