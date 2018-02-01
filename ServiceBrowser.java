/* This is the actual Client Service. */
import java.awt.*;
import javax.swing.*;
import java.rmi.*;
import java.awt.event.*;

public class ServiceBrowser {
	JPanel mainPanel;
	JComboBox serviceList;
	ServiceServer server;

	public void buildGUI {
		JFrame frame = new JFrame("RMI Browser");
		mainPanel = new JPanel();
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

		Object[] services = getServicesList(); // This method does the RMI registry lookup, gets the stub, and
							      			   // calls getServiceList(). (The actual method is further down below)

		serviceList = new JComboBox(serivices); // Here, I add the services (an array of Objects) to the JComboBox
												// (the list). The JComboBox knows how to make displayable Strings
												// out of each thing in the array.

		frame.getContentPane().add(BorderLayout.NORTH, serviceList);

		serviceList.addActionListener(new MyListListener());

		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	void loadService(Object serviceSelection) {
		try {
			Service svc = server.getService(serviceSelection);

			/* Here's where the actual service is added to the GUI, after the user has seleted one. (This method
			   is called by the event listener on the JComboBos). The method getService() is called on the remote
			   server (the stub for ServiceServer) and is passed the String that was displayed in the list (which
			   is the SAME String we originally got from the server when we called getServiceList()). The server
			   returns the actual service (serialized), which automatically deserialized (thanks to RMI) and we 
			   simply call the getGuidPanel() on the service and add the result (a JPanel) to the browser's 
			   mainPanel. */
			mainPanel.removeAll();
			mainPanel.add(svc.getGuiPanel());
			mainPanel.validate();
			mainPanel.repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	Object[] getServicesList() {
		
	}
}