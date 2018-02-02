/* This is the DiceSerice, a universal service, which in turn implements Service */
import javax.swing.*;
import java.awtlevent.*;
import java.io.*;

public class DiceService implements Service {
	JLabel label;
	JComboBo numOfDice;

	/* This is the method of the Service interface -- the one the Client Service is going to call when this
	   service is selected and loaded. As a developer I can do whatever I want in the getGuidPanel() method, as
	   long as I return a JPanel, so it builds the actual dice-rolling GUI. */
	public JPanel getGuiPanel() {
		JPanel panel = new JPanel();
		JButton button = new JButton("Roll 'em!");
		String[] choices = {"1", "2", "3", "4", "5"};
		numOfDice = new JComboBox(choices);
		label = new JLabel("dice values here");
		button.addActionListener(new RoolEmListener()));
		panel.add(numOfDice);
		panel.add(button);
		panel.add(label);
		return panel;
	}

	public class RollEmListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// roll the dice
			String diceOutput = "";
			String selection = (String) numOfDice.getSelectedItem();
			int numOfDiceToRoll = Integer.parseInt(selection);
			for(int i = 0; i < numOfDiceToRoll; i++) {
				int r = (int) ((Math.random() * 6) + 1);
				diceOutput += (" " + r);
			}
			label.setText(diceOutput);
		}
	}
}