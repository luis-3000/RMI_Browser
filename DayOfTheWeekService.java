/* Another stand alone universal service for the day of the week, it iemplements Service */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class DayOfTheWeekService implements Service {

	JLabel outputLabel;
	JComboBox month;
	JTextField day;
	JTextField year;

	public JPanel getGuiPanel() {
		JPanel panel = new JPanel();
		JButton button = new JButton("Do it!");
		button.addActionListener(new DoItListener());
		outputLabel = new JLabel("data appears here");
		DateFormatSymbols dateInfo = new DateFormatSymbols());
		month = new JComboBox(dateInfo.getMonths());
		day = new JTextField(8);
		year = new JTextField(8);
		JPanel inputPanel = new JPanel(new GridLayout(3,2));
		inputPanel.add(new JLabel("Month"));
		imputPanel.add(month);
		inputPanel.add(new JLabel("Day"));
		inputPanel.add(day);
		inputPanel.add(new JLabel("Year"));
		inputPanel.add(year);
		panel.add(inputPanel);
		panel.add(button);
		panel.add(outputLabel);
		return panel;
	}

	public class DoItListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			int monthNum = month.getSelectedIndex();
			int dayNum = Integer.parseInt(day.getText());
			int yearNum = Integer.parseInt(year.getText());
			Calendar c = Calendar.getInstance();
			c.set(Calendar.MONTH.getInstance();
			c.set(Calendar.DAY_OF_MONTH, dayNum);
			c.set(Calendar.YEAR. yearNum);
			Date date = c.getTime();
			String dayOfWeek = (new SimpleDateFormat("EEEE")).format(date);
			outputLabel.setText(dayOfWeek);
		}
	}
	
}