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


public class Main {

	private static final String REGEX_NUMBER = "[0-9]+";
	private static final String REGEX_DATE = "^(?:(?:31(\\/|-|\\.||)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.||)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.||)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.||)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	private static final ErrorHendler ERROR_HENDLER = new ErrorHendler();
	private static int daysToAdd = 0;
	private static Date date = null;
	private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>(){{
		add(new SimpleDateFormat("ddMMyy"));
		add(new SimpleDateFormat("ddMMyyyy"));
		add(new SimpleDateFormat("dd/MM/yy"));
		add(new SimpleDateFormat("dd/MM/yyyy"));
		add(new SimpleDateFormat("dd.MM.yy"));
		add(new SimpleDateFormat("dd.MM.yyyy"));
	}};
	static FrontEnd frontEnd = new FrontEnd();

	public static void main(String[] args) {
		frontEnd.getCalculateButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(validateInputs(frontEnd)){
					calculateDate();
				}
			}
		});

	}

	private static void calculateDate() {
		setDate(frontEnd.getDateField());
		setDaysToAdd(frontEnd.getDaysField());
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		frontEnd.setDateField(dateFormats.get(3).format(cal.getTime()));
		cal.add(Calendar.DATE, daysToAdd);
		frontEnd.setResult(dateFormats.get(3).format(cal.getTime()));
	}

	private static boolean validateInputs(FrontEnd frontEnd ) {
		if(!isNumber(frontEnd.getDaysField())){
			ERROR_HENDLER.setError(frontEnd.getDaysField(), ERROR_HENDLER.getNoNumberError());
		}
		if(!isDate(frontEnd.getDateField())){
			ERROR_HENDLER.setError(frontEnd.getDateField(), ERROR_HENDLER.getNoDateError());
		}
		return isNumber(frontEnd.getDaysField()) && isDate(frontEnd.getDateField());
	}

	private static void setDate(JTextArea jTextArea) {
		for(SimpleDateFormat format : dateFormats) {
			format.setLenient(false);
			try {
				date = format.parse(jTextArea.getText());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (date != null) {
				break;
			}
		}
	}

	private static void setDaysToAdd(JTextArea jTextArea){
		try{
			daysToAdd = Integer.parseInt(jTextArea.getText());
		} catch (NumberFormatException e){

		}
	}

	private static boolean isNumber(JTextArea jTextArea) {
		return jTextArea.getText().matches(REGEX_NUMBER);
	}

	private static boolean isDate(JTextArea jTextArea) {
		return jTextArea.getText().matches(REGEX_DATE);
	}

}

