import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Calculator {

	// Regex
	private static final String REGEX_NUMBER = "[0-9]+";
	private static final String REGEX_DATE = "^(?:(?:31(\\/|-|\\.||)(?:0?[13578]|1[02]))\\1|" +
			"(?:(?:29|30)(\\/|-|\\.||)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|" +
			"^(?:29(\\/|-|\\.||)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
			"(?:(?:16|[2468][048]|[3579][26])00))))$|" +
			"^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.||)(?:(?:0?[1-9])|" +
			"(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

	// Constants
	private static final ErrorHandler ERROR_HANDLER = ErrorHandler.getInstance();
	private static final List<SimpleDateFormat> DATE_FORMATS = new ArrayList<SimpleDateFormat>(){{
		add(new SimpleDateFormat("ddMMyy"));
		add(new SimpleDateFormat("ddMMyyyy"));
		add(new SimpleDateFormat("dd/MM/yy"));
		add(new SimpleDateFormat("dd/MM/yyyy"));
		add(new SimpleDateFormat("dd.MM.yy"));
		add(new SimpleDateFormat("dd.MM.yyyy"));
	}};
	private static final CalculatorFE display = new CalculatorFE();


	public static void main(String[] args) {
		display.getCalculate().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validateInputs()){
					calculateDate(display.getDaysText(), display.getDateText());

				}
			}
		});

		display.getClean().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				display.initFields();
			}
		});
	}


	/**
	 * Check if all inputs is validate, and prints an appropriate error if not
	 * @return True if all inputs is validate, and and otherwise return false
	 */
	private static boolean validateInputs() {
		if(!isDate(Calculator.display.getDateText())){
			ERROR_HANDLER.setError(Calculator.display.getResult(), ErrorHandler.getNoDateError());
		}
		if(!isNumber(Calculator.display.getDaysText())){
			ERROR_HANDLER.setError(Calculator.display.getResult(), ErrorHandler.getNoNumberError());
		}
		return isNumber(Calculator.display.getDaysText()) && isDate(Calculator.display.getDateText());
	}

	/**
	 * Converts text input to a Date
	 * @param text - String of date
	 * @return Date if the text in one of the date formats, and otherwise return null
	 */
	private static Date getDate(String text) {
		Date date;
        for(SimpleDateFormat format : DATE_FORMATS) {
			try {
				date = format.parse(text);
				return date;
			} catch (ParseException ignored) {}
		}
		return null;
	}

	/**
	 * Converts text input to a Integer
	 * @param text - String of number
	 * @return Integer if the text is a number, and otherwise return null
	 */
	private static Integer getDaysToAdd(String text){
		Integer daysToAdd;
        try{
			daysToAdd = Integer.parseInt(text);
			return  daysToAdd;
		} catch (NumberFormatException ignored){}
		return null;
	}

	/**
	 * Check if the text input is a number
	 * @param text - String
	 * @return True or false
	 */
	private static boolean isNumber(String text) {
		return text.matches(REGEX_NUMBER);
	}

	/**
	 * Check if the text input is a date
	 * @param text - String
	 * @return True or false
	 */
	private static boolean isDate(String text) {
		return text.matches(REGEX_DATE);
	}

	/**
	 * Calculate, print the result and displays the data input in dd/MM/yyyy format
	 */
	private static void calculateDate(String daysInText, String dateInText) {
		Integer daysToAdd = getDaysToAdd(daysInText);
		Date date = getDate(dateInText);
		Calendar cal = GregorianCalendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		display.setDateText(DATE_FORMATS.get(3).format(cal.getTime())); // Set date in dd/MM/yyyy format
		if (daysToAdd != null){
			cal.add(Calendar.DATE, daysToAdd); // Add days to date
		}
		display.setResultText(DATE_FORMATS.get(3).format(cal.getTime())); // Set result in dd/MM/yyyy format
	}
}

