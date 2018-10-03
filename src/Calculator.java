import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;


public class Calculator {

	// Regex
	private static final String REGEX_NUMBER = "[0-9]+";
	private static final String REGEX_DATE = "^(?:(?:31(\\/|-|\\.||)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.||)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.||)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.||)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

	// Constants
	private static final ErrorHandler ERROR_HANDLER = new ErrorHandler();
	private static final List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>(){{
		add(new SimpleDateFormat("ddMMyy"));
		add(new SimpleDateFormat("ddMMyyyy"));
		add(new SimpleDateFormat("dd/MM/yy"));
		add(new SimpleDateFormat("dd/MM/yyyy"));
		add(new SimpleDateFormat("dd.MM.yy"));
		add(new SimpleDateFormat("dd.MM.yyyy"));
	}};
	private static final CalculatorFE DISPLAY = new CalculatorFE();


	public static void main(String[] args) {

		DISPLAY.getCalculate().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(validateInputs(DISPLAY)){
					calculateDate(DISPLAY.getDaysText(), DISPLAY.getDateText());

				}
			}
		});
	}

	/**
	 * Check if all inputs is validate, and prints an appropriate error if not
	 * @param frontEnd
	 * @return True if all inputs is validate, and and otherwise return false
	 */
	private static boolean validateInputs(CalculatorFE frontEnd ) {
		if(!isDate(frontEnd.getDateText())){
			ERROR_HANDLER.setError(frontEnd.getResult(), ERROR_HANDLER.getNoDateError());
		}
		if(!isNumber(frontEnd.getDaysText())){
			ERROR_HANDLER.setError(frontEnd.getResult(), ERROR_HANDLER.getNoNumberError());
		}
		return isNumber(frontEnd.getDaysText()) && isDate(frontEnd.getDateText());
	}

	/**
	 * Converts text input to a Date
	 * @param text
	 * @return Date if the text in one of the date formats, and otherwise return null
	 */
	private static Date getDate(String text) {
		Date date = null;
		for(SimpleDateFormat format : dateFormats) {
			try {
				date = format.parse(text);
				return date;
			} catch (ParseException e) {}
		}
		return date;
	}

	/**
	 * Converts text input to a Integer
	 * @param text
	 * @return Integer if the text is a number, and otherwise return null
	 */
	private static Integer getDaysToAdd(String text){
		Integer daysToAdd = null;
		try{
			daysToAdd = Integer.parseInt(text);
			return  daysToAdd;
		} catch (NumberFormatException e){}
		return  daysToAdd;
	}

	/**
	 * Check if the text input is a number
	 * @param text
	 * @return True or false
	 */
	private static boolean isNumber(String text) {
		return text.matches(REGEX_NUMBER);
	}

	/**
	 * Check if the text input is a date
	 * @param text
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
		cal.setTime(date);
		DISPLAY.setDateText(dateFormats.get(3).format(cal.getTime())); // Set date in dd/MM/yyyy format
		cal.add(Calendar.DATE, daysToAdd); // Add days to date
		DISPLAY.setResultText(dateFormats.get(3).format(cal.getTime())); // Set result in dd/MM/yyyy format
	}
}

