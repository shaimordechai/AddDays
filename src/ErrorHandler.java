import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class ErrorHandler {

    private static final Color ERROR_COLOR = Color.RED;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final String NO_NUMBER_ERROR = "נא הזן מספר";
    private static final String NO_DATE_ERROR = "תאריך לא חוקי";

    private static final ErrorHandler instance = new ErrorHandler();

    private ErrorHandler(){}

    public static ErrorHandler getInstance(){
        return instance;
    }

    public  void  setError (JLabel textArea, String error){
        textArea.setForeground(ERROR_COLOR);
        textArea.setFont(DEFAULT_FONT);
        textArea.setToolTipText(null);
        textArea.setText(error);
    }

    public static String getNoNumberError() {
        return NO_NUMBER_ERROR;
    }

    public static String getNoDateError() {
        return NO_DATE_ERROR;
    }
}
