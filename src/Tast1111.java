import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tast1111 {
	public static void main(String[] args) {
		String input = "1.117";

		System.out.println(isDate(input));
	}

	static boolean isDate(String input){
		Date date = new Date();
		DateFormat ddMMyyyyDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			date = ddMMyyyyDateFormat.parse(input);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}
