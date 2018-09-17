import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Tast1111 {
	private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 25);

	public static void main(String[] args) {
		JFrame window = new JFrame();

		JPanel panel = new JPanel();
		JPanel daysLine = new JPanel();
		JPanel dateAndInfo = new JPanel();
		JPanel nearDatePanel = new JPanel();
		JPanel dateLine = new JPanel();
		JPanel calculateLine = new JPanel();
		JPanel resultLine = new JPanel();

		JTextArea days = new JTextArea(1, 7);
		JLabel nearDays = new JLabel("היום ה-");

		JLabel info = new JLabel("?");
		JTextArea date = new JTextArea(1, 7);
		JLabel nearDate = new JLabel("מתאריך");

		JButton calculate = new JButton("       חשב       ");

		JLabel result = new JLabel("תוצאה");


		nearDays.setFont(DEFAULT_FONT);
		days.setFont(DEFAULT_FONT);
		nearDate.setFont(DEFAULT_FONT);
		date.setFont(DEFAULT_FONT);
		calculate.setFont(DEFAULT_FONT);
		result.setFont(DEFAULT_FONT);


		window.setLayout(new FlowLayout());
		panel.setLayout(new VerticalLayout(15));
		dateAndInfo.setLayout(new HorizontalLayout(10));
		nearDatePanel.setLayout(new HorizontalLayout());

		daysLine.setLayout(new FlowLayout(2, 25, 25));
		daysLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		daysLine.add(nearDays);
		daysLine.add(days);

		dateAndInfo.add(info);
		dateAndInfo.add(date);

		nearDatePanel.add(nearDate);

		dateLine.setLayout(new FlowLayout(2, 25, 25));
		dateLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		dateLine.add(nearDatePanel);
		dateLine.add(dateAndInfo);

		calculateLine.setLayout(new FlowLayout());
		calculateLine.add(calculate);

		resultLine.setLayout(new FlowLayout());
		resultLine.add(result);

		panel.add(daysLine);
		panel.add(dateLine);
		panel.add(calculateLine);
		panel.add(resultLine);

		window.setLocationRelativeTo(null);
		window.add(panel);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		panel.requestFocus();
	}

}
