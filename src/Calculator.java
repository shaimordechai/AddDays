import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
    private static final List<SimpleDateFormat> DATE_FORMATS = new ArrayList<>() {{
        add(new SimpleDateFormat("ddMMyy"));
        add(new SimpleDateFormat("ddMMyyyy"));
        add(new SimpleDateFormat("dd/MM/yy"));
        add(new SimpleDateFormat("dd/MM/yyyy"));
        add(new SimpleDateFormat("dd.MM.yy"));
        add(new SimpleDateFormat("dd.MM.yyyy"));
    }};
    private static final SimpleDateFormat SLASH_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private final CalculatorFE display = new CalculatorFE();
    private final ErrorHandler errorHandler = ErrorHandler.getInstance();


    public static void main(String[] args) {
        final Calculator calculator = new Calculator();
        calculator.display.getCalculate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (calculator.validateInputs()) {
                    calculator.calculateDate(calculator.display.getDaysText(), calculator.display.getDateText());
                }
            }
        });

        calculator.display.getClean().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.display.initFields();
            }
        });
    }


    /**
     * Check if all inputs is validate, and prints an appropriate error if not
     *
     * @return True if all inputs is validate, and and otherwise return false
     */
    private boolean validateInputs() {
        if (!isDate(display.getDateText())) {
            errorHandler.setError(display.getResult(), ErrorHandler.NO_DATE_ERROR);
        }
        if (!isNumber(display.getDaysText())) {
            errorHandler.setError(display.getResult(), ErrorHandler.NO_NUMBER_ERROR);
        }
        return isNumber(display.getDaysText()) && isDate(display.getDateText());
    }

    /**
     * Converts text input to a Date
     *
     * @param text - String of date
     * @return Date if the text in one of the date formats, and otherwise return null
     */
    private Date getDate(String text) {
        for (SimpleDateFormat format : DATE_FORMATS) {
            try {
                return format.parse(text);
            } catch (ParseException ignored) {
            }
        }
        return null;
    }

    /**
     * Converts text input to a Integer
     *
     * @param text - String of number
     * @return Integer if the text is a number, and otherwise return null
     */
    private Integer getDaysToAdd(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ignored) {
        }
        return null;
    }

    /**
     * Check if the text input is a number
     *
     * @param text - String
     * @return True or false
     */
    private boolean isNumber(String text) {
        return text.matches(REGEX_NUMBER);
    }

    /**
     * Check if the text input is a date
     *
     * @param text - String
     * @return True or false
     */
    private boolean isDate(String text) {
        return text.matches(REGEX_DATE);
    }

    /**
     * Calculate, print the result and displays the data input in dd/MM/yyyy format
     */
    private void calculateDate(String daysInText, String dateInText) {
        Integer daysToAdd = getDaysToAdd(daysInText);
        Date date = getDate(dateInText);
        if (date == null || daysToAdd == null) {
            return;
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        display.setDateText(formatDate(cal)); // Set date in dd/MM/yyyy format
        cal.add(Calendar.DATE, daysToAdd); // Add days to date
        display.setResultText(formatDate(cal)); // Set result in dd/MM/yyyy format
    }

    private String formatDate(Calendar cal) {
        return SLASH_FORMAT.format(cal.getTime());
    }
}

